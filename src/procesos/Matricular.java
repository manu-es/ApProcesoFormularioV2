package procesos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import beans.*;
import util.*;

/**
 * Encapsula el proceso de matriculación del alumno
 */
public class Matricular {

	/**
	 * Información de todos los alumno registrados (de los que se tiene el
	 * correo y la clave en logins.dat)
	 */
	private HashMap<String, String> listaAlumnosRegistrados;

	/**
	 * Información de todos los alumno matriculados (de los que se tiene el
	 * correo y cursos en solicitudes.dat)
	 */
	private ListaAlumnos listaAlumnosMatriculados;

	/**
	 * Información de la localización del archivo logins.dat
	 */
	private String archivoLogins;

	/**
	 * Información de la localización del archivo solicitudes.dat
	 */
	private String archivoSolicitudes;

	/**
	 * Constructor por defecto, lee y crea las lista de alumnos y recibe la información de Contexto
	 */
	public Matricular(HttpServletRequest request)  throws ClassNotFoundException, IOException{
		ServletContext contexto = request.getServletContext();
		this.archivoLogins = contexto.getRealPath("/WEB-INF/logins.dat");
		this.archivoSolicitudes = contexto.getRealPath("/WEB-INF/solicitudes.dat");
		listaAlumnosRegistrados = new HashMap<String, String>();
		listaAlumnosMatriculados = new ListaAlumnos();
		leeArchivos();
	}

	
	/**
	 * Método que lee los archivos y si no existen los crea
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void leeArchivos() throws ClassNotFoundException, IOException{
		if (UtilidadesFicheros.ifExists(archivoLogins))
		try {
			this.listaAlumnosRegistrados = (HashMap<String, String>) UtilidadesFicheros.leeArchivoSerial(archivoLogins);
		} catch (ClassNotFoundException cnfe) {
			throw new ClassNotFoundException("Error al hacer una conversión");
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("No se ha encontrado el archivo logins.dat");
		} catch (IOException ioe) {
			throw new IOException("Error al leer del archivo logins.dat");
		} else{
			UtilidadesFicheros.creaArchivo(archivoLogins);
		}
		if (UtilidadesFicheros.ifExists(archivoSolicitudes))
			try {
				this.listaAlumnosMatriculados = (ListaAlumnos) UtilidadesFicheros.leeArchivoSerial(archivoSolicitudes);
			} catch (ClassNotFoundException cnfe) {
				throw new ClassNotFoundException("Error al hacer una conversión");
			} catch (FileNotFoundException fnfe) {
				throw new FileNotFoundException("No se ha encontrado el archivo solicitudes.dat");
			} catch (IOException ioe) {
				throw new IOException("Error al leer del archivo solicitudes.dat");
			} else{
				UtilidadesFicheros.creaArchivo(archivoSolicitudes);
			}
	}
	
	
	/**
	 * Método que devuelve la lista de alumnos registrados
	 * @return ListaAlumnos
	 */
	public HashMap<String, String> getListaAlumnosRegistrados() {
		return this.listaAlumnosRegistrados;
	}

	/**
	 * Método que almacena un alumno matriculado en cursos en la lista de
	 * alumnos matriculados y almacena la lista en el archivo solicitudes.dat
	 * @param alumno
	 * @param cursos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void guardaAlumnoMatriculado(Alumno alumno, String[] cursos) throws FileNotFoundException, IOException {
		Curso curso = null;
		ListaCursos listaCursos = new ListaCursos();
		for (int i = 0; i < cursos.length; i++) {
			curso = new Curso(cursos[i]);
			listaCursos.add(curso);
		}
		alumno.setCursos(listaCursos);
		this.listaAlumnosMatriculados.put(alumno.getCorreo(), alumno);
		try {
			UtilidadesFicheros.grabaArchivoSerial(archivoSolicitudes, listaAlumnosMatriculados);
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("No se ha encontrado el archivo ");
		} catch (IOException ioe) {
			throw new IOException("Error al grabar en el archivo solicitudes.");
		}
	}

	/**
	 * Método que comprueba si existe un alumno en la lista de alumnos ya matriculados
	 * @param correo
	 * @return Boolean
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean ifExistAlumnoMatriculado(String correo) throws ClassNotFoundException, IOException {
		return listaAlumnosMatriculados.containsKey(correo);
	}
	
	/**
	 * Método que devuelve un alumno matriculado solicitado
	 * @param recibe un String con el correo del alumno
	 * @return Alumno
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Alumno getAlumnoMatriculado(String correo) {
		Alumno alumno = listaAlumnosMatriculados.get(correo);
		return alumno;
	}
	
	/**
	 * Método que comprueba si el correo del alumno está en la lista de los alumnos registrados
	 * @param recibe un String con el correo del alumno
	 * @return true si está
	 */
	public boolean ifExistAlumnoRegistrado(String correo) {
		return listaAlumnosRegistrados.containsKey(correo);
	}

	/**
	 * Método que comprueba si la clave es correcta para un correo dado
	 * @param recibe un String con el correo
	 * @param recibe un String con la clave
	 * @return true si la clave es correcta
	 */
	public boolean compruebaClave(String correo, String clave) {
		boolean correcta = false;
		String claveAlmacenada = listaAlumnosRegistrados.get(correo);
		if (claveAlmacenada.equals(clave)) {
			correcta = true;
		}
		return correcta;
	}

}
