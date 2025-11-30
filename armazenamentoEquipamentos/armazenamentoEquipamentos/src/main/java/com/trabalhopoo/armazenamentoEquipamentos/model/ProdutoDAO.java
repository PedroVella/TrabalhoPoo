// filepath: c:\Users\Estudo\trabalhoclaudio\TrabalhoPoo\armazenamentoEquipamentos\armazenamentoEquipamentos\src\main\java\com\trabalhopoo\armazenamentoEquipamentos\model\ProdutoDAO.java
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
        String sql = "INSERT INTO produtos(nome, descricao, preco, quantidade, usuario_id) VALUES(?,?,?,?,?)";
        Object[] obj = new Object[5];
        obj[0] = produto.getNome();
        obj[1] = produto.getDescricao();
        obj[2] = produto.getPreco();
        obj[3] = produto.getQuantidade();
        obj[4] = produto.getUsuarioId();
        jdbc.update(sql, obj);
    }

    public ArrayList<Produto> listar(){
        String sql = "SELECT * FROM produtos";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterProdutos(mapa);
    }

    public Produto obterProduto(int id){
        String sql = "SELECT * FROM produtos WHERE id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idProduto = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String descricao = (String) mapa.get("descricao");
        double preco = (Double) mapa.get("preco");
        int quantidade = (Integer) mapa.get("quantidade");
        Integer usuarioId = (Integer) mapa.get("usuario_id");
        Produto prod = new Produto(idProduto, nome, descricao, preco, quantidade);
        prod.setUsuarioId(usuarioId);
        return prod;
    }

    public void atualizarProduto(int id, Produto produto){
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco = ?, quantidade = ?, usuario_id = ? WHERE id = ?";
        Object[] obj = new Object[6];
        obj[0] = produto.getNome();
        obj[1] = produto.getDescricao();
        obj[2] = produto.getPreco();
        obj[3] = produto.getQuantidade();
        obj[4] = produto.getUsuarioId();
        obj[5] = id;
        jdbc.update(sql, obj);
    }

    public void deletar(int id){
        String sql = "DELETE FROM produtos WHERE id=?";
        jdbc.update(sql, id);
    }
}