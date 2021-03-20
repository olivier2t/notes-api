package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailgunConfig {

	@NotEmpty
	private String apiKey;
	@NotEmpty
	private String apiBaseUrl;
	@NotEmpty
	private String from;
	
	@JsonProperty
	public String getApiKey() {
		return apiKey;
	}
	@JsonProperty
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@JsonProperty
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}
	@JsonProperty
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}
	@JsonProperty
	public String getFrom() {
		return from;
	}
	@JsonProperty
	public void setFrom(String from) {
		this.from = from;
	}

}
