
package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
			throw new PersistenciaException("No se pudo agregar el usuario. " + e.getMessage(), e);
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
			List<UsuarioDTO> resultList = (List<UsuarioDTO>) em.createQuery(query, UsuarioDTO.class).getResultList();

			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public UsuarioDTO buscarUsuario(Long id) {
		UsuarioDTO UsuarioDTO = em.find(UsuarioDTO.class, id);
		return UsuarioDTO;
	}

	public UsuarioDTO buscarUsuario(String cedula) throws Exception {
		UsuarioDTO result = null;
		try {

			String query = "SELECT u FROM UsuarioDTO u WHERE u.documento = :cedula";
			TypedQuery<UsuarioDTO> typedQuery = em.createQuery(query, UsuarioDTO.class);
			typedQuery.setParameter("cedula", cedula);
			List<UsuarioDTO> listaUsuarios = typedQuery.getResultList();
			
			if(!listaUsuarios.isEmpty()) {
				result = listaUsuarios.get(0);
			}
		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	public List<UsuarioDTO> buscarAlumnos() throws PersistenciaException {
		try {
			String query = "SELECT u FROM UsuarioDTO u WHERE u.tipo = :alumnoValue";
			TypedQuery<UsuarioDTO> typedQuery = em.createQuery(query, UsuarioDTO.class);
			typedQuery.setParameter("alumnoValue", 2); //
			List<UsuarioDTO> resultList = typedQuery.getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public List<UsuarioDTO> buscarTutores() throws PersistenciaException {
		try {
			String query = "SELECT u FROM UsuarioDTO u WHERE u.tipo = :alumnoValue";
			TypedQuery<UsuarioDTO> typedQuery = em.createQuery(query, UsuarioDTO.class);
			typedQuery.setParameter("alumnoValue", 3); //
			List<UsuarioDTO> resultList = typedQuery.getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public List<UsuarioDTO> buscarFuncionarios() throws PersistenciaException {
		try {
			String query = "SELECT u FROM UsuarioDTO u WHERE u.tipo = :alumnoValue";
			TypedQuery<UsuarioDTO> typedQuery = em.createQuery(query, UsuarioDTO.class);
			typedQuery.setParameter("alumnoValue", 1); //
			List<UsuarioDTO> resultList = typedQuery.getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

	public UsuarioDTO checkCredenciales(final String usuario, final String password) throws PersistenciaException {
		List<UsuarioDTO> usuarios = buscarUsuarios();
		UsuarioDTO usuarioDTO = null;
		for (UsuarioDTO u : usuarios) {
			if (u.getNombreDeUsuario().equals(usuario) && u.getPassword().equals(password) && u.getActivo() == 1) {
				usuarioDTO = u;
			}
		}
		return usuarioDTO;
	}

//	public static String convertirListaAJson(List<UsuarioDTO> usuarios) {
//	    StringBuilder jsonBuilder = new StringBuilder();
//	    jsonBuilder.append("[");
//
//	    for (int i = 0; i < usuarios.size(); i++) {
//	        UsuarioDTO usuario = usuarios.get(i);
//	        jsonBuilder.append("{");
//	        jsonBuilder.append("\"id\": \"").append(usuario.getId()).append("\",");
//	        jsonBuilder.append("\"nombre\": \"").append(usuario.getNombre()).append("\",");
//	        jsonBuilder.append("\"apellido\": \"").append(usuario.getApellido()).append("\",");
//	        jsonBuilder.append("\"activo\": \"").append(usuario.getActivo()).append("\",");
//	        jsonBuilder.append("\"fechaNacimiento\": \"").append(usuario.getFechaNac()).append("\",");
//	        jsonBuilder.append("\"mail\": \"").append(usuario.getMail()).append("\",");
//	        jsonBuilder.append("\"rol\": \"").append(usuario.getAlumno()).append("\",");
//	        jsonBuilder.append("}");
//
//	        if (i < usuarios.size() - 1) {
//	            jsonBuilder.append(",");
//	        }
//	    }
//
//	    jsonBuilder.append("]");
//	    return jsonBuilder.toString();
//	}

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
			List<UsuarioDTO> resultList = (List<UsuarioDTO>) em.createQuery(query, UsuarioDTO.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
		}
	}

//	public UsuarioDTO buscarUsuarioPorMail(String mail) throws PersistenciaException {
//		System.out.println("Mail en buscarUsuarioPorMail DAO -->"+ mail);
//
//		try {
//			Query query = em.createQuery("SELECT u FROM UsuarioDTO u WHERE u.emailPersonal = :email")
//					.setParameter("email", "diegote@gmail.com");
//
//			System.out.println("esta es la query --> " + query.toString());
//			return (UsuarioDTO) query.getSingleResult();
//
//		} catch (PersistenceException e) {
//			throw new PersistenciaException("No se pudo hacer la consulta." + e.getMessage(), e);
//		}
//	}

}
