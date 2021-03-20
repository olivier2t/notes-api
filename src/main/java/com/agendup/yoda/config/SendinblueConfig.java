package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendinblueConfig {

	@NotEmpty
	private String apiKey;
	@NotEmpty
	private String apiBaseUrl;
	@NotEmpty
	private String fromEmail;
	@NotEmpty
	private String from;
	@NotEmpty
	private String timeout;
	@NotEmpty
	private String activate_fr;
	@NotEmpty
	private String activate_en;
	@NotEmpty
	private String welcome_fr;
	@NotEmpty
	private String welcome_en;
	@NotEmpty
	private String activateuser_fr;
	@NotEmpty
	private String activateuser_en;
	@NotEmpty
	private String welcomeuser_fr;
	@NotEmpty
	private String welcomeuser_en;
	@NotEmpty
	private String credentialsrecovery_fr;
	@NotEmpty
	private String credentialsrecovery_en;
	@NotEmpty
	private String delete_fr;
	@NotEmpty
	private String delete_en;
	@NotEmpty
	private String deleteuser_fr;
	@NotEmpty
	private String deleteuser_en;
	
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
	
	@JsonProperty
	public String getTimeout() {
		return timeout;
	}

	@JsonProperty
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	@JsonProperty
	public String getActivate_fr() {
		return activate_fr;
	}

	@JsonProperty
	public void setActivate_fr(String activate_fr) {
		this.activate_fr = activate_fr;
	}

	@JsonProperty
	public String getActivate_en() {
		return activate_en;
	}

	@JsonProperty
	public void setActivate_en(String activate_en) {
		this.activate_en = activate_en;
	}

	@JsonProperty
	public String getWelcome_fr() {
		return welcome_fr;
	}

	@JsonProperty
	public void setWelcome_fr(String welcome_fr) {
		this.welcome_fr = welcome_fr;
	}

	@JsonProperty
	public String getWelcome_en() {
		return welcome_en;
	}

	@JsonProperty
	public void setWelcome_en(String welcome_en) {
		this.welcome_en = welcome_en;
	}

	@JsonProperty
	public String getActivateuser_fr() {
		return activateuser_fr;
	}

	@JsonProperty
	public void setActivateuser_fr(String activateuser_fr) {
		this.activateuser_fr = activateuser_fr;
	}

	@JsonProperty
	public String getActivateuser_en() {
		return activateuser_en;
	}

	@JsonProperty
	public void setActivateuser_en(String activateuser_en) {
		this.activateuser_en = activateuser_en;
	}

	public String getWelcomeuser_fr() {
		return welcomeuser_fr;
	}
	public void setWelcomeuser_fr(String welcomeuser_fr) {
		this.welcomeuser_fr = welcomeuser_fr;
	}
	public String getWelcomeuser_en() {
		return welcomeuser_en;
	}
	public void setWelcomeuser_en(String welcomeuser_en) {
		this.welcomeuser_en = welcomeuser_en;
	}
	@JsonProperty
	public String getCredentialsrecovery_fr() {
		return credentialsrecovery_fr;
	}

	@JsonProperty
	public void setCredentialsrecovery_fr(String credentialsrecovery_fr) {
		this.credentialsrecovery_fr = credentialsrecovery_fr;
	}

	@JsonProperty
	public String getCredentialsrecovery_en() {
		return credentialsrecovery_en;
	}

	@JsonProperty
	public void setCredentialsrecovery_en(String credentialsrecovery_en) {
		this.credentialsrecovery_en = credentialsrecovery_en;
	}
	public String getDelete_fr() {
		return delete_fr;
	}
	public void setDelete_fr(String delete_fr) {
		this.delete_fr = delete_fr;
	}
	public String getDelete_en() {
		return delete_en;
	}
	public void setDelete_en(String delete_en) {
		this.delete_en = delete_en;
	}
	public String getDeleteuser_fr() {
		return deleteuser_fr;
	}
	public void setDeleteuser_fr(String deleteuser_fr) {
		this.deleteuser_fr = deleteuser_fr;
	}
	public String getDeleteuser_en() {
		return deleteuser_en;
	}
	public void setDeleteuser_en(String deleteuser_en) {
		this.deleteuser_en = deleteuser_en;
	}

}
