package com.senai.projetointegrador.mecanicaespecializadabackend.dto;

import java.util.List;

public class OrdemServicoRelatorioDTO {

    private Long quantidadeTotalOs;
    private Double valorTotalFaturamento;
    private Double valorTotalPecas;
    private Double valorTotalServicos;
    private String servicoMaisSolicitado;

    private List<RelatorioServicosPorMecanicoDTO> servicosPorMecanico;
    private List<RelatorioRankingServicoDTO> rankingServicos;

    // Getters e Setters

    public Long getQuantidadeTotalOs() {
        return quantidadeTotalOs;
    }

    public void setQuantidadeTotalOs(Long quantidadeTotalOs) {
        this.quantidadeTotalOs = quantidadeTotalOs;
    }

    public Double getValorTotalFaturamento() {
        return valorTotalFaturamento;
    }

    public void setValorTotalFaturamento(Double valorTotalFaturamento) {
        this.valorTotalFaturamento = valorTotalFaturamento;
    }

    public Double getValorTotalPecas() {
        return valorTotalPecas;
    }

    public void setValorTotalPecas(Double valorTotalPecas) {
        this.valorTotalPecas = valorTotalPecas;
    }

    public Double getValorTotalServicos() {
        return valorTotalServicos;
    }

    public void setValorTotalServicos(Double valorTotalServicos) {
        this.valorTotalServicos = valorTotalServicos;
    }

    public String getServicoMaisSolicitado() {
        return servicoMaisSolicitado;
    }

    public void setServicoMaisSolicitado(String servicoMaisSolicitado) {
        this.servicoMaisSolicitado = servicoMaisSolicitado;
    }

    public List<RelatorioServicosPorMecanicoDTO> getServicosPorMecanico() {
        return servicosPorMecanico;
    }

    public void setServicosPorMecanico(List<RelatorioServicosPorMecanicoDTO> servicosPorMecanico) {
        this.servicosPorMecanico = servicosPorMecanico;
    }

    public List<RelatorioRankingServicoDTO> getRankingServicos() {
        return rankingServicos;
    }

    public void setRankingServicos(List<RelatorioRankingServicoDTO> rankingServicos) {
        this.rankingServicos = rankingServicos;
    }
}