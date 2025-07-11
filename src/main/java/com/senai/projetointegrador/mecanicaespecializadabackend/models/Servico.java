package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valorUnitario", nullable = false)
    private Double valorUnitario;

    public Servico() {}

    public Servico(Integer id, String descricao, Double valorUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}