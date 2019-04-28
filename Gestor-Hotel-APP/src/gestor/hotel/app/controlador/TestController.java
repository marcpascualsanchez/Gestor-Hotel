/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.modelo.Cliente;
import gestor.hotel.app.modelo.Habitacion;
import gestor.hotel.app.modelo.Hospedaje;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class TestController {
    /*SOLO PARA TESTEAR CODIGO COMO DEV*/
    
    private static String mysqlUrl = "jdbc:mysql://localhost/proyecto_hotel";
    private static String mysqlUser = "root";
    private static String mysqlPass = "";
    

    public static void main(String[] args) {
        MySQLController mysqlC = new MySQLController(mysqlUrl, mysqlUser, mysqlPass);
        mysqlC.connect();
        
        //probar inserts y deletes
        
        //campos: `cliente` (`id`, `nombre`, `dni`, `nacionalidad`, `telefono`, `email`, `ocupacion`, `estado_civil`, `id_usuario_creador`)
        //Cliente c1 = new Cliente(11, "Sergi", "777", "cat", "0009", "seru@mail.com", "sonidista", "casat", 14);
        //c1.update(mysqlC);
        
        //Habitacion h1 = new Habitacion(10, 110, 99.5f, 14);
        //h1.update(mysqlC);
        //h1.delete(mysqlC);
        //Hospedaje hos1 = new Hospedaje(4, 2, 1, new Date(Calendar.getInstance().getTime().getTime()), new Date(Calendar.getInstance().getTime().getTime()), 14);
        //System.out.println(hos1.toString());
        //hos1.delete(mysqlC);
       
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
