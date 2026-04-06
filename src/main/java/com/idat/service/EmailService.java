package com.idat.service;

public interface EmailService {
    void enviarCredenciales(String destino, String email, String password);
}