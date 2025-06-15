package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoRelatorioDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.RelatorioRankingServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemServicoRelatorioService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;

    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;

    public OrdemServicoRelatorioDTO gerarRelatorio() {
        OrdemServicoRelatorioDTO dto = new OrdemServicoRelatorioDTO();

        // Quantidade total de OS abertas
        dto.setQuantidadeTotalOs(ordemServicoRepository.count());

        // Valor total de peças
        dto.setValorTotalPecas(zeroSeNull(ordemServicoPecaRepository.sumValorTotalPecas()));

        // Valor total de serviços
        dto.setValorTotalServicos(zeroSeNull(ordemServicoServicoRepository.sumValorTotalServicos()));

        //faturamento total
        dto.setValorTotalFaturamento(
                zeroSeNull(dto.getValorTotalPecas()) + zeroSeNull(dto.getValorTotalServicos())
        );



        // Serviço mais solicitado
        List<Object[]> maisSolicitadoList = ordemServicoServicoRepository.findServicoMaisSolicitadoRaw();
        if (!maisSolicitadoList.isEmpty() && maisSolicitadoList.get(0) != null) {
            dto.setServicoMaisSolicitado((String) maisSolicitadoList.get(0)[0]);
        } else {
            dto.setServicoMaisSolicitado("Nenhum serviço registrado");
        }

        // Ranking dos serviços
        List<Object[]> ranking = ordemServicoServicoRepository.findRankingServicos();
        List<RelatorioRankingServicoDTO> listaRanking = ranking.stream().map(obj -> {
            RelatorioRankingServicoDTO servicoDTO = new RelatorioRankingServicoDTO();
            servicoDTO.setDescricaoServico((String) obj[0]);
            servicoDTO.setQuantidadeExecucoes(((Number) obj[1]).longValue());
            servicoDTO.setValorTotalServico(zeroSeNull((Double) obj[2]));
            return servicoDTO;
        }).collect(Collectors.toList());
        dto.setRankingServicos(listaRanking);

        // (Serviços por mecânico: não implementado pois não há esse relacionamento)

        return dto;
    }

    // Evita NullPointer no Double
    private Double zeroSeNull(Double valor) {
        return valor == null ? 0.0 : valor;
    }
}