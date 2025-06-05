package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdemServicoPecaRepository extends JpaRepository<OrdemServicoPeca, Integer> {
    @Query("select osp.valorTotal from OrdemServicoPeca osp where osp.ordemServico.id = :idOrdemSerico")
    List<Double> valoresOsp(@Param("idOrdemServico") int idOrdemServico);
}
