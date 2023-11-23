package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {
	
	private static String filename = "C:\\Users\\Ruben\\Documents\\librosBinario.bin";
	
	// Método guardar libros / escribir libros
	public void guardarLibrosBinario(List<Libro> libros, String filename) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, true))) {
	        oos.writeObject(libros);
	        oos.close();
	        System.out.println("Libros escritos correctamente en " + filename);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public List<Libro> leerLibrosBinario(String filename) {
	    ArrayList<Libro> libros = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	        libros = (ArrayList<Libro>) ois.readObject();
	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        // Si hay algún error, devolveremos una lista vacía
	        // También puede imprimir el stack trace si es necesario
	    }
	    return libros;
	}
		
	// Métodos para manejar otros tipos de ficheros
}
