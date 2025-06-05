package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "peca")
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valorUnitario", nullable = false)
    private double valorUnitario;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    public Peca() {}

    public Peca(Integer id, String codigo, String descricao, double valorUnitario, int quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
