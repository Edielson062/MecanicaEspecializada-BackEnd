package com.senai.projetointegrador.mecanicaespecializadabackend.service;


import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemSerivicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemSerivicoRepository ordemSerivicoRepository;

    public List<OrdemServico> findAll() {
        return ordemSerivicoRepository.findAll();
    }

    public OrdemServico findById(Integer id) {
        return ordemSerivicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServico save(OrdemServico ordemServico) {
        return ordemSerivicoRepository.save(ordemServico);
    }

    public OrdemServico update(OrdemServico ordemServico) {
        return ordemSerivicoRepository.save(ordemServico);
    }

    public void delete(Integer id) {
        ordemSerivicoRepository.deleteById(id);
    }
}
