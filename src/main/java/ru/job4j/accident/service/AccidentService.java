package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem mem;

    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    public List<Accident> findAll() {
        return new ArrayList<>(mem.findAll());
    }

    public void create(Accident accident) {
        mem.create(accident);
    }

    public void update(Accident accident) {
        mem.update(accident);
    }

    public Accident getAccidentById(int id) {
        return mem.getAccidentById(id);
    }
}
