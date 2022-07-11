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

    private final AccidentTypeMem accidentTypeMem;

    Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident1", "Description", "Moscow",
                        accidentTypeMem.findAccidentTypeById(1)));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident2", "Description", "Spb",
                        accidentTypeMem.findAccidentTypeById(2)));
        accidents.put(id.incrementAndGet(),
                new Accident(id.get(), "Accident3", "Description", "Ekb",
                        accidentTypeMem.findAccidentTypeById(3)));
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
