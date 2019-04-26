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

/**
 *
 * @author user
 */
public class Cliente implements Insertable, Deleteable, Updateable{
    //tabla
    public static String tableName = "cliente";
    
    //campos
    public static List<String> fieldNames = new ArrayList<>(
        Arrays.asList("id", "nombre", "dni", "nacionalidad", "telefono", "email", "ocupacion", "estado_civil", "id_usuario_creador")
    );
    public int id;
    public String nombre;
    public String dni;
    public String nacionalidad;
    public String telefono;
    public String email;
    public String ocupacion;
    public String estado_civil;
    public int id_usuario_creador;
    public Map<String, String> propertyMap;
    
    //campos: `cliente` (`id`, `nombre`, `dni`, `nacionalidad`, `telefono`, `email`, `ocupacion`, `estado_civil`, `id_usuario_creador`)
    public Cliente(int id, String nombre, String dni, String nacionalidad, String telefono, String email, String ocupacion, String estado_civil, int id_usuario_creador){
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
            //this.setFieldNames();
            this.setPropertyMap();
        }
        catch(Exception e){
            System.err.println("Error creating cliente object.");
        }
    }
    
    public Cliente(){
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
   
    /*
    @Override
    public void setFieldNames(){
        this.fieldNames = new ArrayList<String>();
        
        this.fieldNames.add("id");
        this.fieldNames.add("nombre");
        this.fieldNames.add("dni");
        this.fieldNames.add("nacionalidad");
        this.fieldNames.add("dni");
        this.fieldNames.add("telefono");
        this.fieldNames.add("email");
        this.fieldNames.add("ocupacion");
        this.fieldNames.add("estado_civil");
        this.fieldNames.add("id_usuario_creador");
    }
    */
    
    @Override
    public void setPropertyMap(){
        this.propertyMap = new LinkedHashMap<String, String>();
        
        this.propertyMap.put(this.fieldNames.get(0), String.valueOf(this.id));
        this.propertyMap.put(this.fieldNames.get(1), this.nombre);
        this.propertyMap.put(this.fieldNames.get(2), this.dni);
        this.propertyMap.put(this.fieldNames.get(3), this.nacionalidad);
        this.propertyMap.put(this.fieldNames.get(4), this.telefono);
        this.propertyMap.put(this.fieldNames.get(5), this.email);
        this.propertyMap.put(this.fieldNames.get(6), this.ocupacion);
        this.propertyMap.put(this.fieldNames.get(7), this.estado_civil);
        this.propertyMap.put(this.fieldNames.get(8), String.valueOf(this.id_usuario_creador));
    }
    
    public Map getPropertyMap(){
        return this.propertyMap;
    }
    /*END SETTERS & GETTERS*/
    
    @Override
    public void insert(MySQLController controller){
        if(this.propertyMap != null){
            controller.insert(tableName, this.propertyMap);
        }else{
            System.err.println("Parameter list property can't be null if insert is needed.");
        }
    }
    
    @Override
    public void delete(MySQLController controller){
        controller.delete(tableName, this.id);
    }
    
    @Override
    public void update(MySQLController controller){
        controller.update(tableName, this.propertyMap, this.id);
    }
    
    @Override
    public String toString(){
        String result = ""; 
        
        for (int i = 0; i < (this.fieldNames.size() - 1); i++) {//no usamos iterador para controlar la ultima coma
            result += this.fieldNames.get(i) + ": " + this.propertyMap.get(this.fieldNames.get(i)) + ", ";
        }
        
        result += this.propertyMap.get((this.fieldNames.size() - 1));
        
        return result;
    }
}
