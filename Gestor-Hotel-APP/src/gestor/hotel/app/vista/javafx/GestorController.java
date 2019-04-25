/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.vista.javafx;

import gestor.hotel.app.modelo.Habitacion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestorController implements Initializable {

    //buttons
    @FXML private Button buttonUpdate;
    @FXML private Button buttonInsert;
    @FXML private Button buttonDelete;
    
    //textfields
    @FXML private TextField textFieldPrecio;
    @FXML private TextField textFieldNumero;
    
    //tables and table fields
    @FXML private TableView<Habitacion> tablaGestorHabitaciones;
    @FXML private TableColumn columnId;
    @FXML private TableColumn columnNumero;
    @FXML private TableColumn columnPrecio;
    ObservableList<Habitacion> habitaciones;
    
    private int rowNumber;//para controlar posicion del campo seleccionado
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void insert(ActionEvent event){
        
        System.out.println("insertando desde gestor habitaciones.");
       /* Habitacion insertedHabitacion = new Habitacion();
        insertedHabitacion.setNumero(Integer.parseInt(textFieldNumero.getText()));
        insertedHabitacion.setPrecio(Float.parseFloat(textFieldPrecio.getText()));
        insertedHabitacion.setId(0);
        insertedHabitacion.setId_usuario_creador(0);
        insertedHabitacion.setList();
        
        //insertedHabitacion.insert();
*/
    }
    
    @FXML
    private void update(ActionEvent event){
        System.out.println("insertando desde gestor habitaciones.");
    }
    
    @FXML
    private void delete(ActionEvent event){
        System.out.println("insertando desde gestor habitaciones.");
    }
    
}
