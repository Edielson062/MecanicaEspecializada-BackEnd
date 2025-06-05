package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PessoaFisica extends Cliente {

    @Column(name = "cpf", nullable = false, unique = true, length = 20)
    private String cpf;

    @Column(name = "dataNasc")
    private LocalDate dataNasc;

    public PessoaFisica() {
    }

    public PessoaFisica(Integer id, String nome, String email, String telefone, String cpf, LocalDate dataNasc) {
        super(id, nome, email, telefone);
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}