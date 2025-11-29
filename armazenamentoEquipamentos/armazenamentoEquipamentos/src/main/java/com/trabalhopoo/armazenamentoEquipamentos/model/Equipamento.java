package com.trabalhopoo.armazenamentoEquipamentos.model;

public class Equipamento {

    private int id;
    private String nome, descricao;
    private String localizacao;
    private String status;

    //Formulario
    public Equipamento(){

    }

    //Select
    public Equipamento(int id, String nome, String descricao, String localizacao, String status){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.status = status;
    }

    //Insert
    public Equipamento(String nome, String descricao, String localizacao, String status){
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
