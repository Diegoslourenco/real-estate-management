package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Imovel;

public interface ImoveisRepository extends JpaRepository<Imovel, Long> {
	
	public List<Imovel> findByNameContainingOrderByNameAsc(String name);

	public List<Imovel> findByBedroomOrderByBedroomAsc(int bedroom);

	public List<Imovel> findByAddressContainingOrderByAddressAsc(String address);
}
