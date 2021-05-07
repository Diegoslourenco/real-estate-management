package com.gft.imobiliaria.service;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	public void sendEmail(Usuario usuario) throws MessagingException {
		
	    MimeMessage message = mailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom("imobgft@gmail.com");
	    helper.setTo(usuario.getEmail());
	    helper.setSubject("Acesse nosso site para ver as novidades!");
	    helper.setText("Olá, " + usuario.getName() + "!\n"
				+"\nVenha ver as novidades nos imóveis!\n"
				+ "\nAcesse nosso site!\n"
				+ "\nEquipe Imobiliária GFT");
	    
	    ClassPathResource file = new ClassPathResource("/static/documents/imobgft.docx");
	    
	    helper.addAttachment("Imobgft.docx", file);
	    
	    try {
	    	mailSender.send(message);
	    } catch (MailException ex) {
	    	System.err.println(ex.getMessage());
	    }	
	}
}
