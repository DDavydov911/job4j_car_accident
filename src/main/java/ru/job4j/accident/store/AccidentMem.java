package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    AtomicInteger id = new AtomicInteger(0);

    Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(id.incrementAndGet(), new Accident(id.get(), "Accident1", "Description", "Moscow"));
        accidents.put(id.incrementAndGet(), new Accident(id.get(), "Accident2", "Description", "Spb"));
        accidents.put(id.incrementAndGet(), new Accident(id.get(), "Accident3", "Description", "Ekb"));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
