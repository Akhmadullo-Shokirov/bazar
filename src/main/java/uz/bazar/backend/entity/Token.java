package uz.bazar.backend.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Component
public class Token {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String value;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    public Token() {

    }

    public Token(String name, String value, LocalDate creationDate, LocalDate expirationDate) {
        this.name = name;
        this.value = value;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
