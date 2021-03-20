package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtConfig {

	private String issuer = "agendup";
	@NotEmpty
	private String accountSessionSecret;
	private int accountSessionTimeout = 600;
	@NotEmpty
	private String accountMailSecret;
	private int accountMailTimeout = 600;
	@NotEmpty
	private String customerSessionSecret;
	private int customerSessionTimeout = 600;
	@NotEmpty
	private String customerMailSecret;
	private int customerMailTimeout = 600;
	
	@JsonProperty
	public String getIssuer() {
		return issuer;
	}
	@JsonProperty
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	@JsonProperty
	public String getAccountSessionSecret() {
		return accountSessionSecret;
	}
	@JsonProperty
	public void setAccountSessionSecret(String accountSessionSecret) {
		this.accountSessionSecret = accountSessionSecret;
	}
	@JsonProperty
	public int getAccountSessionTimeout() {
		return accountSessionTimeout;
	}
	@JsonProperty
	public void setAccountSessionTimeout(int accountSessionTimeout) {
		this.accountSessionTimeout = accountSessionTimeout;
	}
	@JsonProperty
	public String getAccountMailSecret() {
		return accountMailSecret;
	}
	@JsonProperty
	public void setAccountMailSecret(String accountMailSecret) {
		this.accountMailSecret = accountMailSecret;
	}
	@JsonProperty
	public int getAccountMailTimeout() {
		return accountMailTimeout;
	}
	@JsonProperty
	public void setAccountMailTimeout(int accountMailTimeout) {
		this.accountMailTimeout = accountMailTimeout;
	}
	@JsonProperty
	public String getCustomerSessionSecret() {
		return customerSessionSecret;
	}
	@JsonProperty
	public void setCustomerSessionSecret(String customerSessionSecret) {
		this.customerSessionSecret = customerSessionSecret;
	}
	@JsonProperty
	public int getCustomerSessionTimeout() {
		return customerSessionTimeout;
	}
	@JsonProperty
	public void setCustomerSessionTimeout(int customerSessionTimeout) {
		this.customerSessionTimeout = customerSessionTimeout;
	}
	@JsonProperty
	public String getCustomerMailSecret() {
		return customerMailSecret;
	}
	@JsonProperty
	public void setCustomerMailSecret(String customerMailSecret) {
		this.customerMailSecret = customerMailSecret;
	}
	@JsonProperty
	public int getCustomerMailTimeout() {
		return customerMailTimeout;
	}
	@JsonProperty
	public void setCustomerMailTimeout(int customerMailTimeout) {
		this.customerMailTimeout = customerMailTimeout;
	}

}
