package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.idat.model.Rol;
import com.idat.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

	@Autowired
	private RolService service;

    @GetMapping
    public List<Rol> listar() {
    	return service.listar();
    }

    @GetMapping("/{id}")
    public Rol obtener(@PathVariable Integer id) {
    	return service.obtenerPorId(id);
    }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) {
    	return service.guardar(rol);
    }

    @PutMapping("/{id}")
    public Rol actualizar(@PathVariable Integer id, @RequestBody Rol rol) {
        rol.setIdRol(id);
        return service.actualizar(id, rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
    	service.eliminar(id);
    }
}