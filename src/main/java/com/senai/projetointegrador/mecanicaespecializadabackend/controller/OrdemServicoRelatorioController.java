package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoRelatorioDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoRelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/os-relatorio")
@CrossOrigin("*")
public class OrdemServicoRelatorioController {

    @Autowired
    private OrdemServicoRelatorioService ordemServicoRelatorioService;


    @GetMapping("/dashboard")
    public ResponseEntity<OrdemServicoRelatorioDTO> getRelatorioDashboard() {
        OrdemServicoRelatorioDTO relatorio = ordemServicoRelatorioService.gerarRelatorio();
        return ResponseEntity.ok(relatorio);
    }
}