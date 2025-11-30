package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUsuario(Usuario usuario){
        String sql = "INSERT INTO usuarios(username, email, senha) VALUES(?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = usuario.getUsername();
        obj[1] = usuario.getEmail();
        obj[2] = usuario.getSenha();
        jdbc.update(sql, obj);
    }

    public ArrayList<Usuario> listar(){
        String sql = "SELECT * FROM usuarios ORDER BY id";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterUsuarios(mapa);
    }

    public Usuario obterUsuario(int id){
        String sql = "SELECT * FROM usuarios WHERE id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idUsuario = (Integer) mapa.get("id");
        String username = (String) mapa.get("username");
        String email = (String) mapa.get("email");
        String senha = (String) mapa.get("senha");
        Timestamp ts = (Timestamp) mapa.get("data_criacao");
        LocalDateTime dataCriacao = ts != null ? ts.toLocalDateTime() : null;
        
        return new Usuario(idUsuario, username, email, senha, dataCriacao);
    }

    public Usuario obterUsuarioPorUsername(String username){
        String sql = "SELECT * FROM usuarios WHERE username=?";
        try {
            Map<String,Object> mapa = jdbc.queryForMap(sql, username);
            int idUsuario = (Integer) mapa.get("id");
            String user = (String) mapa.get("username");
            String email = (String) mapa.get("email");
            String senha = (String) mapa.get("senha");
            Timestamp ts = (Timestamp) mapa.get("data_criacao");
            LocalDateTime dataCriacao = ts != null ? ts.toLocalDateTime() : null;
            
            return new Usuario(idUsuario, user, email, senha, dataCriacao);
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizarUsuario(int id, Usuario usuario){
        String sql = "UPDATE usuarios SET username = ?, email = ?, senha = ? WHERE id = ?";
        Object[] obj = new Object[4];
        obj[0] = usuario.getUsername();
        obj[1] = usuario.getEmail();
        obj[2] = usuario.getSenha();
        obj[3] = id;
        jdbc.update(sql, obj);
    }

    public void deletar(int id){
        String sql = "DELETE FROM usuarios WHERE id=?";
        jdbc.update(sql, id);
    }
}
