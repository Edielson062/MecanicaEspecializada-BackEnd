package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaFisica;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clienteFisica")
@CrossOrigin("*")
public class PessoaFisicaController {
    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping
    public List<PessoaFisica> listarClienteFisica() {
        return pessoaFisicaService.listarClienteFisica();
    }

    @PostMapping
    public PessoaFisica incluirClienteFisica(@RequestBody PessoaFisica pessoaFisica){
        return pessoaFisicaService.incluirClienteFisica(pessoaFisica);
    }

    @PutMapping
    public PessoaFisica alterarClienteJFisica(@RequestBody PessoaFisica pessoaFisica) {
        return pessoaFisicaService.alterarClienteFisica(pessoaFisica);
    }

    @DeleteMapping("/{id}")
    public void deletarClienteByIdFisica(@PathVariable int id){
        pessoaFisicaService.deletarByIdClienteFisica(id);
    }
}
