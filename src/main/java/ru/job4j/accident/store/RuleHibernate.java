package ru.job4j.accident.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RuleHibernate implements Transactional {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Rule> findAllRules() {
        return this.tx(
                session -> session.createQuery("from Rule", Rule.class)
                           .list(),
                sf
        );
    }

    public Rule getRuleById(int id) {
        return this.tx(
                session -> session.get(Rule.class, id),
                sf
        );
    }
}
