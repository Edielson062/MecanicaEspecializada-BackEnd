package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Integer> {
    @Query("select os.valorTotal from OrdemServico os where os.id = :idOS")
    Double valorAnterio(@Param("idOS") Integer idOS);


    //PARA RELATORIOS DE OS
    // Faturamento total das OS (campo valorTotal da OS)
    @Query("SELECT SUM(os.valorTotal) FROM OrdemServico os")
    Double sumValorTotalFaturamento();


}
