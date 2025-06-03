package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaJuridica;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clienteJuridica")
@CrossOrigin("*")
public class PessoaJuridicaController {
    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public List<PessoaJuridica> listarClienteJuridica() {
        return pessoaJuridicaService.listarClienteJuridica();
    }

    @PostMapping
    public PessoaJuridica incluirClienteJuridica(@RequestBody PessoaJuridica pessoaJuridica){
        return pessoaJuridicaService.incluirClienteJuridica(pessoaJuridica);
    }

    @PutMapping
    public PessoaJuridica alterarClienteJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaService.alterarClienteJuridica(pessoaJuridica);
    }

    @DeleteMapping("/{id}")
    public void deletarClienteByIdJuridica(@PathVariable int id){
        pessoaJuridicaService.deletarByIdClienteJuridica(id);
    }
}
