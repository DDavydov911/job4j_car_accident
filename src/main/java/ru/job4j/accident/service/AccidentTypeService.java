package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.store.AccidentTypeMem;

import java.util.List;

@Service
public class AccidentTypeService {
    private final AccidentTypeMem typeMem;

    public AccidentTypeService(AccidentTypeMem typeMem) {
        this.typeMem = typeMem;
    }

    public AccidentType getTypeById(int id) {
        return typeMem.getTypeById(id);
    }

    public List<AccidentType> findAll() {
        return typeMem.findAll();
    }
}
