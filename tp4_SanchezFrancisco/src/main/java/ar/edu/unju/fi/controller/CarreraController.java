package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionsCarrera;
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "carreras";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva carrera");
		return "carrera";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
		ModelAndView modelView = new ModelAndView("carreras");
		String mensaje;
		carrera.setEstado(true);
		boolean exito = CollectionsCarrera.agregarCarrera(carrera);
		if(exito) {
			mensaje = "Carrera guardada con exito!";
		}else {
			mensaje = "Carrera no se pudo guardar";
		}
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("carreras", CollectionsCarrera.getCarreras());
		return modelView;
	}
	
	@GetMapping("/modificar/{codCarrera}")
	public String getModificarCarrerasPage(Model model, @PathVariable(value="codCarrera")int codigo) {
		Carrera carreraEncontrada = new Carrera();
		boolean edicion = true;
		carreraEncontrada = CollectionsCarrera.buscarCarrera(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada);
		model.addAttribute("titulo", "Modificar Carrera");
		return "carrera";
	}
	
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
		boolean exito = false;
		String mensaje="";
		try {
			CollectionsCarrera.modificarCarrera(carrera);
			mensaje = "La carrera con codigo "+carrera.getCodCarrera()+" fue modificado con exito";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		return "carreras";
	}
	
	@GetMapping("/eliminar/{codCarrera}")
	public String eliminarCarrera(@PathVariable(value="codCarrera")int codigo) {
		CollectionsCarrera.eliminarCarrera(codigo);
		return "redirect:/carrera/listado";
	}
}
