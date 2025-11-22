package com.sistemaFacturas.factulite.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaFacturas.factulite.dto.ProductoCreateDTO;
import com.sistemaFacturas.factulite.dto.ProductoDTO;
import com.sistemaFacturas.factulite.exception.ProductoNombreEnUsoException;
import com.sistemaFacturas.factulite.model.Producto;
import com.sistemaFacturas.factulite.repository.ProductoRepository;
import com.sistemaFacturas.factulite.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock());
    }

    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO dto) {
        productoRepository.findByNombre(dto.nombre())
                .ifPresent(p -> {
                    throw new ProductoNombreEnUsoException();
                });
        Producto producto = Producto.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .precio(dto.precio())
                .stock(dto.stock())
                .build();
        productoRepository.save(producto);
        return mapToDTO(producto);
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::mapToDTO).toList();
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow();
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoCreateDTO dto) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        productoRepository.save(producto);
        return mapToDTO(producto);
    }

}
