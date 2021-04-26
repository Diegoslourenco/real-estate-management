package com.gft.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.book.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findByNameContaining(String name);
}
