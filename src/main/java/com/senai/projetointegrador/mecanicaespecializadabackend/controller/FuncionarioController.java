package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Funcionario;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionario")
@CrossOrigin("*")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.salvarFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    @PutMapping
    public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.atualizarFuncionario(funcionario);
    }

    @DeleteMapping("/{id}/")
    public void deletarFuncionario(@PathVariable Integer id){
        funcionarioService.deletarFuncionario(id);
    }

}
