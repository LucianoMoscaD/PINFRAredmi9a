package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa1presentacion.Empleado;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.dao.EmpleadosEmpresaDAO;
import com.capa3Persistencia.dao.UsuariosDAO;
import com.capa3Persistencia.entities.EmpleadoEmpresa;
import com.capa3Persistencia.entities.UsuarioPersistencia;
import com.capa3Persistencia.exception.PersistenciaException;




@Stateless
@LocalBean

public class GestionUsuarioService implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuariosDAO usuariosPersistenciaDAO;		

	public Usuario fromUsuarioPersistencia(UsuarioPersistencia e) {
		Usuario usuario=new Usuario();
		usuario.setId(e.getId().longValue());
		usuario.setNombre(e.getNombre());
		usuario.setPassword(e.getPassword());
		
		return usuario;
	}
	public UsuarioPersistencia toUsuarioPersistencia(Usuario e) {
		UsuarioPersistencia usuario = new UsuarioPersistencia();
		usuario.setId(e.getId()!=null?e.getId().longValue():null);
		usuario.setNombre(e.getNombre());
		usuario.setPassword(e.getPassword());
		return usuario;
	}
	
	// servicios para capa de Presentacion
	public List<Usuario> seleccionarUsuarios() throws PersistenciaException {
		//buscamos todos los  objetos UsuarioPersistencia
		List<UsuarioPersistencia> listaUsuariosPersistencia = usuariosPersistenciaDAO.buscarUsuarios();
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		//recorremos listaUsuariosPersistencia y vamos populando listaUsuarios (haciendo la conversion requerida)
		for (UsuarioPersistencia usuarioPersistencia : listaUsuariosPersistencia) {
			listaUsuarios.add(fromUsuarioPersistencia(usuarioPersistencia));
		}
		return listaUsuarios;
	}
	
	public Usuario agregarUsuario(Usuario usuarioSeleccionado) throws PersistenciaException   {
		UsuarioPersistencia u = usuariosPersistenciaDAO.agregarUsuario(toUsuarioPersistencia(usuarioSeleccionado));
		return fromUsuarioPersistencia(u);
	}

	
	public Usuario buscarUsuario(Long id) {
		UsuarioPersistencia e = usuariosPersistenciaDAO.buscarUsuario(id);
		return fromUsuarioPersistencia(e);
	}
	
	public List<UsuarioPersistencia> buscarUsuarios() {
		List<UsuarioPersistencia> e = null;
		try {
			e = usuariosPersistenciaDAO.buscarUsuarios();
		} catch (PersistenciaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
//
//	public List<Usuario> seleccionarEmpleados(String criterioNombre,String criterioDepartamento,Boolean criterioActivo) throws PersistenciaException {
//		//buscamos empleados segun criterio indicado
//		List<EmpleadoEmpresa> listaEmpleadosEmpresa = usuariosPersistenciaDAO.seleccionarEmpleados(criterioNombre,criterioDepartamento,criterioActivo);
//		//lista para devolver la seleccion de empleados
//		List<Empleado> listaEmpleados=new ArrayList<Empleado>();
//		//recorremos listaEmpleadosEmpresa y vamos populando listaEmpleado (haciendo la conversion requerida)
//		for (EmpleadoEmpresa empleadoEmpresa : listaEmpleadosEmpresa) {
//			listaEmpleados.add(fromEmpleadoEmpresa(empleadoEmpresa));
//		}
//		return listaEmpleados;
//		
//	}
//	


//	public Empleado buscarEmpleado(Long i) {
//		EmpleadoEmpresa e = usuariosPersistenciaDAO.buscarEmpleado(i);
//		return fromEmpleadoEmpresa(e);
//	}
//	


//	public void actualizarEmpleado(Empleado empleadoSeleccionado) throws PersistenciaException   {
//		EmpleadoEmpresa e = usuariosPersistenciaDAO.modificarEmpleado(toEmpleadoEmpresa(empleadoSeleccionado));
//	}
//	
//	
	
}
