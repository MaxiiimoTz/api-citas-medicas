package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.idat.model.Usuario;
import com.idat.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }
    
    @PostMapping("/{id}/enviar-credenciales")
    public Map<String, String> enviarCredenciales(@PathVariable Integer id) {
        return service.enviarCredenciales(id);
    }
    
    @PatchMapping("/{id}/estado")
    public Usuario cambiarEstado(
            @PathVariable Integer id,
            @RequestParam Boolean estado
    ) {
        return service.cambiarEstado(id, estado);
    }
    
    @PutMapping("/cambiar-password")
    public Usuario cambiarPassword(@RequestBody Usuario usuario) {
    	return service.cambiarPassword(usuario.getIdUsuario(), usuario.getPassword());
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return service.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarErrores(RuntimeException ex) {
        return Map.of("error", ex.getMessage());
    }
}