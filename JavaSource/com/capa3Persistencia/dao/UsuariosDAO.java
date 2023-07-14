
package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.capa3Persistencia.entities.UsuarioDTO;
import com.capa3Persistencia.exception.PersistenciaException;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean

public class UsuariosDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UsuariosDAO() {
		super();
	}

	public UsuarioDTO agregarUsuario(UsuarioDTO usuario) throws PersistenciaException {

		try {
			UsuarioDTO UsuarioDTO = em.merge(usuario);
			em.flush();
			return UsuarioDTO;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el usuario." + e.getMessage(), e);
		} finally {

		}
	}
	
	public UsuarioDTO modificarUsuario(UsuarioDTO usuario) throws PersistenciaException {

		try {
			UsuarioDTO UsuarioDTO = em.merge(usuario);
			em.flush();
			return UsuarioDTO;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el usuario." + e.getMessage(), e);
		} finally {

		}
	}

	public List<UsuarioDTO> buscarUsuarios() throws PersistenciaException {
		try {

			String query = "Select u from UsuarioDTO u";
			List<UsuarioDTO> resultList = (List<UsuarioDTO>) em
					.createQuery(query, UsuarioDTO.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public UsuarioDTO buscarUsuario(Long id) {
		UsuarioDTO UsuarioDTO = em.find(UsuarioDTO.class, id);
		return UsuarioDTO;
	}

	public static String convertirListaAJson(List<UsuarioDTO> usuarios) {
	    StringBuilder jsonBuilder = new StringBuilder();
	    jsonBuilder.append("[");

	    for (int i = 0; i < usuarios.size(); i++) {
	        UsuarioDTO usuario = usuarios.get(i);
	        jsonBuilder.append("{");
	        jsonBuilder.append("\"id\": \"").append(usuario.getId()).append("\",");
	        jsonBuilder.append("\"nombre1\": \"").append(usuario.getNombre()).append("\",");
	        jsonBuilder.append("\"apellido1\": \"").append(usuario.getApellido()).append("\",");
	        jsonBuilder.append("\"activo\": \"").append(usuario.getActivo()).append("\",");
	        jsonBuilder.append("\"fechaNacimiento\": \"").append(usuario.getFechaNac()).append("\",");
	        jsonBuilder.append("\"mail\": \"").append(usuario.getMail()).append("\",");
	        jsonBuilder.append("\"rol\": \"").append(usuario.getAlumno()).append("\",");
	        jsonBuilder.append("}");

	        if (i < usuarios.size() - 1) {
	            jsonBuilder.append(",");
	        }
	    }

	    jsonBuilder.append("]");
	    return jsonBuilder.toString();
	}


	public List<UsuarioDTO> seleccionarEmpleados(String criterioNombre, String criterioPassword)
			throws PersistenciaException {
		try {

			String query = "Select e from UsuarioDTO e  ";
			String queryCriterio = "";
			if (criterioNombre != null && !criterioNombre.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " e.nombre like '%" + criterioNombre
						+ "%' ";
			}
			if (criterioPassword != null && !criterioPassword.equals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " e.password='" + criterioPassword + "'  ";
			}

			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<UsuarioDTO> resultList = (List<UsuarioDTO>) em
					.createQuery(query, UsuarioDTO.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

}