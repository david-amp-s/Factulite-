package com.sistemaFacturas.factulite.exception;

public class ClienteTelefonoEnUsoException extends RuntimeException {
    public ClienteTelefonoEnUsoException() {
        super("El teléfono ya está en uso por otro cliente.");
    }

}
