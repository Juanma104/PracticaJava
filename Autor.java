package biblioteca;

import java.io.Serializable;

public class Autor implements Serializable {
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anioNacimiento;
	
	public Autor(int id, String nombre, String nacionalidad, int anioNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anioNacimiento = anioNacimiento;
	}

	
	// METODOS DE CLASE // GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}


	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", anioNacimiento="
				+ anioNacimiento + "]";
	}
	
	
}

//ERRORES ACTUALIZADOS
