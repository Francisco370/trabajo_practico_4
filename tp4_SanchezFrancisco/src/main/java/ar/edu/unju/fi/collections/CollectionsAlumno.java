package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionsAlumno {
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	/**
	 * devuelve un arrayList de objetos de la clase Alumno
	 * @return
	 */
	public static List<Alumno> getAlumnos(){
		if(alumnos.isEmpty()) {
			alumnos.add(new Alumno(43527360,"Francisco","Sanchez","ignacio2162001@gmail.com","38828388",LocalDate.of(1980, 11, 23),"San Pedrito 1999",5412));
			alumnos.add(new Alumno(21442245,"Juan","Lopez","ac1620@gmail.com","388425536",LocalDate.of(2001, 4, 13),"San Cayetano 512",1162));
			alumnos.add(new Alumno(31356632,"Dylan","Lopez","lp216@gmail.com","388665478",LocalDate.of(2005, 11, 3),"Las Heras 111",2211));
		}
		return alumnos;
	}
	
	/**
	 * agrega un objeto Alumno al arrayList de alumnos
	 * @param alumno
	 */
	public static boolean agregarAlumno(Alumno alumno) {
		return alumnos.add(alumno) ? true : false;
	}
	
	/**
	 * elimina un objeto alumno del arrayList de alumnos
	 * @param dniAlumno
	 */
	public static void eliminarAlumno(Integer luAlumno) {
		Iterator<Alumno> iterator = alumnos.iterator();
		while(iterator.hasNext()) {
			Alumno alumno = iterator.next();
			if (alumno.getLu().equals(luAlumno)) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * modifica un objeto alumno con los nuevos valores enviado en
	 * @param alumno objeto con los valores de atributos modificados
	 */
	public static void modificarAlumno(Alumno alumno)throws Exception {
		boolean encontrado = false;
		try {
			for(Alumno alum : alumnos) {
				if(alum.getLu().equals(alumno.getLu())) {
					alum.setNombre(alumno.getNombre());
					alum.setApellido(alumno.getApellido());
					alum.setEmail(alumno.getEmail());
					alum.setTeléfono(alumno.getTeléfono());
					alum.setFechaNacimiento(alumno.getFechaNacimiento());
					alum.setDomicilio(alumno.getDomicilio());
					alum.setLu(alumno.getLu());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("El alumno con L.U "+alumno.getLu()+" no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * busca un objeto alumno dentro del arrayList, el criterio es por
	 * @param dni el dni a buscar en el arrayList alumnos
	 */
	public static Alumno buscarAlumno(Integer lu) {
		Predicate<Alumno> filterLu = a -> a.getLu().equals(lu);
		Optional<Alumno> alumno = alumnos.stream().filter(filterLu).findFirst();
		if(alumno.isPresent()) {
			return alumno.get();
		}else {
			return null;
		}
	}
}
