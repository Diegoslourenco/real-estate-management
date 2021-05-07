package com.gft.imobiliaria.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.gft.imobiliaria.model.Estado;
import com.gft.imobiliaria.model.Municipio;
import com.gft.imobiliaria.repository.filter.BairroFilter;
import com.gft.imobiliaria.service.MunicipioService;
import com.gft.imobiliaria.service.BairroService;
import com.gft.imobiliaria.service.EstadoService;

@Controller
@RequestMapping("/bairros")
@PreAuthorize("hasRole('admin')")
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
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("bairros", bairroService.get(bairroFilter));

		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Bairro bairro) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(bairro);
		
		return mv;
	}	
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		bairroService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/bairros");
		
		attributes.addFlashAttribute("message", "Bairro removido com sucesso");
		
		return mv;
	}
	
	@ModelAttribute("estados")
	public List<Estado> allEstados() {
		return estadoService.getAll();
	}
	
	@ModelAttribute("citiesMappedByState")
	public Map<Long, ArrayList<Long>> citiesMappedByState() {
		
		List<Estado> allEstados = estadoService.getAll();
		Map<Long, ArrayList<Long>> map = new HashMap<Long, ArrayList<Long>>();			
		
		for (Estado estado : allEstados) {
			
			long key = estado.getId();
			
			// Ensure that a Estado without Municipio is mapped
			if (estado.getMunicipios().isEmpty()) {
				map.put(key, new ArrayList<Long>());
			}
			
			for (Municipio municipio : estado.getMunicipios()) {
				
				if (!map.containsKey(estado.getId())) {
					
					map.put(key, new ArrayList<Long>());
					map.get(key).add(municipio.getId());
				}
				else {
					map.get(key).add(municipio.getId());
				}
			}
		}
		
		return map;
	}
		
	@ModelAttribute("citiesNameMappedById")
	public Map<Long, String> citiesNameMappedById() {
			
		List<Municipio> allMunicipios = municipioService.getAll();
		Map<Long, String> map = new HashMap<Long, String>();
			
		for (Municipio municipio : allMunicipios) {			
			map.put(municipio.getId(), municipio.getName());
		}
			
		return map;
	}
}
