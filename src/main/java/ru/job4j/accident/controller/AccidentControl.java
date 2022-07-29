package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RulesService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;
    private final AccidentTypeService typeService;
    private final RulesService rulesService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService typeService,
                           RulesService rulesService) {
        this.accidentService = accidentService;
        this.typeService = typeService;
        this.rulesService = rulesService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", rulesService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id, HttpServletRequest req) {
        setAttr(accident, id, req);
        accidentService.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getEditPage(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", accidentService.getAccidentById(id));
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", rulesService.findAll());
        return "accident/update";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id, HttpServletRequest req) {
        setAttr(accident, id, req);
        accidentService.update(accident);
        return "redirect:/";
    }

    private void setAttr(@ModelAttribute Accident accident,
                         @RequestParam("type.id") int id, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accident.setType(typeService.getTypeById(id));
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids).forEach(
                ruleId -> rules.add(rulesService.getRuleById(Integer.parseInt(ruleId)))
        );
        accident.setRules(rules);
        System.out.println("Creating of Accident' rules: ");
        accident.getRules().forEach(System.out::println);
    }
}
