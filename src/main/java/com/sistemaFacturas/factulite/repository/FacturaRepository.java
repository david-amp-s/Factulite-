package com.sistemaFacturas.factulite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaFacturas.factulite.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
