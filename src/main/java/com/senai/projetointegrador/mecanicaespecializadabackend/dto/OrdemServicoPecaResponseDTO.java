package com.senai.projetointegrador.mecanicaespecializadabackend.dto;

public class OrdemServicoPecaResponseDTO {
    private String descricaoPeca;
    private Integer quantidadePeca;
    private Double valorPeca;

    public String getDescricaoPeca() {
        return descricaoPeca;
    }

    public void setDescricaoPeca(String descricaoPeca) {
        this.descricaoPeca = descricaoPeca;
    }

    public Integer getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(Integer quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }

    public Double getValorPeca() {
        return valorPeca;
    }

    public void setValorPeca(Double valorPeca) {
        this.valorPeca = valorPeca;
    }
}
