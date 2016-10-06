/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import org.apache.log4j.*;

/**
 *
 * @author Orlando Cruz
 */
public class LOG4J {
    
    private static Logger log = Logger.getLogger(LOG4J.class);
    public LOG4J() {
        try{
            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("/log4j.properties").getPath());;
            
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
        
    }
    //Muestra un mensaje tal como system.out.println
    public void trace(String mens){
        try{
            log.trace(mens);
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
        
    }
    //Se utiliza para valores tales como variables
    public void debug(String mens){
        try{
            log.debug(mens);
            System.out.println("SE HA DEPURADO LA APP");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
        
    }
    //Se utiliza para mensajes de confirmacion 
    public void info(String mens){
        try{
            log.info(mens);
            System.out.println("NUEVO LOG CREADO");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
    }
    //Se utiliza para mensaje de que todo salio en un proceso 
    public void warn(String mens){
        try{
            log.warn(mens);
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
    }
    //Se utliza para errores que no obligan a cerrar la aplicación
    public void error(String mens){
        try{
            log.error(mens);
            System.out.println("ERROR: HUBO UN PROBLEMA, REVISA TU APP");
        }
        catch(Exception e)
        {
           System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
    }
    //Se utiliza que muestra errores que obligan a cerrar la aplicación
    public void fatal(String mens){
        try{
            log.fatal(mens);
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Algo paso con el LOG: "+e);
        }
    }
    
}
