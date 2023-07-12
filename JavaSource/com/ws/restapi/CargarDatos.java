package com.ws.restapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa3Persistencia.dao.EmpleadosEmpresaDAO;
import com.capa3Persistencia.dao.UsuariosDAO;
import com.capa3Persistencia.entities.EmpleadoEmpresa;
import com.capa3Persistencia.entities.UsuarioPersistencia;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UsuariosDAO usuariosDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()+"\n");
		PrintWriter out = response.getWriter();
		
		try {
			UsuarioPersistencia e=new UsuarioPersistencia("Gerardo", "ventas");
			UsuarioPersistencia empleadoCreado = usuariosDAO.agregarUsuario(e);
			out.println("Se creo el empleado:"+ empleadoCreado.getId()+" Nombre"+empleadoCreado.getNombre());
			
			e=new UsuarioPersistencia("Daniel", "ventas");
			empleadoCreado = usuariosDAO.agregarUsuario(e);
			out.println("Se creo el empleado:"+ empleadoCreado.getId()+" Nombre"+empleadoCreado.getNombre());
			
			e=new UsuarioPersistencia("Maria", "ventas");
			empleadoCreado = usuariosDAO.agregarUsuario(e);
			out.println("Se creo el empleado:"+ empleadoCreado.getId()+" Nombre"+empleadoCreado.getNombre());
			
			
			
			out.println("Se creo el usuario:"+ empleadoCreado.getId());
			
		}catch(Exception e) {
			out.println("No se creo el usuario:"+ e.getClass().getSimpleName()+"-"+e.getMessage());
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
