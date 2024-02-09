package ch.hftm.blog.control;

import ch.hftm.blog.control.dto.AuthorDto;
import ch.hftm.blog.entity.Author;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@Dependent
public class AuthorService {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    Logger logger;

    public List<Author> getAuthors(){
        var authors = authorRepository.listAll();
        logger.info("Returning " + authors.size() + " Authors");
        return authors;
    }

    public Author getAuthorById(Long id) {
        return (Author) authorRepository.findById(id);
    }

    public List<Author> findAuthors(String search) {
        var authors = authorRepository.find("firstname like ?1 or lastname like ?1", "%" + search + "%").list();
        logger.info("Gefunden " + authors.size() + " Anzahl Authors");
        return authors;
    }

    @Transactional
    public void addAuthor(Author author){
        logger.info("Author hinzufügen: " + author.getFirstname() + " " + author.getLastname());
        authorRepository.persist(author);
    }

    @Transactional
    public long addAuthorDto(AuthorDto.NewAuthorDto newAuthorDto){
        logger.info("Author hinzufügen: " + newAuthorDto.firstname() + " " + newAuthorDto.lastname());
        var author = newAuthorDto.toAuthor();
        authorRepository.persist(newAuthorDto.toAuthor());
        return author.getId();
    }

    @Transactional
    public void updateAuthor(Author author) {
        authorRepository.persist(author);
        logger.info("Author updaten: " + author.getId());
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = (Author) authorRepository.findById(id);
        if (author != null) {
            authorRepository.delete(author);
            logger.info("Author löschen: " + id);
        }
    }
}
