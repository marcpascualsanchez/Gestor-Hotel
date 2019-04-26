/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;
import gestor.hotel.app.modelo.Habitacion;
import java.util.ArrayList;
import java.sql.*;


/**
 *
 * @author user
 */
public class HabitacionManager extends EntityManager{
    //tabla
    public static String tableName = "habitacion";
    
    public ArrayList<Habitacion> habitacionList;
    public MySQLController controller;
    
    HabitacionManager(MySQLController controller){ 
        this.habitacionList = new ArrayList<>();
        this.controller = controller;
    }
    
    @Override
    public void load(){
        ResultSet results;
        Habitacion currentHabitacion;
        
        try{
          results = this.controller.select("*", tableName, "");
          
         // iterate through the java resultset
          while (results.next())
          {
            currentHabitacion = new Habitacion();
            
            currentHabitacion.setId(results.getInt("id"));	
            currentHabitacion.setNumero(results.getInt("numero"));
            currentHabitacion.setPrecio(results.getFloat("precio"));
            currentHabitacion.setId_usuario_creador(results.getInt("id_usuario_creador"));
            
            currentHabitacion.setPropertyMap();

            this.habitacionList.add(currentHabitacion);
          }
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
        
        for (Habitacion currentHabitacion : this.habitacionList) {
            result += currentHabitacion.toString() + "\n";
        }
        
        return result;
    }
}
