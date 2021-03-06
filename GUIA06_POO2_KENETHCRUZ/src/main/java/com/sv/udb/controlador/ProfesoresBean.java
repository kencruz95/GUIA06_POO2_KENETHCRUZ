/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.*;
import com.sv.udb.modelo.*;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Laboratorio
 */
@Named(value = "profesoresBean")
@ViewScoped
public class ProfesoresBean implements Serializable{
    @EJB
    private ProfesoresFacadeLocal FCDEProfesores;    
    private Profesores objeProf;
    private List<Profesores> listProf;
    private boolean guardar;
    private LOG4J log;
    /**
     * Creates a new instance of ProfesoresBean
     */
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEProfesores.create(this.objeProf);
            this.listProf.add(this.objeProf);
            log.info("Profesor Registrado: "+objeProf.getNombProf()+" "+objeProf.getApelProf());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al registrar Profesor");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listProf.remove(this.objeProf); //Limpia el objeto viejo
            FCDEProfesores.edit(this.objeProf);
            this.listProf.add(this.objeProf); //Agrega el objeto modificado
            log.info("Profesor Modificado: "+objeProf.getNombProf()+" "+objeProf.getApelProf());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al modificar Profesor");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEProfesores.remove(this.objeProf);
            this.listProf.remove(this.objeProf);
            log.info("Profesor Eliminado: "+objeProf.getNombProf()+" "+objeProf.getApelProf());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al eliminar Profesor");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public ProfesoresBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se ha iniciado el modelo profesores " );
    }
    
    public void limpForm()
    {
        this.objeProf = new Profesores();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listProf = FCDEProfesores.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeProf = FCDEProfesores.find(codi);
            this.guardar = false;
            log.info("Profesor Consultado: "+objeProf.getNombProf()+" "+objeProf.getApelProf());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeProf.getNombProf(), this.objeProf.getApelProf()) + "')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Error al consultar Profesor");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }

    public Profesores getObjeProf() {
        return objeProf;
    }

    public void setObjeProf(Profesores objeProf) {
        this.objeProf = objeProf;
    }

    public List<Profesores> getListProf() {
        return listProf;
    }

    public void setListProf(List<Profesores> listProf) {
        this.listProf = listProf;
    }

        
    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
        
}
