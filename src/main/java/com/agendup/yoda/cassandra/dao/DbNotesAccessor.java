package com.agendup.yoda.cassandra.dao;

import com.agendup.yoda.cassandra.model.DbNote;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.mapping.annotations.QueryParameters;

@Accessor
public interface DbNotesAccessor {
    @Query("SELECT * FROM notes.notes")
    @QueryParameters(consistency="QUORUM")
    Result<DbNote> getAll();
    
    @Query("SELECT * FROM notes.notes WHERE id=?")
    @QueryParameters(consistency="QUORUM")
    Result<DbNote> findById(Long id);
}