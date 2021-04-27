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

import com.gft.imobiliaria.model.Negocio;
import com.gft.imobiliaria.repository.filter.NegocioFilter;
import com.gft.imobiliaria.service.NegocioService;

@Controller
@RequestMapping("/negocios")
public class NegocioController {
	
	private static final String CADASTRO_VIEW = "negocio/NegocioCadastro";
	private static final String BUSCA_VIEW = "negocio/NegocioBusca";
	
	@Autowired
	private NegocioService negocioService;
	
	@GetMapping("/novo")
	public ModelAndView novoNegocio() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("negocio", new Negocio());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@Validated Negocio negocio, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		negocioService.save(negocio);
		
		mv = new ModelAndView("redirect:/negocios/novo");
		attributes.addFlashAttribute("message", "Negócio salvo com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") NegocioFilter negocioFilter) {	
		List<Negocio> allNegocios = negocioService.get(negocioFilter);
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("negocios", allNegocios);
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Negocio negocio) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(negocio);
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		negocioService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/negocios");
		
		attributes.addFlashAttribute("message", "Negócio removido com sucesso");
		
		return mv;
	}
}
