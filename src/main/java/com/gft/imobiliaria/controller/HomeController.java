package com.gft.imobiliaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.imobiliaria.model.Usuario;
import com.gft.imobiliaria.service.UsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String index() {
		return "Home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/registrar")
	public ModelAndView newUser() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("Registrar");
		mv.addObject("usuario", new Usuario());
		
		return mv;	
	}
	
	@PostMapping("/registrar")
	public String save(@ModelAttribute("usuario") @Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return "Registrar";
		}
		
		if (usuarioService.save(usuario)) {
			attributes.addFlashAttribute("message", "Usuário cadastrado com sucesso");
			
			return "redirect:/registrar";		
		}
		
		attributes.addFlashAttribute("messageError", "Usuário e/ou email já cadastrados");
		
		return "redirect:/registrar";	
	}
}
