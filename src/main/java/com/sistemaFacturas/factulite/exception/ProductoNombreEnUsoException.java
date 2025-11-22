package com.sistemaFacturas.factulite.exception;

public class ProductoNombreEnUsoException extends RuntimeException {
    public ProductoNombreEnUsoException() {
        super("El nombre del producto ya está en uso.");
    }

}
