package com.sistemaFacturas.factulite.dto;

import java.time.LocalDate;

public record FacturaDTO(Long id,
                String clienteNombre,
                LocalDate fechaEmision,
                Double totalMonto,
                Boolean estado) {
}
