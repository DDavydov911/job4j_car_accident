/**
package ru.job4j.accident.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

//@NoArgsConstructor
//@RequiredArgsConstructor
@ToString
@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String username;
    String authority;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authorities that = (Authorities) o;
        return id == that.id;
    }
}
 */