package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.time.LocalDateTime;

public class Usuario {

    private int id;
    private String username;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;

    //Formulario
    public Usuario(){

    }

    //Select
    public Usuario(int id, String username, String email, String senha, LocalDateTime dataCriacao){
        this.id = id;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    //Insert
    public Usuario(String username, String email, String senha){
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
