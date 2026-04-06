package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}