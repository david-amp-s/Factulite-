package com.sistemaFacturas.factulite.dto;

import java.util.List;

public record FacturaCreateDTO(
        Long clienteId,
        List<DetalleFacturaDTO> productos) {

}
