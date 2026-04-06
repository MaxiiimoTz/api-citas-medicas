package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.idat.model.HistoriaClinica;
import com.idat.service.HistoriaClinicaService;

@RestController
@RequestMapping("/api/historias")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService service;

    @GetMapping
    public List<HistoriaClinica> listar() {
        return service.listar();
    }

    @PostMapping
    public HistoriaClinica guardar(@RequestBody HistoriaClinica historia) {
        return service.guardar(historia);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}