package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionsMateria {
	private static List<Materia> materias = new ArrayList<Materia>();
	
	/**
	 * devuelve un arrayList de objetos de la clase Materia
	 * @return
	 */
	public static List<Materia> getMaterias(){
		if(materias.isEmpty()) {
			List<Docente> docentes = CollectionsDocente.getDocentes();
			List<Carrera> carreras = CollectionsCarrera.getCarreras();
			materias.add(new Materia(1,"Base de Datos","PRIMERO",(byte)3,"Virtual",docentes.get(0),carreras.get(0)));
			materias.add(new Materia(2,"Herramientas de Informaticas I","PRIMERO",(byte)3,"Virtual",docentes.get(1),carreras.get(0)));
			materias.add(new Materia(3,"Programacion Estructurada","PRIMERO",(byte)2,"Virtual",docentes.get(2),carreras.get(0)));
			materias.add(new Materia(4,"Algebra I","SEGUNDO",(byte)2,"Precensial",docentes.get(1),carreras.get(0)));
		}
		return materias;
	}
	
	/**
	 * agrega un objeto Materia al arrayList de materias
	 * @param materia
	 */
	public static boolean agregarMateria(Materia materia) {
		return materias.add(materia) ? true : false;
	}
	
	/**
	 * elimina un objeto materia del arrayList de materias
	 * @param codigoMateria
	 */
	public static void eliminarMateria(int codMateria) {
		Iterator<Materia> iterator = materias.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getCodMateria() == codMateria)
				iterator.remove();
		}
	}
	
	/**
	 * modifica un objeto materia con los nuevos valores enviado en
	 * @param materia objeto con los valores de atributos modificados
	 */
	public static void modificarMateria(Materia materia)throws Exception {
		boolean encontrado = false;
		try {
			for(Materia mate : materias) {
				if(mate.getCodMateria() == materia.getCodMateria()) {
					mate.setNombre(materia.getNombre());
					mate.setCurso(materia.getCurso());
					mate.setCantidadHoras(materia.getCantidadHoras());
					mate.setModalidad(materia.getModalidad());
					mate.setDocente(materia.getDocente());
					mate.setCarrera(materia.getCarrera());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("La materia con codigo "+materia.getCodMateria()+" no existe");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * busca un objeto materia dentro del arrayList, el criterio es por
	 * @param codigo el codigo a buscar en el arrayList materias
	 */
	public static Materia buscarMateria(int codigo) {
		Predicate<Materia> filterCodigo = m -> m.getCodMateria() == codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if(materia.isPresent()) {
			return materia.get();
		}else {
			return null;
		}
	}
}
