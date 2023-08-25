package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa1presentacion.Usuario;
import com.capa3Persistencia.dao.UsuariosDAO;
import com.capa3Persistencia.entities.UsuarioDTO;
import com.capa3Persistencia.exception.PersistenciaException;

@Stateless
@LocalBean
public class GestionUsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosDAO usuariosPersistenciaDAO;

	public Usuario fromUsuarioDTO(UsuarioDTO e) {
	    Usuario usuario = new Usuario();
	    usuario.setId(e.getId());
	    usuario.setNombre1(e.getNombre1());
	    usuario.setNombre2(e.getNombre2());
	    usuario.setApellido1(e.getApellido1());
	    usuario.setApellido2(e.getApellido2());
	    usuario.setDocumento(e.getDocumento());
	    usuario.setFechaNac(e.getFechaNac());
	    usuario.setEmailPersonal(e.getEmailPersonal());
	    usuario.setTelefono(e.getTelefono());
	    usuario.setLocalidad(e.getLocalidad());
	    usuario.setDepartamento(e.getDepartamento());
	    usuario.setNombreDeUsuario(e.getNombreDeUsuario());
	    usuario.setEmailInstitucional(e.getEmailInstitucional());
	    usuario.setPassword(e.getPassword());
	    usuario.setItr(e.getItr());
	    usuario.setAnioIngreso(e.getAnioIngreso());
	    usuario.setTipo(e.getTipo());
	    usuario.setActivo(e.getActivo());

	    return usuario;
	}

	public UsuarioDTO toUsuarioDTO(Usuario e) {
	    UsuarioDTO usuario = new UsuarioDTO();

	    usuario.setId(e.getId());
	    usuario.setNombre1(e.getNombre1());
	    usuario.setNombre2(e.getNombre2());
	    usuario.setApellido1(e.getApellido1());
	    usuario.setApellido2(e.getApellido2());
	    usuario.setDocumento(e.getDocumento());
	    usuario.setFechaNac(e.getFechaNac());
	    usuario.setEmailPersonal(e.getEmailPersonal());
	    usuario.setTelefono(e.getTelefono());
	    usuario.setLocalidad(e.getLocalidad());
	    usuario.setDepartamento(e.getDepartamento());
	    usuario.setNombreDeUsuario(e.getNombreDeUsuario());
	    usuario.setEmailInstitucional(e.getEmailInstitucional());
	    usuario.setPassword(e.getPassword());
	    usuario.setItr(e.getItr());
	    usuario.setAnioIngreso(e.getAnioIngreso());
	    usuario.setTipo(e.getTipo());
	    usuario.setActivo(e.getActivo());

	    return usuario;
	}
	
    public Usuario obtenerUsuarioPorId(Long id) throws PersistenciaException {
        try {
            return fromUsuarioDTO(usuariosPersistenciaDAO.buscarUsuario(id));
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el usuario por ID", e);
        }
    }

	// servicios para capa de Presentacion
	public List<Usuario> seleccionarUsuarios() throws PersistenciaException {
		// buscamos todos los objetos UsuarioDTO
		List<UsuarioDTO> listaUsuariosPersistencia = usuariosPersistenciaDAO.buscarUsuarios();

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		// recorremos listaUsuariosPersistencia y vamos populando listaUsuarios
		// (haciendo la conversion requerida)
		for (UsuarioDTO usuarioPersistencia : listaUsuariosPersistencia) {
			listaUsuarios.add(fromUsuarioDTO(usuarioPersistencia));
		}
		return listaUsuarios;
	}

	public Usuario agregarUsuario(Usuario usuarioSeleccionado) throws PersistenciaException {
		UsuarioDTO u = usuariosPersistenciaDAO.agregarUsuario(toUsuarioDTO(usuarioSeleccionado));
		
		System.out.println("en agregarUsuario --> "+ u.getActivo());

		return fromUsuarioDTO(u);
	}

	public Usuario buscarUsuario(Long id) {
		UsuarioDTO e = usuariosPersistenciaDAO.buscarUsuario(id);
		return fromUsuarioDTO(e);
	}

	public List<UsuarioDTO> buscarUsuarios() {
		List<UsuarioDTO> e = null;
		try {
			e = usuariosPersistenciaDAO.buscarUsuarios();
		} catch (PersistenciaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	
	public List<UsuarioDTO> buscarAlumnos() {
		List<UsuarioDTO> e = null;
		try {
			e = usuariosPersistenciaDAO.buscarAlumnos();
		} catch (PersistenciaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	
	public List<UsuarioDTO> buscarTutores() {
		List<UsuarioDTO> e = null;
		try {
			e = usuariosPersistenciaDAO.buscarTutores();
		} catch (PersistenciaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}

	public void modificarUsuario(Usuario usuarioExistente) throws PersistenciaException {
		UsuarioDTO u = usuariosPersistenciaDAO.modificarUsuario(toUsuarioDTO(usuarioExistente));
	}
	
	public UsuarioDTO login(final String usuario, final String password) throws PersistenciaException {
		UsuarioDTO usuarioDTO = usuariosPersistenciaDAO.checkCredenciales(usuario, password);
		return usuarioDTO;
	}

	public List<UsuarioDTO> buscarFuncionarios() {
		List<UsuarioDTO> e = null;
		try {
			e = usuariosPersistenciaDAO.buscarFuncionarios();
		} catch (PersistenciaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}

	public Usuario obtenerUsuarioPorCedula(String cedula) throws PersistenciaException {
		UsuarioDTO e = usuariosPersistenciaDAO.buscarUsuario(cedula);
		
    	System.out.println("activo en obtenerUsuarioPorCedula --> " + e.getActivo() );

		return fromUsuarioDTO(e);
	}
}

