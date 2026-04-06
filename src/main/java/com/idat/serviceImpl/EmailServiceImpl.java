package com.idat.serviceImpl;

import org.springframework.stereotype.Service;
import com.idat.service.EmailService;

import kong.unirest.Unirest;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void enviarCredenciales(String destino, String email, String password) {

        try {

            Unirest.post("https://api.resend.com/emails")
                .header("Authorization", "Bearer " + System.getenv("RESEND_API_KEY"))
                .header("Content-Type", "application/json")
                .body("{"
                    + "\"from\":\"onboarding@resend.dev\","
                    + "\"to\":\"" + destino + "\","
                    + "\"subject\":\"Credenciales de acceso\","
                    + "\"html\":\"<h2>Bienvenido</h2>"
                    + "<p>Usuario: " + email + "</p>"
                    + "<p>Password: " + password + "</p>"
                    + "<br><p>⚠️ Cambia tu contraseña al ingresar</p>\""
                + "}")
                .asString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar credenciales");
        }
    }
}