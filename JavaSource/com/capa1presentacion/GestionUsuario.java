package com.capa1presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.dao.UsuariosDAO;
import com.capa3Persistencia.entities.UsuarioDTO;
import com.capa3Persistencia.exception.PersistenciaException;
import com.navegacion.LoginBean;
import com.navegacion.NavigationBean;
import com.utils.ExceptionsTools;

@Named(value = "gestionUsuario") // JEE8
@SessionScoped // JEE8
public class GestionUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionUsuarioService persistenciaBean;

	@Inject
	UsuariosDAO usuarioDAO;

	@Inject
	NavigationBean navegacion;

	@Inject
	LoginBean loginBean;

	private String selectedUserCedula;

	private Usuario usuarioSeleccionado;

	private Usuario usuarioLogueado;

	boolean logueado;

	private List<UsuarioDTO> usuarios;

	private List<UsuarioDTO> usuariosDescartados;

	private Usuario usuarioAModificar;

	private boolean fromAlumnoPage;

	private String estadoFiltro = "Todos";

	private String confirmacionPassword;

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Usuario();
		actualizarListaUsuarios();
	}

	public GestionUsuario() {
		super();
	}

	public void crearAnalista() {

		Usuario usuarioNuevo;
		try {
			usuarioSeleccionado.setEmailInstitucional(usuarioSeleccionado.getNombreDeUsuario() + "@utec.edu.uy");
			usuarioSeleccionado.setTipo(1);
			if (validarDatos(usuarioSeleccionado).isEmpty()) {
				usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);

				// actualizamos id
				Long nuevoId = usuarioNuevo.getId();

				// vaciamos empleadoSeleccionado como para ingresar uno nuevo
				usuarioSeleccionado = new Usuario();

				// mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Se ha agregado un nuevo Analista con id:" + nuevoId.toString(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else {
				for (String error : validarDatosBasicosUsuario(usuarioSeleccionado)) {

					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", error);
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				}
			}
		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void crearAlumno() throws Exception {
		Usuario usuarioNuevo;
		try {
			usuarioSeleccionado
					.setEmailInstitucional(usuarioSeleccionado.getNombreDeUsuario() + "@estudiantes.utec.edu.uy");
			usuarioSeleccionado.setTipo(2);
			if (validarDatos(usuarioSeleccionado).isEmpty()) {
				usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
				// actualizamos id
				Long nuevoId = usuarioNuevo.getId();
				// vaciamos empleadoSeleccionado como para ingresar uno nuevo
				usuarioSeleccionado = new Usuario();

				// mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Se ha agregado un nuevo Alumno con id:" + nuevoId.toString(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else {
				for (String error : validarDatos(usuarioSeleccionado)) {

					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", error);
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				}
			}
		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {

		}
	}

	public void crearTutor() throws Exception {
		Usuario usuarioNuevo;
		try {
			usuarioSeleccionado.setEmailInstitucional(usuarioSeleccionado.getNombreDeUsuario() + "@utec.edu.uy");
			usuarioSeleccionado.setTipo(3);
			if (validarDatos(usuarioSeleccionado).isEmpty()) {
				usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
				// actualizamos id
				Long nuevoId = usuarioNuevo.getId();
				// vaciamos empleadoSeleccionado como para ingresar uno nuevo
				usuarioSeleccionado = new Usuario();

				// mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Se ha agregado un nuevo Tutor con id:" + nuevoId.toString(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else {
				for (String error : validarDatos(usuarioSeleccionado)) {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", error);
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				}
			}
		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {

		}
	}

	public void actualizarUsuario() throws PersistenciaException, IOException {
		if (usuarioAModificar != null) {
			
			System.out.println(usuarioAModificar.toString());
			if (loginBean.getUsuarioLogueado().getTipo() != 1 &&( usuarioAModificar.getPassword() == null
					|| confirmacionPassword.isBlank())) {

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Debe completar todos los campos marcados con un asterisco *");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			} else {
				String password = persistenciaBean.buscarUsuario(usuarioAModificar.getId()).getPassword();
				usuarioAModificar.setPassword(password);
				confirmacionPassword = password;
				if (validarDatosModificacion(usuarioAModificar).isEmpty()) {
					try {
						persistenciaBean.agregarUsuario(usuarioAModificar);
						navegacion.goToBienvenida();
						usuarioAModificar = null;
						confirmacionPassword = null;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					for (String error : validarDatosModificacion(usuarioAModificar)) {
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", error);
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					}
				}
			}
		}
	}

	public void cargarUsuarioAModificar() throws Exception {

		try {
			usuarioAModificar = persistenciaBean.obtenerUsuarioPorCedula(selectedUserCedula);
			selectedUserCedula = null;
		} catch (Exception e) {
			throw e;
		}

	}

	public UsuariosDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuariosDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getSelectedUserCedula() {
		return selectedUserCedula;
	}

	public void setSelectedUserCedula(String selectedUserId) {
		this.selectedUserCedula = selectedUserId;
	}

	public boolean isLogueado() {
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public String reset() {
		usuarioSeleccionado = new Usuario();
		return "";
	}

	public void resetUsuarioAModificar() {
		usuarioAModificar = null;
	}

	public GestionUsuarioService getPersistenciaBean() {
		return persistenciaBean;
	}

	public void setPersistenciaBean(GestionUsuarioService persistenciaBean) {
		this.persistenciaBean = persistenciaBean;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setEmpleadoSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<UsuarioDTO> buscarUsuarios() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarUsuarios();
		return listaUsuarios;
	}

	public List<UsuarioDTO> buscarTutores() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarTutores();
		return listaUsuarios;
	}

	public List<UsuarioDTO> buscarAlumnos() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarAlumnos();
		return listaUsuarios;
	}

	public List<UsuarioDTO> buscarFuncionarios() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarFuncionarios();
		return listaUsuarios;
	}

	public void mostrarAlumnos() throws PersistenciaException {
		usuarios = buscarAlumnos();
		usuariosDescartados = new ArrayList<>();
		estadoFiltro = "Todos";
	}

	public void mostrarAnalistas() throws PersistenciaException {
		usuarios = buscarFuncionarios();
		usuariosDescartados = new ArrayList<>();
		estadoFiltro = "Todos";
	}

	public void mostrarTutores() throws PersistenciaException {
		usuarios = buscarTutores();
		usuariosDescartados = new ArrayList<>();
		estadoFiltro = "Todos";
	}

	public void mostrarUsuarios() throws PersistenciaException {
		usuarios = buscarUsuarios();
		usuariosDescartados = new ArrayList<>();
		estadoFiltro = "Todos";
	}

	public void filtrar() {
		List<UsuarioDTO> usuariosFiltrados = new ArrayList<>();
		usuarios.addAll(usuariosDescartados);

		for (UsuarioDTO descartado : usuariosDescartados) {
			boolean idExists = false;

			for (UsuarioDTO usuario : usuarios) {
				if (usuario.getId() == descartado.getId()) {
					idExists = true;
					break;
				}
			}

			if (!idExists) {
				usuarios.add(descartado);
			}
		}

		if (estadoFiltro.equals("Activo")) {
			for (UsuarioDTO usuario : usuarios) {
				if (usuario.getActivo() == 1) {
					usuariosFiltrados.add(usuario);
				} else {
					usuariosDescartados.add(usuario);
				}
			}
		}
		if (estadoFiltro.equals("Inactivo")) {

			for (UsuarioDTO usuario : usuarios) {
				if (usuario.getActivo() == 0) {
					usuariosFiltrados.add(usuario);
				} else {
					usuariosDescartados.add(usuario);
				}
			}

		}
		if (estadoFiltro.equals("Todos")) {
			if (!usuariosDescartados.isEmpty()) {
				usuariosFiltrados.addAll(usuariosDescartados);
			} else {
				usuariosFiltrados = usuarios;
			}
		}
		usuarios = usuariosFiltrados;

	}

	public UsuarioDTO login() throws PersistenciaException {
		UsuarioDTO usuario = persistenciaBean.login(usuarioSeleccionado.getNombreDeUsuario(),
				usuarioSeleccionado.getPassword());
		return usuario;
	}

	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;

	}

	public void setUsuarioLogueado(Usuario usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;

	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void actualizarListaUsuarios() {
		usuarios = persistenciaBean.buscarUsuarios();
	}

	public Usuario getUsuarioAModificar() {
		return usuarioAModificar;

	}

	public void setUsuarioAModificar(Usuario usuarioAModificar) {
		this.usuarioAModificar = usuarioAModificar;

	}

	public boolean isFromAlumnoPage() {
		return fromAlumnoPage;
	}

	public void setFromAlumnoPage(boolean fromAlumnoPage) {
		this.fromAlumnoPage = fromAlumnoPage;
	}

	public String getEstadoFiltro() {
		return estadoFiltro;
	}

	public void setEstadoFiltro(String estadoFiltro) {
		this.estadoFiltro = estadoFiltro;
	}

	private List<String> validarDatosBasicosUsuario(Usuario usuario) {
		List<String> errores = new ArrayList<>();

		if (usuario.getNombre1() == null || usuario.getNombre1().length() < 3 || usuario.getNombre1().length() > 30
				|| usuario.getNombre1().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Primer nombre- con de 2 a 30 caracteres alfabeticos");
		}
		if (usuario.getNombre2() != null && usuario.getNombre2().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Segundo nombre- con caracteres alfabeticos");
		}
		if (usuario.getApellido1() == null || usuario.getApellido1().length() < 3
				|| usuario.getApellido1().length() > 30 || usuario.getApellido1().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Primer apellido- con de 2 a 30 caracteres alfabeticos");
		}
		if (usuario.getApellido2() != null && usuario.getApellido2().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Segundo apellido- con caracteres alfabeticos");
		}

		if (usuario.getDocumento() == null || usuario.getDocumento().length() < 8 || usuario.getDocumento().length() > 8
				|| !usuario.getDocumento().matches("^\\d+$")) {
			errores.add("Debe completar el campo -Documento- con 8 caracteres numï¿½ricos, sin guiones o puntos");
		}

		// FECHA DE NACIMIENTO
		if (usuario.getFechaNac() == null) {
			errores.add("Debe completar el campo -Fecha de nacimiento- ");
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(usuario.getFechaNac());
			int birthYear = calendar.get(Calendar.YEAR);

			int currentYear = Calendar.getInstance().get(Calendar.YEAR);

			int age = currentYear - birthYear;

			if (age < 18) {
				errores.add("Los usuarios deben tener al menos 18 años de edad");
			}

		}

		if (usuario.getEmailPersonal() == null
				|| !usuario.getEmailPersonal().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			errores.add("Debe completar el campo -Email Personal- con un mail valido");
		}

		if (usuario.getTelefono() == null || usuario.getTelefono().length() < 4 || usuario.getTelefono().length() > 9
				|| !usuario.getTelefono().matches("^\\d+$")) {
			errores.add(
					"Debe completar el campo -Telefono- con de 4 a 9 caracteres numericos, sin guiones o puntos");
		}

		if (usuario.getLocalidad() == null || usuario.getLocalidad().length() < 3
				|| usuario.getLocalidad().length() > 30 || usuario.getLocalidad().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Localidad- con de 2 a 30 caracteres alfabeticos");
		}

		if (usuario.getDepartamento() == null || usuario.getDepartamento().length() < 3
				|| usuario.getDepartamento().length() > 30 || usuario.getDepartamento().matches(".*\\d.*")) {
			errores.add("Debe completar el campo -Departamento- con de 2 a 30 caracteres alfabeticos");
		}

		if (usuario.getEmailInstitucional() == null
				|| !usuario.getEmailInstitucional().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			errores.add("Debe completar el campo -Email Institucional- con un mail valido");
		}

		if (usuario.getPassword() == null
				|| !usuario.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,20}$")) {
			errores.add(
					"Debe completar el campo -Contraseña- con 6 a 20 caracteres que contengan al menos una minuscula, una mayuscula y un numero");
		} else if (!(usuario.getPassword().equals(confirmacionPassword))) {
			errores.add("No concuerdan las contraseï¿½as ingresadas");
		}

		return errores;
	}

	private List<String> validarDatosUsuarioTutor(Usuario usuario) {
		List<String> errores = validarDatosBasicosUsuario(usuario);

		if (usuario.getAreaTutor() == null || usuario.getRolTutor() == null) {
			errores = new ArrayList<>();
			errores.add("Debe completar todos los campos marcados con un asterisco *");

		} else {
			if (usuario.getAreaTutor().length() < 2) {
				errores.add("Debe completar el campo -Area a la que pertenece como Tutor-");
			}

			if (usuario.getRolTutor().length() < 2) {
				errores.add("Debe completar el campo -Rol como Tutor-");
			}
		}

		return errores;
	}

	private List<String> validarDatosUsuarioAlumno(Usuario usuario) {
		List<String> errores = validarDatosBasicosUsuario(usuario);

		if (usuario.getAnioIngreso() == null) {
			errores = new ArrayList<>();
			errores.add("Debe completar todos los campos marcados con un asterisco *");
		} else {
			if (usuario.getAnioIngreso().length() < 2) {
				errores.add("Debe completar el campo -Año de ingreso a la carrera-");
			}
			int anioIngreso = Integer.parseInt(usuario.getAnioIngreso());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(usuario.getFechaNac());
			int nacimiento = calendar.get(Calendar.YEAR);

			int ageAtIngreso = anioIngreso - nacimiento;

			if (ageAtIngreso < 18) {
				errores.add("Los estudiantes deben tener al menos 18 años al momento de ingresar a la carrera");
			}
		}

		return errores;
	}

	private List<String> validarDatos(Usuario usuario) throws Exception {
		List<String> errores = new ArrayList<>();

		if (usuarioSeleccionado.getNombre1() == null || usuarioSeleccionado.getApellido1() == null
				|| usuarioSeleccionado.getDocumento() == null || usuarioSeleccionado.getFechaNac() == null
				|| usuarioSeleccionado.getEmailPersonal() == null || usuarioSeleccionado.getTelefono() == null
				|| usuarioSeleccionado.getLocalidad() == null || usuarioSeleccionado.getDepartamento() == null
				|| usuarioSeleccionado.getEmailInstitucional() == null || usuarioSeleccionado.getPassword() == null
				|| confirmacionPassword.isBlank() || usuarioSeleccionado.getItr() == null) {
			errores.add("Debe completar todos los campos marcados con un asterisco *");
		} else {

			if (usuario != null) {
				if (usuario.getTipo() == 1) {
					errores = validarDatosBasicosUsuario(usuario);
					try {

						if (persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()) != null
								&& persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()).getTipo() == 1) {
							errores.add("El documento ingresado ya esta siendo utilizado por otro analista");
						}
						if (persistenciaBean.obtenerUsuarioPorMail(usuario.getEmailPersonal(), 1) != null) {
							errores.add("El documento ingresado ya esta siendo utilizado por otro analista");
						}
					} catch (PersistenciaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (usuario.getTipo() == 2) {
					errores = validarDatosUsuarioAlumno(usuario);

					if (persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()) != null
							&& persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()).getTipo() == 2) {
						errores.add("El documento ingresado ya esta siendo utilizado por otro alumno");
					}
					if (persistenciaBean.obtenerUsuarioPorMail(usuario.getEmailPersonal(), 2) != null) {
						errores.add("El email personal ingresado ya esta siendo utilizado por otro alumno");
					}
				}
				if (usuario.getTipo() == 3) {
					errores = validarDatosUsuarioTutor(usuario);
					try {
						if (persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()) != null
								&& persistenciaBean.obtenerUsuarioPorCedula(usuario.getDocumento()).getTipo() == 3) {
							errores.add("El documento ingresado ya está siendo utilizado por otro tutor");
						}
						if (persistenciaBean.obtenerUsuarioPorMail(usuario.getEmailPersonal(), 3) != null) {
							errores.add("El email personal ingresado ya está siendo utilizado por otro tutor");
						}
					} catch (PersistenciaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return errores;
	}

	public List<String> validarDatosModificacion(Usuario usuario) {
		List<String> errores = new ArrayList<>();

		if (usuario.getNombre1() == null || usuario.getApellido1() == null || usuario.getDocumento() == null
				|| usuario.getFechaNac() == null || usuario.getEmailPersonal() == null || usuario.getTelefono() == null
				|| usuario.getLocalidad() == null || usuario.getDepartamento() == null
				|| usuario.getEmailInstitucional() == null || usuario.getItr() == null) {
			errores.add("Debe completar todos los campos marcados con un asterisco *");
		} else {
			if (usuario.getTipo() == 3) {
				errores = validarDatosUsuarioTutor(usuario);
			}
			if (usuario.getTipo() == 2) {
				errores = validarDatosUsuarioAlumno(usuario);
			}
			if (usuario.getTipo() == 1) {
				errores = validarDatosBasicosUsuario(usuario);
			}
		}

		return errores;
	}

	public String getConfirmacionPassword() {
		return confirmacionPassword;

	}

	public void setConfirmacionPassword(String confirmacionPassword) {
		this.confirmacionPassword = confirmacionPassword;

	}

}