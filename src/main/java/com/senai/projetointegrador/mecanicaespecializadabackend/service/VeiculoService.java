package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Cliente;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.ClienteVeiculo;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.Veiculo;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ClienteRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ClienteVeiculoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ClienteVeiculoRepository clienteVeiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Veiculo salvarVeiculo(Veiculo veiculo) {
        Cliente cliente = clienteRepository.findById(veiculo.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente com ID " + veiculo.getClienteId() + " não encontrado."));
        ClienteVeiculo clienteVeiculo = new ClienteVeiculo(cliente,veiculo);
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo atualizarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Integer id){
        veiculoRepository.deleteById(id);
    }
}
