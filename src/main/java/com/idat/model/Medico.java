package com.idat.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEDICOS")
@Getter @Setter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(unique = true)
    private String cmp;

    @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;
    
    @ManyToOne
    @JoinColumn(name = "idConsultorio")
    private Consultorio consultorio;

	private Boolean estado = true;

	
	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
    
    
}