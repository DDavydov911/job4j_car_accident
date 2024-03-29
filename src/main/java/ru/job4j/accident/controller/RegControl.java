package ru.job4j.accident.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.service.UserService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserService users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        if (users.findByName(user.getUsername()).isEmpty()) {
            users.save(user);
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", "Username already exists");
        return "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
