/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.modelo.Hospedaje;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class HospedajeManager extends EntityManager{
    //tabla
    public static String tableName = "hospedaje";
    
    public ArrayList<Hospedaje> hospedajeList;
    public MySQLController controller;
    
    HospedajeManager(MySQLController controller){ 
        this.controller = controller;
    }
    
    public ArrayList<Hospedaje> getHospedajeList(){
        return this.hospedajeList;
    }
    
    @Override
    public void load(){
        this.hospedajeList = new ArrayList();
        ResultSet results;
        Hospedaje currentHospedaje;
        
        try{
          results = this.controller.select("*", tableName, "");
          
         // iterate through the java resultset
          while (results.next())
          {
            currentHospedaje = new Hospedaje();
            
            currentHospedaje.setId(results.getInt("id"));   
            currentHospedaje.setIdCliente(results.getInt("id_cliente"));
            currentHospedaje.setIdHabitacion(results.getInt("id_habitacion"));
            currentHospedaje.setFechaInicio(results.getDate("fecha_inicio"));
            currentHospedaje.setFechaFinal(results.getDate("fecha_final"));
            currentHospedaje.setId_usuario_creador(results.getInt("id_usuario_creador"));
            
            currentHospedaje.setPropertyMap();

            this.hospedajeList.add(currentHospedaje);
          }
          //System.out.println(this.toString());
        this.controller.closeStatement();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception loading BDD data in class " + this.getClass().getName());
          System.err.println(e.getMessage());
        }
    }
    
    @Override
    public String toString(){
        String result = "";
        
        for (Hospedaje currentHospedaje : this.hospedajeList) {
            result += currentHospedaje.toString() + "\n";
        }
        
        return result;
    }    
}

