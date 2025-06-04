package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoServicoService {
    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;

    public List<OrdemServicoServico> findAll() {
        return ordemServicoServicoRepository.findAll();
    }

    public OrdemServicoServico Buscar(Integer id) {
        return ordemServicoServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoServico save(OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoRepository.save(ordemServicoServico);
    }

    public void delete(Integer id) {
        ordemServicoServicoRepository.deleteById(id);
    }
}
