package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica,Integer> {
    @Query("select p.cpf from PessoaFisica p where p.cpf = :cpf")
    String cpf(@Param("cpf") String cpf);
}
