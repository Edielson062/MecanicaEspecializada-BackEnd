package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PecaRepository extends JpaRepository<Peca, Integer> {
    @Query("select p.quantidade from Peca p where p.id = :idPeca")
    Integer quantidadeDePecas(@PathVariable int idPeca);

    @Query("select p.valorUnitario from Peca p where p.id = :idPeca")
    Double valorUnitario(@PathVariable int idPeca);

    @Query("select p.codigo from Peca p where p.codigo = :codigoPeca")
    String codigoPeca(@PathVariable String codigoPeca);
}
