/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import static gestor.hotel.app.controlador.HabitacionManager.tableName;
import gestor.hotel.app.modelo.Habitacion;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController extends FXController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML public AnchorPane mainWindow;
    @FXML private Button buttonLogin;
    @FXML private TextField textAreaUser;
    @FXML private TextField textAreaPassword;
    @FXML private Text textError;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        System.out.println("initializing...");
    }   
    
    public void enterIntoMenu(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Login");
    }
    
    public boolean validateEnter(){
        boolean result = false;
        String user = textAreaUser.getText();
        String pass = textAreaPassword.getText();
        String conditions = "nombre='" + user + "' AND clave='" + pass +"'";//SQL conditions
        String fields = "nombre, clave";
        String table = "usuario";
        ResultSet resultsSQL;
        
        try{
          resultsSQL = GestorHotelAPP.mysqlC.select(fields, table, conditions);
          
         // iterate through the java resultset
          resultsSQL.next();
          if( (resultsSQL.getString("nombre").equals(user)) && (resultsSQL.getString("clave").equals(pass)) ){
              result = true;
          }
        }
        catch (Exception e)
        {
          System.err.println("Got an exception loading BDD data in class " + this.getClass().getName());
          System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public void login(){
        System.out.println("log in...");
        //if(this.validateEnter()){
            this.enterIntoMenu();
        /*}else{
            this.showError();
        }*/
    }
    
    public void showError(){
        textError.setText("Error de autentificación");
    }
    
}
