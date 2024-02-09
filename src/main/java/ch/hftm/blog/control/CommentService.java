package ch.hftm.blog.control;

import ch.hftm.blog.control.dto.BlogDto;
import ch.hftm.blog.control.dto.CommentDto;
import ch.hftm.blog.entity.Blog;
import ch.hftm.blog.entity.Comment;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@Dependent
public class CommentService {

    @Inject
    CommentRepository commentRepository;

    @Inject
    Logger logger;

    public List<Comment> getComment() {
        var comments = commentRepository.listAll();
        logger.info("Alle Comment: " + comments.size() + " blogs");
        return comments;
    }

    public Comment getCommentById(Long id) {
        return (Comment) commentRepository.findById(id);
    }

    public List<Comment> findComment(String search) {
        var comments = commentRepository.find("title like ?1 or content like ?1", "%" + search + "%").list();
        logger.info("Gefunden " + comments.size() + " Anzahl Kommentare");
        return comments;
    }

    @Transactional
    public void addComment(Comment comment) {
        logger.info("Comment hinzufügen: " + comment.getMessage());
        commentRepository.persist(comment);
    }

    @Transactional
    public long addCommentDto(CommentDto.NewCommentDto newCommentDto) {
        logger.info("Blog hinzufügen: " + newCommentDto.message());
        // Mapping von CommentDto.NewCommentDto zu Comment
        var comments = newCommentDto.toComment();
        commentRepository.persist(newCommentDto.toComment());
        return comments.getId();
    }

    @Transactional
    public void updateComment(Comment comment) {
        commentRepository.persist(comment);
        logger.info("Comment updaten: " + comment.getId());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = (Comment) commentRepository.findById(id);
        if (comment != null) {
            commentRepository.delete(comment);
            logger.info("Comment löschen: " + id);
        }
    }
}
