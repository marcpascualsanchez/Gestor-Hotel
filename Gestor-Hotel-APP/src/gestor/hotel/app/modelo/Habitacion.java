/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.modelo;
import gestor.hotel.app.controlador.MySQLController;
import java.util.*;
/**
 *
 * @author user
 */
public class Habitacion implements Insertable, Deleteable{
    //tabla
    public static String tableName = "habitacion";
    
    //campos
    public int id;
    public int numero;
    public float precio;
    public int id_usuario_creador;
    public List<String> paramList;
    
    //campos `habitacion` (`id`, `numero`, `precio`, `id_usuario_creador`)
    Habitacion(int id, int numero, float precio, int id_usuario_creador){
        try{
            this.id = id;
            this.numero = numero;
            this.precio = precio;
            this.id_usuario_creador = id_usuario_creador;
            this.setList();
        }
        catch(Exception e){
            System.err.println("Error creating habitacion object.");
        }
    }
    
    public Habitacion(){
        //para crear objetos vacios desde HabitacionManager
    }
    
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
        
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
    
    @Override
    public void setList(){
        this.paramList = new ArrayList<String>();
        
        this.paramList.add(String.valueOf(this.id));
        this.paramList.add(String.valueOf(this.numero));
        this.paramList.add(String.valueOf(this.precio));
        this.paramList.add(String.valueOf(this.id_usuario_creador));
    }
    
    @Override
    public void insert(MySQLController controller){
        controller.insert(tableName, this.paramList);
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

