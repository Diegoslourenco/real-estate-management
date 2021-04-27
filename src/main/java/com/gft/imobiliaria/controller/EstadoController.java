package com.gft.imobiliaria.controller;

import java.util.List;

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

import com.gft.imobiliaria.model.Estado;
import com.gft.imobiliaria.repository.filter.EstadoFilter;
import com.gft.imobiliaria.service.EstadoService;

@Controller
@RequestMapping("/estados")
public class EstadoController {
	
	private static final String CADASTRO_VIEW = "estado/EstadoCadastro";
	private static final String BUSCA_VIEW = "estado/EstadoBusca";
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/novo")
	public ModelAndView novoEstado() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("estado", new Estado());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@Validated Estado estado, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		estadoService.save(estado);
		
		mv = new ModelAndView("redirect:/estados/novo");
		attributes.addFlashAttribute("message", "Estado salvo com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") EstadoFilter estadoFilter) {	
		List<Estado> allEstados = estadoService.get(estadoFilter);
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("estados", allEstados);
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Estado estado) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(estado);
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		estadoService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/estados");
		
		attributes.addFlashAttribute("message", "Estado removido com sucesso");
		
		return mv;
	}
}
