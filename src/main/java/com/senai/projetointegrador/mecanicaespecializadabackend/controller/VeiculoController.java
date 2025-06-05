package com.senai.projetointegrador.mecanicaespecializadabackend.controller;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Veiculo;
import com.senai.projetointegrador.mecanicaespecializadabackend.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veiculo")
@CrossOrigin("*")
public class VeiculoController {

        @Autowired
        private VeiculoService veiculoService;

        @PostMapping
        public Veiculo salvarVeiculo(@RequestBody Veiculo veiculo){
            return veiculoService.salvarVeiculo(veiculo);
        }

        @GetMapping
        public List<Veiculo> listarVeiculos(){
            return veiculoService.listarVeiculos();
        }

        @PutMapping
        public Veiculo atualizarVeiculo(@RequestBody Veiculo veiculo){
            return veiculoService.atualizarVeiculo(veiculo);
        }

        @DeleteMapping("/{id}/")
        public void deletarVeiculo(@PathVariable Integer id){
            veiculoService.deletarVeiculo(id);
        }

}
