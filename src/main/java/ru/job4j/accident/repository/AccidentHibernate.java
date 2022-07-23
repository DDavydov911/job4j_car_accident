package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public class AccidentHibernate implements Transactional {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        return this.tx(
                session -> {
                    session.save(accident);
                    return accident;
                }, sf
        );
    }

    public List<Accident> getAll() {
        return this.tx(
                session -> session.createQuery(
                        "select distinct a from Accident a "
                        + "left join fetch a.rules "
                        + "left join fetch a.type "
                        + "order by a.id", Accident.class
                ).list(),
                sf
        );
    }

    public Accident update(Accident accident) {
        return this.tx(
                session -> {
                    session.update(accident);
                    return accident;
                }, sf
        );
    }

    public Optional<Accident> getAccidentById(int id) {
        return this.tx(
                session -> session.createQuery(
                        "select distinct a from Accident a "
                        + "left join fetch a.rules "
                        + "left join fetch a.type "
                        + "where a.id = :ID")
                        .setParameter("ID", id)
                        .uniqueResultOptional(),
                        sf
        );
    }
}