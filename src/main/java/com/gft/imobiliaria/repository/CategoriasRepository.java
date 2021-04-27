package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findByNameContaining(String name);
}
