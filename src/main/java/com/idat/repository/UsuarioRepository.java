package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByEmail(String email);
}