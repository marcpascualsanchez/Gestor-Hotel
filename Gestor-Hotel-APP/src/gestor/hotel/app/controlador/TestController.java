/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.modelo.Cliente;
import gestor.hotel.app.modelo.Habitacion;

/**
 *
 * @author user
 */
public class TestController {
    /*SOLO PARA TESTEAR CODIGO COMO DEV*/
    
    static String mysqlUrl = "jdbc:mysql://localhost/proyecto_hotel";
    static String mysqlUser = "root";
    static String mysqlPass = "";
    

    public static void main(String[] args) {
        MySQLController mysqlC = new MySQLController(mysqlUrl, mysqlUser, mysqlPass);
        mysqlC.connect();
        
        //probar inserts y deletes
        
        //campos: `cliente` (`id`, `nombre`, `dni`, `nacionalidad`, `telefono`, `email`, `ocupacion`, `estado_civil`, `id_usuario_creador`)
        //Cliente c1 = new Cliente(0, "SERGI", "777", "cat", "0009", "seru@mail.com", "sonidista", "casat", 14);
        //c1.insert(mysqlC);
        
        //Habitacion h1 = new Habitacion(3, 17, 99.5f, 14);
        //h1.update(mysqlC);
        //h1.delete(mysqlC);
       
        /*
        //cargar datos de bdd a nuestra app
        ClienteManager cm1 = new ClienteManager(mysqlC);
        cm1.load();
        System.out.println(cm1.toString());
        
        HabitacionManager hm1 = new HabitacionManager(mysqlC);
        hm1.load();
        System.out.println(hm1.toString());
*/
    }
}
