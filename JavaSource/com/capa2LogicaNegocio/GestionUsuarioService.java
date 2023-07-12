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
		usuario.setApellido(e.getApellido());
		usuario.setNombre(e.getNombre());
		usuario.setFechaNac(e.getFechaNac());
		usuario.setDireccion(e.getDireccion());
		usuario.setNumeroalumno(e.getNumeroEstudiante());
		usuario.setActivo(e.getActivo());
		usuario.setCarreraOEspecialidad(e.getCarreraOEspecialidad());
		usuario.setMail(e.getMail());

		return usuario;
	}

	public UsuarioDTO toUsuarioDTO(Usuario e) {

		UsuarioDTO usuario = new UsuarioDTO();

		usuario.setId(e.getId());
		usuario.setApellido(e.getApellido());
		usuario.setNombre(e.getNombre());
		usuario.setFechaNac(e.getFechaNac());
		usuario.setDireccion(e.getDireccion());
		usuario.setNumeroEstudiante(e.getNumeroalumno());
		usuario.setActivo(e.getActivo());
		usuario.setCarreraOEspecialidad(e.getCarreraOEspecialidad());
		usuario.setMail(e.getMail());


		return usuario;
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

	public void modificarUsuario(Usuario usuarioExistente) throws PersistenciaException {
		UsuarioDTO u = usuariosPersistenciaDAO.modificarUsuario(toUsuarioDTO(usuarioExistente));
	}

}

