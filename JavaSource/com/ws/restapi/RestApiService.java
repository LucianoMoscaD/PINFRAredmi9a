package com.ws.restapi;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionUsuarioService;

@Path("usuarios")
public class RestApiService {

	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@POST
	@Path("crearUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public Usuario crearUsuario(Usuario nuevoUsuario) {
	    try {

	        Usuario usuarioCreado = gestionUsuarioService.agregarUsuario(nuevoUsuario);
	        
	        return usuarioCreado;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@GET
	@Path("obtenerUsuario/{id}")
	@Produces("application/json")
	public Usuario obtenerUsuario(@PathParam("id") Long id){
		try {
			 Usuario usuario = gestionUsuarioService.buscarUsuario(id);
			 if (usuario==null) {
				 return new Usuario();
			 }
			 return usuario;
		}catch(Exception e) {
			e.printStackTrace();
			return new Usuario(); 
		}	
	}
	
	@GET
	@Path("listarUsuarios")
	@Produces("application/json")
	public List<Usuario> listarEmpleados(){
		

		try {
			 List<Usuario> listaUsuarios = gestionUsuarioService.seleccionarUsuarios();
			 return listaUsuarios;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return  new ArrayList<Usuario>(); 
		}	
	}
//		
//	@PUT
//	@Path("modificarUsuario/{id}")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Usuario modificarUsuario(@PathParam("id") Long id, Usuario usuarioModificado) {
//	    try {
//	        Usuario usuarioExistente = gestionUsuarioService.buscarUsuario(id);
//	        if (usuarioExistente == null) {
//	            return new Usuario(); // Or throw an exception or return an error response
//	        }
//	        
//	        // Update the attributes of the existing usuario with the modified usuario
//	        usuarioExistente.setNombre(usuarioModificado.getNombre());
//	        usuarioExistente.setApellido(usuarioModificado.getApellido());
//	        // Update other attributes similarly
//	        
//	        // Save the modified usuario
//	        gestionUsuarioService.modificarUsuario(usuarioExistente);
//	        
//	        return usuarioExistente;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return new Usuario();
//	    }
//	}
}
