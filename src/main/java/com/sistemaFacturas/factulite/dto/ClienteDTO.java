package com.sistemaFacturas.factulite.dto;

public record ClienteDTO(
                Long id,
                String nombre,
                String apellido,
                String email,
                String telefono,
                String direccion) {

}
