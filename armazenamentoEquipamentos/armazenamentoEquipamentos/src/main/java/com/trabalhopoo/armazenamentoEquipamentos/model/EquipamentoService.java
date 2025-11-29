package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    EquipamentoDAO edao;

    public void inserirEquipamento(Equipamento equipamento){
        edao.inserirEquipamento(equipamento);
    }

    public void atualizarEquipamento(int id, Equipamento equipamento){
        edao.atualizarEquipamento(id, equipamento);
    }

    public ArrayList<Equipamento> listarEquipamentos(){
        return edao.listar();
    }

    public Equipamento obterEquipamento(int id){
        return edao.obterEquipamento(id);
    }

    public void deletar(int id){
        edao.deletar(id);
    }

}
