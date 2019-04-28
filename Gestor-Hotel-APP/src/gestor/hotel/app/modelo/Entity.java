/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.modelo;

import gestor.hotel.app.controlador.MySQLController;
import static gestor.hotel.app.modelo.Cliente.tableName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class Entity implements Insertable, Deleteable, Updateable{  
    public void setPropertyMap(){};
    public int id;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    @Override
    public void insert(MySQLController controller, String tableName, Map<String, String> propertyMap){
        if(propertyMap != null){
            controller.insert(tableName, propertyMap);
        }else{
            System.err.println("Property map property can't be null if insert is needed.");
        }
    }
    
    @Override
    public void delete(MySQLController controller, String tableName, int id){
        controller.delete(tableName, id);
    }
    
    @Override
    public void update(MySQLController controller, String tableName, Map<String, String> propertyMap, int id){
        controller.update(tableName, propertyMap, id);
    }
    
    public String toString(List<String> fieldNames, Map<String, String> propertyMap){
        String result = ""; 
        
        for (int i = 0; i < (fieldNames.size() - 1); i++) {//no usamos iterador para controlar la ultima coma
            result += fieldNames.get(i) + ": " + propertyMap.get(fieldNames.get(i)) + ", ";
        }
        
        result += fieldNames.get(fieldNames.size() - 1) + ": " + propertyMap.get(fieldNames.get(fieldNames.size() - 1));
        
        return result;
    }
}
