package br.biblioteca.livros.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.entidades.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {	

}