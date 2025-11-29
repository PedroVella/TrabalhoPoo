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
public class EquipamentoDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirEquipamento(Equipamento equipamento){
        String sql = "INSERT INTO Equipamento(nome, descricao, localizacao, status) VALUES(?,?,?,?)";
        Object[] obj = new Object[4];
        obj[0] = equipamento.getNome();
        obj[1] = equipamento.getDescricao();
        obj[2] = equipamento.getLocalizacao();
        obj[3] = equipamento.getStatus();
        jdbc.update(sql, obj);
    }

    public ArrayList<Equipamento> listar(){
        String sql = "SELECT * FROM Equipamento";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterEquipamentos(mapa);
    }

    public Equipamento obterEquipamento(int id){
        String sql = "SELECT * FROM Equipamento where id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idEquipamento = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String descricao = (String) mapa.get("descricao");
        String localizacao = (String) mapa.get("localizacao");
        String status = (String) mapa.get("status");
        Equipamento equip = new Equipamento(idEquipamento, nome, descricao, localizacao, status);
        return equip;
    }

    public void atualizarEquipamento(int id, Equipamento equipamento){
        String sql = "UPDATE Equipamento SET nome = ?, descricao = ?, localizacao = ?, status = ? WHERE id = ?";
        Object[] obj = new Object[5];
        obj[0] = equipamento.getNome();
        obj[1] = equipamento.getDescricao();
        obj[2] = equipamento.getLocalizacao();
        obj[3] = equipamento.getStatus();
        obj[4] = id;
        jdbc.update(sql, obj);
    }

    public void deletar(int id){
        String sql = "DELETE FROM Equipamento WHERE id=?";
        jdbc.update(sql, id);
    }
}
