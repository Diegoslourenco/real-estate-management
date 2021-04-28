package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Long> {
	
	public List<Estado> findByNameContaining(String name);

}
