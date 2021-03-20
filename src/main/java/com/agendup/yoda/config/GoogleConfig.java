package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleConfig {

	@NotEmpty
	private String apiBaseUrl;
	@NotEmpty
	private String audience;
	@NotEmpty
	private String captchaSecret;
	@NotEmpty
	private String gmailFrom;
	@NotEmpty
	private String gmailAccountUser;
	@NotEmpty
	private String gmailP12File;
	@NotEmpty
	private String gmailServiceAccountId;
	@NotEmpty
	private String mapsKey;
	
	@JsonProperty
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	@JsonProperty
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	@JsonProperty
	public String getAudience() {
		return audience;
	}

	@JsonProperty
	public void setAudience(String audience) {
		this.audience = audience;
	}

	@JsonProperty
	public String getCaptchaSecret() {
		return captchaSecret;
	}

	@JsonProperty
	public void setCaptchaSecret(String captchaSecret) {
		this.captchaSecret = captchaSecret;
	}

	@JsonProperty
	public String getGmailFrom() {
		return gmailFrom;
	}

	@JsonProperty
	public void setGmailFrom(String gmailFrom) {
		this.gmailFrom = gmailFrom;
	}

	@JsonProperty
	public String getGmailAccountUser() {
		return gmailAccountUser;
	}

	@JsonProperty
	public void setGmailAccountUser(String gmailAccountUser) {
		this.gmailAccountUser = gmailAccountUser;
	}

	@JsonProperty
	public String getGmailP12File() {
		return gmailP12File;
	}

	@JsonProperty
	public void setGmailP12File(String gmailP12File) {
		this.gmailP12File = gmailP12File;
	}

	public String getGmailServiceAccountId() {
		return gmailServiceAccountId;
	}

	public void setGmailServiceAccountId(String gmailServiceAccountId) {
		this.gmailServiceAccountId = gmailServiceAccountId;
	}

	public String getMapsKey() {
		return mapsKey;
	}

	public void setMapsKey(String mapsKey) {
		this.mapsKey = mapsKey;
	}

}
