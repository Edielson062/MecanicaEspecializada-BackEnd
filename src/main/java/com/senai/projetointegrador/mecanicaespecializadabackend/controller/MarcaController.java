package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Marca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marca")
@CrossOrigin("*")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    public Marca buscarPorId(@PathVariable Integer id) {
        return marcaService.buscarPorId(id);
    }

    @PostMapping
    public Marca salvarMarca(@RequestBody Marca marca) {
        return marcaService.salvarMarca(marca);
    }

    @GetMapping
    public List<Marca> listarMarcas() {
        return marcaService.listarMarcas();
    }

    @PutMapping
    public Marca atualizarMarca(@RequestBody Marca marca) {
        return marcaService.atualizarMarca(marca);
    }

    @DeleteMapping("/{id}")
    public void deletarMarca(@PathVariable Integer id) {
        marcaService.deletarMarca(id);
    }
}
