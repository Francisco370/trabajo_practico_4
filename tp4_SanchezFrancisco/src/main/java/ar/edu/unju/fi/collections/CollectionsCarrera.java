package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionsCarrera {
	private static List<Carrera> carreras = new ArrayList<Carrera>();
	
	/**
	 * devuelve un arrayList de objetos de la clase Carrera
	 * @return
	 */
	public static List<Carrera> getCarreras(){
		if(carreras.isEmpty()) {
			carreras.add(new Carrera(1,"A.P.U",(byte)3,true));
			carreras.add(new Carrera(2,"Ingeniería Informática",(byte)5,true));
			carreras.add(new Carrera(3,"Ingeniería Quimica",(byte)5,false));
		}
		return carreras;
	}
	
	/**
	 * agrega un objeto Carrera al arrayList de carreras
	 * @param carrera
	 */
	public static boolean agregarCarrera(Carrera carrera) {
		return carreras.add(carrera) ? true : false;
	}
	
	/**
	 * elimina un objeto carrera del arrayList de carreras
	 * @param codigoCarrera
	 */
	public static void eliminarCarrera(int codCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getCodCarrera() == codCarrera)
				iterator.remove();
		}
	}
	
	/**
	 * modifica un objeto carrera con los nuevos valores enviado en
	 * @param carrera objeto con los valores de atributis modificados
	 */
	public static void modificarCarrera(Carrera carrera)throws Exception {
		boolean encontrado = false;
		try {
			for(Carrera carre : carreras) {
				if(carre.getCodCarrera() == carrera.getCodCarrera()) {
					carre.setNombre(carrera.getNombre());
					carre.setCantAnios(carrera.getCantAnios());
					carre.setEstado(carrera.isEstado());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("La carrera con codigo "+carrera.getCodCarrera()+" no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * busca un objeto carrera dentro del arrayList, el criterio es por
	 * @param codigo el codigo a buscar en el arrayList carreras
	 */
	public static Carrera buscarCarrera(int codCarrera) {
		Predicate<Carrera> filterCodigo = c -> c.getCodCarrera() == codCarrera;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if(carrera.isPresent()) {
			return carrera.get();
		}else {
			return null;
		}
	}
}
