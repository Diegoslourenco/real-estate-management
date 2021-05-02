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
	
	public void delete(Long id) {
		imoveis.deleteById(id);
	}

	public List<Imovel> get(ImovelFilter imovelFilter) {
		
		String filter = imovelFilter.getText();
		
		if (filter.isEmpty()) {
			return imoveis.findAll();
		}
		
		int fieldSelected = imovelFilter.getField().ordinal();
		
		if (fieldSelected == 0) {
			return imoveis.findByNameContainingOrderByNameAsc(filter);
		}
		else if (fieldSelected == 1) {
			
			int filterInt;
			
			try {				
				filterInt = Integer.parseInt(filter);
			}
			catch (NumberFormatException e) {
				filterInt = 0;
			}
			
			return imoveis.findByBedroomOrderByBedroomAsc(filterInt);		
		}
		else if (fieldSelected == 4) {
			return imoveis.findByAddressContainingOrderByAddressAsc(filter);
		}
		
		return imoveis.findAll();
	}
}
