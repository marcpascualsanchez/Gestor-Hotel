/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class FXController {
    
    public void changeWindow(AnchorPane previousWindow, String path, String windowTitle){
        try 
        {
            Stage nextStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResourceAsStream(path));
            Scene scene = new Scene(root);
            nextStage.setTitle(windowTitle);
            nextStage.setScene(scene);
            nextStage.getIcons().add(new Image(getClass().getResourceAsStream("/gestor/hotel/app/vista/img/icon.png")));
            
            nextStage.initOwner(previousWindow.getScene().getWindow());
            ((Stage)previousWindow.getScene().getWindow()).close();
            nextStage.show();
            
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
