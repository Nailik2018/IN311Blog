package ch.hftm.blog.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    @JsonbTransient
    private long id;
    private String message;
    private String username;
    private Date date;

    @ManyToOne
    @JsonbTransient
    private Blog blog;

    public Comment(String message, String username, Date date) {
        this.message = message;
        this.username = username;
        this.date = date;
    }
}
