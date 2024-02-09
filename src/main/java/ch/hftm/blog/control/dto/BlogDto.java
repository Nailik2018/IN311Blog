package ch.hftm.blog.control.dto;

import ch.hftm.blog.entity.Blog;
import jakarta.validation.constraints.NotBlank;

public interface BlogDto {

    public record NewBlogDto(@NotBlank String title, String content) {
        public Blog toBlog() {
            return new Blog(title, content);
        }

        public static NewBlogDto fromBlog(Blog blog) {
            return new NewBlogDto(blog.getTitle(), blog.getContent());
        }
    }
}
