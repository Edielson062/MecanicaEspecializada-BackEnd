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

    @PostMapping
    public PessoaFisica salvarPessoaFisica(@RequestBody PessoaFisica pessoaFisica){
        return pessoaFisicaService.salvarPessoaFisica(pessoaFisica);
    }

    @GetMapping
    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaService.listarPessoasFisicas();
    }

    @PutMapping
    public PessoaFisica atualizarPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
        return pessoaFisicaService.atualizarPessoaFisica(pessoaFisica);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoaFisica(@PathVariable Integer id){
        pessoaFisicaService.deletarPessoaFisica(id);
    }
}
