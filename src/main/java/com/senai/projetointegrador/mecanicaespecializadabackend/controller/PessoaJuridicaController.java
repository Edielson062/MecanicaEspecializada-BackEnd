package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaJuridica;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clienteJuridica")
@CrossOrigin("*")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping
    public PessoaJuridica salvarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica){
        return pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
    }

    @GetMapping
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaService.listarPessoasJuridicas();
    }

    @PutMapping
    public PessoaJuridica atualizarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridica);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoaJuridica(@PathVariable Integer id){
        pessoaJuridicaService.deletarPessoaJuridica(id);
    }


    @GetMapping("/existe-cnpj/{cnpj}")
    public ResponseEntity<Map<String, Boolean>> existeCnpj(@PathVariable String cnpj) {
        boolean existe = pessoaJuridicaService.existeCnpj(cnpj);
        Map<String, Boolean> response = new HashMap<>();
        response.put("existe", existe);
        return ResponseEntity.ok(response);
    }
}
