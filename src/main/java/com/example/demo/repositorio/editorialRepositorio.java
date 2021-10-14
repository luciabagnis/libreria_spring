package com.example.demo.repositorio;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Editorial;

@Repository
public interface editorialRepositorio extends JpaRepository<Editorial, String> {

	@Query("SELECT e FROM Editorial e WHERE e.nombre =:nombre")
	public Editorial buscarNombre(@Param("nombre")String nombre);
	
	@Query("SELECT e FROM Editorial e")
	public ArrayList<Editorial> listaEditoriales();
	
}
