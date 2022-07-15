package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentJdbcTemplate;
import ru.job4j.accident.store.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate jdbcTemplate;

    public AccidentService(AccidentMem mem, AccidentJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Accident> findAll() {
        return new ArrayList<>(jdbcTemplate.findAll());
    }

    public void create(Accident accident) {
        jdbcTemplate.create(accident);
    }

    public void update(Accident accident) {
        jdbcTemplate.update(accident);
    }

    public Accident getAccidentById(int id) {
        return jdbcTemplate.getAccidentById(id);
    }
}
