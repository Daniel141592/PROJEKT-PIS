package pis.projekt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/")
    public String test() {
        return "TU POWINIEN BYĆ TWÓJ ULUBIONY MAGAZYN";
    }

    @GetMapping("/login")
    public String login() {
        return "not implemented yet";
    }
}
