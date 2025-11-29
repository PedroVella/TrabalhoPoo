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

    // ROTAS DE PRODUTOS
    @GetMapping("/produtos")
    public String produtos(Model model){
        model.addAttribute("prod", new Produto());
        ProdutoService ps = context.getBean(ProdutoService.class);
        ArrayList<Produto> produtos = ps.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "index";
    }

    @PostMapping("/produtos")
    public String produtos(Model model, @ModelAttribute Produto prod){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.inserirProduto(prod);
        return "redirect:/produtos";
    }

    @GetMapping("/produto/editar/{id}")
    public String produtoEditar(Model model, @PathVariable int id){
        ProdutoService ps = context.getBean(ProdutoService.class);
        Produto prod = ps.obterProduto(id);
        model.addAttribute("prod", prod);
        model.addAttribute("id", id);
        return "index";
    }

    @PostMapping("/produto/atualizar/{id}")
    public String produtoAtualizar(Model model, 
                                   @ModelAttribute Produto prod, 
                                   @PathVariable int id){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.atualizarProduto(id, prod);       
        return "redirect:/produtos";
    }

    @GetMapping("/produto/deletar/{id}")
    public String produtoDeletar(@PathVariable int id){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.deletar(id);
        return "redirect:/produtos";
    }

    // ROTAS DE EQUIPAMENTOS
    @GetMapping("/cadastro")
    public String equipamentoCadastro(Model model){
        model.addAttribute("equip", new Equipamento());
        return "paginacadastro";
    }

    @PostMapping("/cadastro")
    public String equipamentoCadastro(Model model, @ModelAttribute Equipamento equip){
        EquipamentoService es = context.getBean(EquipamentoService.class);
        es.inserirEquipamento(equip);
        return "redirect:/cadastrados";
    }

    @GetMapping("/cadastrados")
    public String equipamentoListar(Model model){
        EquipamentoService es = context.getBean(EquipamentoService.class);
        ArrayList<Equipamento> equipamentos = es.listarEquipamentos();
        model.addAttribute("equipamentos", equipamentos);
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
                                       @PathVariable int id){
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
