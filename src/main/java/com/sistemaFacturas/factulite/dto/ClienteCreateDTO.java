package com.sistemaFacturas.factulite.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteCreateDTO(
        @NotBlank(message = "el nombre no puede estar vacio") String nombre,
        @NotBlank(message = "el apellido no puede estar vacio") String apellido,
        @NotBlank(message = "el correo no puede estar vacio") String email,
        @NotBlank(message = "el telefono no puede estar vacio") String telefono,
        @NotBlank(message = "la direccion no puede estar vacio") String direccion) {

}
