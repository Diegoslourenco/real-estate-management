package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Municipio;
import com.gft.imobiliaria.repository.MunicipiosRepository;
import com.gft.imobiliaria.repository.filter.MunicipioFilter;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipiosRepository municipios;
	
	public void save(Municipio municipio) {
		municipios.save(municipio);
	}

	public List<Municipio> get(MunicipioFilter municipioFilter) {
		
		if (municipioFilter.getText() == null) {
			return municipios.findAll();
		}
		
		return municipios.findByNameContaining(municipioFilter.getText());
	}

	public void delete(Long id) {
		municipios.deleteById(id);
	}
}
