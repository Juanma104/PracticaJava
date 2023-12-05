package biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorFicheros gestorFicheros = new GestorFicheros();
    
    // RUTAS DE LOS ARCHIVOS A CAMBIAR POR EL USUARIO PARA COMENZAR A EJECUTAR LA APLICACIÓN.
    public static String filenameLibros = "archivos\\librosBinario.bin";
    public static String filenameAutores = "archivos\\autoresBinario.bin";
    public static String filenamePrestamos = "archivos\\prestamos.txt";
    
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
                    realizarCopiaDeSeguridad();
                    break;
                case 6:
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
        System.out.println("5. Realizar Copia de Seguridad");
        System.out.println("6. Salir");
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
        // Preguntamos al usuario por el ID del libro y la fecha de devolución
        System.out.println("Introduzca el ID del libro: ");
        int idLibroDevolucion = scanner.nextInt();
        
        // Verificamos si el libro existe antes de intentar registrar la devolución
        if (!libroExiste(idLibroDevolucion)) {
            System.out.println("El libro con ID " + idLibroDevolucion + " no existe. No se puede registrar la devolución.");
            return;
        }
        
        scanner.nextLine(); // Limpiamos el buffer
        System.out.println("Introduzca la fecha de devolución (dd/mm/yyyy): ");
        String fechaDevolucion = scanner.nextLine();

        // Llamamos al método para registrar la devolución en el archivo de texto
        gestorFicheros.registrarDevolucion(idLibroDevolucion, fechaDevolucion, filenamePrestamos, libros);
        System.out.println("Devolución registrada correctamente.");
    }

    
    // METODO VER PRESTAMOS / LEER PRESTAMOS
    private static void verPrestamos() {
        // Llamamos al método para mostrar los préstamos almacenados en el archivo de texto
        gestorFicheros.mostrarPrestamos(filenamePrestamos);
    }
    
    
    //TODO: TERMINAR DE COMENTAR LOS METODOS NUEVOS
    
    //-----APARTADO GESTIONAR IMPORTAR / EXPORTAR XML-----//
    private static void gestionarExportImportXML() {
    	int opcion;

    	// Implementación de bucle do-while para que cuando introduzca la opcion 3 vuelva atras.
        do {
            System.out.println("\nGestión de Exportar Importar XML");
            System.out.println("1. Importar XML");
            System.out.println("2. Exportar XML");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	// Menú de categorias para la opción importar
                	System.out.println("Introduzca que categoría desea importar");
                	System.out.println("1.- Importar XML de Libros");
                	System.out.println("2.- Importar XML de Autores");
                	int categoria = scanner.nextInt();
                	scanner.nextLine();
                	
                	switch (categoria) {
					case 1: {
						// Llamada al metodo Importar Libro XML
						System.out.println("Introduzca el nombre del archivo a importar (incluyendo la extension .xml)");
	                	String nombreArchivo = scanner.nextLine();
	                	importarLibroXML(nombreArchivo);
						break;
					}
					case 2: {
						// Llamada al metodo Importar Autor XML
						System.out.println("Introduzca el nombre del archivo a importar (incluyendo la extension .xml)");
	                	String nombreArchivo = scanner.nextLine();
	                	importarAutorXML(nombreArchivo);
						break;
					}
					default:
						System.out.println("Opción no válida. Por favor, intente de nuevo.");
					}
                	
                    break;
                case 2:
                	// Menú de categorias para la opción importar
                	System.out.println("Introduzca que categoría desea exportar");
                	System.out.println("1.- Exportar XML de Libros");
                	System.out.println("2.- Exportar XML de Autores");
                	int categoria2 = scanner.nextInt();
                	scanner.nextLine();
                	
                	switch (categoria2) {
					case 1: {
						// Llamada al metodo Exportar Libro XML
						System.out.println("Introduzca el nombre del archivo a exportar (incluyendo la extension .xml)");
	                	String nombreArchivo = scanner.nextLine();
	                	exportarLibrosXML(nombreArchivo);
						break;
					}
					case 2: {
						// Llamada al metodo Exportar Autor XML
						System.out.println("Introduzca el nombre del archivo a exportar (incluyendo la extension .xml)");
	                	String nombreArchivo = scanner.nextLine();
	                	exportarAutoresXML(nombreArchivo);
						break;
					}
					default:
						System.out.println("Opción no válida. Por favor, intente de nuevo.");
					}
                	
                    break;
                case 3:
                	System.out.println("Volviendo atrás...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 3);
    }
    
    
    // Método para importar un archivo libro XML
    private static void importarLibroXML(String nombreArchivo) {
        try {
        	int nuevoId;
        	
            // Creamos una nueva instancia de DocumentBuilderFactory y DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Cargamos el archivo XML
            File archivoXML = new File(nombreArchivo);
            Document documento = dBuilder.parse(archivoXML);

            // Normalizamos el documento
            documento.getDocumentElement().normalize();

            // Obtenemos la lista de nodos con un nombre específico en este caso "libro"
            NodeList listaLibros = documento.getElementsByTagName("libro");

            // Iteramos sobre la lista de nodos "listaLibros"
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodoLibro = listaLibros.item(i);

                if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoLibro = (Element) nodoLibro;

                    // Obtenemos los datos del elemento libro
                    String titulo = obtenerContenidoElemento(elementoLibro, "titulo");
                    String autor = obtenerContenidoElemento(elementoLibro, "autor");
                    int anioPublicacion = Integer.parseInt(obtenerContenidoElemento(elementoLibro, "anioPublicacion"));
                    String genero = obtenerContenidoElemento(elementoLibro, "genero");

                    // Controlamos la asignación de id del libro que vamos a añadir
                    if (libros.isEmpty()) {
                    	nuevoId = 1;
                    } else {
                    	nuevoId = libros.get(libros.size() - 1).getId() + 1;
                    }
                    
                    // Añadimos el objeto libro
                    libros.add(new Libro(nuevoId, titulo, autor, anioPublicacion, genero));

                    // Imprimimos el resultado
                    System.out.println("Libro importado:");
                    System.out.println("Título: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Año de Publicación: " + anioPublicacion);
                    System.out.println("Género: " + genero);
                    System.out.println("------------------------");
                }
            }
            
            // Guardamos los cambios en nuestro fichero de libros
            gestorFicheros.guardarLibrosBinario(libros, filenameLibros);

            System.out.println("Archivo XML importado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    // Método para importar un archivo autor XML
    private static void importarAutorXML(String nombreArchivo) {
        try {
        	int nuevoId;
        	
            // Creamos una nueva instancia de DocumentBuilderFactory y DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Cargamos el archivo XML
            File archivoXML = new File(nombreArchivo);
            Document documento = dBuilder.parse(archivoXML);

            // Normalizamos el documento
            documento.getDocumentElement().normalize();

            // Obtenemos la lista de nodos con un nombre específico en este caso "autor"
            NodeList listaAutores = documento.getElementsByTagName("autor");

            // Iteramos sobre la lista de nodos
            for (int i = 0; i < listaAutores.getLength(); i++) {
                Node nodoAutor = listaAutores.item(i);

                if (nodoAutor.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoAutor = (Element) nodoAutor;

                    // Obtenemos los datos del elemento autor
                    String nombre = obtenerContenidoElemento(elementoAutor, "nombre");
                    String nacionalidad = obtenerContenidoElemento(elementoAutor, "nacionalidad");
                    int anioNacimiento = Integer.parseInt(obtenerContenidoElemento(elementoAutor, "anioNacimiento"));

                    // Controlamos la asignación de id del autor que vamos a añadir
                    if (autores.isEmpty()) {
                    	nuevoId = 1;
                    } else {
                    	nuevoId = autores.get(autores.size() - 1).getId() + 1;
                    }
                    
                 	// Añadimos el objeto autor
                    autores.add(new Autor(nuevoId, nombre, nacionalidad, anioNacimiento));

                    // Imprimimos el resultado
                    System.out.println("Autor importado:");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Nacionalidad: " + nacionalidad);
                    System.out.println("Año de Nacimiento: " + anioNacimiento);
                    System.out.println("------------------------");
                }
            }
            
            // Guardamos los cambios en nuestro fichero de autores
            gestorFicheros.guardarAutoresBinario(autores, filenameAutores);

            System.out.println("Archivo XML importado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Método auxiliar para obtener el contenido de un elemento
    private static String obtenerContenidoElemento(Element elementoPadre, String nombreElemento) {
        NodeList listaNodos = elementoPadre.getElementsByTagName(nombreElemento);
        Element elemento = (Element) listaNodos.item(0);
        return elemento.getTextContent();
    }
    
    
    
    // Método para exportar libros a un archivo XML
    private static void exportarLibrosXML(String nombreArchivo) {
        try {
            // Creamos una nueva instancia de DocumentBuilderFactory y DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Creamos un nuevo documento XML
            Document documento = dBuilder.newDocument();

            // Creamos el elemento raíz del documento
            Element raiz = documento.createElement("libros");
            documento.appendChild(raiz);

            // Creamos un elemento para cada libro y agregarlo al documento
            for (Libro libro : libros) {
                Element libroElemento = documento.createElement("libro");
                raiz.appendChild(libroElemento);

                // Añadimos atributos al elemento libro
                libroElemento.setAttribute("id", String.valueOf(libro.getId()));

                // Añadimos subelementos (titulo, autor, anioPublicacion, genero) al elemento libro
                agregarElemento(documento, libroElemento, "titulo", libro.getTitulo());
                agregarElemento(documento, libroElemento, "autor", libro.getAutor());
                agregarElemento(documento, libroElemento, "anioPublicacion", String.valueOf(libro.getAnioPublicacion()));
                agregarElemento(documento, libroElemento, "genero", libro.getGenero());
            }

            // Guardamos el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(nombreArchivo));
            transformer.transform(source, result);

            System.out.println("Archivo XML de libros exportado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    // Método para exportar autores a un archivo XML
    private static void exportarAutoresXML(String nombreArchivo) {
        try {
            // Creamos una nueva instancia de DocumentBuilderFactory y DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Creamos un nuevo documento XML
            Document documento = dBuilder.newDocument();

            // Creamos el elemento raíz del documento
            Element raiz = documento.createElement("autores");
            documento.appendChild(raiz);

            // Creamos un elemento para cada autor y agregarlo al documento
            for (Autor autor : autores) {
                Element autorElemento = documento.createElement("autor");
                raiz.appendChild(autorElemento);

                // Añadimos atributos al elemento autor
                autorElemento.setAttribute("id", String.valueOf(autor.getId()));

                // Añadimos subelementos (nombre, nacionalidad, anioNacimiento) al elemento autor
                agregarElemento(documento, autorElemento, "nombre", autor.getNombre());
                agregarElemento(documento, autorElemento, "nacionalidad", autor.getNacionalidad());
                agregarElemento(documento, autorElemento, "anioNacimiento", String.valueOf(autor.getAnioNacimiento()));
            }

            // Guardamos el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(nombreArchivo));
            transformer.transform(source, result);

            System.out.println("Archivo XML de autores exportado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Método auxiliar para agregar elementos al documento XML
    private static void agregarElemento(Document documento, Element padre, String nombre, String valor) {
        Element elemento = documento.createElement(nombre);
        elemento.appendChild(documento.createTextNode(valor));
        padre.appendChild(elemento);
    }
    
    

    
    //-----APARTADO REALIZAR COPIAS DE SEGURIDAD-----//
    private static void realizarCopiaDeSeguridad() {

        // Declaramos la carpeta destino donde se guardaran todos los ficheros "backup"
        String carpetaDestino = System.getProperty("user.dir") + File.separator + "backup";

        // Solicitamos al usuario que ingrese el nombre del archivo para el fichero de libros
        System.out.print("Ingrese el nombre del archivo para guardar LIBROS (incluyendo la extension .bin): ");
        scanner.nextLine();
        String nombreDelArchivoLibros = scanner.nextLine();
        
        // Solicitamos al usuario que ingrese el nombre del archivo para el fichero de autores
        System.out.print("Ingrese el nombre del archivo para guardar AUTORES (incluyendo la extension .bin): ");
        String nombreDelArchivoAutores = scanner.nextLine();
        
        // Solicitamos al usuario que ingrese el nombre del archivo para el fichero de préstamos
        System.out.print("Ingrese el nombre del archivo para guardar PRÉSTAMOS (incluyendo la extension .txt): ");
        String nombreDelArchivoPrestamos = scanner.nextLine();
        
        // Guardamos los datos de libros y autores en distintos arrays de bytes, para prestamos en un String
        byte[] datosAExportarLibros = leerDatosDesdeArchivoBinario(filenameLibros);
        byte[] datosAExportarAutores = leerDatosDesdeArchivoBinario(filenameAutores);
        String datosAExportarPrestamos = leerDatosDesdeArchivoNoBinario(filenamePrestamos);
        
        // Llamamos al método para exportar el archivo binario de LIBROS
        gestorFicheros.exportarArchivoBinario(datosAExportarLibros, nombreDelArchivoLibros, carpetaDestino);
        // Llamamos al método para exportar el archivo binario de AUTORES
        gestorFicheros.exportarArchivoBinario(datosAExportarAutores, nombreDelArchivoAutores, carpetaDestino);
        // Llamamos al método para exportar el archivo de PRÉSTAMOS
        gestorFicheros.exportarArchivoTexto(datosAExportarPrestamos, nombreDelArchivoPrestamos, carpetaDestino);

    }

    
    // Método para leer datos desde un archivo binario
    private static byte[] leerDatosDesdeArchivoBinario(String rutaArchivo) {
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            // Crear un array de bytes para almacenar los datos leídos
            byte[] datos = new byte[(int) new File(rutaArchivo).length()];

            // Leemos los datos del archivo
            fis.read(datos);

            return datos;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Manejo de errores
        }
    }
    
    
    
    // Método para leer datos desde un archivo no binario
    private static String leerDatosDesdeArchivoNoBinario(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Manejo de errores
        }
    }
    
}
