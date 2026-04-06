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
	
	@Query(value = """
		    SELECT DATE(u.fecha_registro), COUNT(*)
		    FROM pacientes p
		    JOIN usuarios u ON p.id_usuario = u.id_usuario
		    WHERE u.fecha_registro BETWEEN :inicio AND :fin
		    GROUP BY DATE(u.fecha_registro)
		    ORDER BY DATE(u.fecha_registro)
		""", nativeQuery = true)
		List<Object[]> contarPacientesPorPeriodo(
		    @Param("inicio") LocalDateTime inicio,
		    @Param("fin") LocalDateTime fin
		);
	    
	    
	Optional<Paciente> findByUsuarioIdUsuario(Integer idUsuario);
}