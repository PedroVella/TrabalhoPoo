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
}
