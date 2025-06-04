package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServico")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.findAll();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Integer id) {
        return ordemServicoService.findById(id);
    }

    @PostMapping
    public OrdemServico inserir(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.save(ordemServico);
    }

    @PutMapping
    public OrdemServico atualizar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.update(ordemServico);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        ordemServicoService.delete(id);
    }
}
