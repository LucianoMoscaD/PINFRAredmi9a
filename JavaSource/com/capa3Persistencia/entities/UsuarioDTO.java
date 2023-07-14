package com.capa3Persistencia.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "USUARIOS")
public class UsuarioDTO {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_USUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	private long id;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 50)
	private String apellido;

	@Column(nullable = false, length = 50)
	private String fechaNac;

	@Column(nullable = false)
	private int alumno;

	@Column(nullable = false, length = 50)
	private String direccion;

	@Column(nullable = false, length = 50)
	private String mail;

	@Column(nullable = false, length = 50)
	private String activo;
	
	@Column(nullable = true, length = 100)
	private String carreraOEspecialidad;
	
	@Column(nullable = true)
	private String numeroEstudiante;
	
	
	
	public UsuarioDTO() {
		super();
	}


	public UsuarioDTO(long id, String nombre, String apellido, String fechaNac, int alumno, String direccion, String mail,
			String activo, String carreraOEspecialidad, String numeroEstudiante) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.alumno = alumno;
		this.direccion = direccion;
		this.mail = mail;
		this.activo = activo;
		this.carreraOEspecialidad = carreraOEspecialidad;
		this.numeroEstudiante = numeroEstudiante;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getFechaNac() {
		return fechaNac;
	}



	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public String getActivo() {
		return activo;
	}



	public void setActivo(String activo) {
		this.activo = activo;
	}



	public String getCarreraOEspecialidad() {
		return carreraOEspecialidad;
	}



	public void setCarreraOEspecialidad(String carreraOEspecialidad) {
		this.carreraOEspecialidad = carreraOEspecialidad;
	}



	public String getNumeroEstudiante() {
		return numeroEstudiante;
	}



	public void setNumeroEstudiante(String numeroEstudiante) {
		this.numeroEstudiante = numeroEstudiante;
	}

	public int getAlumno() {
		return alumno;
	}



	public void setAlumno(int alumno) {
		this.alumno = alumno;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac
				+ ", alumno=" + alumno + ", direccion=" + direccion + ", mail=" + mail + ", activo=" + activo
				+ ", carreraOEspecialidad=" + carreraOEspecialidad + ", numeroEstudiante=" + numeroEstudiante + "]";
	}

}
