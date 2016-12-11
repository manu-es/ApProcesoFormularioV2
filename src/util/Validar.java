package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsula el soporte para la validadción de los formatos de los datos de
 * entrada
 */
public class Validar {

	/**
	 * Proceso que valida el formato de la clave, debe tener entre 8 y 12
	 * caracteres.
	 * 
	 * @param clave,
	 *            con el String de la clave
	 * @return boolean
	 */
	public static boolean clave(String clave) {
		boolean correcto = true;
		if (clave.length() < 8 || clave.length() > 12) {
			correcto = false;
		}
		return correcto;
	}

	/**
	 * Proceso que valida el formato del correo electrónico
	 * 
	 * @param correo,
	 *            con el String de la clave
	 * @return boolean
	 */
	public static boolean correo(String correo) {
		boolean correcto = true;
		Pattern pat = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mat = pat.matcher(correo);
		if (!mat.matches()) {
			correcto = false;
		}
		return correcto;
	}

	/**
	 * Proceso que verifica que se haya seleccionado algún curso
	 * 
	 * @param cursos,
	 *            Array con los cursos seleccionados
	 * @return boolean
	 */
	public static boolean cursos(String[] cursos) {
		boolean correcto = true;
		if (cursos == null) {
			correcto = false;
		}
		return correcto;
	}
}
