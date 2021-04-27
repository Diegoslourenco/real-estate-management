package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.repository.filter.CategoriaFilter;
import com.gft.imobiliaria.model.Categoria;
import com.gft.imobiliaria.repository.CategoriasRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriasRepository categorias;
	
	public void save(Categoria categoria) {
		categorias.save(categoria);
	}

	public List<Categoria> get(CategoriaFilter categoriaFilter) {
		
		if (categoriaFilter.getText() == null) {
			return categorias.findAll();
		}
		
		return categorias.findByNameContaining(categoriaFilter.getText());
	}

	public void delete(Long id) {
		categorias.deleteById(id);
	}
}
