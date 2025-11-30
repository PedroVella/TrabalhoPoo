package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, 
                           @RequestParam String senha, 
                           HttpSession session, 
                           Model model) {
        UsuarioService us = context.getBean(UsuarioService.class);
        Usuario usuario = us.obterUsuarioPorUsername(username);
        
        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuarioId", usuario.getId());
            session.setAttribute("username", usuario.getUsername());
            return "redirect:/cadastrados";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ROTAS DE USUÁRIO
    @GetMapping("/cadastrousuario")
    public String cadastroUsuario() {
        return "cadastrousuario";
    }

    @PostMapping("/cadastrousuario")
    public String cadastroUsuarioPost(@RequestParam String username,
                                     @RequestParam String email,
                                     @RequestParam String senha,
                                     @RequestParam String confirmaSenha,
                                     Model model) {
        // Validar se as senhas coincidem
        if (!senha.equals(confirmaSenha)) {
            model.addAttribute("erro", "As senhas não coincidem");
            return "cadastrousuario";
        }

        // Validar se username já existe
        UsuarioService us = context.getBean(UsuarioService.class);
        Usuario usuarioExistente = us.obterUsuarioPorUsername(username);
        if (usuarioExistente != null) {
            model.addAttribute("erro", "Nome de usuário já está em uso");
            return "cadastrousuario";
        }

        // Criar novo usuário
        Usuario novoUsuario = new Usuario(username, email, senha);
        us.inserirUsuario(novoUsuario);
        
        model.addAttribute("sucesso", "Cadastro realizado com sucesso! Faça login para continuar.");
        return "cadastrousuario";
    }

    
    // ROTAS DE EQUIPAMENTOS
    @GetMapping("/cadastro")
    public String equipamentoCadastro(Model model){
        model.addAttribute("equip", new Equipamento());
        model.addAttribute("id", null);
        return "paginacadastro";
    }

    @PostMapping("/cadastro")
    public String equipamentoCadastro(Model model, @ModelAttribute Equipamento equip, HttpSession session){
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        equip.setUsuarioId(usuarioId);
        
        EquipamentoService es = context.getBean(EquipamentoService.class);
        es.inserirEquipamento(equip);
        return "redirect:/cadastrados";
    }

    @GetMapping("/cadastrados")
    public String equipamentoListar(Model model, @RequestParam(value = "q", required = false) String q, HttpSession session){
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        
        EquipamentoService es = context.getBean(EquipamentoService.class);
        ArrayList<Equipamento> equipamentos = es.listarEquipamentos(q, usuarioId);
        model.addAttribute("equipamentos", equipamentos);
        model.addAttribute("q", q);
        return "paginacadastrados";
    }

    @GetMapping("/equipamento/editar/{id}")
    public String equipamentoEditar(Model model, @PathVariable int id){
        EquipamentoService es = context.getBean(EquipamentoService.class);
        Equipamento equip = es.obterEquipamento(id);
        model.addAttribute("equip", equip);
        model.addAttribute("id", id);
        return "paginacadastro";
    }

    @PostMapping("/equipamento/atualizar/{id}")
    public String equipamentoAtualizar(Model model, 
                                       @ModelAttribute Equipamento equip, 
                                       @PathVariable int id,
                                       HttpSession session){
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        equip.setUsuarioId(usuarioId);
        
        EquipamentoService es = context.getBean(EquipamentoService.class);
        es.atualizarEquipamento(id, equip);       
        return "redirect:/cadastrados";
    }

    @GetMapping("/equipamento/deletar/{id}")
    public String equipamentoDeletar(@PathVariable int id){
        EquipamentoService es = context.getBean(EquipamentoService.class);
        es.deletar(id);
        return "redirect:/cadastrados";
    }
}