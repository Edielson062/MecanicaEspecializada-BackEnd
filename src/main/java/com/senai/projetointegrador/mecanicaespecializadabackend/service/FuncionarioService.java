package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Funcionario;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario salvarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Integer id){
        funcionarioRepository.deleteById(id);
    }
}
