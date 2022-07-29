package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.store.AccidentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
//    private final AccidentService accidents;
//
//    public IndexControl(AccidentService accidents) {
//        this.accidents = accidents;
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("accidents", accidents.findAll());
//        return "index";
//    }

    private final AccidentRepository accidents;

    public IndexControl(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> res = new ArrayList<>();
        res.addAll(accidents.findAll());
//        res.add(new Accident("acc1", "text1", "address1"));
//        res.add(new Accident("acc2", "text2", "address2"));
//        res.add(new Accident("acc3", "text3", "address3"));
        model.addAttribute("accidents", res);
        return "index";
    }
}
