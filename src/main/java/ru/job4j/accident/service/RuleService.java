package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
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
}
