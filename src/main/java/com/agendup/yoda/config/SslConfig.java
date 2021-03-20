package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SslConfig {

	@NotEmpty
	private String trustStore;
	@NotEmpty
	private String trustStorePassword;
	@NotEmpty
	private String keyStore;
	@NotEmpty
	private String keyStorePassword;
	
	@JsonProperty
	public String getTrustStore() {
		return trustStore;
	}
	
	@JsonProperty
	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	@JsonProperty
	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	@JsonProperty
	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}
	
	@JsonProperty
	public String getKeyStore() {
		return keyStore;
	}
	
	@JsonProperty
	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}
	
	@JsonProperty
	public String getKeyStorePassword() {
		return keyStorePassword;
	}
	
	@JsonProperty
	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}
}
