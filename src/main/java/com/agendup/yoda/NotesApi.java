package com.agendup.yoda;

import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.agendup.yoda.model.Note;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Path("/notes")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-03-16T16:44:43.811714+01:00[Europe/Paris]")public class NotesApi  {
   private final NotesApiService delegate;

   public NotesApi(@Context ServletConfig servletContext) {
      NotesApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("NotesApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (NotesApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = NotesApiServiceFactory.getNotesApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Add a new note.", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Note added successfully.", content = @Content(schema = @Schema(implementation = Note.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input") })
    public Response addNote(@Parameter(description = "" ,required=true) Note body

,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addNote(body,securityContext);
    }
    @DELETE
    @Path("/{noteId}")
    
    
    @Operation(summary = "Deletes a note", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Note successfully deleted"),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Note not found") })
    public Response deleteNote(@Parameter(description = "Note id to delete",required=true) @PathParam("noteId") Long noteId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteNote(noteId,securityContext);
    }
    @GET
    @Path("/{noteId}")
    
    @Produces({ "application/json" })
    @Operation(summary = "Find note by ID", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Note successfully retrieved", content = @Content(schema = @Schema(implementation = Note.class))),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Note not found") })
    public Response getNoteById(@Parameter(description = "ID of note to return",required=true) @PathParam("noteId") Long noteId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getNoteById(noteId,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @Operation(summary = "List all notes.", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List loaded successfully.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Note.class)))) })
    public Response getNotes(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getNotes(securityContext);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Update a note.", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Note updated successfully.", content = @Content(schema = @Schema(implementation = Note.class))),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Note not found"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input") })
    public Response updateNote(@Parameter(description = "" ,required=true) Note body

,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateNote(body,securityContext);
    }
}
