package com.senai.projetointegrador.mecanicaespecializadabackend.service;


import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemSerivicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemSerivicoRepository ordemSerivicoRepository;
    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;

    public List<OrdemServico> findAll() {
        return ordemSerivicoRepository.findAll();
    }

    public OrdemServico findById(Integer id) {
        return ordemSerivicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServico save(OrdemServico ordemServico) {
        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServico.getId());
        Double valorTotalOSS = 0.0;
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServico.getId());
        Double valorTotalOSP = 0.0;
        for (int i = 0; i < valoresOSS.size(); i++) {
            valorTotalOSS += valoresOSS.get(i);
        }
        for (int i = 0; i < valoresOSP.size(); i++) {
            valorTotalOSP += valoresOSP.get(i);
        }
        ordemServico.setValorTotal(valorTotalOSS + valorTotalOSP);
        return ordemSerivicoRepository.save(ordemServico);
    }

    public OrdemServico update(OrdemServico ordemServico) {
        return ordemSerivicoRepository.save(ordemServico);
    }

    public void delete(Integer id) {
        ordemSerivicoRepository.deleteById(id);
    }
}
