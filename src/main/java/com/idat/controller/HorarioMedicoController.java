package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.idat.model.HorarioMedico;
import com.idat.service.HorarioMedicoService;

@RestController
@RequestMapping("/api/horarios")
public class HorarioMedicoController {

    @Autowired
    private HorarioMedicoService service;

    @GetMapping
    public List<HorarioMedico> listar(){
        return service.listar();
    }

    @PostMapping
    public HorarioMedico guardar(@RequestBody HorarioMedico horario) {
        return service.guardar(horario);
    }
}