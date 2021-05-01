package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Imovel;

public interface ImoveisRepository extends JpaRepository<Imovel, Long> {
	
	public List<Imovel> findByNameContaining(String name);
}
