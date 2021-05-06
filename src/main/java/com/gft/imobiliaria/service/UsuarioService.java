package com.gft.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.imobiliaria.model.Usuario;
import com.gft.imobiliaria.repository.UsuariosRepository;
import com.gft.imobiliaria.repository.filter.UsuarioFilter;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuariosRepository usuarios;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public List<Usuario> get(UsuarioFilter usuarioFilter) {
		
		if (usuarioFilter.getText() == null) {
			return usuarios.findAll();
		}
		
		return usuarios.findByNameContaining(usuarioFilter.getText());
	}
	
	public void delete(Long id) {
		usuarios.deleteById(id);	
	}
	
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
	
	public void sendEmail(Usuario usuario) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("imobgft@gmail.com");
		simpleMailMessage.setTo(usuario.getEmail());
		simpleMailMessage.setSubject("Acesse nosso site para ver as novidades!");
		simpleMailMessage.setText("Olá, " + usuario.getName() + "!\n"
									+"\nSeguem novidades nos imóveis!\n"
									+ "\nAcesse nosso site!\n"
									+ "\nEquipe Imobiliária GFT");
		
		mailSender.send(simpleMailMessage);			
	}
}
