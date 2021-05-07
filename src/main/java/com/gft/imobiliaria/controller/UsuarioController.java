package com.gft.imobiliaria.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.imobiliaria.model.Usuario;
import com.gft.imobiliaria.repository.filter.UsuarioFilter;
import com.gft.imobiliaria.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static final String BUSCA_VIEW = "usuario/UsuarioBusca";
	private static final String CADASTRO_VIEW = "usuario/UsuarioCadastro";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/novo")
	public ModelAndView novoUsuario() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(CADASTRO_VIEW);
		mv.addObject("usuario", new Usuario());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("usuario") @Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		if (usuarioService.save(usuario)) {
			
			mv = new ModelAndView("redirect:/usuarios/novo");
			attributes.addFlashAttribute("message", "Usuario salvo com sucesso");
			
			return mv;		
		}
		
		mv = new ModelAndView("redirect:/usuarios/novo");
		attributes.addFlashAttribute("messageError", "Usuário e/ou email já cadastrados");
			
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") UsuarioFilter usuarioFilter) {	
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("usuarios", usuarioService.get(usuarioFilter));

		return mv;
	}	
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		usuarioService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		
		attributes.addFlashAttribute("message", "Usuário removido com sucesso");
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView sendEmail(@PathVariable("id") Usuario usuario, RedirectAttributes attributes) throws MessagingException {
		
		usuarioService.sendEmail(usuario);
		
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		attributes.addFlashAttribute("message", "Email enviado com sucesso para " + usuario.getEmail());
		
		return mv;
	}
}
