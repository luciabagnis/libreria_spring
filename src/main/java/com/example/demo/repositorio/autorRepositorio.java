package com.example.demo.repositorio;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Autor;

@Repository
public interface autorRepositorio extends JpaRepository<Autor, String> {

	@Query("SELECT a FROM Autor a WHERE a.nombre =:nombre")
	public Autor buscarNombre(@Param("nombre")String nombre);

	@Query("SELECT a FROM Autor a")
	public ArrayList<Autor> autores();
	
}
