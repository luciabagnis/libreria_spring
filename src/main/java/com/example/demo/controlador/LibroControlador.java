package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.entidad.Libro;
import com.example.demo.error.ErrorServicio;
import com.example.demo.servicio.libroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

	@Autowired
	public libroServicio libroS;

	@GetMapping()
	public String libros(ModelMap modelo) {
		List<Libro> listaLibros = libroS.listaLibros();
		modelo.addAttribute("libros", listaLibros);
		return "libro.html";
	}

	@GetMapping("/cargar")
	public String carga() {
		return "form-libro.html";
	}

	@PostMapping("/cargar")
	public String cargarLibros(@RequestParam String titulo,@RequestParam Integer isbn,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam String autor,@RequestParam String editorial) {

		try {
			libroS.crearLibro(titulo,isbn,anio, ejemplares, autor, editorial);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ("redirect:/libro");

	}
	
	@GetMapping("/eliminar/{id}")
	public String removerAutor(@PathVariable String id) {
		try {
			libroS.eliminar(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("redirect:/libro");
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable String id) {
		return "edit-libro";
	}

	@PostMapping("/editar/{id}")
	public String editarLibro(@PathVariable String id,@RequestParam String titulo,@RequestParam Integer isbn,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam String autor,@RequestParam String editorial) {
		try {
			libroS.editar(id, titulo, isbn, anio, ejemplares, autor, editorial);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/libro";
	}
	
	
	@GetMapping("alta/{id}")
	public String alta(@PathVariable String id) {
		libroS.alta(id);
		return "redirect:/libro";
	}
	
	@GetMapping("baja/{id}")
	public String baja(@PathVariable String id) {
		libroS.baja(id);
		return "redirect:/libro";
	}
		
}
