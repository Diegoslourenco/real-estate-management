package com.gft.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.imobiliaria.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

	public List<Usuario> findByNameContaining(String name);
}
