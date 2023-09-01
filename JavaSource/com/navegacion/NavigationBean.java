package com.navegacion;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa1presentacion.GestionUsuario;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.exception.PersistenciaException;

import java.io.IOException;
import java.io.Serializable;

@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	GestionUsuario gestionUsuarios;
	
	@Inject
	LoginBean loginBean;

    public void goToLogin() throws IOException {
        gestionUsuarios.setUsuarioSeleccionado(new Usuario());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Login.xhtml");
    }
     
    public void goToRegistro()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Registro.xhtml");
    }
    
    public void goToRegistroEstudiante()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        gestionUsuarios.setUsuarioSeleccionado(new Usuario());
        externalContext.redirect(externalContext.getRequestContextPath() + "/AltaEstudiante.xhtml");
    }
    
    public void goToRegistroFuncionario()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        gestionUsuarios.setUsuarioSeleccionado(new Usuario());
        externalContext.redirect(externalContext.getRequestContextPath() + "/AltaFuncionario.xhtml");
    }
    
    public void goToRegistroTutor()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        gestionUsuarios.setUsuarioSeleccionado(new Usuario());
        externalContext.redirect(externalContext.getRequestContextPath() + "/AltaTutor.xhtml");
    }
    
    public void goToBienvenida()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Bienvenida.xhtml");
    }
    
    public void goToIndex()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }
    
    public void goToModificarUsuarios() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/ModificarUsuario.xhtml");
    }
    
    public void goToListarUsuarios()  throws IOException, PersistenciaException {
        gestionUsuarios.mostrarUsuarios();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/ListaUsuarios.xhtml");
    }
    
    public void goToModificacion() {
    	
    	try {
        	gestionUsuarios.cargarUsuarioAModificar();
        	if(gestionUsuarios.getUsuarioAModificar()!=null) {
        		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/Modificacion.xhtml");
        	}
    
    	} catch(Exception npe) {
    		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No se ha encontrado el usuario con el siguiente documento:" + gestionUsuarios.getSelectedUserCedula(), "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    	}
    }
    
    public void goToModificarDatosPropios() throws IOException {
    	gestionUsuarios.setSelectedUserCedula(loginBean.getUsuarioLogueado().getDocumento());
    	try {
			gestionUsuarios.cargarUsuarioAModificar();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        externalContext.redirect(externalContext.getRequestContextPath() + "/Modificacion.xhtml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}

