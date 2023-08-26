package com.capa1presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	private String selectedUserCedula;

	private Usuario usuarioSeleccionado;

	private Usuario usuarioLogueado;

	boolean logueado;

	private List<UsuarioDTO> usuarios;

	private Usuario usuarioAModificar;

	private boolean fromAlumnoPage;

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
			usuarioSeleccionado.setTipo(1);
			if (validarDatosBasicosUsuario(usuarioSeleccionado).isEmpty()) {
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

	public void crearAlumno() {
		Usuario usuarioNuevo;
		try {
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

	public void crearTutor() {
		Usuario usuarioNuevo;
		try {
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
			persistenciaBean.agregarUsuario(usuarioAModificar);
			navegacion.goToBienvenida();
			usuarioAModificar = null;
		}
	}

	public void cargarUsuarioAModificar() {
		try {

			usuarioAModificar = persistenciaBean.obtenerUsuarioPorCedula(selectedUserCedula);
			System.out.println("activo en cargarUsuarioAModificar --> " + usuarioAModificar.getActivo());

			selectedUserCedula = null;

		} catch (PersistenciaException e) {
			String errorMessage = "Error al cargar los datos del usuario";
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
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
	}

	public void mostrarAnalistas() throws PersistenciaException {
		usuarios = buscarFuncionarios();
	}

	public void mostrarTutores() throws PersistenciaException {
		usuarios = buscarTutores();
	}

	public void mostrarUsuarios() throws PersistenciaException {
		usuarios = buscarUsuarios();
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

	private List<String> validarDatosBasicosUsuario(Usuario usuarioSeleccionado2) {
		List<String> errores = new ArrayList<>();

			if (usuarioSeleccionado.getNombre1() == null || usuarioSeleccionado.getNombre1().length() < 2
					|| usuarioSeleccionado.getNombre1().length() > 30
					|| usuarioSeleccionado.getNombre1().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Primer nombre” con de 2 a 30 caracteres alfabéticos");
			}
			if (usuarioSeleccionado.getNombre2().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Segundo nombre” con caracteres alfabéticos");
			}
			if (usuarioSeleccionado.getApellido1() == null || usuarioSeleccionado.getApellido1().length() < 2
					|| usuarioSeleccionado.getApellido1().length() > 30
					|| usuarioSeleccionado.getApellido1().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Primer apellido” con de 2 a 30 caracteres alfabéticos");
			}
			if (usuarioSeleccionado.getApellido2().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Segundo apellido” con caracteres alfabéticos");
			}

			if (usuarioSeleccionado.getDocumento() == null || usuarioSeleccionado.getDocumento().length() < 8
					|| usuarioSeleccionado.getDocumento().length() > 8
					|| !usuarioSeleccionado.getDocumento().matches("^\\d+$")) {
				errores.add("Debe completar el campo “Documento” con 8 caracteres numéricos, sin guiones o puntos");
			}

			// FECHA DE NACIMIENTO
			if (usuarioSeleccionado.getFechaNac() == null) {
				errores.add("Debe completar el campo “Fecha de nacimiento” ");
			}

			if (usuarioSeleccionado.getEmailPersonal() == null || !usuarioSeleccionado.getEmailPersonal()
					.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				errores.add("Debe completar el campo “Email Personal” con un mail valido");
			}

			if (usuarioSeleccionado.getTelefono() == null || usuarioSeleccionado.getTelefono().length() < 4
					|| usuarioSeleccionado.getTelefono().length() > 9
					|| !usuarioSeleccionado.getTelefono().matches("^\\d+$")) {
				errores.add(
						"Debe completar el campo “Teléfono” con de 4 a 9 caracteres numéricos, sin guiones o puntos");
			}

			if (usuarioSeleccionado.getLocalidad() == null || usuarioSeleccionado.getLocalidad().length() < 2
					|| usuarioSeleccionado.getLocalidad().length() > 30
					|| usuarioSeleccionado.getLocalidad().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Localidad” con de 2 a 30 caracteres alfabéticos");
			}

			if (usuarioSeleccionado.getDepartamento() == null || usuarioSeleccionado.getDepartamento().length() < 2
					|| usuarioSeleccionado.getDepartamento().length() > 30
					|| usuarioSeleccionado.getDepartamento().matches(".*\\d.*")) {
				errores.add("Debe completar el campo “Departamento” con de 2 a 30 caracteres alfabéticos");
			}

			if (usuarioSeleccionado.getEmailInstitucional() == null || !usuarioSeleccionado.getEmailInstitucional()
					.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				errores.add("Debe completar el campo “Email Institucional” con un mail valido");
			}

			if (usuarioSeleccionado.getPassword() == null
					|| !usuarioSeleccionado.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,20}$")) {
				errores.add(
						"Debe completar el campo “Contraseña” con 6 a 20 caracteres que contengan al menos una minúscula, una mayúscula y un número");
			}
		return errores;
	}
	
	private List<String> validarDatosUsuarioTutor(Usuario usuarioSeleccionado2) {
		List<String> errores = validarDatosBasicosUsuario(usuarioSeleccionado2);
		
		if(usuarioSeleccionado2.getAreaTutor() == null || usuarioSeleccionado2.getAreaTutor().length() < 2) {
			errores.add("Debe completar el campo “Área a la que pertenece como Tutor”");
		}
		
		if(usuarioSeleccionado2.getRolTutor() == null || usuarioSeleccionado2.getRolTutor().length() < 2) {
			errores.add("Debe completar el campo “Rol como Tutor”");
		}
		
		return errores;
	}
	
	private List<String> validarDatosUsuarioAlumno(Usuario usuarioSeleccionado2) {
		List<String> errores = validarDatosBasicosUsuario(usuarioSeleccionado2);
		
		if(usuarioSeleccionado2.getAnioIngreso() == null || usuarioSeleccionado2.getAnioIngreso().length() < 2) {
			errores.add("Debe completar el campo “Año de ingreso a la carrera”");
		}
		return errores;
	}
	
	private List<String> validarDatos(Usuario usuario){
		List<String> errores = new ArrayList<>();
		
		if(usuario!=null) {
			if(usuario.getTipo() == 1) {
				errores = validarDatosBasicosUsuario(usuario);
			}
			
			if(usuario.getTipo() == 2) {
				errores = validarDatosUsuarioAlumno(usuario);
			}
			if(usuario.getTipo() == 3) {
				errores = validarDatosUsuarioTutor(usuario);
			}
		}
		return errores;
	}
	
}