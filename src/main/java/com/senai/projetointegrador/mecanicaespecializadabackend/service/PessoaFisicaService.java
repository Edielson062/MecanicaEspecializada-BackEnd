package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaFisica;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public List<PessoaFisica> listarClienteFisica(){
        return pessoaFisicaRepository.findAll();
    }

    public PessoaFisica incluirClienteFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica alterarClienteFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public void deletarByIdClienteFisica(int id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
