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
public class VeiculoService { //

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ClienteVeiculoRepository clienteVeiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Veiculo salvarVeiculo(Veiculo veiculo) {
        // Regra para garantir associação correta:
        if (veiculo.getCliente() == null || veiculo.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente deve ser informado!");
        }
        // Recupera o cliente do banco para garantir que está anexado ao contexto
        Cliente cliente = clienteRepository.findById(veiculo.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + veiculo.getCliente().getId() + " não encontrado."));
        veiculo.setCliente(cliente);

        // Agora sim salva, com cliente gerenciado pelo JPA
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
