package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class AccidentTypeHibernate implements Transactional {
    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> findAll() {
        return this.tx(
                session -> session.createQuery("from AccidentType", AccidentType.class)
                        .list(),
                sf
        );
    }

    public AccidentType getTypeById(int id) {
        return this.tx(
                session -> session.get(AccidentType.class, id),
                sf
        );
    }
}
