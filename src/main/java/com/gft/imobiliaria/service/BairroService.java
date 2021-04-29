package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Bairro;
import com.gft.imobiliaria.repository.BairrosRepository;
import com.gft.imobiliaria.repository.filter.BairroFilter;

@Service
public class BairroService {
	
	@Autowired
	private BairrosRepository bairros;
	
	public void save(Bairro bairro) {
		bairros.save(bairro);
	}

	public List<Bairro> get(BairroFilter bairroFilter) {
		
		if (bairroFilter.getText() == null) {
			return bairros.findAll();
		}
		
		return bairros.findByNameContaining(bairroFilter.getText());
	}

	public void delete(Long id) {
		bairros.deleteById(id);
	}
}
