package com.capa1presentacion;

import java.util.Date;

public class Usuario {

	private static final long serialVersionUID = 1L;

	private long id;

	private String nombre1;

	private String nombre2;

	private String apellido1;

	private String apellido2;

	private String documento;

	private Date fechaNac;

	private String emailPersonal;

	private String telefono;

	private String localidad;

	private String departamento;

	private String nombreDeUsuario; 

	private String emailInstitucional;

	private String password;

	private String itr;

	private String anioIngreso;

	private int tipo;
	
	private int activo;
	
	private String areaTutor;
	
	private String rolTutor;

    public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Usuario() {
		super();
	}

	public Usuario(long id, String nombre1, String nombre2, String apellido1, String apellido2, String documento,
			Date fechaNac, String emailPersonal, String telefono, String localidad, String departamento,
			String nombreDeUsuario, String emailInstitucional, String password, String itr, String anioIngreso, int tipo, int activo, String areaTutor, String rolTutor) {
		super();
		this.id = id;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.documento = documento;
		this.fechaNac = fechaNac;
		this.emailPersonal = emailPersonal;
		this.telefono = telefono;
		this.localidad = localidad;
		this.departamento = departamento;
		this.nombreDeUsuario = nombreDeUsuario;
		this.emailInstitucional = emailInstitucional;
		this.password = password;
		this.itr = itr;
		this.anioIngreso = anioIngreso;
		this.tipo = tipo;
		this.activo = activo;
		this.rolTutor = rolTutor;
		this.areaTutor = areaTutor;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(String anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getEmailInstitucional() {
		return emailInstitucional;
	}

	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getItr() {
		return itr;
	}

	public void setItr(String itr) {
		this.itr = itr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", documento=" + documento + ", fechaNac=" + fechaNac
				+ ", emailPersonal=" + emailPersonal + ", telefono=" + telefono + ", localidad=" + localidad
				+ ", departamento=" + departamento + ", nombreDeUsuario=" + nombreDeUsuario + ", emailInstitucional="
				+ emailInstitucional + ", password=" + password + ", itr=" + itr + ", anioIngreso=" + anioIngreso
				+ ", tipo=" + tipo + ", activo=" + activo + "]";
	}

	public String getAreaTutor() {
		return areaTutor;
		
	}

	public void setAreaTutor(String areaTutor) {
		this.areaTutor = areaTutor;
		
	}

	public String getRolTutor() {
		return rolTutor;
		
	}

	public void setRolTutor(String rolTutor) {
		this.rolTutor = rolTutor;
		
	}




}