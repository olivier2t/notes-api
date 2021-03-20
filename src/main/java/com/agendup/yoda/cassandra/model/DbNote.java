package com.agendup.yoda.cassandra.model;

import java.util.Date;
import java.util.List;

import com.agendup.yoda.model.Note;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "notes", name = "notes", 
readConsistency = "QUORUM", writeConsistency = "QUORUM", 
caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class DbNote extends Note {
	@PartitionKey
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject")
	private String subject = null;

	@Column(name = "body")
	private String body = null;

	@Column(name = "tags")
    private List<String> tags = null;
	
	@Column(name = "date")
	private Date date = null;

    
	public DbNote() {
		super();
	}

	public DbNote(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setbody(String body) {
		this.body = body;
	}

	public List<String> getTags() {
	  return tags;
	}
  
	public void setTags(List<String> tags) {
	  this.tags = tags;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
