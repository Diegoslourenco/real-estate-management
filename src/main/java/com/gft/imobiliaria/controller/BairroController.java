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

import com.gft.imobiliaria.model.Bairro;
import com.gft.imobiliaria.repository.filter.BairroFilter;
import com.gft.imobiliaria.service.MunicipioService;
import com.gft.imobiliaria.service.BairroService;
import com.gft.imobiliaria.service.EstadoService;

@Controller
@RequestMapping("/bairros")
public class BairroController {
	
	private static final String CADASTRO_VIEW = "bairro/BairroCadastro";
	private static final String BUSCA_VIEW = "bairro/BairroBusca";
	private static final String CADASTRO_ERRO_VIEW = "bairro/BairroCadastroErro";
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/novo")
	public ModelAndView novoBairro() {
		ModelAndView mv = new ModelAndView();
		
		if (municipioService.getAll().isEmpty()) {
			
			mv.setViewName(CADASTRO_ERRO_VIEW);
			
			return mv;
		}
		
		mv.setViewName(CADASTRO_VIEW);
		mv.addObject("bairro", new Bairro());
		mv.addObject("municipios", municipioService.getAll());
		mv.addObject("estados", estadoService.getAll());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@ModelAttribute("bairro") @Validated Bairro bairro, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		bairroService.save(bairro);
		
		mv = new ModelAndView("redirect:/bairros/novo");
		attributes.addFlashAttribute("message", "Bairro salvo com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") BairroFilter bairroFilter) {	
		List<Bairro> allBairros = bairroService.get(bairroFilter);
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("bairros", allBairros);

		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Bairro bairro) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(bairro);
		mv.addObject("municipios", municipioService.getAll());
		mv.addObject("estados", estadoService.getAll());
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		bairroService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/bairros");
		
		attributes.addFlashAttribute("message", "Bairro removido com sucesso");
		
		return mv;
	}
}
