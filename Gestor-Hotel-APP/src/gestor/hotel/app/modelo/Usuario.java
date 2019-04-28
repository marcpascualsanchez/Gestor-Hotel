/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.modelo;

import gestor.hotel.app.controlador.MySQLController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author user
 */
public class Usuario extends Entity{
    //tabla
    public static String tableName = "usuario";
    
    //campos
    public static List<String> fieldNames = new ArrayList<>(
        Arrays.asList("id", "nombre", "clave", "admin", "id_usuario_creador")
    );

    public int id;
    public String nombre;
    public String clave;
    public int admin;
    public Map<String, String> propertyMap;
    
    //campos `usuario` (`id`, `nombre`, `clave`, `admin`)
    public Usuario(int id, String nombre, String clave, int admin){
        try{
            this.id = id;
            this.nombre = nombre;
            this.clave = clave;
            this.admin = admin;
            this.setPropertyMap();
        }
        catch(Exception e){
            System.err.println("Error creating usuario object.");
        }
    }
    
    public Usuario(){
        //para crear objetos vacios desde HabitacionManager
    }
    
    /*SETTERS&GETTERS*/
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
        
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getClave(){
        return this.clave;
    }
    
    public void setClave(String clave){
        this.clave = clave;
    }
    
    public int getAdmin(){
        return this.admin;
    }
    
    public void setAdmin(int admin){
        this.admin = admin;
    }
    
    /*END OF SETTERS&GETTERS*/
    
    @Override
    public void setPropertyMap(){
        this.propertyMap = new LinkedHashMap<String, String>();
        
        this.propertyMap.put(this.fieldNames.get(0), String.valueOf(this.id));
        this.propertyMap.put(this.fieldNames.get(1), String.valueOf(this.nombre));
        this.propertyMap.put(this.fieldNames.get(2), String.valueOf(this.clave));
        this.propertyMap.put(this.fieldNames.get(3), String.valueOf(this.admin));
    }
    
    public void insert(MySQLController mysqlC){
        super.insert(mysqlC, this.tableName, this.propertyMap);
    }
    
    public void update(MySQLController mysqlC){
        super.update(mysqlC,this.tableName, this.propertyMap, this.id);
    }
    
    public void delete(MySQLController mysqlC){
        super.delete(mysqlC, this.tableName, this.id);
    }
    
    @Override
    public String toString(){
        return super.toString(this.fieldNames, this.propertyMap);
    }
}
