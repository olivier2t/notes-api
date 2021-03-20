package com.agendup.yoda;

import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import com.agendup.yoda.config.ApiConfig;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Api extends Application<ApiConfig> {
	private static final Logger logger = LoggerFactory.getLogger(Api.class);

	public static void main(String[] args) throws Exception {
		logger.info("Starting Api service");
		new Api().run(args);
	}

	@Override
	public String getName() {
		return "api";
	}

	@Override
	public void initialize(Bootstrap<ApiConfig> bootstrap) {
		logger.info(this.getName() + " is initialized");
	}

	@Override
	public void run(ApiConfig configuration, Environment environment) {
		try {
			String pidStr = ManagementFactory.getRuntimeMXBean().getName();
			String pid = pidStr.substring(0,pidStr.indexOf("@"));
			if (pid == null || pid.equals("")) pid = "0000";
			PrintWriter out = new PrintWriter("/tmp/api.pid");
			out.print(pid);
			out.close();

			// Store configuration statically in Utils
			Utils.setConfig(configuration);
			
			// Keep filters first registered!
			// environment.servlets().addFilter("ApiFilter", new ApiFilter(configuration))
            // .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
			
		    // Enable CORS headers
		    final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		    // Configure CORS parameters
		    cors.setInitParameter("allowedOrigins", "*");
		    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,PATCH,DELETE,HEAD");
		    // Add URL mapping
		    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		    			
			logger.info("Starting Cassandra Client Manager");
			CassandraClientManager cassandraClientManager = new CassandraClientManager(configuration.getCassandra());
			environment.lifecycle().manage(cassandraClientManager);
			
			// logger.info("Starting Template Service Manager");
			// TemplateManager templateManager = new TemplateManager();
			// environment.lifecycle().manage(templateManager);
			
			logger.info("Registering PublicApi");
			final NotesApi notesApi = new NotesApi(environment.getJerseyServletContainer().getServletConfig());
			environment.jersey().register(notesApi);
			
			// logger.info("Registering AccountAuthApi");
			// final AccountAuthApi accountAuthApi = new AccountAuthApi(environment.getJerseyServletContainer().getServletConfig());
			// environment.jersey().register(accountAuthApi);
			
			// logger.info("Registering AccountsApi");
			// final AccountsApi accountsApi = new AccountsApi(environment.getJerseyServletContainer().getServletConfig());
			// environment.jersey().register(accountsApi);
			
			// logger.info("Registering CustomerAuthApi");
			// final CustomerAuthApi customerAuthApi = new CustomerAuthApi(environment.getJerseyServletContainer().getServletConfig());
			// environment.jersey().register(customerAuthApi);
			
			// logger.info("Registering CustomersApi");
			// final CustomersApi customerApi = new CustomersApi(environment.getJerseyServletContainer().getServletConfig());
			// environment.jersey().register(customerApi);
									
		} catch (Exception e) {
			logger.error("Unhandled exception", e);
		}
	}

}
