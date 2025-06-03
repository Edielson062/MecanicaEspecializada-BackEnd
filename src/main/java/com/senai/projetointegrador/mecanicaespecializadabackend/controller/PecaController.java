package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Peca;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peca")
public class PecaController {
    @Autowired
    private PecaService pecaService;

    @GetMapping
    public List<Peca> getAllPecas(){
        return pecaService.findAll();
    }

    @GetMapping("/{id}")
    public Peca getPecaById(@PathVariable int id){
        return pecaService.findById(id);
    }

    @PostMapping
    public Peca createPeca(@RequestBody Peca peca){
        return pecaService.save(peca);
    }

    @PutMapping
    public Peca updatePeca(@RequestBody Peca peca){
        return pecaService.update(peca);
    }

    @DeleteMapping("/{id}")
    public void deletePeca(@PathVariable int id){
        pecaService.delete(id);
    }
}
