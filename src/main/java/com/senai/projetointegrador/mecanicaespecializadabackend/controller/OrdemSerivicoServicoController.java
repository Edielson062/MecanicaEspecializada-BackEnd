package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemSerivicoServico")
@CrossOrigin("*")
public class OrdemSerivicoServicoController {
    @Autowired
    private OrdemServicoServicoService ordemServicoServicoService;

    @GetMapping
    public List<OrdemServicoServico> listar() {
        return ordemServicoServicoService.findAll();
    }

    @GetMapping("/{id}")
    public OrdemServicoServico buscar(@PathVariable int id) {
        return ordemServicoServicoService.Buscar(id);
    }

    @PostMapping
    public OrdemServicoServico inserir(@RequestBody OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoService.save(ordemServicoServico);
    }

    @PutMapping
    public OrdemServicoServico alterar(@RequestBody OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoService.save(ordemServicoServico);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id) {
        ordemServicoServicoService.delete(id);
    }
}
