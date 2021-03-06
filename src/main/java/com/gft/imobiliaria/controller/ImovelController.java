package com.gft.imobiliaria.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.imobiliaria.model.Bairro;
import com.gft.imobiliaria.model.Categoria;
import com.gft.imobiliaria.model.Estado;
import com.gft.imobiliaria.model.Imagem;
import com.gft.imobiliaria.model.Imovel;
import com.gft.imobiliaria.model.Municipio;
import com.gft.imobiliaria.model.Negocio;
import com.gft.imobiliaria.repository.filter.FieldsImovelFilter;
import com.gft.imobiliaria.repository.filter.ImovelFilter;
import com.gft.imobiliaria.service.MunicipioService;
import com.gft.imobiliaria.service.NegocioService;

import com.gft.imobiliaria.service.BairroService;
import com.gft.imobiliaria.service.CategoriaService;
import com.gft.imobiliaria.service.EstadoService;
import com.gft.imobiliaria.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImovelController {
	
	private static final String CADASTRO_VIEW = "imovel/ImovelCadastro";
	private static final String BUSCA_VIEW = "imovel/ImovelBusca";
	private static final String EDITAR_IMAGEM_VIEW = "imovel/ImovelImagemTrocar";
	
	@Autowired
	private NegocioService negocioService;
	
	@Autowired
	private CategoriaService categoriaService;	
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/novo")
	public ModelAndView novoImovel() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(CADASTRO_VIEW);
		mv.addObject("imovel", new Imovel());
			
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@RequestParam("files") MultipartFile[] images, @ModelAttribute("imovel") @Validated Imovel imovel, Errors errors, RedirectAttributes attributes) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		imovelService.save(imovel, images);
		
		mv = new ModelAndView("redirect:/imoveis/novo");
		attributes.addFlashAttribute("message", "Im??vel salvo com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") ImovelFilter imovelFilter) {	
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("imoveis", imovelService.get(imovelFilter));

		return mv;
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView searchImoveis(@ModelAttribute("filter") ImovelFilter imovelFilter) {	
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("imoveis", imovelService.get(imovelFilter));

		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Imovel imovel) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("imovel", imovel);
		
		return mv;
	}
	
	@GetMapping("/imagens/{id}")
	public ModelAndView updateImages(@PathVariable("id") Imovel imovel) {
		
		ModelAndView mv = new ModelAndView(EDITAR_IMAGEM_VIEW);
		mv.addObject("imovel", imovel);
		
		List<Imagem> allImagens = imovel.getImagens();
		
		
		if (allImagens.isEmpty()) {
			return mv;
		}
		else if (allImagens.size() == 1) {
			mv.addObject("imagem1", allImagens.get(0));
			
			return mv;
		}
			
		mv.addObject("imagem1", allImagens.get(0));
		mv.addObject("imagem2", allImagens.get(1));
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		imovelService.delete(id);
		
		ModelAndView mv = new ModelAndView("redirect:/imoveis");
		
		attributes.addFlashAttribute("message", "Im??vel removido com sucesso");
		
		return mv;
	}	
	
	@ModelAttribute("negocios")
	public List<Negocio> allNegocios() {
		return negocioService.getAll();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> allCategorias() {
		return categoriaService.getAll();
	}
	
	@ModelAttribute("estados")
	public List<Estado> allEstados() {
		return estadoService.getAll();
	}
	
	@ModelAttribute("allFieldsImovelFilter")
	public List<FieldsImovelFilter> allFieldsImovelFilter() {
		return Arrays.asList(FieldsImovelFilter.values());
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
	
	@ModelAttribute("districtsMappedByCity")
	public Map<Long, ArrayList<Long>> districtsMappedByCity() {
		
		List<Municipio> allMunicipios = municipioService.getAll();
		Map<Long, ArrayList<Long>> map = new HashMap<Long, ArrayList<Long>>();			
		
		for (Municipio municipio : allMunicipios) {
			
			long key = municipio.getId();
			
			// Ensure that a Municipio without Bairro is mapped
			if (municipio.getBairros().isEmpty()) {
				map.put(key, new ArrayList<Long>());
			}
			
			for (Bairro bairro : municipio.getBairros()) {
				
				if (!map.containsKey(municipio.getId())) {
					
					map.put(key, new ArrayList<Long>());
					map.get(key).add(bairro.getId());
				}
				else {
					map.get(key).add(bairro.getId());
				}
			}
		}
		
		return map;
	}
		
	@ModelAttribute("districtsNameMappedById")
	public Map<Long, String> districtsNameMappedById() {
			
		List<Bairro> allBairros = bairroService.getAll();
		Map<Long, String> map = new HashMap<Long, String>();
			
		for (Bairro bairro : allBairros) {			
			map.put(bairro.getId(), bairro.getName());
		}
			
		return map;
	}
}
