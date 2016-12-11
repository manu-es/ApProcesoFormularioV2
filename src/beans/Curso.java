package beans;

import java.io.Serializable;

/**
 * Encapsula el concepto de curso
 */
@SuppressWarnings("serial")
public class Curso implements Serializable {

	/**
	 * Información del nombre del curso
	 */
	private String nombre;

	/**
	 * Constructor del curso, recibe String con el nombre del curso
	 * @param nombre
	 */
	public Curso(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve el nombre del curso
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
}
