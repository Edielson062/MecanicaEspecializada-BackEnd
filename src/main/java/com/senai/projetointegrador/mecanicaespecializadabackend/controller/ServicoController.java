package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servico")
@CrossOrigin("*")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{id}")
    public Servico buscarPorId(@PathVariable Integer id) {
        return servicoService.buscarPorId(id);
    }

    @PostMapping
    public Servico salvarServico(@RequestBody Servico servico) {
        return servicoService.salvarServico(servico);
    }

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoService.listarServicos();
    }

    @PutMapping
    public Servico atualizarServico(@RequestBody Servico servico) {
        return servicoService.atualizarServico(servico);
    }

    @DeleteMapping("/{id}")
    public void deletarServico(@PathVariable Integer id) {
        servicoService.deletarServico(id);
    }
}
