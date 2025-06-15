package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrdemServicoPecaRepository extends JpaRepository<OrdemServicoPeca, Integer> {
    @Query("select osp.valorTotal from OrdemServicoPeca osp where osp.ordemServico.id = :idOrdemServico")
    List<Double> valoresOsp(@Param("idOrdemServico") int idOrdemServico);

    List<OrdemServicoPeca> findByOrdemServicoId(Integer ordemServicoId);

    @Query("select osp from OrdemServicoPeca osp where osp.ordemServico.id = :idOrdemServico")
    List<OrdemServicoPeca> listarOrdemServicoPecaByOrdemServico(@Param("idOrdemServico")int idOrdemServico);




    // Valor total de peças utilizadas em todas as OS
    @Query("SELECT SUM(osp.valorTotal) FROM OrdemServicoPeca osp")
    Double sumValorTotalPecas();

}
