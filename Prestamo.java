package biblioteca;

import java.io.Serializable;

public class Prestamo implements Serializable {
	private int idLibro;
	private String nombreUsuario;
	private String fechaPrestamo;
	private String fechaDevolucion;
	
	public Prestamo(int idLibro, String nombreUsuario, String fechaPrestamo, String fechaDevolucion) {
		super();
		this.idLibro = idLibro;
		this.nombreUsuario = nombreUsuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}


	public int getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}


	public String getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}


	@Override
	public String toString() {
		return idLibro + ", " + nombreUsuario + ", Préstamo: " + fechaPrestamo + ", Devolución: " + fechaDevolucion;
	}
	
}

// ERRORES ACTUALIZADOS
