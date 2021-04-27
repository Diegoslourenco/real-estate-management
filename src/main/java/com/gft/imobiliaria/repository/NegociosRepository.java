package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Negocio;

public interface NegociosRepository extends JpaRepository<Negocio, Long>{

		public List<Negocio> findByNameContaining(String name);
}
