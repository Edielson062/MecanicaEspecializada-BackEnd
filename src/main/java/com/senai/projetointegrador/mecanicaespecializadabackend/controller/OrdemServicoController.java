package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoResponseDTO;
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
    public OrdemServicoDTO buscarPorId(@PathVariable Integer id) {
        return ordemServicoService.buscarPorId(id);
    }

    @PostMapping
    public OrdemServicoDTO salvarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        return ordemServicoService.salvarOrdemServico(ordemServicoDTO);
    }

    @GetMapping
    public List<OrdemServicoResponseDTO> listarOrdensServico() {
        return ordemServicoService.listarOrdensServico();
    }

    @PutMapping
    public OrdemServicoDTO atualizarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        return ordemServicoService.atualizarOrdemServico(ordemServicoDTO);
    }

    @PutMapping("/{id}/pagar")
    public OrdemServicoDTO pagarOrdemServico(@PathVariable int id) {
        return ordemServicoService.pagarOrdemServico(id);
    }

    @PutMapping("/{id}/cancelar")
    public OrdemServicoDTO cancelarOrdemServico(@PathVariable int id) {
        return ordemServicoService.cancelarOrdemServico(id);
    }

    @PutMapping("/{id}/reabrir")
    public OrdemServicoDTO reabrirOrdemServico(@PathVariable int id) {
        return ordemServicoService.reabrirOrdemServico(id);
    }

}
