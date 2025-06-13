package com.senai.projetointegrador.mecanicaespecializadabackend.controller;


import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoPecaDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoPecaResponseDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoResponsDTo;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServicoPeca")
@CrossOrigin("*")
public class OrdemServicoPecaController {

    @Autowired
    private OrdemServicoPecaService ordemServicoPecaService;

    @GetMapping("/{id}")
    public OrdemServicoPecaResponseDTO buscarPorId(@PathVariable Integer id) {
        return ordemServicoPecaService.buscarPorIdDto(id);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoPeca> salvarOrdemServicoPeca(@RequestBody OrdemServicoPecaDTO dto) {
        OrdemServicoPeca salvo = ordemServicoPecaService.salvarOrdemServicoPeca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<OrdemServicoPecaResponseDTO> listarOrdemServicoPecas() {
        return ordemServicoPecaService.listarOrdemServicoPecas();
    }

    @GetMapping("/pecas/{id}")
    public List<OrdemServicoPecaResponseDTO> listarOrdemServicoPecaPorOSID(@PathVariable Integer id) {
        return ordemServicoPecaService.listarOrdemServicoPecaPorOSID(id);
    }

    @PutMapping
    public OrdemServicoPeca atualizarOrdemServicoPeca(@RequestBody OrdemServicoPeca ordemServicoPeca) {
        return ordemServicoPecaService.atualizarOrdemServicoPeca(ordemServicoPeca);
    }

    @DeleteMapping("{id}")
    public void deletarOrdemServicoPeca(@PathVariable Integer id) {
        ordemServicoPecaService.deletarOrdemServicoPeca(id);
    }
}
