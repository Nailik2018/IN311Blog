package ch.hftm.blog.boundary;

import ch.hftm.blog.control.BlogService;
import ch.hftm.blog.control.dto.BlogDto;
import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.errors.BlogException;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
@Tag(name="Blog REST API")

@Path("blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogRessource {

    @Inject
    BlogService blogService;

    BlogException blogException;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Blog> getBlogs(){
//        return blogService.getBlogs();
//    }

//    @RolesAllowed("admin")
    @GET
    public List<Blog> getEntries(@QueryParam("search") String search, @HeaderParam("password") String password) {
        System.out.println("Password: " + password);
        if (search == null || search.isBlank()) {
            return blogService.getBlogs();
        }else{
            if(!password.equals("password")){
//                throw new BlogException("Password ist nicht korrekt", 401);
                blogException = new BlogException("Password ist nicht korrekt", 401);
            }else{

            }
            return blogService.findBlogs(search);
        }
    }

//    @Tag(name="Blog Get by ID")
    @RolesAllowed("admin")
//    @Authenticated
    @GET
    @Path("{id}")
    public Blog getBlogById(@PathParam("id") Long id) {
        return blogService.getBlogById(id);
    }

//    @GET
//    @Path("{id}")
//    public Response getBlogById(@PathParam("id") Long id) {
//        var blog = this.blogService.getBlogById(id);
//        // Keine Anhung warum das nicht funktioniert
//        return Response.ok().entity(blog).header(name:"info", value:"Bitter version 2 Vewrenwnden").build();
//    }

//    @RolesAllowed("admin")
    @Operation(description = "Add a new Blog")
    @RequestBody(content  = @Content(example="{message: string, username: }"),description = "The new Blog", required = true)
    @APIResponse(responseCode = "201", description = "Jupii new Blog created :-)")
    @APIResponse(responseCode = "418", description = "Ich bin ein Tea Pot :-(")
    @POST
    public Response addBlog(@Valid BlogDto.NewBlogDto newBlogDto, @Context UriInfo uriInfo) {
        System.out.println("URI: " + uriInfo.getAbsolutePath());
        var id = this.blogService.addBlogDto(newBlogDto);
        var uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();
        System.out.println("ID: " + id);
        return Response.created(uri).build();
    }

    @PUT
    public void updateBlog(Blog blog) {
        this.blogService.updateBlog(blog);
    }

    @DELETE
    public void deleteBlog(Long id) {
        this.blogService.deleteBlog(id);
    }
}