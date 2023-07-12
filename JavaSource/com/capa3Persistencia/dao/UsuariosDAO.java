package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.EmpleadoEmpresa;
import com.capa3Persistencia.entities.UsuarioPersistencia;
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

	public UsuarioPersistencia agregarUsuario(UsuarioPersistencia usuario) throws PersistenciaException {

		try {
			UsuarioPersistencia usuarioPersistencia = em.merge(usuario);
			em.flush();
			return usuarioPersistencia;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el usuario." + e.getMessage(), e);
		} finally {

		}
	}

	public List<UsuarioPersistencia> buscarUsuarios() throws PersistenciaException {
		try {

			String query = "Select u from UsuarioPersistencia u";
			List<UsuarioPersistencia> resultList = (List<UsuarioPersistencia>) em
					.createQuery(query, UsuarioPersistencia.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public UsuarioPersistencia buscarUsuario(Long id) {
		UsuarioPersistencia usuarioPersistencia = em.find(UsuarioPersistencia.class, id);
		return usuarioPersistencia;
	}

	public static String convertirListaAJson(List<UsuarioPersistencia> usuarios) {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("[");

		for (int i = 0; i < usuarios.size(); i++) {
			UsuarioPersistencia usuario = usuarios.get(i);
			jsonBuilder.append("{");
			jsonBuilder.append("\"id\": \"").append(usuario.getId()).append("\",");
			jsonBuilder.append("\"nombre\": \"").append(usuario.getNombre()).append("\",");
			jsonBuilder.append("\"password\":\"").append(usuario.getPassword()).append("\"");
			jsonBuilder.append("}");

			if (i < usuarios.size() - 1) {
				jsonBuilder.append(",");
			}
		}

		jsonBuilder.append("]");
		return jsonBuilder.toString();
	}

	public List<UsuarioPersistencia> seleccionarEmpleados(String criterioNombre, String criterioPassword)
			throws PersistenciaException {
		try {

			String query = "Select e from UsuarioPersistencia e  ";
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
			List<UsuarioPersistencia> resultList = (List<UsuarioPersistencia>) em
					.createQuery(query, UsuarioPersistencia.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

}
