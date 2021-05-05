package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Usuario;
import com.gft.imobiliaria.repository.UsuariosRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuariosRepository usuarios;
	
	public boolean save(Usuario usuario) {
		
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		
		if (checkUniqueEmail(usuario) || checkUniqueUsername(usuario)) {
			return false;
		}
		
		usuario.setPermission("user");
		usuario.setActive(true);
		usuarios.save(usuario);
		
		return true;
	}
	
	public boolean checkUniqueEmail(Usuario novoUsuario) {
		List<Usuario> allUsuarios = usuarios.findAll();
		
		for (Usuario usuario : allUsuarios) {
			
			if (usuario.getEmail().equals(novoUsuario.getEmail())) {
				return true;
			}	
		}
		
		return false;	
	}
	
	public boolean checkUniqueUsername(Usuario novoUsuario) {
		List<Usuario> allUsuarios = usuarios.findAll();
		
		for (Usuario usuario : allUsuarios) {
			
			if (usuario.getUsername().equals(novoUsuario.getUsername())) {
				return true;
			}	
		}
		
		return false;	
	}
}
