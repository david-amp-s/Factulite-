package com.sistemaFacturas.factulite.exception;

public class ClienteCorreoEnUsoException extends RuntimeException {
    public ClienteCorreoEnUsoException() {
        super("El correo electrónico ya está en uso por otro cliente.");
    }

}
