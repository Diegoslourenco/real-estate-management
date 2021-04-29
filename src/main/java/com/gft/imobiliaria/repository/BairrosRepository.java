package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Bairro;

public interface BairrosRepository extends JpaRepository<Bairro, Long> {
	
	public List<Bairro> findByNameContaining(String name);
}
