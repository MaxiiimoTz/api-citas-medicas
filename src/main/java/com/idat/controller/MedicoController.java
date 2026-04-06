package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.idat.model.Medico;
import com.idat.service.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping
    public List<Medico> listar() {
        return service.listar();
    }
    
    @GetMapping("/especialidad/{id}")
    public List<Medico> porEspecialidad(@PathVariable Integer id){
        return service.obtenerPorEspecialidad(id);
    }
    
    @GetMapping("/activos/count")
    public long contarActivos() {
    	return service.contarActivos();
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public Medico obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return service.obtenerPorUsuario(idUsuario);
    }

    @PostMapping
    public Medico guardar(@RequestBody Medico medico) {
        return service.guardar(medico);
    }
    
    @PutMapping("/{id}")
    public Medico actualizar(@PathVariable Integer id, @RequestBody Medico medico) {
        medico.setIdMedico(id);
        return service.guardar(medico);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}