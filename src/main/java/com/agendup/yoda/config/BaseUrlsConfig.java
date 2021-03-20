package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseUrlsConfig {

	@NotEmpty
	private String api;
	@NotEmpty
	private String console;
	@NotEmpty
	private String booking;
	@NotEmpty
	private String assets;
	@NotEmpty
	private String assetsPrivate;
	@NotEmpty
	private String accounts;
	@NotEmpty
	private String accountsPrivate;
	
	@JsonProperty
	public String getApi() {
		return api;
	}

	@JsonProperty
	public void setApi(String api) {
		this.api = api;
	}
	
	@JsonProperty
	public String getConsole() {
		return console;
	}
	
	@JsonProperty
	public void setConsole(String console) {
		this.console = console;
	}

	@JsonProperty
	public String getBooking() {
		return booking;
	}
	
	@JsonProperty
	public void setBooking(String booking) {
		this.booking = booking;
	}

	@JsonProperty
	public String getAssets() {
		return assets;
	}

	@JsonProperty
	public void setAssets(String assets) {
		this.assets = assets;
	}

	@JsonProperty
	public String getAssetsPrivate() {
		return assetsPrivate;
	}

	@JsonProperty
	public void setAssetsPrivate(String assetsPrivate) {
		this.assetsPrivate = assetsPrivate;
	}

	@JsonProperty
	public String getAccounts() {
		return accounts;
	}

	@JsonProperty
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	@JsonProperty
	public String getAccountsPrivate() {
		return accountsPrivate;
	}

	@JsonProperty
	public void setAccountsPrivate(String accountsPrivate) {
		this.accountsPrivate = accountsPrivate;
	}
	
}
