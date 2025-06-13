package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrdemServicoServicoRepository extends JpaRepository<OrdemServicoServico,Integer> {

    @Query("select oss.valorTotal from OrdemServicoServico oss where oss.ordemServico.id = :idOrdemServico")
    List<Double> valoresOss(@Param("idOrdemServico") int idOrdemServico);

    @Query("select oss from OrdemServicoServico oss where oss.ordemServico.id = :idOrdemServico")
    List<OrdemServicoServico> listarOrdemServicoServicoByOrdemServico(@Param("idOrdemServico")int idOrdemServico);
}
