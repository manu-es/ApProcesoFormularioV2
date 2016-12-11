package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Encapsula el soporte para la entrada desde archivos
 */
public class UtilidadesFicheros {

    /**
     * Proceso que encargado de leer un archivo, recibe un String con el nombre del archivo y
     * devuelve en ArrayList que contiene cada una de las lineas de texto del archivo.
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<String> leeFichero(String nombreArchivo) throws FileNotFoundException, IOException {
        File archivo = new File(nombreArchivo);
        return leeFichero(archivo);
    }

    /**
     * Proceso que encargado de leer un archivo, recibe un archivo(File) con el nombre del archivo y
     * devuelve en ArrayList que contiene cada una de las lineas de texto del archivo.
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<String> leeFichero(File archivo) throws FileNotFoundException, IOException {
        BufferedReader bf = null;
        String linea;
        ArrayList<String> lineas = new ArrayList<String>();
        if (archivo.exists()) {
            if (archivo.canRead()) {
                bf = new BufferedReader(new FileReader(archivo));
                while ((linea = bf.readLine()) != null) {
                    lineas.add(linea);
                }
            } else{
            	throw new IOException();
            }
        } else{
        	throw new FileNotFoundException();
        }
        if (bf != null) {
            bf.close();
        }
        return lineas;
    }

    /**
     * Proceso que encargado de escribir en un archivo, recibe un String con el nombre del archivo y
     * un String con los datos a guardar en el archivo.
     * @param nombreArchivo
     * @param dato
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void grabaArchivo(String nombreArchivo, String dato) throws FileNotFoundException, IOException {
        File archivo = new File(nombreArchivo);
        grabaArchivo(archivo, dato);
    }

    /**
     * Proceso que encargado de escribir en un archivo, recibe un archivo(File) con el nombre del archivo y
     * un String con los datos a guardar en el archivo.
     * @param archivo
     * @param dato
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void grabaArchivo(File archivo, String dato) throws FileNotFoundException, IOException {
        PrintWriter pw = null;
        if (archivo.exists()) {
            if (archivo.canWrite()) {
                FileWriter fr = new FileWriter(archivo);
				pw = new PrintWriter(fr);
				pw.println(dato);
            } else {
            	throw new IOException();
            }
        } else{
        	throw new FileNotFoundException();
        }
        if (pw != null) {
            pw.close();
        }
    }

    /**
     * Proceso encargado de borrar un archivo, recibe el string del nombre del archivo.
     * @param nombreArchivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void borraArchivo(String nombreArchivo) throws FileNotFoundException, IOException {
        File archivo = new File(nombreArchivo);
        borraArchivo(archivo);
    }

    /**
     * Proceso encargado de borrar un archivo, recibe el archivo.
     * @param archivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void borraArchivo(File archivo) throws FileNotFoundException, IOException {
        if (archivo.exists()) {
            if (archivo.canWrite()) {
                archivo.delete();
            }
        }
    }

    /**
     * Proceso encargado de crear un archivo, recibe el string del nombre del archivo.
     * @param nomArchivo
     * @throws IOException
     */
    public static void creaArchivo(String nomArchivo) throws IOException {
        File archivo = new File(nomArchivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    /**
     * Proceso de comprobar si existe un archivo, recibe el string con el nombre del archivo
     * @param nomArchivo
     * @return
     * @throws IOException
     */
    public static boolean ifExists(String nomArchivo) throws IOException {
        File archivo = new File(nomArchivo);
        return archivo.exists();
    }

    /**
     * Proceso encargado de grabar un objeto serializado, recibe el string con el nombre del archivo
     * en el que debe ser guardado y el objeto
     * @param nombreArchivo
     * @param dato
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void grabaArchivoSerial(String nombreArchivo, Object dato) throws FileNotFoundException, IOException {
        File archivo = new File(nombreArchivo);
        grabaArchivoSerial(archivo, dato);
    }

    /**
     * Proceso encargado de grabar un objeto serializado, recibe el archivo en el que debe ser guardado
     * y el objeto
     * @param archivo
     * @param dato
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void grabaArchivoSerial(File archivo, Object dato) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = null;
        if (archivo.exists()) {
            if (archivo.canWrite()) {
                oos = new ObjectOutputStream(new FileOutputStream(archivo));
                oos.writeObject(dato);
            } else {
            	throw new IOException();
            }
        } else {
        	throw new FileNotFoundException();
        }
        if (oos != null) {
            oos.close();
        }
    }

    /**
     * Proceso encargado de leer de un archivo serializado, recibe el string con el nombre del
     * archivo y devuelve el objeto serializado.
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Object leeArchivoSerial(String nombreArchivo) throws ClassNotFoundException, FileNotFoundException,
                                                                IOException {
        File archivo = new File(nombreArchivo);
        return leeArchivoSerial(archivo);
    }

    /**
     * Proceso encargado de leer un archivo serializado, recibe el archivo y devuelve el objeto.
     * @param archivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Object leeArchivoSerial(File archivo) throws ClassNotFoundException, FileNotFoundException, IOException {
        Object objeto = null;
        ObjectInputStream ois = null;
        if (archivo.exists()) {
            if (archivo.canRead()) {
                ois = new ObjectInputStream(new FileInputStream(archivo));
                objeto = ois.readObject();
            } else {
            	throw new IOException();
            }
        } else {
        	throw new FileNotFoundException();
        }
        if (ois != null) {
            ois.close();
        }
        return objeto;
    }

    /**
     * Lee un fichero de propiedades y si lo puede leer correctamente devuelve un objeto Properties,
     * sino devuelve null
     * @param fichero
     * @return
     */
    public static Properties leerFicheroPropiedades(String fichero) throws FileNotFoundException, IOException {
        Properties propiedades = new Properties();
        FileInputStream entrada = null;

        try {
            entrada = new FileInputStream(fichero);
        } catch (FileNotFoundException e) {
            propiedades = null;
            throw e;
        }

        // cargamos el archivo de propiedades
        try {
            propiedades.load(entrada);
        } catch (IOException e) {
            propiedades = null;
            throw e;
        }

        return propiedades;
    }
}

