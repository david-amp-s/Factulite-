package com.sistemaFacturas.factulite.service;

import java.util.List;

import com.sistemaFacturas.factulite.dto.FacturaCreateDTO;
import com.sistemaFacturas.factulite.dto.FacturaDTO;

public interface FacturaService {
    FacturaDTO crearFactura(FacturaCreateDTO dto);

    List<FacturaDTO> obtenerTodasLasFacturas();

    List<FacturaDTO> obtenerFacturasPorCliente(Long clienteId);
}
