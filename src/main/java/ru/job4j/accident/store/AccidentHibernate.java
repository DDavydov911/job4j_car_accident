package ru.job4j.accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            Accident accidentDB = session.get(Accident.class, accident.getId());
            System.out.println("In Persistence:");
            System.out.println("name: " + accidentDB.getName());
            System.out.println("type: " + accidentDB.getType());
            accidentDB.getRules().forEach(System.out::println);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a " +
                            "left join fetch a.rules " +
                            "left join fetch a.type " +
                            "order by a.id", Accident.class)
                    .list();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.update(accident);
        }
    }

    public Accident getAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }
}