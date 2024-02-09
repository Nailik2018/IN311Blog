package ch.hftm.blog.control.dto;

import ch.hftm.blog.entity.Author;
import jakarta.validation.constraints.NotBlank;

public class AuthorDto {

    public record NewAuthorDto(@NotBlank String firstname, @NotBlank String lastname){
        public Author toAuthor(){
            return new Author(firstname, lastname);
        }

        public static NewAuthorDto fromAuthor(Author author){
            return new NewAuthorDto(author.getFirstname(), author.getLastname());
        }
    }
}
