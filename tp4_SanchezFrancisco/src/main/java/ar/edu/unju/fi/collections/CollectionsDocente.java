package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionsDocente {
	private static List<Docente> docentes = new ArrayList<Docente>();
	
	/**
	 * devuelve un arrayList de objetos de la clase Docente
	 * @return
	 */
	public static List<Docente> getDocentes(){
		if(docentes.isEmpty()) {
			docentes.add(new Docente(1234,"Jose","Vilte","13er@gmail.com",388423241));
			docentes.add(new Docente(1311,"Martin","Lopez","13er@gmail.com",388423241));
			docentes.add(new Docente(2412,"Juan","Rodriguez","13er@gmail.com",388423241));
		}
		return docentes;
	}
	
	/**
	 * agrega un objeto Docente al arrayList de docentes
	 * @param docente
	 */
	public static boolean agregarDocente(Docente docente) {
		return docentes.add(docente) ? true : false;
	}
	
	/**
	 * elimina un objeto docente del arrayList de docentes
	 * @param codigoDocente
	 */
	public static void eliminarDocente(int codigoDocente) {
		Iterator<Docente> iterator = docentes.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getLegajo() == codigoDocente) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * modifica un objeto docente con los nuevos valores enviado en
	 * @param docente objeto con los valores de atributos modificados
	 */
	public static void modificarDocente(Docente docente)throws Exception {
		boolean encontrado = false;
		try {
			for(Docente doc : docentes) {
				if(doc.getLegajo() == docente.getLegajo()) {
					doc.setNombre(docente.getNombre());
					doc.setApellido(docente.getApellido());
					doc.setEmail(docente.getEmail());
					doc.setTelefono(docente.getTelefono());
				}
			}
			if(!encontrado) {
				throw new Exception ("El docente con el legajo "+docente.getLegajo()+" no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * busca un objeto docente dentro del arrayList, el criterio es por
	 * @param legajo el legajo a buscar en el arrayList docentes
	 */
	public static Docente buscarDocente(int legajo) {
		Predicate<Docente> filterCodigo = d -> d.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterCodigo).findFirst();
		if(docente.isPresent()) {
			return docente.get();
		}else
			return null;
	}
}
