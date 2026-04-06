package com.idat.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historia_clinica")
@Getter @Setter
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoria;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @Column(columnDefinition = "TEXT")
    private String antecedentes;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(columnDefinition = "TEXT")
    private String enfermedadesCronicas;

    private LocalDateTime fechaRegistro = LocalDateTime.now();

	public Integer getIdHistoria() {
		return idHistoria;
	}

	public void setIdHistoria(Integer idHistoria) {
		this.idHistoria = idHistoria;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getEnfermedadesCronicas() {
		return enfermedadesCronicas;
	}

	public void setEnfermedadesCronicas(String enfermedadesCronicas) {
		this.enfermedadesCronicas = enfermedadesCronicas;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    
    
    
}