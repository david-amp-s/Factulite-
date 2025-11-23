package com.sistemaFacturas.factulite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaFacturas.factulite.model.Factura;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByClienteId(Long clienteId);

}
