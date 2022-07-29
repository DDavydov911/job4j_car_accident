package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.store.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {
    private final AccidentTypeRepository typeRepository;

    public AccidentTypeService(AccidentTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public AccidentType getTypeById(int id) {
        return typeRepository.findById(id).get();
    }

    public List<AccidentType> findAll() {
        List<AccidentType> res = new ArrayList<>();
        typeRepository.findAll().forEach(res::add);
        return res;
    }
/**
    private final AccidentTypeHibernate typeMem;

    public AccidentTypeService(AccidentTypeHibernate typeMem) {
        this.typeMem = typeMem;
    }

    public AccidentType getTypeById(int id) {
        return typeMem.getTypeById(id);
    }

    public List<AccidentType> findAll() {
        return typeMem.findAll();
    }
 */
}
