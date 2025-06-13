package com.senai.projetointegrador.mecanicaespecializadabackend.dto;

public class OrdemServicoPecaDTO {
    private Integer pecaId;
    private Integer ordemServicoId;
    private Integer quantidade;

    public Integer getPecaId() {
        return pecaId;
    }

    public void setPecaId(Integer pecaId) {
        this.pecaId = pecaId;
    }

    public Integer getOrdemServicoId() {
        return ordemServicoId;
    }

    public void setOrdemServicoId(Integer ordemServicoId) {
        this.ordemServicoId = ordemServicoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
