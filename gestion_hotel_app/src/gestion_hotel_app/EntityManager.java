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
public abstract class EntityManager {
    public static String tableName;
    public List<String> paramList;
    
    public abstract void load();
}
