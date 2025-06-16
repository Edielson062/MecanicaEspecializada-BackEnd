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


    // Valor total de serviços realizados em todas as OS
    @Query("SELECT SUM(oss.valorTotal) FROM OrdemServicoServico oss")
    Double sumValorTotalServicos();

    // Ranking dos serviços mais executados: descrição, quantidade realizada, faturamento
    @Query("SELECT s.descricao, COUNT(oss.id) AS qtd, SUM(oss.valorTotal) AS valorTotal " +
            "FROM OrdemServicoServico oss JOIN oss.servico s " +
            "GROUP BY s.descricao ORDER BY qtd DESC")
    List<Object[]> findRankingServicos();

    // Lista para pegar o serviço mais solicitado (ordene e pegue o primeiro no service)
    @Query("SELECT s.descricao, COUNT(oss.id) AS qtd " +
            "FROM OrdemServicoServico oss JOIN oss.servico s " +
            "GROUP BY s.descricao ORDER BY qtd DESC")
    List<Object[]> findServicoMaisSolicitadoRaw();
}
