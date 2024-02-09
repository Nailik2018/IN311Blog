package ch.hftm.blog.boundary;

import ch.hftm.blog.control.AuthorService;
import ch.hftm.blog.control.dto.AuthorDto;
import ch.hftm.blog.entity.Author;
import ch.hftm.blog.errors.BlogException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name="Author REST API")

@Path("author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRessource {

    @Inject
    AuthorService authorService;

    BlogException blogException;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Author> getAuthors() {
//        return authorService.getAuthors();
//    }

    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAuthors(@QueryParam("search") String search, @HeaderParam("password") String password) {
        System.out.println("Password: " + password);
        if(search == null || search.isBlank()) {
            return authorService.getAuthors();
        }else{
            if(!password.equals("password")){
                throw new BlogException("Password ist nicht korrekt", 401);
            }else{
                return authorService.findAuthors(search);
            }
        }
    }

    @GET
    @Path("{id}")
    public Author getAuthorById(@PathParam("id") Long id) {
        return authorService.getAuthorById(id);
    }

//    @POST
//    public void addAuthor(@Valid Author author, @Context UriInfo uriInfo) {
//        this.authorService.addAuthor(author);
//    }

    @Operation(description = "Add a new Author")
    @RequestBody(content  = @Content(example="{\"firstname\": \"string\", \"lastname\": \"string\"}"),description = "The new Author", required = true)
    @APIResponse(responseCode = "201", description = "Jupii new Author created :-)")
    @APIResponse(responseCode = "418", description = "Ich bin ein Tea Pot :-(")
    @POST
    public Response addAuthor(@Valid AuthorDto.NewAuthorDto newAuthorDto, @Context UriInfo uriInfo) {
        System.out.println("URI: " + uriInfo.getAbsolutePath());
        var id = this.authorService.addAuthorDto(newAuthorDto);
        var uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();
        System.out.println("ID: " + id);
        return Response.created(uri).build();
    }

    @PUT
    public void updateAuthor(Author author) {
        this.authorService.updateAuthor(author);
    }

    @DELETE
    public void deleteAuthor(Long id) {
        this.authorService.deleteAuthor(id);
    }
}
