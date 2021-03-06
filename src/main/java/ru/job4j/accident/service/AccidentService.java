package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository repository) {
        this.accidentRepository = repository;
    }

    public List<Accident> findAll() {
        List<Accident> res = new ArrayList<>(accidentRepository.findAll());
        return res;
    }

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    public Accident getAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    public void update(Accident accident) {
        accidentRepository.save(accident);
    }
}
