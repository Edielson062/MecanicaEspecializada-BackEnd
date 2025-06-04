package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoPecaService {
    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;

    public List<OrdemServicoPeca> findAll() {
        return ordemServicoPecaRepository.findAll();
    }

    public OrdemServicoPeca findById(Integer id) {
        return ordemServicoPecaRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoPeca save(OrdemServicoPeca ordemServicoPeca) {
        return ordemServicoPecaRepository.save(ordemServicoPeca);
    }

    public void delete(Integer id) {
        ordemServicoPecaRepository.deleteById(id);
    }
}
