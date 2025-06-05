package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Peca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peca")
@CrossOrigin("*")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping("/{id}")
    public Peca listarPorId(@PathVariable Integer id){
        return pecaService.buscarPorId(id);
    }

    @PostMapping
    public Peca salvarPeca(@RequestBody Peca peca){
        return pecaService.salvarPeca(peca);
    }

    @GetMapping
    public List<Peca> listarPecas(){
        return pecaService.listarPecas();
    }

    @PutMapping
    public Peca atualizarPeca(@RequestBody Peca peca){
        return pecaService.atualizarPeca(peca);
    }

    @DeleteMapping("/{id}")
    public void deletarPeca(@PathVariable Integer id){
        pecaService.deletarPeca(id);
    }
}
