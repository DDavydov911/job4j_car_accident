package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.store.AccidentTypeMem;

@Controller
public class AccidentControl {

    private final AccidentService service;
    private final AccidentTypeMem typeMem;

    public AccidentControl(AccidentService service, AccidentTypeMem typeMem) {
        this.service = service;
        this.typeMem = typeMem;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeMem.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id) {
        accident.setType(typeMem.findAccidentTypeById(id));
        service.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getEditPage(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", service.getAccidentById(id));
        model.addAttribute("types", typeMem.findAll());
        return "accident/update";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id) {
        accident.setType(typeMem.findAccidentTypeById(id));
        service.update(accident);
        return "redirect:/";
    }
}
