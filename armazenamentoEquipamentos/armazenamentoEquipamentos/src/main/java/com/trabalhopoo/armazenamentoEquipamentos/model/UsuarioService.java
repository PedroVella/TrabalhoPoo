package com.trabalhopoo.armazenamentoEquipamentos.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO udao;

    public void inserirUsuario(Usuario usuario){
        udao.inserirUsuario(usuario);
    }

    public void atualizarUsuario(int id, Usuario usuario){
        udao.atualizarUsuario(id, usuario);
    }

    public ArrayList<Usuario> listarUsuarios(){
        return udao.listar();
    }

    public Usuario obterUsuario(int id){
        return udao.obterUsuario(id);
    }

    public Usuario obterUsuarioPorUsername(String username){
        return udao.obterUsuarioPorUsername(username);
    }

    public void deletar(int id){
        udao.deletar(id);
    }
}
