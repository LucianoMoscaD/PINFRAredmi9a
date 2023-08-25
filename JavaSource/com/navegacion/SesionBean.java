package com.navegacion;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "sesionBean") //
@SessionScoped
public class SesionBean implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5106208709104578853L;

	protected int tipo;
	
	protected boolean logueado;


	public boolean isLogueado() {
		return logueado;
	}

	public  void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	

}
