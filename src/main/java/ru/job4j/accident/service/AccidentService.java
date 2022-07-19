package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentHibernate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
/**
    private final AccidentJdbcTemplate jdbcTemplate;

    public AccidentService(AccidentJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
*/

    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidentHibernate.getAll());
    }

    public void create(Accident accident) {

        Accident accident1 = accidentHibernate.save(accident);
        System.out.println("In service:");
        System.out.println("name: " + accident1.getName());
        System.out.println("type: " + accident1.getType());
        accident1.getRules().forEach(System.out::println);
    }

    public void update(Accident accident) {
        accidentHibernate.update(accident);
    }

    public Accident getAccidentById(int id) {
        return accidentHibernate.getAccidentById(id);
    }
}
