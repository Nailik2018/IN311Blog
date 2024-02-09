//package ch.hftm;
//
//import ch.hftm.blog.control.BlogService;
//import ch.hftm.blog.entity.Blog;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@QuarkusTest
//public class BlogServiceTest {
//
//    @Inject
//    BlogService blogService;
//
//    @Test
//    void addTestBlogs() {
//        Blog blog1 = new Blog();
//        blog1.setTitle("China");
//        blog1.setContent("Peking Ente");
//        Blog blog2 = new Blog();
//        blog2.setTitle("Bangladesch");
//        blog2.setContent("Honey Hunting im Mangrovenwald");
//        List<Blog> blogs;
//
//        // Act
//        int countBlogsBefore = blogService.getBlogs().size();
//        blogService.addBlog(blog1);
//        blogService.addBlog(blog2);
//        blogs = blogService.getBlogs();
//
//    }
//}
