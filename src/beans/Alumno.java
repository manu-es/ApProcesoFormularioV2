package beans;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * Encapsula el concepto de alumno
 */
@SuppressWarnings("serial")
public class Alumno implements Serializable {

	/**
	 * Información del correo del alumno
	 */
	private String correo;

	/**
	 * Información de los cursos a los que se ha apuntado el alumno
	 */
	private ListaCursos listaCursos;

	/**
	 * Constructor por defecto
	 */
	public Alumno() {
		this.correo = "";
		this.listaCursos=null;
	}

	/**
	 * Método que devuelve el correo del alumno
	 * @return String
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Método que establece el correo
	 * @param correo, recibe un String
	 */
	public void setCorreo(String correo){
		this.correo= correo;
	}
	
	/**
	 * Método que devuelve la lista de los cursos a los que se ha apuntado el alumno
	 * @return ListaCursos
	 */
	public ListaCursos getCursos() {
		return listaCursos;
	}

	/**
	 * Método que añade una lista de los cursos a los que se ha apuntado el
	 * alumno, recibe una lista de cursos
	 * @param listaCursos
	 */
	public void setCursos(ListaCursos listaCursos) {
		this.listaCursos = listaCursos;
	}

	/**
	 * Método que devuelve un String con los cursos a los que se ha matriculado
	 * el alumno
	 * @return String
	 */
	public String getStringCursos() {
		String cursos = "";
		Curso curso;
		ListIterator<Curso> iterador = listaCursos.listIterator();
		while (iterador.hasNext()) {
			curso = (Curso) iterador.next();
			cursos = cursos + curso.getNombre() + ", ";
		}
		return cursos.substring(0, cursos.length() - 2);
	}
}
