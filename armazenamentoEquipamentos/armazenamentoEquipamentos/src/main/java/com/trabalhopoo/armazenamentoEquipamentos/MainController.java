package com.trabalhopoo.armazenamentoEquipamentos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cadastrados")
    public String cadastrados() {
        return "paginacadastrados";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "paginacadastro";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
}
