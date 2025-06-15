package com.senai.projetointegrador.mecanicaespecializadabackend.dto;


public class RelatorioRankingServicoDTO {
    private String descricaoServico;
    private Long quantidadeExecucoes;
    private Double valorTotalServico;

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Long getQuantidadeExecucoes() {
        return quantidadeExecucoes;
    }

    public void setQuantidadeExecucoes(Long quantidadeExecucoes) {
        this.quantidadeExecucoes = quantidadeExecucoes;
    }

    public Double getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(Double valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }
}