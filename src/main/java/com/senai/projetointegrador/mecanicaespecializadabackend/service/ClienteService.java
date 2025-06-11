package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Cliente;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaFisicaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<Cliente> listarClientes(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.addAll(pessoaFisicaRepository.findAll());
        clientes.addAll(pessoaJuridicaRepository.findAll());
        return clientes;
    }


}
