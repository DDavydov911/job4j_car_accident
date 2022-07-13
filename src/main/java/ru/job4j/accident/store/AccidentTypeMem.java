package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    private final AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        types.put(id.incrementAndGet(),  AccidentType.of(id.get(), "Две машины"));
        types.put(id.incrementAndGet(),  AccidentType.of(id.get(), "Машина и человек"));
        types.put(id.incrementAndGet(),  AccidentType.of(id.get(), "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(types.values());
    }

    public AccidentType getTypeById(int id) {
        return types.get(id);
    }
}
