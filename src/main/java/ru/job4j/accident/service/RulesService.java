package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.RuleMem;

import java.util.List;

@Service
public class RulesService {
    private final RuleMem ruleMem;

    public RulesService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> findAll() {
        return ruleMem.findAll();
    }

    public Rule getRuleById(int id) {
        return ruleMem.getRuleById(id);
    }
}
