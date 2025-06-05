package com.senai.projetointegrador.mecanicaespecializadabackend.service;


import com.senai.projetointegrador.mecanicaespecializadabackend.enums.StatusOrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;

    public List<OrdemServico> findAll() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico findById(Integer id) {
        return ordemServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServico save(OrdemServico ordemServico) {
        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(StatusOrdemServico.EM_ABERTO);
        }
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
        return ordemServicoRepository.save(ordemServico);
    }


    public OrdemServico update(OrdemServico ordemServico) {
        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServico.getId());
        Double valorTotalOSS = 0.0;
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServico.getId());
        Double valorTotalOSP = 0.0;
        Double valorAnterior = ordemServicoRepository.valorAnterio(ordemServico.getId());
        for (int i = 0; i < valoresOSS.size(); i++) {
            valorTotalOSS += valoresOSS.get(i);
        }
        for (int i = 0; i < valoresOSP.size(); i++) {
            valorTotalOSP += valoresOSP.get(i);
        }
        if (valorAnterior != (valorTotalOSP + valorTotalOSS)) {
            ordemServico.setValorTotal(valorTotalOSS + valorTotalOSP);
        }
        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico pagarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.PAGA);
        ordemServico.setDataFechamento(LocalDate.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico cancelarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.CANCELADA);
        ordemServico.setDataFechamento(LocalDate.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void delete(Integer id) {
        ordemServicoRepository.deleteById(id);
    }
}
