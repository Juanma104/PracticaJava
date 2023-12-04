package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorFicheros gestorFicheros = new GestorFicheros();
    public static String filenameLibros = "C:\\Users\\Ruben\\Documents\\librosBinario.bin";
    public static String filenameAutores = "C:\\Users\\Ruben\\Documents\\autoresBinario.bin";
    public static String filenamePrestamos = "C:\\Users\\Ruben\\Documents\\prestamos.txt";
    // Declaramos la lista de tipo Libro "libros"
    private static List<Libro> libros;
    // Declaramos la lista de tipo Autor "autores"
    private static List<Autor> autores;

    public static void main(String[] args) {
    	// Asignamos a "libros" el contenido que haya en el fichero "filenameLibros" para recuperar los posibles datos que contenga
        libros = gestorFicheros.leerLibrosBinario(filenameLibros);
        // Asignamos a "autores" el contenido que haya en el fichero "filenameAutores" para recuperar los posibles datos que contenga
        autores = gestorFicheros.leerAutoresBinario(filenameAutores);

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

    
    //-----APARTADO GESTIONAR LIBROS-----//
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
        gestorFicheros.guardarLibrosBinario(libros, filenameLibros);
    }

    
    
    
    // METODO LEER LIBRO
    private static void leerLibros() {
    	// Llamamos a la funcion leerLibrosBinario que leera todos los objetos del fichero
        List<Libro> librosLeidos = gestorFicheros.leerLibrosBinario(filenameLibros);

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
        gestorFicheros.eliminarLibrosBinario(filenameLibros, idLibroBorrar);
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
        gestorFicheros.actualizarLibroBinario(filenameLibros, idLibroActualizar, nuevoLibro);
    }

    
    
    
    //-----APARTADO GESTIONAR AUTORES-----//
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
                    escribirAutor();
                    break;
                case 2:
                    leerAutores();
                    break;
                case 3:
                    actualizarAutor();
                    break;
                case 4:
                    eliminarAutor();
                    break;
                case 5:
                    System.out.println("Volviendo atrás...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    
    // METODO ESCRIBIR AUTOR
    private static void escribirAutor() {
        int nuevoId;

        scanner.nextLine(); 
        System.out.println("Introduzca el nombre del autor: ");
        String nombreAutor = scanner.nextLine();
        System.out.println("Introduzca la nacionalidad del autor: ");
        String nacionalidadAutor = scanner.nextLine();
        System.out.println("Introduzca el año de nacimiento del autor: ");
        int anioNacimientoAutor = scanner.nextInt();

        if (autores.isEmpty()) {
            nuevoId = 1;
        } else {
            nuevoId = autores.get(autores.size() - 1).getId() + 1;
        }

        autores.add(new Autor(nuevoId, nombreAutor, nacionalidadAutor, anioNacimientoAutor));

        gestorFicheros.guardarAutoresBinario(autores, filenameAutores);
    }

    
	// METODO LEER AUTOR
    private static void leerAutores() {
        List<Autor> autoresLeidos = gestorFicheros.leerAutoresBinario(filenameAutores);

        if (autoresLeidos != null) {
            for (Autor autor : autoresLeidos) {
                System.out.println("Id: " + autor.getId());
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Nacionalidad: " + autor.getNacionalidad());
                System.out.println("Año de Nacimiento: " + autor.getAnioNacimiento());
                System.out.println("-----");
            }
        }
    }

    
    // METODO ACTUALIZAR AUTOR
    private static void actualizarAutor() {
        System.out.println("Introduzca el ID del autor que desea actualizar: ");
        int idAutorActualizar = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduzca el nuevo nombre del autor: ");
        String nuevoNombreAutor = scanner.nextLine();
        System.out.println("Introduzca la nueva nacionalidad del autor: ");
        String nuevaNacionalidadAutor = scanner.nextLine();
        System.out.println("Introduzca el nuevo año de nacimiento del autor: ");
        int nuevoAnioNacimientoAutor = scanner.nextInt();

        Autor nuevoAutor = new Autor(idAutorActualizar, nuevoNombreAutor, nuevaNacionalidadAutor, nuevoAnioNacimientoAutor);

        gestorFicheros.actualizarAutorBinario(filenameAutores, idAutorActualizar, nuevoAutor);
    }

    
    // METODO ELIMINAR AUTOR
    private static void eliminarAutor() {
        System.out.println("Introduzca el id del autor que desea eliminar: ");
        int idAutorBorrar = scanner.nextInt();
        scanner.nextLine();

        gestorFicheros.eliminarAutorBinario(filenameAutores, idAutorBorrar);
    }
    
    
    
    //-----APARTADO GESTIONAR PRESTAMOS-----//
    private static void gestionarPrestamos() {
        int opcion;

        do {
            System.out.println("\nGestión de Préstamos");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Ver préstamos");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    registrarDevolucion();
                    break;
                case 3:
                    verPrestamos();
                    break;
                case 4:
                    System.out.println("Volviendo atrás...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);
    }

    
    // METODO REGISTRAR PRESTAMO
    private static void registrarPrestamo() {
        // Preguntamos al usuario por el ID del libro, nombre del usuario y fechas
        System.out.println("Introduzca el ID del libro: ");
        int idLibroPrestamo = scanner.nextInt();
        
        // Verificamos si el libro existe antes de intentar registrar el préstamo
        if (!libroExiste(idLibroPrestamo)) {
            System.out.println("El libro con ID " + idLibroPrestamo + " no existe. No se puede registrar el préstamo.");
            return;
        }
        
        // Verificamos si el libro está prestado antes de registrar el préstamo
        if (libroEstaPrestado(idLibroPrestamo)) {
            System.out.println("Este libro ya está prestado y no se puede prestar nuevamente.");
        } else {
            scanner.nextLine();
            System.out.println("Introduzca el nombre del usuario: ");
            String nombreUsuarioPrestamo = scanner.nextLine();
            System.out.println("Introduzca la fecha de préstamo (dd/mm/yyyy): ");
            String fechaPrestamo = scanner.nextLine();
            System.out.println("Introduzca la fecha de devolución (dd/mm/yyyy): ");
            String fechaDevolucion = scanner.nextLine();

            // Creamos un objeto Prestamo con la información proporcionada por el usuario
            Prestamo prestamo = new Prestamo(idLibroPrestamo, nombreUsuarioPrestamo, fechaPrestamo, fechaDevolucion);

            // Llamamos al método para guardar el préstamo en el archivo de texto
            gestorFicheros.registrarPrestamo(prestamo, filenamePrestamos, libros);
            System.out.println("Préstamo registrado correctamente.");
        }
    }

    // MÉTODO VERIFICAR SI UN LIBRO EXISTE
    private static boolean libroExiste(int idLibro) {
        // For-each de la lista libros
    	for (Libro libro : libros) {
    		// Compara que el id del libro sea el id que se ha introducido
            if (libro.getId() == idLibro) {
                return true;
            }
        }
        return false;
    }

    // MÉTODO VERIFICAR SI UN LIBRO ESTA PRESTADO
    private static boolean libroEstaPrestado(int idLibro) {
    	// For-each de la lista libros
    	for (Libro libro : libros) {
    		// Compara que el id del libro sea el id que se ha introducido Y que este en estado prestado
            if (libro.getId() == idLibro && libro.isPrestado()) {
                return true;
            }
        }
        return false;
    }
    

    // METODO REGISTRAR DEVOLUCIÓN
    private static void registrarDevolucion() {
        // Preguntar al usuario por el ID del libro y la fecha de devolución
        System.out.println("Introduzca el ID del libro: ");
        int idLibroDevolucion = scanner.nextInt();
        
        // Verificar si el libro existe antes de intentar registrar la devolución
        if (!libroExiste(idLibroDevolucion)) {
            System.out.println("El libro con ID " + idLibroDevolucion + " no existe. No se puede registrar la devolución.");
            return;
        }
        
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Introduzca la fecha de devolución (dd/mm/yyyy): ");
        String fechaDevolucion = scanner.nextLine();

        // Llamar al método para registrar la devolución en el archivo de texto
        gestorFicheros.registrarDevolucion(idLibroDevolucion, fechaDevolucion, filenamePrestamos, libros);
        System.out.println("Devolución registrada correctamente.");
    }

    
    // METODO VER PRESTAMOS / LEER PRESTAMOS
    private static void verPrestamos() {
        // Llamar al método para mostrar los préstamos almacenados en el archivo de texto
        gestorFicheros.mostrarPrestamos(filenamePrestamos);
    }
    
    
    
    
    
    // TODO: HACER APARTADO DE IMPORTAR EXPORTAR XML Y BACKUP
    
    
    
    //-----APARTADO GESTIONAR EXPORTAR IMPORTAR XML-----//
    private static void gestionarExportImportXML() {
        // TODO: Implementar lógica para exportar/importar datos con XML
    }
}
