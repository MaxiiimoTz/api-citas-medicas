package com.idat.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.idat.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    long countByFecha(LocalDate fecha);

    long countByEstado(String estado);

    @Query(value = """
        SELECT DAYOFWEEK(fecha) AS dia, COUNT(*) AS total
        FROM citas
        WHERE fecha BETWEEN :inicio AND :fin
        GROUP BY DAYOFWEEK(fecha)
        ORDER BY DAYOFWEEK(fecha)
    """, nativeQuery = true)
    List<Object[]> contarCitasPorSemana(
        @Param("inicio") LocalDate inicio,
        @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT c.estado, COUNT(c)
        FROM Cita c
        WHERE c.fecha BETWEEN :inicio AND :fin
        GROUP BY c.estado
    """)
    List<Object[]> contarCitasPorEstadoYFecha(
        @Param("inicio") LocalDate inicio,
        @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT CONCAT(u.nombres, ' ', u.apellidos), COUNT(c)
        FROM Cita c
        JOIN c.medico m
        JOIN m.usuario u
        GROUP BY u.nombres, u.apellidos
    """)
    List<Object[]> contarCitasPorMedico();
    
    @Query("""
        SELECT c
        FROM Cita c
        WHERE c.paciente.usuario.idUsuario = :idUsuario
        ORDER BY c.fecha, c.hora
    """)
    List<Cita> obtenerCitasPorPaciente(@Param("idUsuario") Integer idUsuario);

    @Query("""
        SELECT c
        FROM Cita c
        WHERE c.medico.usuario.idUsuario = :idUsuario
        ORDER BY c.fecha, c.hora
    """)
    List<Cita> obtenerCitasPorMedico(@Param("idUsuario") Integer idUsuario);

    @Query(value = """
        SELECT * FROM citas
        WHERE id_medico = :idMedico
        AND fecha = :fecha
        AND TIME(hora) = TIME(:hora)
    """, nativeQuery = true)
    List<Cita> existeCita(
        @Param("idMedico") Integer idMedico,
        @Param("fecha") LocalDate fecha,
        @Param("hora") LocalTime hora
    );
}