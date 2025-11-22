package com.sistemaFacturas.factulite.service;

import java.util.List;

import com.sistemaFacturas.factulite.dto.ClienteCreateDTO;
import com.sistemaFacturas.factulite.dto.ClienteDTO;

public interface ClienteService {
    ClienteDTO registrarCliente(ClienteCreateDTO dto);

    List<ClienteDTO> obtenerTodosLosClientes();

    ClienteDTO editarCliente(Long id, ClienteCreateDTO dto);

    ClienteDTO obtenerClientePorId(Long id);
}
