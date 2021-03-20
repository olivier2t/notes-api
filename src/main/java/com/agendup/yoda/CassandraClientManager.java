package com.agendup.yoda;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.agendup.yoda.config.CassandraConfig;
import com.agendup.yoda.model.Note;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.QueryLogger;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TupleType;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.extras.codecs.jdk8.InstantCodec;
import com.datastax.driver.extras.codecs.jdk8.LocalDateCodec;
import com.datastax.driver.extras.codecs.jdk8.LocalTimeCodec;
import com.datastax.driver.extras.codecs.jdk8.ZonedDateTimeCodec;
import com.datastax.driver.extras.codecs.json.JacksonJsonCodec;
import com.datastax.driver.mapping.MappingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.lifecycle.Managed;

public class CassandraClientManager implements Managed {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static Cluster cluster;
	private static Session session;
	private static MappingManager manager;
	private CassandraConfig config;

	public CassandraClientManager(CassandraConfig cc) {
		this.config = cc;
	}

	@Override
	public void start() throws Exception {
		logger.debug("Starting Cassandra client...");
		
		while (!this.initialize()) {
			Thread.sleep(2000);
		}

	}
		
	private boolean initialize() {
		try {
			CodecRegistry codecRegistry = new CodecRegistry();
			
			LoadBalancingPolicy dcRoundRobinPolicy = DCAwareRoundRobinPolicy.builder()
					// name of your local datacenter
					.withLocalDc(this.config.getLocalDC())
					// number of hosts for each remote DC, and add them at the end of query plans
					.withUsedHostsPerRemoteDc(2)
					// whether remote hosts included by the previous option are included when the consistency level of the query is LOCAL_ONE or LOCAL_QUORUM
					.allowRemoteDCsForLocalConsistencyLevel()
					.build();
			
			LoadBalancingPolicy latencyPolicy = LatencyAwarePolicy.builder(dcRoundRobinPolicy)
	        			// how much worse a host must perform (compared to the fastest host) in order to be excluded
	        			.withExclusionThreshold(2.0)
	        			// how fast the weight given to older latencies decreases over time
	        			.withScale(100, TimeUnit.MILLISECONDS)
	        			// retry period is the duration for which a slow host will be penalized
	        			.withRetryPeriod(10, TimeUnit.SECONDS)
	        			// how often the minimum average latency (i.e. the fastest host) is recomputed
	        			.withUpdateRate(100, TimeUnit.MILLISECONDS)
	        			// guarantees that we have enough measurements before we start excluding a host
	        			.withMininumMeasurements(50)
	        			.build();

			Builder builder = Cluster.builder()
					.withProtocolVersion(ProtocolVersion.V3)
					.withAuthProvider(new PlainTextAuthProvider(this.config.getUser(), this.config.getPassword()))
				    .withCodecRegistry(codecRegistry)
			        .withLoadBalancingPolicy(latencyPolicy);
			        // .withSSL();
			
			for (String host : this.config.getHosts())
				builder.addContactPoint(host);
			
			cluster = builder.build();
			
			QueryLogger queryLogger = QueryLogger.builder()
				.withConstantThreshold(300)
				.build();
			cluster.register(queryLogger);
				
			session = cluster.connect();
			
		    codecRegistry.register(new JacksonJsonCodec<Note>(Note.class));

		    // Codecs from Cassandra extras
		    // InstantCodec maps Instant to CQL’s timestamp
		    codecRegistry.register(InstantCodec.instance);
		    // LocalDateCodec maps LocalDate to date
		    codecRegistry.register(LocalDateCodec.instance);
		    // LocalTimeCodec maps LocalTime to time
		    codecRegistry.register(LocalTimeCodec.instance);
		    
		    // ZonedDateTimeCodec maps ZonedDateTime to a tuple<timestamp,varchar>
		    // Due to internal implementation details, you have to retrieve the tuple type from a live cluster:
		    TupleType tupleType = cluster.getMetadata().newTupleType(DataType.timestamp(), DataType.varchar());
		    codecRegistry.register(new ZonedDateTimeCodec(tupleType));

		    manager = new MappingManager(session);
		    				    	    
			Row row = session.execute("select release_version from system.local").one();
			logger.info("Cassandra client initialized with server version {} and connected hosts {}",
					row.getString("release_version"),
					Arrays.toString(session.getState().getConnectedHosts().toArray()));
			

			return true;
			
		} catch (Exception e) {
			logger.error("Cassandra client version failed to initialize. Retrying in 5 seconds...", e);
			return false;
		}
	}

	@Override
	public void stop() throws Exception {
		logger.debug("Stopping Cassandra client...");

	    if (cluster != null) cluster.close();

		logger.info("Cassandra client stopped");
	}
	
	public static Cluster getCluster() {
		return cluster;
	}

	public static void setCluster(Cluster cluster) {
		CassandraClientManager.cluster = cluster;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		CassandraClientManager.session = session;
	}

	public static MappingManager getManager() {
		return manager;
	}

	public static void setManager(MappingManager manager) {
		CassandraClientManager.manager = manager;
	}
	
	// private String getFile(String fileName) throws IOException {
	// 	GenericUrl url = new GenericUrl(fileName);
	// 	logger.debug("Fetching file at {}", url.toString());
	// 	HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
	// 	HttpResponse httpResponse = requestFactory.buildGetRequest(url).execute();

	// 	if (httpResponse.getStatusCode() >= 200 && httpResponse.getStatusCode() < 300) {
	// 		return IOUtils.toString(httpResponse.getContent(), "UTF-8").trim();
	// 	} else {
	// 		logger.error("Error while fetching file at {}. Answer from server was HTTP {} {}", url.toString(),
	// 				httpResponse.getStatusCode(), httpResponse.getStatusMessage());
	// 		throw new IOException();
	// 	}
	// }

}