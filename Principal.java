package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Importar otras clases necesarias
public class Principal {
	private static Scanner scanner = new Scanner(System.in);
	private static GestorFicheros gestorFicheros = new GestorFicheros();
	private static Libro libro = new Libro();
	private static String filename = "C:\\Users\\Ruben\\Documents\\librosBinario.bin";
	private static ArrayList<Libro> libros = new ArrayList<>();
	

	public static void main(String[] args) {
		boolean salir = false;
		while (!salir) {
			mostrarMenu();
			int opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				//Gestionar libros
				gestionarLibros();
				break;
			case 2:
				//Gestionar autores
				gestionarAutores();
				break;
			case 3:
				//Gestionar préstamos
				gestionarPrestamos();
				break;
			case 4:
				//Exportar/Importar datos con XML
				gestionarExportImportXML();
				break;
			case 5:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}
	}

	private static void mostrarMenu() {
		System.out.println("\nBienvenido al Sistema de Gestión de Biblioteca");
		System.out.println("1. Gestionar Libros");
		System.out.println("2. Gestionar Autores");
		System.out.println("3. Gestionar Préstamos");
		System.out.println("4. Exportar/Importar Datos (XML)");
		System.out.println("5. Salir");
		System.out.print("Seleccione una opción: ");
	}

	private static void gestionarLibros() {
		//Implementar lógica para gestionar libros
		
		int opcion;
		
		System.out.println("\nGestion de Libros");
		System.out.println("1. Escribir / Crear libro");
		System.out.println("2. Leer libro");
		System.out.println("3. Actualizar libro");
		System.out.println("4. Eliminar libro");
		System.out.println("5. Volver");
		System.out.print("Seleccione una opción: ");
		
		opcion = scanner.nextInt();
		
		switch (opcion) {
		case 1: {
			// ESCRIBIR / CREAR LIBROS BINARIOS
			
		    // Deja una linea después de nextInt()
		    scanner.nextLine();

		    if (libros.isEmpty()) {
		        int idLibro = 1;

		        System.out.println("Introduzca el titulo del libro: ");
		        String tituloLibro = scanner.nextLine();
		        System.out.println("Introduzca el autor del libro: ");
		        String autorLibro = scanner.nextLine();
		        System.out.println("Introduzca el año de publicacion del libro: ");
		        int anioPublicacion = scanner.nextInt();
		        // Deja una linea después de nextInt()
		        scanner.nextLine();
		        System.out.println("Introduzca el genero del libro: ");
		        String generoLibro = scanner.nextLine();

		        libros.add(new Libro(idLibro, tituloLibro, autorLibro, anioPublicacion, generoLibro));

		    } else {
		        int nuevoId = libro.obtenerUltimoId(libros) + 1;

		        System.out.println("Introduzca el titulo del libro: ");
		        String tituloLibro = scanner.nextLine();
		        System.out.println("Introduzca el autor del libro: ");
		        String autorLibro = scanner.nextLine();
		        System.out.println("Introduzca el año de publicacion del libro: ");
		        int anioPublicacion = scanner.nextInt();
		        // Deja una linea después de nextInt()
		        scanner.nextLine();
		        System.out.println("Introduzca el genero del libro: ");
		        String generoLibro = scanner.nextLine();

		        libros.add(new Libro(nuevoId, tituloLibro, autorLibro, anioPublicacion, generoLibro));
		    }

		    gestorFicheros.guardarLibrosBinario(libros, filename);
		    break;
		}
		
		case 2: {
			// LEER LIBROS BINARIOS
			List<Libro> librosLeidos = gestorFicheros.leerLibrosBinario(filename);
			
			// Mostrar los libros leídos
			if (librosLeidos != null) {
				for (Libro libro : libros) {
					System.out.println("Id: " + libro.getId());
					System.out.println("Titulo: " + libro.getTitulo());
					System.out.println("Autor: " + libro.getAutor());
					System.out.println("Año Publicacion: " + libro.getAnioPublicacion());
					System.out.println("Genero: " + libro.getGenero());
					System.out.println("---");
					}
			}
				
			break;
		}
		default:
			throw new IllegalArgumentException("Valor no esperado: " + opcion);
		}
	}

	private static void gestionarAutores() {
		//Implementar lógica para gestionar autores
	}

	private static void gestionarPrestamos() {
		//Implementar lógica para gestionar préstamos
	}

	private static void gestionarExportImportXML() {
		//Implementar lógica para exportar/importar datos con XML
	}
	//Otros métodos según sea necesario
}
