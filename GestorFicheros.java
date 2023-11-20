package biblioteca;

import java.io.*;
import java.util.List;

public class GestorFicheros {
	public void guardarLibrosBinario(List<Libro> libros, String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(libros);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Libro> leerLibrosBinario(String filename) {
// Implementar la lectura de ficheros binarios
	}
// Métodos para manejar otros tipos de ficheros
}
