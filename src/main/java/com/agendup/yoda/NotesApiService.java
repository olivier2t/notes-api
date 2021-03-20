package com.agendup.yoda;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.agendup.yoda.model.Note;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-03-17T12:06:55.018587+01:00[Europe/Paris]")public abstract class NotesApiService {
    public abstract Response addNote(Note body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteNote(Long noteId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getNoteById(Long noteId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getNotes(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateNote(Note body,SecurityContext securityContext) throws NotFoundException;
}
