package com.capa1presentacion;

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

	private Usuario usuarioSeleccionado;
	
	private Usuario usuarioLogueado;
	
	boolean logueado; 

	public GestionUsuario() {
		super();
	}

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Usuario();
		
	}

	public void salvarCambios() {

		Usuario usuarioNuevo;
		try {
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

	public String reset() {
		usuarioSeleccionado = new Usuario();
		return "";
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

	public String mostrarUsuarios() {
		List<UsuarioDTO> listaUsuarios = persistenciaBean.buscarUsuarios();
		return UsuariosDAO.convertirListaAJson(listaUsuarios);
	}
	
//	public void checkCredenciales(final String usuario, final String password) {
//	    try {
//	        Class.forName("org.h2.Driver");
//
//	        Context context = new InitialContext();
//	        DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/ExampleDS");
//	        try (Connection connection = dataSource.getConnection()) {
//	            String query = "SELECT * FROM Usuarios WHERE Usuario = ? AND Password = ?";
//	            PreparedStatement statement = connection.prepareStatement(query);
//	            statement.setString(1, usuario);
//	            statement.setString(2, password);
//	            
//	            ResultSet resultSet = statement.executeQuery();
//	            
//	            if (resultSet.next()) {
//	                // User credentials are valid
//	                logueado = true;
//	            } else {
//	                // User credentials are invalid
//	                logueado = false;
//	            }
//	        }
//	    } catch ( | SQLException | NamingException e) {
//	        // Handle any errors that occurred during the database connection or query execution
//	        e.printStackTrace();
//	    }
//	}


	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;
		
	}

	public void setUsuarioLogueado(Usuario usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
		
	}

}