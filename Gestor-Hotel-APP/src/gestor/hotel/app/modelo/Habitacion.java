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
public class Habitacion extends Entity{
    //tabla
    public static String tableName = "habitacion";
    
    //campos
    public static List<String> fieldNames = new ArrayList<>(
        Arrays.asList("id", "numero", "precio", "id_usuario_creador")
    );

    public int numero;
    public float precio;
    public int id_usuario_creador;
    public Map<String, String> propertyMap;
    
    //campos `habitacion` (`id`, `numero`, `precio`, `id_usuario_creador`)
    public Habitacion(int id, int numero, float precio, int id_usuario_creador){
        try{
            this.id = id;
            this.numero = numero;
            this.precio = precio;
            this.id_usuario_creador = id_usuario_creador;
            //this.setFieldNames();
            this.setPropertyMap();
        }
        catch(Exception e){
            System.err.println("Error creating habitacion object.");
        }
    }
    
    public Habitacion(){
        //para crear objetos vacios desde HabitacionManager
    }
    
    /*SETTERS&GETTERS*/
        
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public float getPrecio(){
        return this.precio;
    }
    
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    public int getId_usuario_creador(){
        return this.id_usuario_creador;
    }

    public void setId_usuario_creador(int id_usuario_creador){
        this.id_usuario_creador = id_usuario_creador;
    }
    
    /*END OF SETTERS&GETTERS*/
    
    /*JavaFX SETTERS*/
    public void setIdFX(SimpleIntegerProperty id){
        this.numero = id.get();
    }
    
    public SimpleIntegerProperty getIdFX(){
        return new SimpleIntegerProperty(this.id);
    }
    
    public void setNumeroFX(SimpleIntegerProperty numero){
        this.numero = numero.get();
    }
    
    public SimpleIntegerProperty getNumeroFX(){
        return new SimpleIntegerProperty(this.numero);
    }
    
    public void getPrecioFX(SimpleFloatProperty precio){
        this.precio = precio.get();
    }
    
    public SimpleFloatProperty getPrecioFX(){
        return new SimpleFloatProperty(this.precio);
    }
    /*END OF JavaFX SETTERS*/
    
    /*
    @Override
    public void setFieldNames(){
        this.fieldNames = new ArrayList<String>();
        
        this.fieldNames.add("id");
        this.fieldNames.add("numero");
        this.fieldNames.add("precio");
        this.fieldNames.add("id_usuario_creador");
    }
    */
    
    @Override
    public void setPropertyMap(){
        this.propertyMap = new LinkedHashMap<String, String>();
        
        this.propertyMap.put(this.fieldNames.get(0), String.valueOf(this.id));
        this.propertyMap.put(this.fieldNames.get(1), String.valueOf(this.numero));
        this.propertyMap.put(this.fieldNames.get(2), String.valueOf(this.precio));
        this.propertyMap.put(this.fieldNames.get(3), String.valueOf(this.id_usuario_creador));
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

