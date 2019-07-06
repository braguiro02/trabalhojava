package br.biblioteca.livros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Autor;
import br.biblioteca.livros.entidades.Livro;
import br.biblioteca.livros.services.AutorService;
import br.biblioteca.livros.services.LivroService;


@RequestMapping("/livros")
@Controller
public class LivroController {
	@Autowired
	 LivroService livroService;
	@Autowired
	 AutorService autorService;
	
	/*public ModelAndView getLivros()
	{
		Iterable<Livro> livros = livroRepository.findAll();
		
		for(Livro livro:livros)
		{
			System.out.println(livro.getNome());
		}
		return new ModelAndView("index");
	}
	*/
	@GetMapping("/list")
	public ModelAndView list() 
	{
		// System.out.println("Listei os livros");
		List<Livro> livros = livroService.listarLivros();
		/*
		 * for(Livro livro : livros) { System.out.println("Livros: "+ livro.getNome());
		 * }
		 */
		return new ModelAndView("livros/list", "listaLivros", livros);
	}
	
	@GetMapping("/novo")
	public ModelAndView newBook(@ModelAttribute Livro livro) 
	{
		ModelAndView modelAndView = new ModelAndView("livros/livro");
		Iterable<Autor> autores = autorService.listarAutores();
		modelAndView.addObject("autores", autores);
		return modelAndView;
	}
	
	@PostMapping(value = "/gravar")
	public ModelAndView create(Livro livro) 
	{
		livroService.salvaLivro(livro);

	   return new ModelAndView("redirect:/livros/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView update(@PathVariable("id") Long id) 
	{
		Livro livro = livroService.buscarLivro(id);
		List<Autor> autores = autorService.listarAutores();
		ModelAndView modelAndView = new ModelAndView("livros/livro");
		modelAndView.addObject("autores", autores);
		modelAndView.addObject("livro", livro);
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) 
	{
		livroService.apagarLivro(id);
		return new ModelAndView("redirect:/livros/list");
	}
}