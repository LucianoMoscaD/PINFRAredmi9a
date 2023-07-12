package com.capa1presentacion;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Usuario {

	@NotNull
	private Long id;
	@NotNull
	@Length(min=7,	max=50)
	private String nombre;
	@NotNull
	@Length(min=5,	max=50)
	private String password;
	
	

	public Usuario() {
		super();
	}



	public Usuario(Long id, String nombre, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
