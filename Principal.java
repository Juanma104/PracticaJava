package biblioteca;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorFicheros gestorFicheros = new GestorFicheros();
    private static String filename = "C:\\Users\\Ruben\\Documents\\librosBinario.bin";
    // Declaramos la lista de tipo Libro "libros"
    private static List<Libro> libros;

    public static void main(String[] args) {
    	// Asignamos a "libros" el contenido que haya en el fichero "filename" para recuperar los posibles datos que contenga
        libros = gestorFicheros.leerLibrosBinario(filename);

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    gestionarLibros();
                    break;
                case 2:
                    gestionarAutores();
                    break;
                case 3:
                    gestionarPrestamos();
                    break;
                case 4:
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
        int opcion;

        // Implementación de bucle do-while para que cuando introduzca la opcion 5 vuelva atras.
        do {
            System.out.println("\nGestion de Libros");
            System.out.println("1. Escribir / Crear libro");
            System.out.println("2. Leer libro");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    escribirLibro();
                    break;
                case 2:
                    leerLibros();
                    break;
                case 3:
                	actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    System.out.println("Volviendo atras...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    
    
    // METODO ESCRIBIR LIBRO
    private static void escribirLibro() {
    	int nuevoId;
    	
    	scanner.nextLine(); // Evitamos posible error al introducir datos por consola
        System.out.println("Introduzca el titulo del libro: ");
        String tituloLibro = scanner.nextLine();
        System.out.println("Introduzca el autor del libro: ");
        String autorLibro = scanner.nextLine();
        System.out.println("Introduzca el año de publicacion del libro: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Evitamos posible error al introducir datos por consola
        System.out.println("Introduzca el genero del libro: ");
        String generoLibro = scanner.nextLine();
        
        /* En el caso del Id, comprobamos si la lista de libros esta vacia, en ese caso le asignaremos al libro que estamos creando
        // la id "1", de lo contrario, obtendra el tamaño de elementos que tiene, recuperamos el ultimo id que haya
         * y le sumamos uno. */
        if (libros.isEmpty()) {
        	nuevoId = 1;
        } else {
        	nuevoId = libros.get(libros.size() - 1).getId() + 1;
        }
        
        // Añadimos un nuevo libro con los atributos introducidos por el usuario
        libros.add(new Libro(nuevoId, tituloLibro, autorLibro, anioPublicacion, generoLibro));
        
        // Por ultimo guardamos los cambios en el fichero.
        gestorFicheros.guardarLibrosBinario(libros, filename);
    }

    
    
    
    // METODO LEER LIBRO
    private static void leerLibros() {
    	// Llamamos a la funcion leerLibrosBinario que leera todos los objetos del fichero
        List<Libro> librosLeidos = gestorFicheros.leerLibrosBinario(filename);

        // Si hay objetos dentro del fichero hace el bucle for-each recogiendo los datos de cada objeto.
        if (librosLeidos != null) {
            for (Libro libro : librosLeidos) {
                System.out.println("Id: " + libro.getId());
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Año Publicacion: " + libro.getAnioPublicacion());
                System.out.println("Genero: " + libro.getGenero());
                System.out.println("-----");
            }
        }
    }

    
    // METODO ELIMINAR LIBRO
    private static void eliminarLibro() {
    	// Preguntamos al usuario el id que desea borrar
        System.out.println("Introduzca el id del libro que desea eliminar: ");
        int idLibroBorrar = scanner.nextInt();
        scanner.nextLine(); // Evitamos posible error al introducir datos por consola

        // Llamamos al metodo eliminarLibrosBinario
        gestorFicheros.eliminarLibrosBinario(filename, idLibroBorrar);
    }

    
    
    // METODO ACTUALIZAR / MODIFICAR LIBRO
    private static void actualizarLibro() {
        // Preguntamos al usuario el ID del libro a actualizar
        System.out.println("Introduzca el ID del libro que desea actualizar: ");
        int idLibroActualizar = scanner.nextInt();
        scanner.nextLine();

        // Preguntamos al usuario la nueva información
        System.out.println("Introduzca el nuevo título del libro: ");
        String nuevoTitulo = scanner.nextLine();
        System.out.println("Introduzca el nuevo autor del libro: ");
        String nuevoAutor = scanner.nextLine();
        System.out.println("Introduzca el nuevo año de publicación del libro: ");
        int nuevoAnioPublicacion = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca el nuevo género del libro: ");
        String nuevoGenero = scanner.nextLine();

        // Creamos un nuevo objeto Libro con la nueva información actualizada
        Libro nuevoLibro = new Libro(idLibroActualizar, nuevoTitulo, nuevoAutor, nuevoAnioPublicacion, nuevoGenero);

        // Llamamos al método actualizarLibroBinario para actualizar el libro en el archivo
        gestorFicheros.actualizarLibroBinario(filename, idLibroActualizar, nuevoLibro);
    }

    
    
    
    
    
    
    //-----TODO: IMPLEMENTAR FUNCIONAMIENTO AUTORES-----//
    private static void gestionarAutores() {
        int opcion;

        // Implementación de bucle do-while para que cuando introduzca la opcion 5 vuelva atras.
        do {
            System.out.println("\nGestión de Autores");
            System.out.println("1. Escribir / Crear autor");
            System.out.println("2. Leer autor");
            System.out.println("3. Actualizar autor");
            System.out.println("4. Eliminar autor");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //TODO: Implementación del metodo escribirAutor();
                    break;
                case 2:
                	//TODO: Implementación del metodo leerLibros();
                    break;
                case 3:
                	//TODO: Implementación del metodo actualizarLibro();
                    break;
                case 4:
                	//TODO: Implementación del metodo eliminarLibro();
                    break;
                case 5:
                    System.out.println("Volviendo atras...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }
    
    

    private static void gestionarPrestamos() {
        // TODO: Implementar lógica para gestionar préstamos
    }

    private static void gestionarExportImportXML() {
        // TODO: Implementar lógica para exportar/importar datos con XML
    }
}
