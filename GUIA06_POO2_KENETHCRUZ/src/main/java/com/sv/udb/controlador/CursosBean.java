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
@Named(value = "cursosBean")
@ViewScoped
public class CursosBean implements Serializable{
    @EJB
    private CursosFacadeLocal FCDECursos;    
    private Cursos objeCurs;
    private List<Cursos> listCurs;
    private boolean guardar;
    private LOG4J log;
    /**
     * Creates a new instance of CursosBean
     */
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDECursos.create(this.objeCurs);
            this.listCurs.add(this.objeCurs);
            log.info("Curso Registrado: "+objeCurs.getNombCurs());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al registrar Curso");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listCurs.remove(this.objeCurs); //Limpia el objeto viejo
            FCDECursos.edit(this.objeCurs);
            this.listCurs.add(this.objeCurs); //Agrega el objeto modificado
            log.info("Curso Modificado: "+objeCurs.getNombCurs());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al modificar Curso");
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
            FCDECursos.remove(this.objeCurs);
            this.listCurs.remove(this.objeCurs);
            log.info("Curso Eliminado: "+objeCurs.getNombCurs());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al eliminar Curso");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public CursosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se ha iniciado el modelo curso " );
    }
    
    public void limpForm()
    {
        this.objeCurs = new Cursos();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listCurs = FCDECursos.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeCurs = FCDECursos.find(codi);
            this.guardar = false;
            log.info("Curso Consultado: "+objeCurs.getNombCurs());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeCurs.getNombCurs()) + "')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Error al consultar Curso");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }

    public List<Cursos> getListCurs() {
        return listCurs;
    }

    public void setListCurs(List<Cursos> listCurs) {
        this.listCurs = listCurs;
    }

    public Cursos getObjeCurs() {
        return objeCurs;
    }

    public void setObjeCurs(Cursos objeCurs) {
        this.objeCurs = objeCurs;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
        
}
