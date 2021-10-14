package com.example.demo.servicio;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Editorial;
import com.example.demo.entidad.Libro;
import com.example.demo.error.ErrorServicio;
import com.example.demo.repositorio.autorRepositorio;
import com.example.demo.repositorio.editorialRepositorio;
import com.example.demo.repositorio.libroRepositorio;

@Service
public class libroServicio {

	@Autowired
	public libroRepositorio lr;
	@Autowired
	public autorRepositorio ar;
	@Autowired
	public editorialRepositorio ed;

	public Libro crearLibro(String titulo,Integer isbn, Integer anio, Integer ejemplares, String autor,String editorial) throws ErrorServicio {
		Libro aux = lr.libroTitulo(titulo);
		
		if (aux == null) {
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setIsbn(isbn);
		libro.setAnio(anio);
		libro.setAlta(true);
		libro.setEjemplares(ejemplares);
		libro.setEjemplaresPrestados(0);
		libro.setEjemplaresRestantes(ejemplares);
		
		lr.save(libro);


		Autor auxiliar = ar.buscarNombre(autor);
		if (auxiliar == null) {
			auxiliar = new Autor();
			libro.setAutor(auxiliar);
			auxiliar.setAlta(true);
			auxiliar.setNombre(autor);
			ar.save(auxiliar);
			lr.save(libro);
		} else {
			libro.setAutor(auxiliar);
			ar.save(auxiliar);
			lr.save(libro);
		}
		
		Editorial auxi = ed.buscarNombre(editorial);
		if (auxi == null) {
			auxi = new Editorial();
			libro.setEditorial(auxi);
			auxi.setAlta(true);
			auxi.setNombre(editorial);
			ed.save(auxi);
			lr.save(libro);
		} else {
			libro.setEditorial(auxi);
			auxi.setNombre(editorial);
			ed.save(auxi);
			lr.save(libro);
		}
		
				
		return lr.save(libro);
		}else {
			throw new ErrorServicio("El libro ya esta registrado");
		}

	}

	// LIBROS
	public ArrayList<Libro> listaLibros() {

		
		return lr.listaLibros();

	}
	
	//EDITAR
	
	public Libro editar(String id, String titulo,Integer isbn, Integer anio, Integer ejemplares, String autor,String editorial) throws ErrorServicio {
		Libro libro = lr.getById(id);
		
		if (libro != null) {
			libro.setTitulo(titulo);
			libro.setIsbn(isbn);
			libro.setEjemplares(ejemplares);
			
			Autor auxiliar = ar.buscarNombre(autor);
			if (auxiliar == null) {
				auxiliar = new Autor();
				libro.setAutor(auxiliar);
				auxiliar.setAlta(true);
				auxiliar.setNombre(autor);
				ar.save(auxiliar);
				lr.save(libro);
			} else {
				libro.setAutor(auxiliar);
				auxiliar.setNombre(autor);
				lr.save(libro);
			}
			
			Editorial auxi = ed.buscarNombre(editorial);
			if (auxi == null) {
				auxi = new Editorial();
				libro.setEditorial(auxi);
				auxi.setAlta(true);
				auxi.setNombre(editorial);
				ed.save(auxi);
				lr.save(libro);
			} else {
				libro.setEditorial(auxi);
				auxi.setNombre(editorial);
				ed.save(auxi);
				lr.save(libro);
			}
			return lr.save(libro);
		}else {
			throw new ErrorServicio("No existe el libro");

		}
		
		
	}

	public void eliminar(String id) throws ErrorServicio {
		
		Optional<Libro> libro = lr.findById(id);
		if (libro.isEmpty() || libro == null) {
			throw new ErrorServicio("No se encuentra libro");
		}else {
			lr.deleteById(id);
		}
		
	}
	
	public Libro alta(String id) {
		
		Libro libro = lr.getById(id);
		
		libro.setAlta(true);
		
		return lr.save(libro);
				
	}
	
	public Libro baja(String id) {
		Libro libro = lr.getById(id);
		
		libro.setAlta(false);
		return lr.save(libro);
	}
	
	
	
	
	
	// VALIDACION
	public void validar(String titulo, Integer anio, String id, Integer ejemplares, Autor autor, Editorial editorial)
			throws ErrorServicio {
		if (titulo.isEmpty() || titulo == null) {
			throw new ErrorServicio("Ingresar Título");
		}
		if (anio == null) {
			throw new ErrorServicio("Ingresar Año");
		}

		if (ejemplares == null) {
			throw new ErrorServicio("Ingresar Ejemplares");
		}

		if (autor == null) {
			throw new ErrorServicio("Ingresar Autor");
		}
		if (editorial == null) {
			throw new ErrorServicio("Ingresar Editorial");
		}

	}

}
