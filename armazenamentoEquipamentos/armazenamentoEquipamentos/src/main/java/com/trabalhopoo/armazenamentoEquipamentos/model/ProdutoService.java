package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDAO pdao;

    public void inserirProduto(Produto produto){
        pdao.inserirProduto(produto);
    }

    public void atualizarProduto(int id, Produto produto){
        pdao.atualizarProduto(id, produto);
    }

    public ArrayList<Produto> listarProdutos(){
        return pdao.listar();
    }

    public Produto obterProduto(int id){
        return pdao.obterProduto(id);
    }

    public void deletar(int id){
        pdao.deletar(id);
    }

}
