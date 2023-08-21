package com.navegacion;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.capa1presentacion.GestionUsuario;
import com.capa1presentacion.Usuario;

import java.io.IOException;
import java.io.Serializable;

@ManagedBean
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
    
    public void goToBienvenida()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Bienvenida.xhtml");
    }
    
    public void goToIndex()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }
    
    public void goToModificarUsuarios() throws IOException {
    	gestionUsuarios.actualizarListaUsuarios();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/ModificarUsuario.xhtml");
    }
    
    public void goToListarUsuarios()  throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/ListaUsuarios.xhtml");
    }
    
    public void goToModificacion() throws IOException {
    	gestionUsuarios.cargarUsuarioAModificar();
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Modificacion.xhtml");
    }
    
    public void goToModificarDatosPropios() throws IOException {
    	gestionUsuarios.setSelectedUserId(loginBean.getUsuarioLogueado().getId());
    	gestionUsuarios.cargarUsuarioAModificar();
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/Modificacion.xhtml");
    }
    
}

