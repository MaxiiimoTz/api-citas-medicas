package com.idat.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.idat.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	// Obtener citas por fecha actual
	long countByFecha(LocalDate fecha);
	
	// Obtener citas por un estado
	long countByEstado(String estado);
	
	// Obtener las citas semanales
	@Query(
		    value = """
		        SELECT DATEPART(WEEKDAY, fecha) AS dia, COUNT(*) AS total
		        FROM CITAS
		        WHERE fecha BETWEEN :inicio AND :fin
		        GROUP BY DATEPART(WEEKDAY, fecha)
		        ORDER BY DATEPART(WEEKDAY, fecha)
		    """,
		    nativeQuery = true
		)
	
		List<Object[]> contarCitasPorSemana(
		    @Param("inicio") LocalDate inicio,
		    @Param("fin") LocalDate fin
	);
		
	// Citas por periodo
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
	
	// Citas por medico
	@Query("""
		    SELECT 
		        CONCAT(u.nombres, ' ', u.apellidos), 
		        COUNT(c)
		    FROM Cita c
		    JOIN c.medico m
		    JOIN m.usuario u
		    GROUP BY u.nombres, u.apellidos
		""")
		List<Object[]> contarCitasPorMedico();
		
	
		// Citas por paciente
		@Query("""
		    SELECT c
		    FROM Cita c
		    WHERE c.paciente.usuario.idUsuario = :idUsuario
		    ORDER BY c.fecha, c.hora
		""")
		List<Cita> obtenerCitasPorPaciente(@Param("idUsuario") Integer idUsuario);
		
		
	// Obtener citas por médico	
		@Query("""
			    SELECT c
			    FROM Cita c
			    WHERE c.medico.usuario.idUsuario = :idUsuario
			    ORDER BY c.fecha, c.hora
			""")
			List<Cita> obtenerCitasPorMedico(@Param("idUsuario") Integer idUsuario);
		
		// Evitar citas duplicadas 
		@Query(value = """
		    SELECT * FROM CITAS
		    WHERE id_medico = :idMedico
		    AND fecha = :fecha
		    AND CONVERT(VARCHAR(5), hora, 108) = CONVERT(VARCHAR(5), :hora, 108)
		""", nativeQuery = true)
		List<Cita> existeCita(
		    @Param("idMedico") Integer idMedico,
		    @Param("fecha") LocalDate fecha,
		    @Param("hora") LocalTime hora
		);
}