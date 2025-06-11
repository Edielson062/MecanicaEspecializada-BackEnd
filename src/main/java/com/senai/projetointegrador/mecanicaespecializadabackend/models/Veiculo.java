package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*
    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;*/

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "quilometragem", nullable = false)
    private Integer quilometragem;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(Integer id, Modelo modelo, Integer ano, String placa, Integer quilometragem, Cliente cliente) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
