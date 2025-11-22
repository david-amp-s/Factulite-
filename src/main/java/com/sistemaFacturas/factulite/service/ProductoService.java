package com.sistemaFacturas.factulite.service;

import java.util.List;

import com.sistemaFacturas.factulite.dto.ProductoCreateDTO;
import com.sistemaFacturas.factulite.dto.ProductoDTO;

public interface ProductoService {
    ProductoDTO registrarProducto(ProductoCreateDTO dto);

    List<ProductoDTO> listarProductos();

    ProductoDTO obtenerProductoPorId(Long id);

    ProductoDTO actualizarProducto(Long id, ProductoCreateDTO dto);
}
