package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libro implements Serializable {	
	
	// IMPLEMENTACIÓN DE ATRIBUTOS DE CLASE
	private int id;
	private String titulo;
	private String autor;
	private int anioPublicacion;
	private String genero;
	private boolean prestado;
	
	// CONSTRUCTORES Libro CON SOBRECARGA APLICADA
	public Libro() {
		
	}
	
	public Libro(int id, String titulo, String autor, int anioPublicacion, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.genero = genero;
		this.prestado = false;
	}	
	
	
	// METODOS DE CLASE // GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anioPublicacion=" + anioPublicacion
				+ ", genero=" + genero + ", prestado=" + prestado + "]";
	}

    
	
}
