/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;
import gestor.hotel.app.modelo.Cliente;
import java.util.*;
import java.sql.*;

/**
 *
 * @author user
 */
public class ClienteManager extends EntityManager{
    //tabla
    public static String tableName = "cliente";
    
    public ArrayList<Cliente> clienteList;
    public MySQLController controller;
    
    ClienteManager(MySQLController controller){ 
        this.clienteList = new ArrayList<>();
        this.controller = controller;
    }
    
    @Override
    public void load(){
        ResultSet results;
        Cliente currentCliente;
        
        try{
          this.clienteList = new ArrayList<>();
          results = this.controller.select("*", tableName, "");
          
         // iterate through the java resultset
          while (results.next())
          {
            currentCliente = new Cliente();
            
            currentCliente.setId(results.getInt("id"));	
            currentCliente.setNombre(results.getString("nombre"));
            currentCliente.setDni(results.getString("dni"));	
            currentCliente.setNacionalidad(results.getString("nacionalidad"));	
            currentCliente.setTelefono(results.getString("telefono"));	
            currentCliente.setEmail(results.getString("email"));	
            currentCliente.setOcupacion(results.getString("ocupacion"));	
            currentCliente.setEstado_civil(results.getString("estado_civil"));	
            currentCliente.setId_usuario_creador(results.getInt("id_usuario_creador"));
            
            currentCliente.setPropertyMap();

            this.clienteList.add(currentCliente);
          }
        this.controller.closeStatement();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception loading BDD data in class " + this.getClass().getName());
          System.err.println(e.getMessage());
        }
    }
    
    public List getClienteList(){
        return this.clienteList;
    }
    
    @Override
    public String toString(){
        String result = "";
        
        for (Cliente currentCliente : this.clienteList) {
            result += currentCliente.toString() + "\n";
        }
        
        return result;
    }
}
