package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServico")
@CrossOrigin("*")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Integer id) {
        return ordemServicoService.buscarPorId(id);
    }

    @PostMapping
    public OrdemServico salvarOrdemServico(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.salvarOrdemServico(ordemServico);
    }

    @GetMapping
    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoService.listarOrdensServico();
    }

    @PutMapping
    public OrdemServico atualizarOrdemServico(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.atualizarOrdemServico(ordemServico);
    }

    @DeleteMapping("/{id}")
    public void deletarOrdemServico(@PathVariable Integer id) {
        ordemServicoService.deletarOrdemServico(id);
    }
}
