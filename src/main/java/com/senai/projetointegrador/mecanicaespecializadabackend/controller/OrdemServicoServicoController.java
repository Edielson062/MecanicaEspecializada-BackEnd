package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServicoServico")
@CrossOrigin("*")
public class OrdemServicoServicoController {

    @Autowired
    private OrdemServicoServicoService ordemServicoServicoService;

    @GetMapping("/{id}")
    public OrdemServicoServico buscarPorId(@PathVariable Integer id) {
        return ordemServicoServicoService.buscarPorId(id);
    }

    @PostMapping
    public OrdemServicoServico salvarOrdemServicoServico(@RequestBody OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoService.salvarOrdemServicoServico(ordemServicoServico);
    }

    @GetMapping
    public List<OrdemServicoServico> listarOrdemServicoServicos() {
        return ordemServicoServicoService.listarOrdemServicoServicos();
    }

    @PutMapping
    public OrdemServicoServico atualizarOrdemServicoServico(@RequestBody OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoService.atualizarOrdemServicoServico(ordemServicoServico);
    }

    @DeleteMapping("/{id}")
    public void deletarOrdemServicoServico(@PathVariable Integer id) {
        ordemServicoServicoService.deletarOrdemServicoServico(id);
    }
}
