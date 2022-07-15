package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.store.AccidentJdbcTemplate;


@Controller
public class IndexControl {

    private final AccidentService service;
    public IndexControl(AccidentService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.findAll());
        return "index";
    }


//    private final AccidentJdbcTemplate accidents;
//
//    public IndexControl(AccidentJdbcTemplate accidents) {
//        this.accidents = accidents;
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("accidents", accidents.findAll());
//        return "index";
//    }
}
