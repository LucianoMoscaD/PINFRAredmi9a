package com.ws.restapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa3Persistencia.dao.UsuariosDAO;
import com.capa3Persistencia.entities.UsuarioDTO;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/MostrarDatos")
public class MostrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UsuariosDAO usuariosDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n");
        PrintWriter out = response.getWriter();

        try {
            List<UsuarioDTO> listaUsuarios = usuariosDAO.buscarUsuarios();
            for (UsuarioDTO usuario : listaUsuarios) {
                out.println("Usuario: " + usuario.getId() + " Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
                // Print other attributes as needed
            }

        } catch (Exception e) {
            out.println("No se creó el usuario: " + e.getClass().getSimpleName() + "-" + e.getMessage());
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
