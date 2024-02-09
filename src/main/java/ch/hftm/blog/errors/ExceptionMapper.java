package ch.hftm.blog.errors;

import io.quarkus.hibernate.validator.runtime.jaxrs.ResteasyReactiveViolationException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {

    @ServerExceptionMapper(ResteasyReactiveViolationException.class)
    public Response handleViolationException(ResteasyReactiveViolationException exception){
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }

    @ServerExceptionMapper(ConstraintViolationException.class)
    public Response handleViolationException(ConstraintViolationException exception){
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
