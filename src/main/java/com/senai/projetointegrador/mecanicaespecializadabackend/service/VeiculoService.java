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

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo salvarVeiculo(Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        Integer idCliente = veiculo.getIdCliente();
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente com ID " + idCliente + " não encontrado."));

        ClienteVeiculo clienteVeiculo = new ClienteVeiculo(cliente, veiculoSalvo);
        clienteVeiculoRepository.save(clienteVeiculo);

        return veiculoSalvo;
    }

    public Veiculo alterarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Integer veiculoId){
        veiculoRepository.deleteById(veiculoId);
    }
}
