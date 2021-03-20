package com.agendup.yoda.config;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitCountConfig {

	@Min(1)
	private int maxHitsPerIpPerMinute = 300;
	@Min(1)
	private int maxFailedAuthPerIpPerMinute = 1;
	@Min(1)
	private int maxAccountAuthApiHitsPerIpPerMinute = 5;
	@Min(1)
	private int maxAccountApiHitsPerIpPerMinute = 100;
	@Min(1)
	private int maxCustomerAuthApiHitsPerIpPerMinute = 5;
	@Min(1)
	private int maxCustomerApiHitsPerIpPerMinute = 100;
	@Min(1)
	private int maxPublicApiHitsPerIpPerMinute = 300;
	@Min(1)
	private int maxHitsPerUserPerMinute = 300;
	@Min(1)
	private int maxAccountAuthApiHitsPerUserPerMinute = 5;
	@Min(1)
	private int maxAccountApiHitsPerUserPerMinute = 100;
	@Min(1)
	private int maxCustomerAuthApiHitsPerUserPerMinute = 5;
	@Min(1)
	private int maxCustomerApiHitsPerUserPerMinute = 100;
	@Min(1)
	private int maxPublicApiHitsPerUserPerMinute = 300;
	@Min(1)
	private int maxAccountsPerEmail = 50;

	@JsonProperty
	public int getMaxHitsPerIpPerMinute() {
		return maxHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxHitsPerIpPerMinute(int maxHitsPerIpPerMinute) {
		this.maxHitsPerIpPerMinute = maxHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxFailedAuthPerIpPerMinute() {
		return maxFailedAuthPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxFailedAuthPerIpPerMinute(int maxFailedAuthPerIpPerMinute) {
		this.maxFailedAuthPerIpPerMinute = maxFailedAuthPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxAccountAuthApiHitsPerIpPerMinute() {
		return maxAccountAuthApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxAccountAuthApiHitsPerIpPerMinute(int maxAccountAuthApiHitsPerIpPerMinute) {
		this.maxAccountAuthApiHitsPerIpPerMinute = maxAccountAuthApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxAccountApiHitsPerIpPerMinute() {
		return maxAccountApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxAccountApiHitsPerIpPerMinute(int maxAccountApiHitsPerIpPerMinute) {
		this.maxAccountApiHitsPerIpPerMinute = maxAccountApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxCustomerAuthApiHitsPerIpPerMinute() {
		return maxCustomerAuthApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxCustomerAuthApiHitsPerIpPerMinute(int maxCustomerAuthApiHitsPerIpPerMinute) {
		this.maxCustomerAuthApiHitsPerIpPerMinute = maxCustomerAuthApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxCustomerApiHitsPerIpPerMinute() {
		return maxCustomerApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxCustomerApiHitsPerIpPerMinute(int maxCustomerApiHitsPerIpPerMinute) {
		this.maxCustomerApiHitsPerIpPerMinute = maxCustomerApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxPublicApiHitsPerIpPerMinute() {
		return maxPublicApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public void setMaxPublicApiHitsPerIpPerMinute(int maxPublicApiHitsPerIpPerMinute) {
		this.maxPublicApiHitsPerIpPerMinute = maxPublicApiHitsPerIpPerMinute;
	}

	@JsonProperty
	public int getMaxHitsPerUserPerMinute() {
		return maxHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxHitsPerUserPerMinute(int maxHitsPerUserPerMinute) {
		this.maxHitsPerUserPerMinute = maxHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxAccountAuthApiHitsPerUserPerMinute() {
		return maxAccountAuthApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxAccountAuthApiHitsPerUserPerMinute(int maxAccountAuthApiHitsPerUserPerMinute) {
		this.maxAccountAuthApiHitsPerUserPerMinute = maxAccountAuthApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxAccountApiHitsPerUserPerMinute() {
		return maxAccountApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxAccountApiHitsPerUserPerMinute(int maxAccountApiHitsPerUserPerMinute) {
		this.maxAccountApiHitsPerUserPerMinute = maxAccountApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxCustomerAuthApiHitsPerUserPerMinute() {
		return maxCustomerAuthApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxCustomerAuthApiHitsPerUserPerMinute(int maxCustomerAuthApiHitsPerUserPerMinute) {
		this.maxCustomerAuthApiHitsPerUserPerMinute = maxCustomerAuthApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxCustomerApiHitsPerUserPerMinute() {
		return maxCustomerApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxCustomerApiHitsPerUserPerMinute(int maxCustomerApiHitsPerUserPerMinute) {
		this.maxCustomerApiHitsPerUserPerMinute = maxCustomerApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxPublicApiHitsPerUserPerMinute() {
		return maxPublicApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public void setMaxPublicApiHitsPerUserPerMinute(int maxPublicApiHitsPerUserPerMinute) {
		this.maxPublicApiHitsPerUserPerMinute = maxPublicApiHitsPerUserPerMinute;
	}

	@JsonProperty
	public int getMaxAccountsPerEmail() {
		return maxAccountsPerEmail;
	}

	@JsonProperty
	public void setMaxAccountsPerEmail(int maxAccountsPerEmail) {
		this.maxAccountsPerEmail = maxAccountsPerEmail;
	}
}
