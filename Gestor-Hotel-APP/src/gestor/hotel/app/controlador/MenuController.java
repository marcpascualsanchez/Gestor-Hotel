/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MenuController extends FXController implements Initializable {
    @FXML AnchorPane mainWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void enterIntoHabitacionesManagement(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/HabitacionGestor.fxml", "Gestor de Habitaciones");
    }
    
    public void enterIntoClientesManagement(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/ClienteGestor.fxml", "Gestor de Clientes");
    }
    
    public void enterIntoUsuariosManagement(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/UsuarioGestor.fxml", "Gestor de Usuarios");
    }
    
    public void enterIntoLogin(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Login.fxml", "Login");
    }
    
}
