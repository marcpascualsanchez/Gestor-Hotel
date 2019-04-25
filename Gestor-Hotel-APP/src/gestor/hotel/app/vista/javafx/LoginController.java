/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.vista.javafx;

import gestor.hotel.app.GestorHotelAPP;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML AnchorPane window;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResourceAsStream("Gestor.fxml"));
            Scene scene = new Scene(root);
            secondStage.setTitle("Gestor");
            secondStage.setScene(scene);
            
            secondStage.initOwner(window.getScene().getWindow());
            ((Stage)window.getScene().getWindow()).close();
            secondStage.show();
            
        } 
        catch (NullPointerException ex) 
        {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(GestorHotelAPP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
