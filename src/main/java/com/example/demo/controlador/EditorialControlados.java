package com.example.demo.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Editorial;
import com.example.demo.error.ErrorServicio;
import com.example.demo.servicio.editorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialControlados {

	@Autowired
	public editorialServicio editorialS;

	@GetMapping()
	public String listaEditorial(ModelMap modelo) {
		ArrayList<Editorial> editorial = editorialS.listarEditoriales();
		modelo.addAttribute("editorial", editorial);
		return "editorial.html";
	}
	
	@GetMapping("/cargar")
	public String cargarEditorial() {
		return "form-editorial.html";
	}
	
	@PostMapping("/cargar")
	public String nuevaEditorial(@RequestParam String nombre)throws ErrorServicio {
		try {
			editorialS.crearEditorial(nombre);
		}catch(ErrorServicio e) {
			e.printStackTrace();
		}
		return "redirect:/editorial";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable String id) {
		return "edit-editorial.html";
	}
	
	@PostMapping("/editar/{id}")
	public String editarNombre(@PathVariable String id,@RequestParam String nombre) {
		try {
			editorialS.editar(nombre, id);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"redirect:/editorial";

	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		try {
			editorialS.eliminar(id);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"redirect:/editorial";
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
		editorialS.alta(id);
		return "redirect:/editorial";
	}
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
		editorialS.baja(id);
		return "redirect:/editorial";
	}
	
	
	
	
	
	
	
}
