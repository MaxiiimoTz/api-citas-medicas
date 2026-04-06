package com.idat.service;

import java.util.List;
import java.util.Map;

import com.idat.model.Usuario;

public interface UsuarioService {

    List<Usuario> listar();
    Usuario obtenerPorId(Integer id);
    Usuario guardar(Usuario usuario);
    Usuario cambiarEstado(Integer id, Boolean estado);
    Usuario cambiarPassword(Integer idUsuario, String nuevaPassword);
    Usuario actualizar(Integer id, Usuario usuario);
    Map<String, String> enviarCredenciales(Integer idUsuario);
    void eliminar(Integer id);
}