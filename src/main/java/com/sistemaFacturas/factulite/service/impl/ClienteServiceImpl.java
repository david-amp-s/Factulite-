package com.sistemaFacturas.factulite.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaFacturas.factulite.dto.ClienteCreateDTO;
import com.sistemaFacturas.factulite.dto.ClienteDTO;
import com.sistemaFacturas.factulite.exception.ClienteCorreoEnUsoException;
import com.sistemaFacturas.factulite.exception.ClienteTelefonoEnUsoException;
import com.sistemaFacturas.factulite.model.Cliente;
import com.sistemaFacturas.factulite.repository.ClienteRepository;
import com.sistemaFacturas.factulite.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    ClienteDTO mapToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion());
    }

    @Override
    public ClienteDTO registrarCliente(ClienteCreateDTO dto) {
        clienteRepository.findByEmail(dto.email()).ifPresent(c -> {
            throw new ClienteCorreoEnUsoException();
        });

        clienteRepository.findByTelefono(dto.telefono()).ifPresent(c -> {
            throw new ClienteTelefonoEnUsoException();
        });
        Cliente cliente = Cliente.builder()
                .nombre(dto.nombre())
                .apellido(dto.apellido())
                .email(dto.email())
                .telefono(dto.telefono())
                .direccion(dto.direccion())
                .build();
        clienteRepository.save(cliente);
        return mapToDTO(cliente);
    }

    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::mapToDTO).toList();
    }

    @Override
    public ClienteDTO editarCliente(Long id, ClienteCreateDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
        clienteRepository.save(cliente);
        return mapToDTO(cliente);
    }

    @Override
    public ClienteDTO obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow();
    }

}
