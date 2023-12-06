package biblioteca;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GestorFicheros {
	// Instancia de la clase Principal que tiene las rutas (filenames)
	Principal principal = new Principal();
	
	
	//-----APARTADO DE LIBROS-----//
	
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
		// Creamos una lista de libros provisional que devolveremos mas tarde con todos los libros leidos
	    List<Libro> libros = new ArrayList<>();
	    try {
	        File file = new File(filename);
	        // MANEJO DE ERRORES: Comprobamos que el fichero tenga contenido, si no tiene no leerá nada
	        if (file.length() > 0) {
	            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	            	// Leemos los objetos de la lista con ObjectInputStream
	                libros = (List<Libro>) ois.readObject();
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return libros;
	}

	// Método eliminar libros
	public void eliminarLibrosBinario(String filename, int idLibroBorrar) {
		try {
			// Leemos la lista de libros que hay en el fichero
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
	
	
	// Método para verificar la existencia de un libro por su ID
    public boolean existeLibro(String filename, int idLibro) {
        List<Libro> libros = leerLibrosBinario(filename);
        for (Libro libro : libros) {
            if (libro.getId() == idLibro) {
                return true;
            }
        }
        return false;
    }

	
	//-----APARTADO DE AUTORES-----//
	
	// Método guardar autores / escribir autores
	public void guardarAutoresBinario(List<Autor> autores, String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			// Escribimos el objeto autor a la lista de autores haciendo uso de ObjectOutputStream
			oos.writeObject(autores);
			System.out.println("Autores escritos correctamente en " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método leer autores
	public List<Autor> leerAutoresBinario(String filename) {
		// Creamos una lista de autores provisional que devolveremos mas tarde con todos los autores leidos
	    List<Autor> autores = new ArrayList<>();
	    try {
	        File file = new File(filename);
	        // MANEJO DE ERRORES: Comprobamos que el fichero tenga contenido, si no tiene no leerá nada
	        if (file.length() > 0) {
	            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	            	// Leemos los objetos de la lista con ObjectInputStream
	                autores = (List<Autor>) ois.readObject();
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return autores;
	}

	
	// Método eliminar autores
	public void eliminarAutorBinario(String filename, int idAutorBorrar) {
		try {
			// Leemos la lista de autores que hay en el fichero
			List<Autor> autoresLeidos = leerAutoresBinario(filename);
			// Creamos un iterador para recorrer dicha lista
			Iterator<Autor> iterator = autoresLeidos.iterator();

			// Mientras el iterador siga recorriendo la lista va comprobando si el id es el que pide el usuario
			while (iterator.hasNext()) {
				Autor autor = iterator.next();
				if (autor.getId() == idAutorBorrar) {
					iterator.remove();
					System.out.println("Autor con ID " + idAutorBorrar + " eliminado correctamente.");
					break;
				}
			}

			// Guardamos la lista actualizada en el fichero
			guardarAutoresBinario(autoresLeidos, filename);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método actualizar autores / modificar autores
	public void actualizarAutorBinario(String filename, int idAutorActualizar, Autor nuevoAutor) {
		try {
			// Leemos la lista de autores existentes
			List<Autor> autoresLeidos = leerAutoresBinario(filename);

			// Buscamos en la lista el autor que desea el usuario
			for (int i = 0; i < autoresLeidos.size(); i++) {
				// Obtenemos el objeto autor
				Autor autor = autoresLeidos.get(i);
				// Comparamos que la id del objeto sea la que queremos actualizar
				if (autor.getId() == idAutorActualizar) {
					autoresLeidos.set(i, nuevoAutor);
					System.out.println("Autor con ID " + idAutorActualizar + " actualizado correctamente.");
					break;
				}
			}

			// Guardamos la lista actualizada
			guardarAutoresBinario(autoresLeidos, filename);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Método para verificar la existencia de un autor por su ID
    public boolean existeAutor(String filename, int idAutor) {
        List<Autor> autores = leerAutoresBinario(filename);
        for (Autor autor : autores) {
            if (autor.getId() == idAutor) {
                return true;
            }
        }
        return false;
    }
	

	
	//-----APARTADO DE PRESTAMOS-----//
	
	// Método registrar prestamo / añadir prestamo
	public void registrarPrestamo(Prestamo prestamo, String filename, List<Libro> libros) {
		// Declaramos el objeto writer PrintWriter para escribir sobre el fichero declarado
	    try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
	    	// Escribimos el metodo toString de la clase prestamo
	        writer.println(prestamo.toString());
	        // Actualizamos el estado del libro a prestado
	        for (Libro libro : libros) {
	            if (libro.getId() == prestamo.getIdLibro()) {
	                libro.setPrestado(true);
	                break;
	            }
	        }
	        // Guardamos los cambios en el archivo de libros para actualizar el estado del libro
	        guardarLibrosBinario(libros, principal.filenameLibros);
	        System.out.println("Libros actualizados correctamente.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

    
	// Método registrar devolución / añadir devolución
	public void registrarDevolucion(int idLibro, String fechaDevolucion, String filename, List<Libro> libros) {
	    // Declaramos los objetos reader y writer, este ultimo escribira sobre un fichero temporal para asegurar que no se pierden datos
		// BufferedReader leeremos el fichero pasado por parametro
		try (BufferedReader reader = new BufferedReader(new FileReader(filename));
	         PrintWriter writer = new PrintWriter(new FileWriter("temp.txt", true))) {

	        String line;
	        // Se lee cada línea del archivo de préstamos.
	        while ((line = reader.readLine()) != null) {
	        	// Se divide la línea en partes utilizando la coma como separador.
	            String[] parts = line.split(",");
	            // Se extrae el ID del libro de la primera parte y se pasa a un entero.
	            int id = Integer.parseInt(parts[0].trim());

	            // Se verifica si el ID del libro en la línea coincide con el ID del libro que se está devolviendo.
	            if (id == idLibro) {
	                // Modificamos la línea para registrar la fecha de devolución
	                writer.println(line + ", Devolución: " + fechaDevolucion);
	                // Actualizamos el estado del libro a "no prestado"
	                for (Libro libro : libros) {
	                    if (libro.getId() == idLibro) {
	                        libro.setPrestado(false);
	                        break;
	                    }
	                }
	            } else {
	                writer.println(line);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Renombramos el archivo temporal al original para que se apliquen los cambios
	    File tempFile = new File("temp.txt");
	    File originalFile = new File(filename);
	    tempFile.renameTo(originalFile);
	    
	    // Guardamos los cambios en el archivo de libros porque ahora el libro pasará a ser no prestado
	    guardarLibrosBinario(libros, principal.filenameLibros);
	    System.out.println("Libros actualizados correctamente.");
	}
    

	// Método mostrar prestamos / leer prestamos
    public void mostrarPrestamos(String filename) {
    	// Declaramos el objeto reader del fichero prestamos
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            
            // Leemos todo el fichero mientras tenga contenido
            while ((line = reader.readLine()) != null) {
            	// Imprimimos cada linea del fichero
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    //-----APARTADO DE COPIAS DE SEGURIDAD-----//
    
    // MÉTODO EXPORTAR ARCHIVO BINARIO
    public void exportarArchivoBinario(byte[] datos, String nombreArchivo, String carpetaDestino) {
        // Creamos la carpeta si no existe
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Creamos la ruta completa del archivo
        String rutaArchivo = carpetaDestino + File.separator + nombreArchivo;
        
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            // Escribimos los datos en el archivo binario
            fos.write(datos);
            System.out.println("Archivo exportado exitosamente en " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
    
    // MÉTODO EXPORTAR ARCHIVO TEXTO
    public void exportarArchivoTexto(String datos, String nombreArchivo, String carpetaDestino) {
        // Creamos la carpeta si no existe
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Creamos la ruta completa del archivo
        String rutaArchivo = carpetaDestino + File.separator + nombreArchivo;

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            // Escribimos los datos en el archivo de texto
            writer.write(datos);
            System.out.println("Archivo exportado exitosamente en " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

//ERRORES ACTUALIZADOS
