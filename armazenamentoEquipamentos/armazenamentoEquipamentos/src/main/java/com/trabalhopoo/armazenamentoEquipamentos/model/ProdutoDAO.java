package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProdutoDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirProduto(Produto produto){
        String sql = "INSERT INTO Produto(nome, descricao, preco, quantidade) VALUES(?,?,?,?)";
        Object[] obj = new Object[4];
        obj[0] = produto.getNome();
        obj[1] = produto.getDescricao();
        obj[2] = produto.getPreco();
        obj[3] = produto.getQuantidade();
        jdbc.update(sql, obj);
    }

    public ArrayList<Produto> listar(){
        String sql = "SELECT * FROM Produto";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterProdutos(mapa);
    }

    public Produto obterProduto(int id){
        String sql = "SELECT * FROM Produto where id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idProduto = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String descricao = (String) mapa.get("descricao");
        double preco = (Double) mapa.get("preco");
        int quantidade = (Integer) mapa.get("quantidade");
        Produto prod = new Produto(idProduto, nome, descricao, preco, quantidade);
        return prod;
    }

    public void atualizarProduto(int id, Produto produto){
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        Object[] obj = new Object[5];
        obj[0] = produto.getNome();
        obj[1] = produto.getDescricao();
        obj[2] = produto.getPreco();
        obj[3] = produto.getQuantidade();
        obj[4] = id;
        jdbc.update(sql, obj);
    }

    public void deletar(int id){
        String sql = "DELETE FROM Produto WHERE id=?";
        jdbc.update(sql, id);
    }
}
