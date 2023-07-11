<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="org.utec.utils.BuscarAlumnos" import="org.utec.utils.Alumno"%>

<%

	BuscarAlumnos busqueda=new BuscarAlumnos();
	String critNombre=request.getParameter("nombre");
	String critApellido=request.getParameter("apellido");
	Integer critCI= Integer.parseInt(request.getParameter("ci"));
	Alumno alumno= busqueda.buscarAlumno( critNombre, critApellido, critCI);


%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscador de usuarios</title>
    <style>
        html {
        	font-family: Gill Sans Extrabold, sans-serif;
        }
}
        img{
            margin: 40px;
        }

        form {
            padding: 20px;
            background-color: lightgray;
            margin:15px;
            border-radius: 4px;
        }
        h2 {
        margin: 15px;
        }      
    </style>
</head>
<body>
    <h1><img src="uteclogo.jpg" alt="Logo UTEC" width="200px" height=auto>Sistema de Gestión de Alumnos</h1>
    <hr color="skyblue" size="5px">
    <h2>Usuario</h2>
 
   
   
    <form>
        <table>
            <tr>
                <td>Primer Nombre: </td>
                <td><input type="text" name="nombre1" value="<%= alumno.getNombre1() %>"></td>
            </tr>
            <tr>
                <td>Segundo Nombre: </td>
                <td><input type="text" name="nombre2" value="<%= alumno.getNombre2() %>"></td>
            </tr>
            <tr>
                <td>Primer Apellido: </td>
                <td><input type="text" name="apellido1" value="<%= alumno.getApellido1() %>"></td>
            </tr>
            <tr>
                <td>Segundo Apellido: </td>
                <td><input type="text" name="apellido2" value="<%= alumno.getApellido2() %>"></td>
            </tr>
            <tr>
                <td>Constraseña: </td>
                <td><input type="text" name="contrasena" value="<%= alumno.getContrasena() %>"></td>
            </tr>
            <tr>
                <td>Cédula de identidad: </td>
                <td><input type="text" name="cedula" value="<%= alumno.getCi() %>"></td>
            </tr>
           
            <tr>
                <td>Fecha de nacimiento:</td>
                <td><input type="text" name="fechaNac" value="<%= alumno.getfechaNac() %>"></td>
            </tr>
            <tr>
                <td>Telefono: </td>
                <td><input type="text" name="telefono" value="<%= alumno.getTelefono() %>"></td>
            </tr>
            <tr>
                <td>Departamento: </td>
                <td><input type="text" name="departamento" value="<%= alumno.getDepartamento() %>"></td>
            </tr>
             <tr>
                <td>Localidad: </td>
                <td><input type="text" name="localidad" value="<%= alumno.getLocalidad() %>"></td>
            </tr>
             <tr>
                <td>Email Personal: </td>
                <td><input type="text" name="mail" value="<%= alumno.getMail() %>"></td>
            </tr>
             <tr>
                <td>Email Institucional:</td>
                <td><input type="text" name="mailInstitucional" value="<%= alumno.getmailInstitucional() %>"></td>
            </tr>
            <tr>
                <td>ITR: </td>
                <td><input type="text" name="itr" value="<%= alumno.getItr() %>"></td>
            </tr>
            <tr>
                <td>Tipo de Usuario: </td>
                <td><input type="text" name="rol" value="<%= alumno.getRol() %>"></td>
            </tr>
            <!-- Dependiendo del rol elegido se muestran los siguientes campos -->
	
			<!-- Estudiante -->
            <tr>
                <td>Año de ingreso a la carrera: </td>
                <td><input type="text" name="generacion" value="<%= alumno.getGeneracion() %>"></td>
            </tr>
             <!-- Tutor -->
            <tr>
                <td>Area a la que pertenece: </td>
                <td><input type="text" name="area" value="<%= alumno.getArea() %>"></td>
            </tr>
            <!-- Tutor -->
            <tr>
                <td>Rol que cumple: </td>
                <td><input type="text" name="rol" value="<%= alumno.getRol() %>"></td>
            </tr>
     
        </table>
    </form>


</body>
</html>
