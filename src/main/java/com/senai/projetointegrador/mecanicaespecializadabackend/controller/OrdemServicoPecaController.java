package com.senai.projetointegrador.mecanicaespecializadabackend.controller;


import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServicoPeca")
@CrossOrigin("*")
public class OrdemServicoPecaController {

    @Autowired
    private OrdemServicoPecaService ordemServicoPecaService;

    @GetMapping("/{id}")
    public OrdemServicoPeca buscarPorId(@PathVariable Integer id) {
        return ordemServicoPecaService.buscarPorId(id);
    }

    @PostMapping
    public OrdemServicoPeca salvarOrdemServicoPeca(@RequestBody OrdemServicoPeca ordemServicoPeca) {
        return ordemServicoPecaService.salvarOrdemServicoPeca(ordemServicoPeca);
    }

    @GetMapping
    public List<OrdemServicoPeca> listarOrdemServicoPecas() {
        return ordemServicoPecaService.listarOrdemServicoPecas();
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
