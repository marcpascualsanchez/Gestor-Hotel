/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app;

import gestor.hotel.app.controlador.MySQLController;
import gestor.hotel.app.controlador.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class GestorHotelAPP extends Application {
    
    private static String mysqlUrl = "jdbc:mysql://localhost/proyecto_hotel";
    private static String mysqlUser = "root";
    private static String mysqlPass = "";
    public static MySQLController mysqlC;
    
    @Override
    public void start(Stage primaryStage) {
        
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResourceAsStream("vista/javafx/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("vista/img/icon.png")));
            primaryStage.show();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mysqlC = new MySQLController(mysqlUrl, mysqlUser, mysqlPass);
        mysqlC.connect();
        launch(args);
    }
    
}
