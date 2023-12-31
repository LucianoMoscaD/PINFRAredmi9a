package com.capa1presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private Long selectedUserId;

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

	public void persistirAlumno() {
		Usuario usuarioNuevo;
		try {
			usuarioSeleccionado.setAlumno(1);
			usuarioSeleccionado.setActivo("0");
			usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
			// actualizamos id
			Long nuevoId = usuarioNuevo.getId();
			// vaciamos empleadoSeleccionado como para ingresar uno nuevo
			usuarioSeleccionado = new Usuario();

			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha agregado un nuevo Usuario con id:" + nuevoId.toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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

	public void salvarCambios() {

		Usuario usuarioNuevo;
		try {
			usuarioSeleccionado.setAlumno(0);
			usuarioSeleccionado.setActivo("0");
			usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
			
			// actualizamos id
			Long nuevoId = usuarioNuevo.getId();
			
			// vaciamos empleadoSeleccionado como para ingresar uno nuevo
			usuarioSeleccionado = new Usuario();

			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha agregado un nuevo Usuario con id:" + nuevoId.toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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
			navegacion.goToBienvenida();
			persistenciaBean.agregarUsuario(usuarioAModificar);
			usuarioAModificar = null;
		}
	}

	public void cargarUsuarioAModificar() {
		try {

			usuarioAModificar = persistenciaBean.obtenerUsuarioPorId(selectedUserId);
			selectedUserId = null;

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

	public Long getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(Long selectedUserId) {
		this.selectedUserId = selectedUserId;
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

	public List<UsuarioDTO> mostrarUsuarios() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarUsuarios();
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

	public void mostrarFuncionarios() throws PersistenciaException {
		usuarios = buscarFuncionarios();
	}

	public UsuarioDTO login() throws PersistenciaException {
		UsuarioDTO usuario = persistenciaBean.login(usuarioSeleccionado.getUsuario(),usuarioSeleccionado.getPassword());
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

}