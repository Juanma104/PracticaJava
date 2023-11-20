package biblioteca;



import java.io.Serializable;

public class Autor implements Serializable {
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anionacimiento;
	private String genero;
	
	
	
	
	public Autor(int id, String nombre, String nacionalidad, int anionacimiento, String genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anionacimiento = anionacimiento;
		this.genero = genero;
	}




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




	public int getAnionacimiento() {
		return anionacimiento;
	}




	public void setAnionacimiento(int anionacimiento) {
		this.anionacimiento = anionacimiento;
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
	
	
	// Constructor, getters y setters
}

//HOLA
// EJEMPLO HOLA
