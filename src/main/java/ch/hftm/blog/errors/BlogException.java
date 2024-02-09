package ch.hftm.blog.errors;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class BlogException extends WebApplicationException {

    private String msg;
    private int status;

    public BlogException(String msg, int status) {
        super(msg, status);
        this.msg = msg;
        this.status = status;
    }

    @Override
    public Response getResponse() {
        return Response.status(status).entity(msg).type(MediaType.APPLICATION_JSON).build();
    }
}
