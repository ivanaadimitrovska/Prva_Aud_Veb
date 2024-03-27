package mk.ukim.finki.prva_aud_veb.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Objects;
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {

    }
}
