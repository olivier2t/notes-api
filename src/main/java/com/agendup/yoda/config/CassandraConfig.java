package com.agendup.yoda.config;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CassandraConfig {

	@NotEmpty
	private List<String> hosts;
	@NotEmpty
	private String localDC;
	@NotEmpty
	private String user;
	@NotEmpty
	private String password;

	@JsonProperty
	public List<String> getHosts() {
		return hosts;
	}

	@JsonProperty
	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

	@JsonProperty
	public String getLocalDC() {
		return localDC;
	}

	@JsonProperty
	public void setLocalDC(String localDC) {
		this.localDC = localDC;
	}

	@JsonProperty
	public String getUser() {
		return user;
	}

	@JsonProperty
	public void setUser(String user) {
		this.user = user;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
