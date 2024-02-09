package ch.hftm.blog.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @Size.List({
            @Size(min = 2, message = "Der Vorname muss mindestens 2 Zeichen lang sein"),
            @Size(max = 128, message = "Der Vorname darf maximal 128 Zeichen lang sein")
    })
    private String firstname;
    @NotBlank
    @Size(min = 2, max = 128)
    private String lastname;

    public Author(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }
}