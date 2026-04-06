package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.idat.model.Especialidad;
import com.idat.service.EspecialidadService;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    @GetMapping
    public List<Especialidad> listar() {
        return service.listar();
    }

    @PostMapping
    public Especialidad guardar(@RequestBody Especialidad especialidad) {
        return service.guardar(especialidad);
    }
    
    @PutMapping("/{id}")
    public Especialidad actualizar(@PathVariable Integer id, @RequestBody Especialidad especialidad) {
        return service.actualizar(id, especialidad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}