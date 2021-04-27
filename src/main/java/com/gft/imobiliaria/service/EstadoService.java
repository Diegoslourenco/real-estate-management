package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.repository.filter.EstadoFilter;
import com.gft.imobiliaria.model.Estado;
import com.gft.imobiliaria.repository.EstadosRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadosRepository estados;
	
	public void save(Estado estado) {
		estados.save(estado);
	}

	public List<Estado> get(EstadoFilter estadoFilter) {
		
		if (estadoFilter.getText() == null) {
			return estados.findAll();
		}
		
		return estados.findByNameContaining(estadoFilter.getText());
	}

	public void delete(Long id) {
		estados.deleteById(id);
	}
}
