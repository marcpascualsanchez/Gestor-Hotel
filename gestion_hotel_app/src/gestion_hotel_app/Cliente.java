/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_hotel_app;
import java.util.*;

/**
 *
 * @author user
 */
public class Cliente implements Insertable, Deleteable{
    //tabla
    public static String tableName = "cliente";
    
    //campos
    public int id;
    public String nombre;
    public String dni;
    public String nacionalidad;
    public String telefono;
    public String email;
    public String ocupacion;
    public String estado_civil;
    public int id_usuario_creador;
    public List<String> paramList;
    
    //campos: `cliente` (`id`, `nombre`, `dni`, `nacionalidad`, `telefono`, `email`, `ocupacion`, `estado_civil`, `id_usuario_creador`)
    Cliente(int id, String nombre, String dni, String nacionalidad, String telefono, String email, String ocupacion, String estado_civil, int id_usuario_creador){
        try{
            this.id = id;
            this.nombre = nombre;
            this.dni = dni;
            this.nacionalidad = nacionalidad;
            this.telefono = telefono;
            this.email = email;
            this.ocupacion = ocupacion;
            this.estado_civil = estado_civil;
            this.id_usuario_creador = id_usuario_creador;
            this.setList();
        }
        catch(Exception e){
            System.err.println("Error creating cliente object.");
        }
    }
    
    Cliente(){
        //constructor vacio para client Manager
    }
    
    /*START SETTERS & GETTERS*/
    
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

    public String getDni(){
        return this.dni;
    }
    
    public void setDni(String dni){
        this.dni = dni;
    }
    
    public String getNacionalidad(){
        return this.nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public String getOcupacion(){
        return this.ocupacion;
    }

    public void setOcupacion(String ocupacion){
        this.ocupacion = ocupacion;
    }
    
    public String getEstado_civil(){
        return this.estado_civil;
    }

    public void setEstado_civil(String estado_civil){
        this.estado_civil = estado_civil;
    }
    
    public int getId_usuario_creador(){
        return this.id_usuario_creador;
    }

    public void setId_usuario_creador(int id_usuario_creador){
        this.id_usuario_creador = id_usuario_creador;
    }
    
    @Override
    public void setList(){
        this.paramList = new ArrayList<String>();
        
        this.paramList.add(String.valueOf(this.id));
        this.paramList.add(this.nombre);
        this.paramList.add(this.dni);
        this.paramList.add(this.nacionalidad);
        this.paramList.add(this.telefono);
        this.paramList.add(this.email);
        this.paramList.add(this.ocupacion);
        this.paramList.add(this.estado_civil);
        this.paramList.add(String.valueOf(this.id_usuario_creador));
    }
    
    public List getList(){
        return this.paramList;
    }
    /*END SETTERS & GETTERS*/
    
    @Override
    public void insert(MySQLController controller){
        if(this.paramList != null){
            controller.insert(tableName, this.paramList);
        }else{
            System.err.println("Parameter list property can't be null if insert is needed.");
        }
    }
    
    @Override
    public void delete(MySQLController controller){
        controller.delete(tableName, this.id);
    }
    
    @Override
    public String toString(){
        String result = ""; 
        
        for (int i = 0; i < (this.paramList.size() - 1); i++) {//no usamos iterador para controlar la ultima coma
            result += this.paramList.get(i) + ", ";
        }
        
        result += this.paramList.get(this.paramList.size() - 1);
        
        return result;
    }
}
