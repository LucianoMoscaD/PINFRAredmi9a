package com.capa1presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa3Persistencia.entities.UsuarioDTO;
import com.capa3Persistencia.exception.PersistenciaException;
import com.navegacion.SesionBean;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    GestionUsuario gestionUsuarioBean;

    @Inject
    SesionBean sesionBean;
    
    private UsuarioDTO usuarioLogueado;

    public String login() throws PersistenciaException {
    	setUsuarioLogueado(gestionUsuarioBean.login());
        if (getUsuarioLogueado() != null) {
            sesionBean.setLogueado(true); 
            if(1 == getUsuarioLogueado().getAlumno()) {
            	sesionBean.setAlumno(true);
            } else {
                sesionBean.setAlumno(false);
            };
            return "Bienvenida.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }

    public String logoff() throws PersistenciaException {
        sesionBean.setLogueado(false);
        sesionBean.setAlumno(false);
        
        System.out.println(sesionBean.isLogueado());
        System.out.println(sesionBean.getAlumno());
        return "index.xhtml?faces-redirect=true";
    }

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
		
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
		
	}
}
