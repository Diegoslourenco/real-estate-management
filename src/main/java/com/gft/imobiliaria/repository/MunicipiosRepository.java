package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Municipio;

public interface MunicipiosRepository extends JpaRepository<Municipio, Long> {
	
	public List<Municipio> findByNameContaining(String name);

}
