package com.sistemaFacturas.factulite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemaFacturas.factulite.dto.ClienteCreateDTO;
import com.sistemaFacturas.factulite.dto.ClienteDTO;
import com.sistemaFacturas.factulite.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute(
                "cliente",
                new ClienteCreateDTO("", "", "", "", ""));
        return "clientes/crear";
    }

    @PostMapping("/guardar")
    public String registrarCliente(@ModelAttribute("cliente") ClienteCreateDTO dto) {
        clienteService.registrarCliente(dto);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute ClienteCreateDTO dto) {
        clienteService.editarCliente(id, dto);
        return "redirect:/clientes";
    }
}
