package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdemSerivicoRepository extends JpaRepository<OrdemServico,Integer> {
    @Query("select os.valorTotal from OrdemServico os where os.id = :idOS")
    Double valorAnterio(@Param("idOS") Integer idOS);
}
