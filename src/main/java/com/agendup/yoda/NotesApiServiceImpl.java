package com.agendup.yoda;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.agendup.yoda.cassandra.dao.ApiRequest;
import com.agendup.yoda.cassandra.model.DbNote;
import com.agendup.yoda.model.Note;

import com.agendup.yoda.cassandra.dao.DbNotesAccessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-03-16T16:44:43.811714+01:00[Europe/Paris]")public class NotesApiServiceImpl extends NotesApiService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Response addNote(Note body, SecurityContext securityContext) throws NotFoundException {
        ApiRequest<Note, DbNote> request = new ApiRequest<>(Note.class, DbNote.class);
        try {
                request.setPost(body, body.getId());

                Response response = request.execute().buildApiResponse();
                logger.info("Note created ID:{}", request.getRequestObject().getId());
                return response;

        } catch (Exception e) {
                logger.error("Operation failed.", e);
                return Utils.getApiRequest(500, "Internal Error").buildApiResponse();
        }
    }

    @Override
    public Response deleteNote(Long noteId, SecurityContext securityContext) throws NotFoundException {
        ApiRequest<Note, DbNote> request = new ApiRequest<>(Note.class, DbNote.class);
        try {
                if (noteId == null)
                        return Utils.getApiRequest(400, "Bad Request - Missing Account ID").buildApiResponse();
                request.setDelete(noteId);
                if (request.getCurrentObject() == null)
                        return Utils.getApiRequest(404, "Not Found").buildApiResponse();

                Response response = request.execute().buildApiResponse();
                logger.info("Note deleted ID:{}", request.getCurrentObject().getId());
                return response;

        } catch (Exception e) {
                logger.error("Operation failed.", e);
                return Utils.getApiRequest(500, "Internal Error").buildApiResponse();
        }    
    }
    @Override
    public Response getNoteById(Long noteId, SecurityContext securityContext) throws NotFoundException {
        ApiRequest<Note, DbNote> request = new ApiRequest<>(Note.class, DbNote.class);
        try {
                if (noteId == null || noteId == 0)
                        return Utils.getApiRequest(400, "Bad Request - Note ID must be provided").buildApiResponse();

                request.setGetOne(noteId);
                if (request.getCurrentObject() == null) return Utils.getApiRequest(404, "Not Found").buildApiResponse();
                return request.execute().buildApiResponse();

        } catch (Exception e) {
                return Utils.getApiRequest(500, "Internal Error").buildApiResponse();
        }
    }
    @Override
    public Response getNotes(SecurityContext securityContext) throws NotFoundException {
        ApiRequest<Note, DbNote> request = new ApiRequest<>(Note.class, DbNote.class);
        try {
                request.setGetList();
                DbNotesAccessor accessor = (DbNotesAccessor) CassandraClientManager.getManager()
                                .createAccessor(DbNotesAccessor.class);
                request.setResponseObjects(accessor.getAll().all());
                return request.execute().buildApiResponse();

        } catch (Exception e) {
                logger.error("Operation failed.", e);
                return Utils.getApiRequest(500, "Internal Error").buildApiResponse();
        }
    }
    @Override
    public Response updateNote(Note body, SecurityContext securityContext) throws NotFoundException {
        ApiRequest<Note, DbNote> request = new ApiRequest<>(Note.class, DbNote.class);
        try {
                if (body.getId() == null)
                        return Utils.getApiRequest(400, "Bad Request - Missing Account ID").buildApiResponse();
                request.setPut(body, body.getId());
                if (request.getCurrentObject() == null)
                        return Utils.getApiRequest(404, "Not Found").buildApiResponse();

                Response response = request.execute().buildApiResponse();
                logger.info("Note updated ID:{}", request.getRequestObject().getId());
                return response;

        } catch (Exception e) {
                logger.error("Operation failed.", e);
                return Utils.getApiRequest(500, "Internal Error").buildApiResponse();
        }
    }
}
