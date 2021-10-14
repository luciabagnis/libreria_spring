package com.example.demo.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Editorial;
import com.example.demo.entidad.Libro;

@Repository
public interface libroRepositorio extends JpaRepository<Libro, String> {

	@Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
	public Libro libroTitulo(@Param("titulo")String titulo);
	
	@Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
	public List<Libro> buscarAutor(@Param("nombre")Autor autor);
	
	@Query("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombre")
	public List<Libro> buscarEditorial(@Param("nombre")Editorial editorial);
	
	@Query("SELECT l FROM Libro l WHERE l.anio = :anio")
	public List<Libro> buscarLibro(@Param("anio")Integer anio);
	
	@Query("SELECT l FROM Libro l")
	public ArrayList<Libro> listaLibros();
}
