package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Veiculo;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "veiculos")
@CrossOrigin("*")
public class VeiculoController {

        @Autowired
        private VeiculoService veiculoService;

        @PostMapping
        public Veiculo salvarVeiculo(@RequestBody Veiculo veiculo){
            return veiculoService.salvarVeiculo(veiculo);
        }

        @GetMapping
        public List<Veiculo> listarVeiculo(){
            return veiculoService.listarVeiculos();
        }

        @PutMapping
        public Veiculo alterarVeiculo(@RequestBody Veiculo veiculo){
            return veiculoService.alterarVeiculo(veiculo);
        }

        @DeleteMapping("/{id}/")
        public void deletarVeiculo(@PathVariable Integer veiculoId){
            veiculoService.deletarVeiculo(veiculoId);
        }

}
