package hu.progmatic.thymeleafpractice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;

    private String message;

    private LocalDate created;

    public Log() {

    }

    public Log(String message, LocalDate created) {
        this.message = message;
        this.created = created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
