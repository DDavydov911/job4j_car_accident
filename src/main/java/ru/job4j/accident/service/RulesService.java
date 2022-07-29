package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.RuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RulesService {
    private final RuleRepository ruleRepository;

    public RulesService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public List<Rule> findAll() {
        List<Rule> res = new ArrayList<>();
        ruleRepository.findAll().forEach(res::add);
        return res;
    }

    public Rule getRuleById(int id) {
        return ruleRepository.findById(id).get();
    }

    /**
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
     */
}
