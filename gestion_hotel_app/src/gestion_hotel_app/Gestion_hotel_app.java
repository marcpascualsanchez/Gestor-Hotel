/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_hotel_app;

/**
 *
 * @author user
 */
public class Gestion_hotel_app {
    static String mysqlUrl = "jdbc:mysql://localhost/proyecto_hotel";
    static String mysqlUser = "root";
    static String mysqlPass = "";

    public static void main(String[] args) {
        MySQLController mysqlC = new MySQLController(mysqlUrl, mysqlUser, mysqlPass);
        mysqlC.connect();
        
        //probar inserts y deletes
        /*
        Cliente c1 = new Cliente(0, "nombre2","dni2", "nacionalidad2", "telf2", "email2", "ocu2", "est2", 14);
        //c1.insert(mysqlC);
        
        Habitacion h1 = new Habitacion(3, 10, 50.5f, 14);
        //h1.insert(mysqlC);
        //h1.delete(mysqlC);
        */
        
        //cargar datos de bdd a nuestra app
        ClienteManager cm1 = new ClienteManager(mysqlC);
        cm1.load();
        System.out.println(cm1.toString());
        
        HabitacionManager hm1 = new HabitacionManager(mysqlC);
        hm1.load();
        System.out.println(hm1.toString());
    }
    
}
