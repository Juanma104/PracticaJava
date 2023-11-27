package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorFicheros {

	
    // Método guardar libros / escribir libros
    public void guardarLibrosBinario(List<Libro> libros, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        	// Escribimos el objeto libro a la lista de libros haciendo uso de ObjectOutputStream
            oos.writeObject(libros);
            System.out.println("Libros escritos correctamente en " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Método leer libros
    public List<Libro> leerLibrosBinario(String filename) {
    	// Creamos una lista que contendra los libros que vamos a leer
        List<Libro> libros = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
        	// Leemos el objeto libro de la lista de libros haciendo uso de ObjectInputStream
            libros = (List<Libro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return libros;
    }

    
    // Método eliminar libros
    public void eliminarLibrosBinario(String filename, int idLibroBorrar) {
        try {
            // Leer la lista de libros que hay en el fichero
            List<Libro> librosLeidos = leerLibrosBinario(filename);

            // Creamos un iterador para recorrer dicha lista
            Iterator<Libro> iterator = librosLeidos.iterator();

            // Mientras el iterador siga recorriendo la lista va comprobando si el id es el que pide el usuario
            while (iterator.hasNext()) {
                Libro libro = iterator.next();
                if (libro.getId() == idLibroBorrar) {
                    iterator.remove();
                    System.out.println("Libro con ID " + idLibroBorrar + " eliminado correctamente.");
                    break; // Importante: salimos del bucle después de eliminar el libro que buscamos
                }
            }

            // Guardamos la lista actualizada en el fichero
            guardarLibrosBinario(librosLeidos, filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Método actualizar / modificar libros
    public void actualizarLibroBinario(String filename, int idLibroActualizar, Libro nuevoLibro) {
        try {
            // Leemos la lista de libros existentes
            List<Libro> librosLeidos = leerLibrosBinario(filename);

            // Buscamos en la lista el libro que desea el usuario
            for (int i = 0; i < librosLeidos.size(); i++) {
            	// Obtenemos el objeto libro
                Libro libro = librosLeidos.get(i);
                // Comparamos que la id del objeto sea la que queremos actualizar
                if (libro.getId() == idLibroActualizar) {
                    // Actualizamos la información del libro con la nueva información
                    librosLeidos.set(i, nuevoLibro);
                    System.out.println("Libro con ID " + idLibroActualizar + " actualizado correctamente.");
                    break; // Salimos del bucle for
                }
            }

            // Guardamos la lista actualizada
            guardarLibrosBinario(librosLeidos, filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
