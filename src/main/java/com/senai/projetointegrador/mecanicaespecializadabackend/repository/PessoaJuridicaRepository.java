package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica,Integer> {
    @Query("select p from PessoaJuridica p where p.cnpj = :cnpj")
    String cnpj(@Param("cnpj") String cnpj);
}
