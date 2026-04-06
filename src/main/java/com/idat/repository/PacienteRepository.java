package com.idat.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.idat.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	long count();
	
	@Query("""
	        SELECT CAST(u.fechaRegistro AS date), COUNT(p)
	        FROM Paciente p
	        JOIN p.usuario u
	        WHERE u.fechaRegistro BETWEEN :inicio AND :fin
	        GROUP BY CAST(u.fechaRegistro AS date)
	        ORDER BY CAST(u.fechaRegistro AS date)
	    """)
	    List<Object[]> contarPacientesPorPeriodo(
	        @Param("inicio") LocalDateTime inicio,
	        @Param("fin") LocalDateTime fin
	    );
	    
	    
	Optional<Paciente> findByUsuarioIdUsuario(Integer idUsuario);
}