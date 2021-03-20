package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OvhConfig {

	@NotEmpty
	private String authUrl;
	@NotEmpty
	private String tenantName;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String region;
		
	@JsonProperty
	public String getAuthUrl() {
		return authUrl;
	}
	
	@JsonProperty
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	@JsonProperty
	public String getTenantName() {
		return tenantName;
	}

	@JsonProperty
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	@JsonProperty
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty
	public String getRegion() {
		return region;
	}

	@JsonProperty
	public void setRegion(String region) {
		this.region = region;
	}
	
}
