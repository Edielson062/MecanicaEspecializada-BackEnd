package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Funcionario;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionarios")
@CrossOrigin("*")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public Funcionario salvarFuncionario(Funcionario funcionario){
        return funcionarioService.salvarFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> listarFuncionario(){
        return funcionarioService.listarFuncionarios();
    }

    @PutMapping
    public Funcionario alterarFuncionario(Funcionario funcionario){
        return funcionarioService.alterarFuncionario(funcionario);
    }

    @DeleteMapping("/{id}/")
    public void deletarFuncionario(@PathVariable Integer funcionarioId){
        funcionarioService.deletarFuncionario(funcionarioId);
    }

}
