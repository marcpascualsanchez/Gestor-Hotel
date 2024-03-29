/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MenuController extends FXController implements Initializable {
    @FXML AnchorPane mainWindow;
    @FXML private Text textError;

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
        if(GestorHotelAPP.currentUsuarioPermission == 1){
            changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/UsuarioGestor.fxml", "Gestor de Usuarios");
        }else{
            this.showError();
        }
    }
    
    public void enterIntoHospedajesManagement(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/HospedajeGestor.fxml", "Gestor de Reservas");
    }
    
    public void enterIntoLogin(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Login.fxml", "Login");
    }
    
    public void showError(){
        textError.setText("Error de permisos.");
    }
    
}
