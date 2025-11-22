package com.sistemaFacturas.factulite.dto;

public record ProductoCreateDTO(
        String nombre,
        String descripcion,
        Double precio,
        Integer stock) {

}
