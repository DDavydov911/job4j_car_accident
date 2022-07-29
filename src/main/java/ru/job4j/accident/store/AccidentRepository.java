package ru.job4j.accident.store;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @Query("select distinct a from Accident a join fetch a.rules order by a.id")
    @EntityGraph(attributePaths = {"type", "rules"})
    List<Accident> findAll();

//    @Override
//    @EntityGraph(attributePaths = {"type", "rules"})
//    @NonNull
//    Iterable<Accident> findAll();

//    @Query("select distinct a from Accident a join fetch a.rules where a.id = ?1")
//    @EntityGraph(attributePaths = {"type", "rules"})
//    Accident findById(int id);

    @Override
    @EntityGraph(attributePaths = {"type", "rules"})
    @NonNull
    Optional<Accident> findById(@NonNull Integer id);


//    @Override
//    @Modifying
//    @EntityGraph(attributePaths = {"type", "rules"})
//    Accident save(Accident accident);



}
