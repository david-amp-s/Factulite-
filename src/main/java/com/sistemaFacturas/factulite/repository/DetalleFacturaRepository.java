package com.sistemaFacturas.factulite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaFacturas.factulite.model.DetalleFactura;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

}
