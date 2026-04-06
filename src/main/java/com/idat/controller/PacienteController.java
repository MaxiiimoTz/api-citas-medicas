package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.idat.model.Paciente;
import com.idat.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }
    
    @GetMapping("/count")
    public long contarPaciente() {
    	return service.contarPaciente();
    }
    
    @GetMapping("/reporte/periodo")
    public List<Map<String, Object>> reportePacientesPeriodo(
        @RequestParam LocalDate inicio,
        @RequestParam LocalDate fin
    ) {
        return service.reportePacientesPeriodo(
            inicio.atStartOfDay(),
            fin.atTime(23, 59, 59)
        );
    }

    @GetMapping("/{id}")
    public Paciente obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public Paciente obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return service.obtenerPorUsuario(idUsuario);
    }
    
    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return service.guardar(paciente);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Integer id, @RequestBody Paciente paciente) {
        return service.actualizar(id, paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}