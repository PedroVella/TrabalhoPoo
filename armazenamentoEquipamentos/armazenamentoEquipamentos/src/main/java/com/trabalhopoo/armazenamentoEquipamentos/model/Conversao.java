package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversao {


    public static ArrayList<Equipamento> converterEquipamentos(List<Map<String,Object>> mapa){
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        for(Map<String,Object> registro : mapa){
            int id = (Integer) registro.get("id");
            String nome = (String) registro.get("nome");
            String descricao = (String) registro.get("descricao");
            String localizacao = (String) registro.get("localizacao");
            String status = (String) registro.get("status");
            equipamentos.add(new Equipamento(id, nome, descricao, localizacao, status));
        }
        return equipamentos;
    }

    public static ArrayList<Usuario> converterUsuarios(List<Map<String,Object>> mapa){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Map<String, Object> registro : mapa){
            int id = (Integer) registro.get("id");
            String username = (String) registro.get("username");
            String email = (String) registro.get("email");
            String senha = (String) registro.get("senha");
            java.sql.Timestamp ts = (java.sql.Timestamp) registro.get("data_criacao");
            java.time.LocalDateTime dataCriacao = ts != null ? ts.toLocalDateTime() : null;
            
            Usuario u = new Usuario(id, username, email, senha, dataCriacao);
            usuarios.add(u);
        }
        return usuarios;
    }

}
