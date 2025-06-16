package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoResponsDTo;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServicoServico")
@CrossOrigin("*")
public class OrdemServicoServicoController {

    @Autowired
    private OrdemServicoServicoService ordemServicoServicoService;

    @GetMapping("/{id}")
    public OrdemServicoServicoResponsDTo buscarPorId(@PathVariable Integer id) {
        return ordemServicoServicoService.buscarPorIdDto(id);
    }


    @PostMapping
    public ResponseEntity<OrdemServicoServico> salvar(@RequestBody OrdemServicoServicoDTO dto) {
        OrdemServicoServico salvo = ordemServicoServicoService.salvarOrdemServicoServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<OrdemServicoServicoResponsDTo> listarOrdemServicoServicos() {
        return ordemServicoServicoService.listarOrdemServicoServicos();
    }

    @GetMapping("/servicos/{id}")
    public List<OrdemServicoServicoResponsDTo> listarOrdemServicoServicosPorOSID(@PathVariable Integer id) {
        return ordemServicoServicoService.listarOrdemServicoServicosPorOSID(id);
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
