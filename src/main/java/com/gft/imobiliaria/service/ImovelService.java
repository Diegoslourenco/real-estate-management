package com.gft.imobiliaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Bairro;
import com.gft.imobiliaria.model.Categoria;
import com.gft.imobiliaria.model.Estado;
import com.gft.imobiliaria.model.Imovel;
import com.gft.imobiliaria.model.Municipio;
import com.gft.imobiliaria.model.Negocio;
import com.gft.imobiliaria.repository.BairrosRepository;
import com.gft.imobiliaria.repository.CategoriasRepository;
import com.gft.imobiliaria.repository.EstadosRepository;
import com.gft.imobiliaria.repository.ImoveisRepository;
import com.gft.imobiliaria.repository.MunicipiosRepository;
import com.gft.imobiliaria.repository.NegociosRepository;
import com.gft.imobiliaria.repository.filter.ImovelFilter;

@Service
public class ImovelService {
	
	@Autowired
	private ImoveisRepository imoveis;
	
	@Autowired
	private NegociosRepository negocios;
	
	@Autowired
	private CategoriasRepository categorias;
	
	@Autowired
	private BairrosRepository bairros;
	
	@Autowired
	private MunicipiosRepository municipios;
	
	@Autowired
	private EstadosRepository estados;
	
	public void save(Imovel imovel) {
		imoveis.save(imovel);
	}
	
	public void delete(Long id) {
		imoveis.deleteById(id);
	}

	public List<Imovel> get(ImovelFilter imovelFilter) {
		
		String filter = imovelFilter.getText();
		
		if (filter == null || filter.isEmpty()) {
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
		else if (fieldSelected == 2) {
			
			List<Negocio> allNegocios = negocios.findByNameContaining(filter);
			List<Imovel> allImoveis = new ArrayList<Imovel>();
					
			for (Negocio negocio : allNegocios) {
				allImoveis.addAll(negocio.getImoveis());		
			}
			
			return allImoveis;
		}
		else if (fieldSelected == 3) {
			
			List<Categoria> allCategorias = categorias.findByNameContaining(filter);
			List<Imovel> allImoveis = new ArrayList<Imovel>();
			
			for (Categoria categoria : allCategorias) {
				allImoveis.addAll(categoria.getImoveis());				
			}
			
			return allImoveis;
		}
		else if (fieldSelected == 4) {
			return imoveis.findByAddressContainingOrderByAddressAsc(filter);
		}
		else if (fieldSelected == 5) {
			
			List<Bairro> allBairros = bairros.findByNameContaining(filter);
			List<Imovel> allImoveis = new ArrayList<Imovel>();
			
			for (Bairro bairro : allBairros) {
				allImoveis.addAll(bairro.getImoveis());				
			}
			
			return allImoveis;
		}
		else if (fieldSelected == 6) {
			
			List<Municipio> allMunicipios = municipios.findByNameContaining(filter);
			List<Imovel> allImoveis = new ArrayList<Imovel>();
			
			for (Municipio municipio : allMunicipios) {
				allImoveis.addAll(municipio.getImoveis());				
			}
			
			return allImoveis;
		}
		else if (fieldSelected == 7) {
			
			List<Estado> allEstados = estados.findByNameContaining(filter);
			List<Imovel> allImoveis = new ArrayList<Imovel>();
			
			for (Estado estado : allEstados) {
				allImoveis.addAll(estado.getImoveis());				
			}
			
			return allImoveis;
		}
		
		return imoveis.findAll();
	}
}
