package com.idat.service;

import java.util.List;
import com.idat.model.Rol;

public interface RolService {

    List<Rol> listar();
    Rol obtenerPorId(Integer id);
    Rol guardar(Rol rol);
    Rol actualizar(Integer id, Rol rol);
    void eliminar(Integer id);
}