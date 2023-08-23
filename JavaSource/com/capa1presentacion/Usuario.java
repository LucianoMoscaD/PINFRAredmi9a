package com.capa1presentacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Usuario {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_USUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	private long id;

	@Column(nullable = false, length = 50)
	private String nombre1;
	
	@Column(nullable = true, length = 50)
	private String nombre2;

	@Column(nullable = false, length = 50)
	private String apellido1;
	
	@Column(nullable = true, length = 50)
	private String apellido2;
	
	@Column(nullable = false, length = 50)
	private String documento;
	
	@Column(nullable = false, length = 50)
	private Date fechaNac;

	@Column(nullable = false, length = 50)
	private String emailPersonal;
	
	@Column(nullable = false, length = 50)
	private String telefono;
	
	@Column(nullable = false, length = 50)
	private String localidad;
	
	@Column(nullable = false, length = 50)
	private String departamento;
	
	@Column(nullable = false, length = 50)
	private String nombreDeUsuario; //REVISAR 
	
	@Column(nullable = false, length = 50)
	private String emailInstitucional;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String itr;
	
	/* SI ES ESTUDIANTE 
	 * @Column(nullable = false, length = 50)
		private String anoIngreso;
	 */
	/* SI ES TUTOR 
	 * @Column(nullable = false, length = 50)
		private String areaTutor;
		
		@Column(nullable = false, length = 50)
		private String rolTutor;
	 */
	
	public Usuario() {
		super();
	}

	public Usuario(long id, String nombre1, String nombre2, String apellido1, String apellido2, String documento,
			Date fechaNac, String emailPersonal, String telefono, String localidad, String departamento,
			String nombreDeUsuario, String emailInstitucional, String password, String itr) {
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
				+ emailInstitucional + ", password=" + password + ", itr=" + itr + "]";
	}

	
	
}