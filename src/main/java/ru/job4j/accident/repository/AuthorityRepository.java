package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    @Query("select distinct a from Authority a where a.authority = ?1")
    Authority findByAuthority(String authority);
}
