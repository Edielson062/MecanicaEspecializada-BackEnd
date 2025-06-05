package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaJuridica;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<PessoaJuridica> listarClienteJuridica(){
        return pessoaJuridicaRepository.findAll();
    }

    public PessoaJuridica incluirClienteJuridica(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public PessoaJuridica alterarClienteJuridica(PessoaJuridica pessoaJuridica) {
        String cnpj = pessoaJuridicaRepository.cnpj(pessoaJuridica.getCnpj());
        if(cnpj != null){
            throw new IllegalStateException("Cnpj já cadastrado");
        }else{
            pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        }
        return pessoaJuridica;
    }

    public void deletarByIdClienteJuridica(int id) {
        pessoaJuridicaRepository.deleteById(id);
    }
}
