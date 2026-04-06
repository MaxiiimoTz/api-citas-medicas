package com.idat.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.idat.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCredenciales(String destino, String email, String password) {

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setTo(destino);
        mensaje.setSubject("Credenciales de acceso");

        mensaje.setText(
            "Bienvenido al sistema\n\n" +
            "Tus credenciales son:\n" +
            "Email: " + email + "\n" +
            "Password: " + password + "\n\n" +
            "⚠️ Debes cambiar tu contraseña al iniciar sesión."
        );

        mailSender.send(mensaje);
    }
}