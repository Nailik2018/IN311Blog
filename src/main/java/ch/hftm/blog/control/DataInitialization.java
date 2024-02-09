package ch.hftm.blog.control;

import ch.hftm.blog.entity.Author;
import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.entity.Comment;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Date;

@ApplicationScoped
public class DataInitialization {

    @Inject
    BlogService blogService;

    @Inject
    AuthorService authorService;

    @Inject
    CommentService commentService;

//    @Inject
//    KeyGeneratorHelper keyGeneratorHelper;

    @Transactional
    public void init(@Observes StartupEvent event) {
        // Initialize Data (only if there is no Data around)
        createSomeBlogsAndAuthors();
    }

    public void createSomeBlogsAndAuthors(){

//        keyGeneratorHelper.generateKeys();
//        keyGeneratorHelper.generateSessionToken("alice", "admin");

        Author kilian = new Author();
        kilian.setFirstname("Kilian");
        kilian.setLastname("Althaus");
        Author max = new Author();
        max.setFirstname("Max");
        max.setLastname("Mustermann");
        authorService.addAuthor(kilian);
        authorService.addAuthor(max);

        Blog blog1 = new Blog();
        blog1.setContent("Peking China");
        blog1.setTitle("Peking Ente essen und danach auf die Mauer");
        Blog blog2 = new Blog();
        blog2.setTitle("Tenerifa Spanien");
        blog2.setContent("Lost places, Vulkan und Meer");
        Blog blog3 = new Blog();
        blog3.setTitle("Gleitschirmferien in Bosnien und Herzegowina");
        blog3.setContent("Gleitschirmfliegen in Bosnien und Herzegowina");
        blog1.setAuthor(kilian);
        blog2.setAuthor(max);
        blog3.setAuthor(kilian);

        Comment comment1 = new Comment();
        Date date = new Date();
        comment1.setDate(date);
        comment1.setMessage("Hallo ich bin ein Kommentar zu China");
        comment1.setUsername("max.mustermann@muster.ch");
        Comment comment2 = new Comment();
        Date date2 = new Date();
        comment2.setDate(date2);
        comment2.setMessage("Hallo ich bin ein Kommentar zu Tenerifa Spanien");
        comment2.setUsername("hans.mustermann@muster.ch");
        Comment comment3 = new Comment();
        Date date3 = new Date();
        comment3.setDate(date3);
        comment3.setMessage("Hallo ich bin ein weiterer Kommentar");
        comment3.setUsername("hans.mustermann@muster.ch");
        commentService.addComment(comment1);
        commentService.addComment(comment2);
        commentService.addComment(comment3);

        blog1.addComment(comment1);
        blog2.addComment(comment2);
        blog1.addComment(comment3);
        blogService.addBlog(blog1);
        blogService.addBlog(blog2);
        blogService.addBlog(blog3);
    }
}