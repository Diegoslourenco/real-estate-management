package com.gft.book.controller;

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

import com.gft.book.filter.CategoriaFilter;
import com.gft.book.model.Categoria;
import com.gft.book.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	private static final String CADASTRO_VIEW = "CategoriaCadastro";
	private static final String BUSCA_VIEW = "CategoriaBusca";
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/novo")
	public ModelAndView novaCategoria() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("categoria", new Categoria());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(CADASTRO_VIEW);
			
			return mv;
		}
		
		categoriaService.save(categoria);
		
		mv = new ModelAndView("redirect:/categorias/novo");
		attributes.addFlashAttribute("message", "Categoria salva com sucesso");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute("filter") CategoriaFilter categoriaFilter) {	
		List<Categoria> allCategorias = categoriaService.get(categoriaFilter);
		
		ModelAndView mv = new ModelAndView(BUSCA_VIEW);
		mv.addObject("categorias", allCategorias);
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView update(@PathVariable("id") Categoria categoria) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(categoria);
		
		return mv;
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		categoriaService.delete(id);
		
		attributes.addFlashAttribute("message", "Categoria removida com sucesso");
		
		return "redirect:/categorias";
	}
}