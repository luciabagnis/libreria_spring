package com.example.demo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Editorial;
import com.example.demo.error.ErrorServicio;
import com.example.demo.repositorio.editorialRepositorio;

import java.util.ArrayList;



@Service
public class editorialServicio {

	@Autowired
	public editorialRepositorio er;

	// CREAR NUEVA EDITORIAL
	public Editorial crearEditorial(String nombre) throws ErrorServicio {

		Editorial aux = er.buscarNombre(nombre);

		if (aux == null) {
			Editorial editorial = new Editorial();
			editorial.setNombre(nombre);
			editorial.setAlta(true);

			er.save(editorial);
			return editorial;
		} else {
			throw new ErrorServicio("Ya existe la editorial");
		}

	}
	
	//EDITAR
	public Editorial editar(String nombre,String id)throws ErrorServicio{
		
		Editorial editorial = er.getById(id);
		
		if (editorial != null ) {
			editorial.setNombre(nombre);
			er.save(editorial);
			return editorial;
		}else {
			throw new ErrorServicio("No existe la editorial");
		}
		
		
	}
	
	
	
	
	
	

	// METODO ALTA

	public Editorial alta(String id) {

		Editorial editorial = er.getById(id);

		editorial.setAlta(true);
		er.save(editorial);

		return editorial;

	}

	// METODO BAJA

	public Editorial baja(String id) {
		Editorial editorial = er.getById(id);

		editorial.setAlta(false);
		er.save(editorial);

		return editorial;
	}
	
	
	//ELIMINAR
	public void eliminar(String id) throws ErrorServicio {
		
		Optional<Editorial> editorial = er.findById(id);
		if (editorial.isEmpty() || editorial == null) {
			throw new ErrorServicio("No se encuentra editorial");
		}else {
			er.deleteById(id);
		}
	}
		
	//LISTAR EDITORIALES
		
	public ArrayList<Editorial> listarEditoriales(){
		return er.listaEditoriales();
	}
		
	//BUSCAR
	
	public Editorial buscarNombre(String nombre) {
		Editorial editorial = er.buscarNombre(nombre);
		er.save(editorial);
		return editorial;
	}
		
		
	}
	
	
	
	
	
	
	
