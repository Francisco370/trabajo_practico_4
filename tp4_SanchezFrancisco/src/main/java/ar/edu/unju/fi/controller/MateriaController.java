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
import ar.edu.unju.fi.collections.CollectionsDocente;
import ar.edu.unju.fi.collections.CollectionsMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;


@Controller
@RequestMapping ("/materia")
public class MateriaController {
	@Autowired
	private Materia materia;
	
	@Autowired
	private Docente docente;
	
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		model.addAttribute("materias", CollectionsMateria.getMaterias());
		model.addAttribute("titulo","Materias");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "materias";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {
		boolean edicion = false;
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("materia", materia);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva materia");
		return "materia";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia")Materia materia, Model model) {
		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;
		carrera = CollectionsCarrera.buscarCarrera(materia.getCarrera().getCodCarrera());
		materia.setCarrera(carrera);
		docente = CollectionsDocente.buscarDocente(materia.getDocente().getLegajo());
		materia.setDocente(docente);
		boolean exito = CollectionsMateria.agregarMateria(materia);
		if(exito) {
			mensaje = "Materia guardada con exito";
		}else {
			mensaje = "Materia no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("materias", CollectionsMateria.getMaterias());
		return modelView;
	}
	
	@GetMapping("/modificar/{codMateria}")
	public String getModificarMateriasPage(Model model, @PathVariable(value="codMateria")int codigo) {
		Materia materiaEncontrada = new Materia();
		boolean edicion = true;
		materiaEncontrada = CollectionsMateria.buscarMateria(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo", "Modificar Materia");
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		return "materia";
	}
	
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia")Materia materia, Model model) {
		docente = CollectionsDocente.buscarDocente(materia.getDocente().getLegajo());
		carrera = CollectionsCarrera.buscarCarrera(materia.getCarrera().getCodCarrera());
		materia.setDocente(docente);
		materia.setCarrera(carrera);
		boolean exito = false;
		String mensaje = "";
		try {
			CollectionsMateria.modificarMateria(materia);
			mensaje = "La Materia con codigo " + materia.getCodMateria() + " fue modificada con exito!";
			exito = true;
		}catch(Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("materias", CollectionsMateria.getMaterias());
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}
	
	@GetMapping("/eliminar/{codMateria}")
	public String eliminarMateria(@PathVariable(value="codMateria")int cod) {
		CollectionsMateria.eliminarMateria(cod);
		return "redirect:/materia/listado";
	}
}
