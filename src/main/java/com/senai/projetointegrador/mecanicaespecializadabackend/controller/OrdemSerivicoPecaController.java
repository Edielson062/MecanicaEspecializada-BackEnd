package com.senai.projetointegrador.mecanicaespecializadabackend.controller;


import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.OrdemServicoPecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemSerivicoPeca")
public class OrdemSerivicoPecaController {
    @Autowired
    private OrdemServicoPecaService ordemServicoPecaService;

    @GetMapping
    public List<OrdemServicoPeca> listar() {
        return ordemServicoPecaService.findAll();
    }

    @GetMapping("/{id}")
    public OrdemServicoPeca buscar(@PathVariable int id) {
        return ordemServicoPecaService.findById(id);
    }

    @PostMapping
    public OrdemServicoPeca adicionar(@RequestBody OrdemServicoPeca ordemServicoPeca) {
        return ordemServicoPecaService.save(ordemServicoPeca);
    }

    @PutMapping
    public OrdemServicoPeca atualizar(@RequestBody OrdemServicoPeca ordemServicoPeca) {
        return ordemServicoPecaService.save(ordemServicoPeca);
    }

    @DeleteMapping("{id}")
    public void remover(@PathVariable int id) {
        ordemServicoPecaService.delete(id);
    }
}
