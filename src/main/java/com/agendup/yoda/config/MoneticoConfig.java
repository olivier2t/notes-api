package com.agendup.yoda.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneticoConfig {

	@NotEmpty
	private String tpe;
	@NotEmpty
	private String version;
	@NotEmpty
	private String societe;
	@NotEmpty
	private String key;
	
	@JsonProperty
	public String getTpe() {
		return tpe;
	}

	@JsonProperty
	public void setTpe(String tpe) {
		this.tpe = tpe;
	}

	@JsonProperty
	public String getVersion() {
		return version;
	}

	@JsonProperty
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty
	public String getSociete() {
		return societe;
	}

	@JsonProperty
	public void setSociete(String societe) {
		this.societe = societe;
	}

	@JsonProperty
	public String getKey() {
		return key;
	}

	@JsonProperty
	public void setKey(String key) {
		this.key = key;
	}

}
