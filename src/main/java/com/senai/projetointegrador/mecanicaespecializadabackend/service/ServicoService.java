package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico atualizarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void deletarServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}
