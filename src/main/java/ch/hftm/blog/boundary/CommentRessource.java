package ch.hftm.blog.boundary;

import ch.hftm.blog.control.CommentService;
import ch.hftm.blog.control.dto.BlogDto;
import ch.hftm.blog.control.dto.CommentDto;
import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.entity.Comment;
import ch.hftm.blog.errors.BlogException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("comment")
@Produces("application/json")
@Consumes("application/json")
public class CommentRessource {

    @Inject
    CommentService commentService;

    BlogException blogException;

    @GET
    public List<Comment> getEntries(@QueryParam("search") String search, @HeaderParam("password") String password) {
        System.out.println("Password: " + password);
        if (search == null || search.isBlank()) {
            return commentService.getComment();
        }else{
            if(!password.equals("password")){
//                throw new BlogException("Password ist nicht korrekt", 401);
                blogException = new BlogException("Password ist nicht korrekt", 401);
            }else{

            }
            return commentService.findComment(search);
        }
    }

    @GET
    @Path("{id}")
    public Comment getCommentById(@PathParam("id") Long id) {
        return commentService.getCommentById(id);
    }

    @Operation(description = "Add a new Comment")
    @RequestBody(content  = @Content(example="{\"title\": \"string\"}"),description = "The new Comment", required = true)
    @APIResponse(responseCode = "201", description = "Jupii new Comment created :-)")
    @APIResponse(responseCode = "418", description = "Ich bin ein Tea Pot :-(")
    @POST
    public Response addBlog(@Valid CommentDto.NewCommentDto newCommentDtoDto, @Context UriInfo uriInfo) {
        System.out.println("URI: " + uriInfo.getAbsolutePath());
        var id = this.commentService.addCommentDto(newCommentDtoDto);
        var uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();
        System.out.println("ID: " + id);
        return Response.created(uri).build();
    }

    @PUT
    public void updateComment(Comment comment) {
        this.commentService.updateComment(comment);
    }

    @DELETE
    public void deleteComment(Long id) {
        this.commentService.deleteComment(id);
    }
}
