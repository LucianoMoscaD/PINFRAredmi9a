package com.capa1presentacion;

import javax.annotation.PostConstruct;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionEmpleadoService;
import com.capa3Persistencia.exception.PersistenciaException;
import com.utils.ExceptionsTools;
import javax.enterprise.context.SessionScoped;	//JEE8
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;


//@ManagedBean(name="usuario")

@Named(value="gestionEmpleado")		//JEE8
@SessionScoped				        //JEE8
public class GestionEmpleado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionEmpleadoService persistenciaBean;
	
	private Empleado empleadoSeleccionado;
	
	public GestionEmpleado() {
		super();
	}
	@PostConstruct
	public void init() {
		empleadoSeleccionado=new Empleado();
	}
	
	//Pasar a modo 
	public String salvarCambios() {
			
			Empleado empleadoNuevo;
			try {
				empleadoNuevo = (Empleado) persistenciaBean.agregarEmpleado(empleadoSeleccionado);
				//actualizamos id
				Long nuevoId=empleadoNuevo.getId();
				//vaciamos empleadoSeleccionado como para ingresar uno nuevo
				empleadoSeleccionado=new Empleado();
				
				//mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				  "Se ha agregado un nuevo Empleado con id:"+nuevoId.toString(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				return "";
				
			} 
			catch (PersistenciaException e) {
				
				Throwable rootException=ExceptionsTools.getCause(e);
				String msg1=e.getMessage();
				String msg2=ExceptionsTools.formatedMsg(rootException);
				//mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				
				e.printStackTrace();
			}
			finally {
				
			}
			
			return "";
	}
	
	public String reset() {
		empleadoSeleccionado=new Empleado();
		return "";
	}
	
	public GestionEmpleadoService getPersistenciaBean() {
		return persistenciaBean;
	}
	public void setPersistenciaBean(GestionEmpleadoService persistenciaBean) {
		this.persistenciaBean = persistenciaBean;
	}
	public Empleado getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}
	public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}
		
	
}
