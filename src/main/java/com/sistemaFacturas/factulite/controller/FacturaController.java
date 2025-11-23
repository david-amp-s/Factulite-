package com.sistemaFacturas.factulite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemaFacturas.factulite.dto.FacturaCreateDTO;
import com.sistemaFacturas.factulite.dto.FacturaDTO;
import com.sistemaFacturas.factulite.service.FacturaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    // Mostrar todas las facturas
    @GetMapping
    public String listarFacturas(Model model) {
        List<FacturaDTO> facturas = facturaService.obtenerTodasLasFacturas();
        model.addAttribute("facturas", facturas);
        return "facturas/lista"; // tu template Thymeleaf
    }

    // Mostrar facturas de un cliente específico
    @GetMapping("/cliente/{id}")
    public String facturasPorCliente(@PathVariable("id") Long clienteId, Model model) {
        List<FacturaDTO> facturas = facturaService.obtenerFacturasPorCliente(clienteId);
        model.addAttribute("facturas", facturas);
        return "facturas/lista"; // puedes usar la misma vista
    }

    // Formulario para crear factura
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("facturaCreateDTO", new FacturaCreateDTO(
                0L, // clienteId por defecto
                List.of() // lista vacía de productos
        ));
        return "facturas/crear";
    }

    // Guardar factura
    @PostMapping("/crear")
    public String crearFactura(@ModelAttribute FacturaCreateDTO dto) {
        facturaService.crearFactura(dto);
        return "redirect:/facturas";
    }
}
