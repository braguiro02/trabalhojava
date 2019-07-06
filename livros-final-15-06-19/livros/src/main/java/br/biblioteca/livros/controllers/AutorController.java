package br.biblioteca.livros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Autor;
import br.biblioteca.livros.services.AutorService;

@RequestMapping("/autores")
@Controller
public class AutorController {
	@Autowired
	AutorService autorService;
	@GetMapping("/list")
	public ModelAndView list() 
	{
		List<Autor> autores = autorService.listarAutores();
		return new ModelAndView("autores/list", "listaAutores", autores);
	}
	
	@GetMapping("/novo")
	public ModelAndView newAutor() 
	{
		ModelAndView modelAndView = new ModelAndView("autores/autor");
		Iterable<Autor> autores = autorService.listarAutores();
		modelAndView.addObject("autores", autores);
		return modelAndView;
	}
	
	@PostMapping(value = "/gravar")
	public ModelAndView create(Autor autor) 
	{

		autorService.salvaAutor(autor);
	   return new ModelAndView("redirect:/autores/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView update(@PathVariable("id") Long id) 
	{
		Autor autor = autorService.buscarAutor(id);
		ModelAndView modelAndView = new ModelAndView("autores/form");
		modelAndView.addObject("autores", autor);
		return modelAndView;
	}

	/*
	 * System.out.println("Alterei o autor com ID: " + id); return new
	 * ModelAndView("redirect:/autores/list");
	 */
	
	@GetMapping("/exclui/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) 
	{
		autorService.apagarAutor(id);
		return new ModelAndView("redirect:/autores/list");
	}
}
