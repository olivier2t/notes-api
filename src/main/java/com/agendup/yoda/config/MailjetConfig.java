package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailjetConfig {

	@NotEmpty
	private String apiPublicKey;
	@NotEmpty
	private String apiPrivateKey;
	@NotEmpty
	private String fromEmail;
	@NotEmpty
	private String from;
	
	@JsonProperty
	public String getApiPublicKey() {
		return apiPublicKey;
	}
	@JsonProperty
	public void setApiPublicKey(String apiPublicKey) {
		this.apiPublicKey = apiPublicKey;
	}
	@JsonProperty
	public String getApiPrivateKey() {
		return apiPrivateKey;
	}
	@JsonProperty
	public void setApiPrivateKey(String apiPrivateKey) {
		this.apiPrivateKey = apiPrivateKey;
	}
	@JsonProperty
	public String getFromEmail() {
		return fromEmail;
	}
	@JsonProperty
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
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
