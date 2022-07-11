package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    AtomicInteger id = new AtomicInteger(0);

    Map<Integer, AccidentType> types = new HashMap<>();

    public AccidentTypeMem() {
        types.put(id.incrementAndGet(), AccidentType.of(id.get(), "Две машины"));
        types.put(id.incrementAndGet(), AccidentType.of(id.get(), "Машина и человек"));
        types.put(id.incrementAndGet(), AccidentType.of(id.get(), "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(types.values());
    }

    public AccidentType findAccidentTypeById(int id) {
        return types.get(id);
    }

}
