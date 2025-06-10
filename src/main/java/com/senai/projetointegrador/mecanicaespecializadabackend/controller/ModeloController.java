package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Modelo;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("modelo")
@CrossOrigin("*")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping("/{id}")
    public Modelo buscarPorId(@PathVariable Integer id) {
        return modeloService.buscarPorId(id);
    }

    @PostMapping
    public Modelo salvarModelo(@RequestBody Modelo modelo) {
        return modeloService.salvarModelo(modelo);
    }

    @GetMapping
    public List<Modelo> listarModelos() {
        return modeloService.listarModelos();
    }

    @PutMapping
    public Modelo atualizarModelo(@RequestBody Modelo modelo) {
        return modeloService.atualizarModelo(modelo);
    }

    @DeleteMapping("/{id}")
    public void deletarModelo(@PathVariable Integer id) {
        modeloService.deletarModelo(id);
    }
}
