package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoResponseDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoResponsDTo;
import com.senai.projetointegrador.mecanicaespecializadabackend.factory.CalculoValorStrategyFactory;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoValorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoServicoService {

    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoServicoResponsDTo buscarPorIdDto(Integer id) {
        OrdemServicoServico entidade = ordemServicoServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdemServicoServico não encontrada com ID: " + id));

        Servico servico = servicoRepository.findById(entidade.getServico().getId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + entidade.getServico().getId()));

        OrdemServicoServicoResponsDTo dto = new OrdemServicoServicoResponsDTo();
        dto.setDescricaoServico(servico.getDescricao());
        dto.setQuantidadeServico(entidade.getQuantidade());
        dto.setValorServico(entidade.getValorTotal());

        return dto;
    }

    public OrdemServicoServico salvarOrdemServicoServico(OrdemServicoServicoDTO dto) {

        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + dto.getServicoId()));

        OrdemServico ordemServico = ordemServicoRepository.findById(dto.getOrdemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + dto.getOrdemServicoId()));

        double valorUnitario = servicoRepository.valorUnitario(servico.getId());

        CalculoValorStrategy strategy = CalculoValorStrategyFactory.getStrategy(CalculoValorStrategyFactory.TipoItem.SERVICO);
        double valorTotal = strategy.calcularValorTotal(dto.getQuantidade(), valorUnitario);

        OrdemServicoServico ordemServicoServico = new OrdemServicoServico();
        ordemServicoServico.setServico(servico);
        ordemServicoServico.setOrdemServico(ordemServico);
        ordemServicoServico.setQuantidade(dto.getQuantidade());
        ordemServicoServico.setValorTotal(valorTotal);

        return ordemServicoServicoRepository.save(ordemServicoServico);
    }


    public List<OrdemServicoServicoResponsDTo> listarOrdemServicoServicos() {
        List<OrdemServicoServico> entidades = ordemServicoServicoRepository.findAll();
        return converterEntidadeParaDtoResponse(entidades);
    }

    public List<OrdemServicoServicoResponsDTo> listarOrdemServicoServicosPorOSID(Integer id) {
        List<OrdemServicoServico> entidades = ordemServicoServicoRepository.listarOrdemServicoServicoByOrdemServico(id);
        return converterEntidadeParaDtoResponse(entidades);
    }

    public OrdemServicoServico atualizarOrdemServicoServico(OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoRepository.save(ordemServicoServico);
    }

    public void deletarOrdemServicoServico(Integer id) {
        ordemServicoServicoRepository.deleteById(id);
    }


    private List<OrdemServicoServicoResponsDTo> converterEntidadeParaDtoResponse(List<OrdemServicoServico> entidades) {
        List<OrdemServicoServicoResponsDTo> dtos = new ArrayList<>();

        for (OrdemServicoServico ordemServicoServico : entidades) {
            Servico servico = servicoRepository.findById(ordemServicoServico.getServico().getId())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + ordemServicoServico.getServico().getId()));

            OrdemServicoServicoResponsDTo dto = new OrdemServicoServicoResponsDTo();
            dto.setDescricaoServico(servico.getDescricao());
            dto.setQuantidadeServico(ordemServicoServico.getQuantidade());
            dto.setValorServico(ordemServicoServico.getValorTotal());

            dtos.add(dto);
        }

        return dtos;
    }
}
