package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoPecaDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoPecaResponseDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.factory.CalculoValorStrategyFactory;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.*;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoValorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoPecaService {

    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;
    @Autowired
    private PecaRepository pecaRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoPecaResponseDTO buscarPorIdDto(Integer id) {
        OrdemServicoPeca entidade = ordemServicoPecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdemServicoServico não encontrada com ID: " + id));

        Peca peca = pecaRepository.findById(entidade.getPeca().getId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + entidade.getPeca().getId()));

        OrdemServicoPecaResponseDTO dto = new OrdemServicoPecaResponseDTO();
        dto.setDescricaoPeca(peca.getDescricao());
        dto.setQuantidadePeca(entidade.getQuantidade());
        dto.setValorPeca(entidade.getValorTotal());

        return dto;
    }

    public OrdemServicoPeca salvarOrdemServicoPeca(OrdemServicoPecaDTO dto) {
        Peca peca = this.pecaRepository.findById(dto.getPecaId()).orElseThrow(() -> new RuntimeException("Peca não encontrado com ID: " + dto.getPecaId()));
        OrdemServico ordemServico = this.ordemServicoRepository.findById(dto.getOrdemServicoId()).orElseThrow(() -> new RuntimeException("Ordem de Servico não encontrado com ID: " + dto.getOrdemServicoId()));
        this.pecaRepository.reduzirEstoque(dto.getPecaId(), dto.getQuantidade());
        double valorUnitario = pecaRepository.valorUnitario(peca.getId());

        CalculoValorStrategy strategy = CalculoValorStrategyFactory.getStrategy(CalculoValorStrategyFactory.TipoItem.PECA);
        double valorTotal = strategy.calcularValorTotal(dto.getQuantidade(), valorUnitario);

        OrdemServicoPeca ordemServicoPeca = new OrdemServicoPeca();
        ordemServicoPeca.setPeca(peca);
        ordemServicoPeca.setOrdemServico(ordemServico);
        ordemServicoPeca.setQuantidade(dto.getQuantidade());
        ordemServicoPeca.setValorTotal(valorTotal);

        return ordemServicoPecaRepository.save(ordemServicoPeca);
    }

    public List<OrdemServicoPecaResponseDTO> listarOrdemServicoPecas() {
        List<OrdemServicoPeca> entidades = ordemServicoPecaRepository.findAll();
        return converterEntidadeParaDtoResponse(entidades);
    }

    public List<OrdemServicoPecaResponseDTO> listarOrdemServicoPecaPorOSID(Integer id) {
        List<OrdemServicoPeca> entidades = ordemServicoPecaRepository.listarOrdemServicoPecaByOrdemServico(id);
        return converterEntidadeParaDtoResponse(entidades);
    }

    public OrdemServicoPeca atualizarOrdemServicoPeca(OrdemServicoPeca ordemServicoPeca) {
        OrdemServicoPeca antiga = ordemServicoPecaRepository.findById(ordemServicoPeca.getId())
                .orElseThrow(() -> new IllegalStateException("Ordem de serviço de peça não encontrada"));

        int quantidadeAntiga = antiga.getQuantidade();
        int quantidadeNova = ordemServicoPeca.getQuantidade();
        int diferenca = quantidadeNova - quantidadeAntiga;

        double valorUnitario = pecaRepository.valorUnitario(ordemServicoPeca.getPeca().getId());
        ordemServicoPeca.setValorTotal(quantidadeNova * valorUnitario);

        if (diferenca > 0) {
            int atualizou = pecaRepository.reduzirEstoque(ordemServicoPeca.getPeca().getId(), diferenca);
            if (atualizou == 0) {
                throw new IllegalStateException("Estoque insuficiente para aumentar a quantidade");
            }
        } else if (diferenca < 0) {
            pecaRepository.reporEstoque(ordemServicoPeca.getPeca().getId(), -diferenca);
        }

        return ordemServicoPecaRepository.save(ordemServicoPeca);
    }


    public void deletarOrdemServicoPeca(Integer id) {
        ordemServicoPecaRepository.deleteById(id);
    }

    private List<OrdemServicoPecaResponseDTO> converterEntidadeParaDtoResponse(List<OrdemServicoPeca> entidades) {
        List<OrdemServicoPecaResponseDTO> dtos = new ArrayList<>();

        for (OrdemServicoPeca ordemServicoPeca : entidades) {
            Peca peca = pecaRepository.findById(ordemServicoPeca.getPeca().getId())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + ordemServicoPeca.getPeca().getId()));

            OrdemServicoPecaResponseDTO dto = new OrdemServicoPecaResponseDTO();
            dto.setDescricaoPeca(peca.getDescricao());
            dto.setQuantidadePeca(ordemServicoPeca.getQuantidade());
            dto.setValorPeca(ordemServicoPeca.getValorTotal());

            dtos.add(dto);
        }

        return dtos;
    }
}
