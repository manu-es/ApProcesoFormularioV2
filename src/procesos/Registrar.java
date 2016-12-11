package procesos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import beans.*;
import util.UtilidadesFicheros;

/**
 * Encapsula el proceso de almacenar(registrar) un nuevo alumno en el archivo logins.dat
 */
public class Registrar {
	
	/**
	 * Información de todos los alumno registrados (de los que se tiene el correo y la clave en logins.dat)
	 */
	private HashMap<String, String> listaAlumnosRegistrados;
	
	/**
	 * Información de la localización del archivo logins.dat
	 */
	private String archivoLogins;
	
	/**
	 * Constructor, recibe la lista de los alumnos registrados y la información de Contexto.
	 * @param listaAlumnosRegistrados
	 */
	public Registrar(HashMap<String, String> listaAlumnosRegistrados, HttpServletRequest request){
		this.listaAlumnosRegistrados = listaAlumnosRegistrados;
		ServletContext contexto = request.getServletContext();
		this.archivoLogins = contexto.getRealPath("/WEB-INF/logins.dat");
	}
	
	/**
	 * Método que almacena un alumno en la lista de alumnos registrados
	 * @param correo
	 * @param clave
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void RegistraAlumno(String correo, String clave) throws FileNotFoundException, IOException{
		listaAlumnosRegistrados.put(correo, clave);
		guardaListaAlumnosRegistrados();
	}
	
	/**
	 * Método que guarda la lista de alumnos registrados en el archivo logins.dat
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void guardaListaAlumnosRegistrados() throws FileNotFoundException, IOException{
		try {
		UtilidadesFicheros.grabaArchivoSerial(archivoLogins, listaAlumnosRegistrados);
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("No se ha encontrado el archivo ");
		} catch (IOException ioe) {
			throw new IOException("Error al grabar en el archivo.");
		}
	}

}
