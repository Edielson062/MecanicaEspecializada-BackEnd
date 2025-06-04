package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    @Query("select s.valorUnitario from Servico s where s.id = :idServico")
    Double valorUnitario(@PathVariable int idServico);
}
