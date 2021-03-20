package com.agendup.yoda;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-03-16T16:44:43.811714+01:00[Europe/Paris]")public class NotesApiServiceFactory {
    private final static NotesApiService service = new NotesApiServiceImpl();

    public static NotesApiService getNotesApi() {
        return service;
    }
}
