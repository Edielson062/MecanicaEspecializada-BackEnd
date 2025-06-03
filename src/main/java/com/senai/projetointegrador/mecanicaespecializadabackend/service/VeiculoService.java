package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Veiculo;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo salvarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo alterarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Integer veiculoId){
        veiculoRepository.deleteById(veiculoId);
    }
}
