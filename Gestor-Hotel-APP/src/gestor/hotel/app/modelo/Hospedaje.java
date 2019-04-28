/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.modelo;

import gestor.hotel.app.controlador.MySQLController;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class Hospedaje extends Entity {
    //tabla
    public static String tableName = "hospedaje";
    
    //campos
    public static List<String> fieldNames = new ArrayList<>(
        Arrays.asList("id", "id_cliente", "id_habitacion", "fecha_inicio", "fecha_final", "id_usuario_creador")
    );

    public int id;
    public int idCliente;
    public int idHabitacion;
    public Date fechaInicio;
    public Date fechaFinal;
    public int id_usuario_creador;

    public Map<String, String> propertyMap;
    
    //campos `hospedaje` (`id`, `id_cliente`, `id_habitacion`, `fecha_inicio`, `fecha_final`, `id_usuario_creador`)
    public Hospedaje(int id, int idCliente, int idHabitacion, Date fechaInicio, Date fechaFinal, int id_usuario_creador){
        try{
            this.id = id;
            this.idCliente = idCliente;
            this.idHabitacion = idHabitacion;
            this.fechaInicio = fechaInicio;
            this.fechaFinal = fechaFinal;
            this.id_usuario_creador = id_usuario_creador;
            //this.setFieldNames();
            this.setPropertyMap();
        }
        catch(Exception e){
            System.err.println("Error creating habitacion object.");
        }
    }
    
    public Hospedaje(){
        //para crear objetos vacios desde HabitacionManager
    }
    
    /*SETTERS&GETTERS*/
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public int getIdCliente(){
        return this.idCliente;
    }

    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public int getIdHabitacion(){
        return this.idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion){
        this.idHabitacion = idHabitacion;
    } 
        
    public Date getFechaInicio(){
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio){
        this.fechaInicio = fechaInicio;
    } 

    public Date getFechaFinal(){
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal){
        this.fechaFinal = fechaFinal;
    }
    
    public int getId_usuario_creador(){
        return this.id_usuario_creador;
    }

    public void setId_usuario_creador(int id_usuario_creador){
        this.id_usuario_creador = id_usuario_creador;
    }
    
    /*END OF SETTERS&GETTERS*/
    
    @Override
    public void setPropertyMap(){
        this.propertyMap = new LinkedHashMap<String, String>();
        
        this.propertyMap.put(this.fieldNames.get(0), String.valueOf(this.id));
        this.propertyMap.put(this.fieldNames.get(1), String.valueOf(this.idCliente));
        this.propertyMap.put(this.fieldNames.get(2), String.valueOf(this.idHabitacion));
        this.propertyMap.put(this.fieldNames.get(3), String.valueOf(this.fechaInicio));
        this.propertyMap.put(this.fieldNames.get(4), String.valueOf(this.fechaFinal));
        this.propertyMap.put(this.fieldNames.get(5), String.valueOf(this.id_usuario_creador));
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

