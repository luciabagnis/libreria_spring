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

import com.example.demo.entidad.Autor;
import com.example.demo.error.ErrorServicio;
import com.example.demo.servicio.autorServicio;

@Controller
@RequestMapping("/autores")
public class AutorControlador {

	@Autowired
	public autorServicio autorS;

	@GetMapping()
	public String listaAutores(ModelMap modelo) {
		List<Autor> autores = autorS.listaAutores();
		modelo.addAttribute("autor", autores);
		return "autor.html";
	}

	@GetMapping("/cargar")
	public String cargar() {
		return "form-autor.html";
	}

	@PostMapping("/cargar")
	public String cargar(@RequestParam String nombre) {
		try {
			autorS.crearAutor(nombre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("redirect:/autores");
	}

	@GetMapping("/eliminar/{id}")
	public String removerAutor(@PathVariable String id) {
		try {
			autorS.eliminar(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("redirect:/autores");
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable String id) {
		return "edit-autor";
	}

	@PostMapping("/editar/{id}")
	public String editarAutor(@PathVariable String id, @RequestParam String nombre) {
		try {
			autorS.editar(id, nombre);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/autores";
	}
	
	
	@GetMapping("/alta/{id}")
	public String altaAutor(@PathVariable String id) {
		try {
			autorS.alta(id);
			return "redirect:/autores";
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
		try {
			autorS.baja(id);
			return "redirect:/autores";
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
	
	}
	
	


}
