package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PessoaJuridica extends  Cliente {

    @Column(name = "razaoSocial", nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", length = 20, unique = true, nullable = false)
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(int id, String nome, String email, String telefone, String razaoSocial, String cnpj) {
        super(id, nome, email, telefone);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
