package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    private final AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(id.incrementAndGet(), Rule.of(id.get(), "Статья. 1"));
        rules.put(id.incrementAndGet(), Rule.of(id.get(), "Статья. 2"));
        rules.put(id.incrementAndGet(), Rule.of(id.get(), "Статья. 3"));
    }

    public List<Rule> findAll() {
        System.out.println("Execute findAll() in Repo");
        return new ArrayList<>(rules.values());
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
