package biblioteca;



import java.io.Serializable;

public class Prestamo implements Serializable {
	private int idlibro;
	private String nusuario;
	private String fechaprestamo;
	private int aniopublicacion;
	
	
	
	
	public Prestamo(int idlibro, String nusuario, String fechaprestamo, int aniopublicacion) {
		super();
		this.idlibro = idlibro;
		this.nusuario = nusuario;
		this.fechaprestamo = fechaprestamo;
		this.aniopublicacion = aniopublicacion;
	}




	public int getIdlibro() {
		return idlibro;
	}




	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}




	public String getNusuario() {
		return nusuario;
	}




	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}




	public String getFechaprestamo() {
		return fechaprestamo;
	}




	public void setFechaprestamo(String fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}




	public int getAniopublicacion() {
		return aniopublicacion;
	}




	public void setAniopublicacion(int aniopublicacion) {
		this.aniopublicacion = aniopublicacion;
	}
	
	
	
	
	
	
	// Constructor, getters y setters
}