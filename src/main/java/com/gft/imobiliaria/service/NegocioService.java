package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Negocio;
import com.gft.imobiliaria.repository.NegociosRepository;
import com.gft.imobiliaria.repository.filter.NegocioFilter;

@Service
public class NegocioService {

	@Autowired
	private NegociosRepository negocios;
	
	public void save(Negocio negocio) {
		negocios.save(negocio);
	}

	public List<Negocio> get(NegocioFilter negocioFilter) {
		
		if (negocioFilter.getText() == null) {
			return negocios.findAll();
		}
		
		return negocios.findByNameContaining(negocioFilter.getText());
	}

	public void delete(Long id) {
		negocios.deleteById(id);
	}
}
