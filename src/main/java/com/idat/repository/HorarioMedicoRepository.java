package com.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.model.HorarioMedico;

public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Integer> {

    @Query("""
        SELECT h FROM HorarioMedico h
        WHERE h.medico.idMedico = :idMedico
        AND h.diaSemana = :diaSemana
    """)
    List<HorarioMedico> findByMedicoAndDiaSemana(Integer idMedico, String diaSemana);
}