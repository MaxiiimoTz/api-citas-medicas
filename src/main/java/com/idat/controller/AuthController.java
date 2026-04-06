package com.idat.controller;

import com.idat.model.Usuario;
import com.idat.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        System.out.println("EMAIL RECIBIDO: " + email);
        System.out.println("PASSWORD RECIBIDO: " + password);

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            System.out.println("USUARIO NO ENCONTRADO");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario no existe");
        }

        System.out.println("PASSWORD DB: " + usuario.getPassword());

        if (!usuario.getPassword().equals(password)) {
            System.out.println("PASSWORD INCORRECTO");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", "demo-token");
        response.put("usuario", usuario);

        return ResponseEntity.ok(response);
    }
}