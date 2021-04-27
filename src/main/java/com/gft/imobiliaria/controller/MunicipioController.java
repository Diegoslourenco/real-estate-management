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

import com.gft.imobiliaria.model.Municipio;
import com.gft.imobiliaria.repository.filter.MunicipioFilter;
import com.gft.imobiliaria.service.MunicipioService;

@Controller
@RequestMapping("/municipios")
public class MunicipioController {
	
	private static final String CADASTRO_VIEW = "municipio/MunicipioCadastro";
	private static final String BUSCA_VIEW = "municipio/MunicipioBusca";
	
	@Autowired
	private MunicipioService municipioService;
	
	@GetMapping("/novo")
	public ModelAndView novoMunicipio() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("municipio", new Municipio());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@Validated Municipio municipio, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		municipioService.save(municipio);
		
		mv = new ModelAndView("redirect:/municipios/novo");
		attributes.addFlashAttribute("message", "Municipio salvo com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") MunicipioFilter municipioFilter) {	
		List<Municipio> allMunicipios = municipioService.get(municipioFilter);
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("municipios", allMunicipios);
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Municipio municipio) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(municipio);
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		municipioService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/municipios");
		
		attributes.addFlashAttribute("message", "Municipio removido com sucesso");
		
		return mv;
	}
}
