package ch.hftm.blog.control.dto;

import ch.hftm.blog.entity.Comment;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public interface CommentDto {

    public record NewCommentDto(@NotBlank String message, String username, Date date) {
        public Comment toComment() {
            return new Comment(message, username, date);
        }

        public static NewCommentDto fromComment(Comment comment) {
            return new NewCommentDto(comment.getMessage(), comment.getUsername(), comment.getDate());
        }
    }
}
