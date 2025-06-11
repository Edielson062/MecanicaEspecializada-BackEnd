package com.senai.projetointegrador.mecanicaespecializadabackend.dto;

import com.senai.projetointegrador.mecanicaespecializadabackend.enums.StatusOrdemServico;

public class OrdemServicoResponseDTO {

    private Integer id;
    private String nome;
    private String descricaoVeiculo;
    private StatusOrdemServico status;
    private String observacoes;
    private Double valorTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVeiculo() {
        return descricaoVeiculo;
    }

    public void setVeiculo(String veiculo) {
        this.descricaoVeiculo = veiculo;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
