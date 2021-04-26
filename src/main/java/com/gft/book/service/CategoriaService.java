package com.gft.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.book.filter.CategoriaFilter;
import com.gft.book.model.Categoria;
import com.gft.book.repository.CategoriasRepository;

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
