package ch.hftm.blog.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter // Wird benötigt, damit die Setter-Methoden vorhanden sind
@Getter // Wird benötigt, damit die Getter-Methoden vorhanden sind
@AllArgsConstructor // Wird benötigt, damit der Konstruktor mit allen Attributen vorhanden ist
@NoArgsConstructor // Wird benötigt, damit der Default-Konstruktor vorhanden ist
@Entity // Wird immer benötigt, wenn eine Klasse in der Datenbank gespeichert werden soll
public class Blog {
    @Id // Wird benötigt, damit die ID als Primary Key verwendet wird
    @GeneratedValue // Wird benötigt, damit die ID automatisch generiert wird
    @JsonbTransient // Wird benötigt, damit die ID nicht im JSON-Objekt zurückgegeben wird
    private long id;
//    @NotNull
    @NotBlank(message = "Der Titel darf nicht leer sein") // Wird benötigt, damit der Titel nicht leer sein darf
    @Size.List({
        @Size(min = 2, message = "Der Titel muss mindestens 2 Zeichen lang sein"),
        @Size(max = 128, message = "Der Titel darf maximal 128 Zeichen lang sein")
    }) // Wird benötigt, damit der Titel zwischen 2 und 128 Zeichen lang sein darf
    private String title;
    private String content;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @ManyToOne // Wird benötigt, damit die Beziehung zwischen Blog und Author erstellt wird
    @JoinColumn(name = "author_id", referencedColumnName = "ID") // Wird benötigt, damit die Spalte author_id in der Tabelle Blog erstellt wird
    private Author author;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Helper method to add a comment to the blog
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBlog(this);
    }

    // Helper method to remove a comment from the blog
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setBlog(null);
    }
}