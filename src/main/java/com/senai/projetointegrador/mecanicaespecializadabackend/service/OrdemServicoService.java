package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServico salvarOrdemServico(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public void deletarOrdemServico(Integer id) {
        ordemServicoRepository.deleteById(id);
    }
}
