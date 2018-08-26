package org.harvan.example.fullstack.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private Long id;
    @Column(name = "PERSON_NAME")
    private String name;
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateOfBirth='").append(dateOfBirth).append('\'');
        sb.append('}');
        return sb.toString();
    }
}