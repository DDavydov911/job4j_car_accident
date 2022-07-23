package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;

import java.util.List;

@Service
public class RulesService {
    private final RuleHibernate ruleMem;

    public RulesService(RuleHibernate ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> findAll() {
        return ruleMem.findAllRules();
    }

    public Rule getRuleById(int id) {
        return ruleMem.getRuleById(id);
    }
}
