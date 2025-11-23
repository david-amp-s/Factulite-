package com.sistemaFacturas.factulite.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaFacturas.factulite.dto.DetalleFacturaDTO;
import com.sistemaFacturas.factulite.dto.FacturaCreateDTO;
import com.sistemaFacturas.factulite.dto.FacturaDTO;
import com.sistemaFacturas.factulite.model.Cliente;
import com.sistemaFacturas.factulite.model.DetalleFactura;
import com.sistemaFacturas.factulite.model.Factura;
import com.sistemaFacturas.factulite.model.Producto;
import com.sistemaFacturas.factulite.repository.ClienteRepository;
import com.sistemaFacturas.factulite.repository.DetalleFacturaRepository;
import com.sistemaFacturas.factulite.repository.FacturaRepository;
import com.sistemaFacturas.factulite.repository.ProductoRepository;
import com.sistemaFacturas.factulite.service.FacturaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final DetalleFacturaRepository detalleFacturaRepository;

    FacturaDTO mapToDo(Factura factura) {
        return new FacturaDTO(
                factura.getId(),
                factura.getCliente().getNombre(),
                factura.getFecha(),
                factura.getTotal(),
                factura.getEstado());
    }

    @Override
    public FacturaDTO crearFactura(FacturaCreateDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow();
        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(LocalDate.now());
        factura.setEstado(false);
        factura.setTotal(0.0);
        facturaRepository.save(factura);
        for (DetalleFacturaDTO detalleFactura : dto.productos()) {
            Producto producto = productoRepository.findById(detalleFactura.productoId()).orElseThrow();
            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleFactura.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
            detalleFacturaRepository.save(detalle);
            factura.setTotal(factura.getTotal() + detalle.getSubtotal());

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);
        }
        facturaRepository.save(factura);
        return mapToDo(factura);
    }

    @Override
    public List<FacturaDTO> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream().map(this::mapToDo).toList();
    }

    @Override
    public List<FacturaDTO> obtenerFacturasPorCliente(Long clienteId) {
        List<Factura> facturas = facturaRepository.findByClienteId(clienteId);
        return facturas.stream().map(this::mapToDo).toList();
    }

}
