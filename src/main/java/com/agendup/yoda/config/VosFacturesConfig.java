package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VosFacturesConfig {

	@NotEmpty
	private String apiBaseUrl;
	@NotEmpty
	private String apiToken;
	@NotEmpty
	private String departmentId;
	@NotEmpty
	private String test;
	
	@JsonProperty
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	@JsonProperty
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}
	
	@JsonProperty
	public String getApiToken() {
		return apiToken;
	}
	
	@JsonProperty
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	
	@JsonProperty
	public String getDepartmentId() {
		return departmentId;
	}
	
	@JsonProperty
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@JsonProperty
	public String getTest() {
		return test;
	}
	
	@JsonProperty
	public void setTest(String test) {
		this.test = test;
	}

}
