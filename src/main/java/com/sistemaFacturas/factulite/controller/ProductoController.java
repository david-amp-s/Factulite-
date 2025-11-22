package com.sistemaFacturas.factulite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemaFacturas.factulite.dto.ProductoCreateDTO;
import com.sistemaFacturas.factulite.dto.ProductoDTO;
import com.sistemaFacturas.factulite.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    // -----------------------------
    // 1. Listar productos
    // -----------------------------
    @GetMapping
    public String listarProductos(Model model) {
        List<ProductoDTO> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "productos/lista"; // Vista lista.html
    }

    // -----------------------------
    // 2. Formulario de nuevo producto
    // -----------------------------
    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new ProductoCreateDTO("", "", 0.0, 0));
        return "productos/crear"; // Vista crear.html
    }

    // -----------------------------
    // 3. Guardar producto
    // -----------------------------
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") ProductoCreateDTO dto) {
        productoService.registrarProducto(dto);
        return "redirect:/productos";
    }

    // -----------------------------
    // 4. Formulario de edición
    // -----------------------------
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "productos/editar"; // Vista editar.html
    }

    // -----------------------------
    // 5. Actualizar producto
    // -----------------------------
    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id,
            @ModelAttribute("producto") ProductoCreateDTO dto) {
        productoService.actualizarProducto(id, dto);
        return "redirect:/productos";
    }
}
