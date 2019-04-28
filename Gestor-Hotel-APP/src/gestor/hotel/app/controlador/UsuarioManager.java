/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.modelo.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class UsuarioManager extends EntityManager{
    //tabla
    public static String tableName = "usuario";
    
    public ArrayList<Usuario> usuarioList;
    public MySQLController controller;
    
    UsuarioManager(MySQLController controller){ 
        this.controller = controller;
    }
    
    public ArrayList<Usuario> getUsuarioList(){
        return this.usuarioList;
    }
    
    @Override
    public void load(){
        this.usuarioList = new ArrayList<>();
        ResultSet results;
        Usuario currentUsuario;
        
        try{
          results = this.controller.select("*", tableName, "");
          
         // iterate through the java resultset
          while (results.next())
          {
            currentUsuario = new Usuario();
            
            currentUsuario.setId(results.getInt("id"));	
            currentUsuario.setNombre(results.getString("nombre"));
            currentUsuario.setClave(results.getString("clave"));
            currentUsuario.setAdmin(results.getInt("admin"));
            
            currentUsuario.setPropertyMap();

            this.usuarioList.add(currentUsuario);
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
        
        for (Usuario currentHabitacion : this.usuarioList) {
            result += currentHabitacion.toString() + "\n";
        }
        
        return result;
    }    
}
