package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servico")
public class ServiceController {
    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public List<Servico> listar() {
        return servicoService.findAll();
    }

    @GetMapping("/{id}")
    public Servico buscar(@PathVariable int id) {
        return servicoService.findById(id);
    }

    @PostMapping
    public Servico inserir(@RequestBody Servico servico) {
        return servicoService.save(servico);
    }

    @PutMapping
    public Servico atualizar(@RequestBody Servico servico) {
        return servicoService.update(servico);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id) {
        servicoService.delete(id);
    }
}
