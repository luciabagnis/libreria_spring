package com.example.demo.servicio;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entidad.Autor;
import com.example.demo.error.ErrorServicio;
import com.example.demo.repositorio.autorRepositorio;

@Service
public class autorServicio {

	@Autowired
	public autorRepositorio ar;

	// CREA AUTOR NUEVO
	@Transactional
	public Autor crearAutor(String nombre) throws ErrorServicio {

		// Creo un autor y llamo al repositorio para que busque según nombre
		Autor aux = ar.buscarNombre(nombre);

		// En el caso de que no exista un autor con ese nombre se crea
		if (aux == null) {

			Autor autor = new Autor();
			autor.setNombre(nombre);
			autor.setAlta(true);

			validacion(nombre);

			return ar.save(autor);
		} else {
			throw new ErrorServicio("El autor ya se ingreso");
		}
	}

	public Autor editar(String id, String nombre) throws ErrorServicio {

		Autor autor = ar.getById(id);

		autor.setNombre(nombre);
		ar.save(autor);
		return autor;
	}

	// METODO ALTA
	public Autor alta(String id) throws ErrorServicio {

		Autor autor = ar.getById(id);

		autor.setAlta(true);
		ar.save(autor);
		return autor;

	}

	public Autor baja(String id) throws ErrorServicio {
		Autor autor = ar.getById(id);
		autor.setAlta(false);
		ar.save(autor);
		
		return autor;
	}

	public void eliminar(String id) throws ErrorServicio {

		Optional<Autor> autor = ar.findById(id);
		if (autor.isEmpty() || autor == null) {
			throw new ErrorServicio("No se encuentra el autor");
		} else {
			ar.deleteById(id);
		}

	}

	// LISTAR AUTORES
	public ArrayList<Autor> listaAutores() {

		return ar.autores();
	}

	public Autor buscar(String id) {
		Autor autor = ar.getById(id);
		return autor;
	}

	// METODO TIRA ERROR SI NOMBRE ES NULO
	public void validacion(String nombre) throws ErrorServicio {

		if (nombre.isBlank() || nombre.isEmpty() || nombre == null) {

			throw new ErrorServicio("Se debe ingresar el nombre del autor");
		}

	}

}