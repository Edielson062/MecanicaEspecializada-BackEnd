package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoServicoService {

    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    public OrdemServicoServico buscarPorId(Integer id) {
        return ordemServicoServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoServico salvarOrdemServicoServico(OrdemServicoServico ordemServicoServico) {
        double valorUnitario = servicoRepository.valorUnitario(ordemServicoServico.getServico().getId());
        ordemServicoServico.setValorTotal(valorUnitario * ordemServicoServico.getQuantidade());
        return ordemServicoServicoRepository.save(ordemServicoServico);
    }

    public List<OrdemServicoServico> listarOrdemServicoServicos() {
        return ordemServicoServicoRepository.findAll();
    }

    public OrdemServicoServico atualizarOrdemServicoServico(OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoRepository.save(ordemServicoServico);
    }

    public void deletarOrdemServicoServico(Integer id) {
        ordemServicoServicoRepository.deleteById(id);
    }
}
