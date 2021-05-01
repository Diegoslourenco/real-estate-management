package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Imovel;
import com.gft.imobiliaria.repository.ImoveisRepository;
import com.gft.imobiliaria.repository.filter.ImovelFilter;

@Service
public class ImovelService {
	
	@Autowired
	private ImoveisRepository imoveis;
	
	public void save(Imovel imovel) {
		imoveis.save(imovel);
	}

	public List<Imovel> get(ImovelFilter imovelFilter) {
		
		if (imovelFilter.getText() == null) {
			return imoveis.findAll();
		}
		
		return imoveis.findByNameContaining(imovelFilter.getText());
	}

	public void delete(Long id) {
		imoveis.deleteById(id);
	}
}
