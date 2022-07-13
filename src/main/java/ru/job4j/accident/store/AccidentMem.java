package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(0);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident1", "Description", "Moscow",
                        AccidentType.of(1, "Две машины"), Set.of(Rule.of(1, "Статья 1"))));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident2", "Description", "Spb",
                        AccidentType.of(2, "Машина и человек"), Set.of(Rule.of(2, "Статья 2"))));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident3", "Description", "Ekb",
                        AccidentType.of(3, "Машина и велосипед"), Set.of(Rule.of(1, "Статья 1"),
                        Rule.of(3, "Статья 3"))));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(id.get(), accident);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }
}
