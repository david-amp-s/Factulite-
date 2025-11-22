package com.sistemaFacturas.factulite.dto;

public record ProductoDTO(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock) {

}
