/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import gestor.hotel.app.modelo.Habitacion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HabitacionGestorController extends FXController implements Initializable {
    //para gestionar objetos habitaciones
    private HabitacionManager habitacionManager;
    
    //main window
    @FXML private AnchorPane mainWindow;
    
    //textfields
    @FXML private TextField textFieldPrecio;
    @FXML private TextField textFieldNumero;
    
    //tables and table fields
    @FXML private TableView<Habitacion> tablaGestorHabitaciones;
    @FXML private TableColumn columnId;
    @FXML private TableColumn columnNumero;
    @FXML private TableColumn columnPrecio;
    ObservableList<Habitacion> listaHabitacionesTabla;
    
    private int selectedRowNumber;//para controlar posicion del campo seleccionado
    
        //gestionar las filas seleccionadas por el usuario
    private ListChangeListener<Habitacion> selectorTablaGestorHabitaciones;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.habitacionManager = new HabitacionManager(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.setSelector();
        final ObservableList<Habitacion> selectedTableGestorHabitaciones = tablaGestorHabitaciones.getSelectionModel().getSelectedItems();
        selectedTableGestorHabitaciones.addListener(selectorTablaGestorHabitaciones);
    }    
    
    private void loadTabla(){
        this.habitacionManager.load();
        
        this.columnId.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnNumero.setCellValueFactory(new PropertyValueFactory("numero"));
        this.columnPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        
        listaHabitacionesTabla = FXCollections.observableArrayList(this.habitacionManager.getHabitacionList());
        
        
        this.tablaGestorHabitaciones.setItems(listaHabitacionesTabla);
    }
  
    @FXML
    private void insert(ActionEvent event){
        Habitacion insertedHabitacion;
        int newNumero = Integer.parseInt(textFieldNumero.getText());
        float newPrecio = Float.parseFloat(textFieldPrecio.getText());
        
        insertedHabitacion = new Habitacion();
        insertedHabitacion.setNumero(newNumero);
        insertedHabitacion.setPrecio(newPrecio);
        insertedHabitacion.setId(0);//autoincrement
        insertedHabitacion.setId_usuario_creador(14);
        insertedHabitacion.setPropertyMap();

        insertedHabitacion.insert(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.resetTextFields();
        
    }
    
    @FXML
    private void update(ActionEvent event){
        Habitacion currentHabitacion = this.getHabitacionSelected();
        
        if(currentHabitacion != null){
            currentHabitacion.setNumero(Integer.parseInt(textFieldNumero.getText()));
            currentHabitacion.setPrecio(Float.parseFloat(textFieldPrecio.getText()));
            currentHabitacion.setPropertyMap();

            currentHabitacion.update(GestorHotelAPP.mysqlC);
            this.loadTabla();
            this.resetTextFields();
        }
    }
    
    @FXML
    private void delete(ActionEvent event){
        Habitacion currentHabitacion = this.getHabitacionSelected();
        
        if(currentHabitacion != null){
            currentHabitacion.delete(GestorHotelAPP.mysqlC);
            this.loadTabla();
            this.resetTextFields();
        }

    }
    
    public void setSelector(){
        this.selectorTablaGestorHabitaciones =
        new ListChangeListener<Habitacion>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Habitacion> c){
                showSelectedHabitacion();
            }
        };
    }
    
    public Habitacion getHabitacionSelected(){
        Habitacion selectedRow = null;
        
        if(this.tablaGestorHabitaciones != null){
            List<Habitacion> selectedRows = this.tablaGestorHabitaciones.getSelectionModel().getSelectedItems();
            if(selectedRows.size() == 1){
                selectedRow = selectedRows.get(0);
            }
        }
        
        return selectedRow;
    }
    
    public void showSelectedHabitacion(){
        final Habitacion currentHabitacion = this.getHabitacionSelected();
        this.selectedRowNumber = listaHabitacionesTabla.indexOf(currentHabitacion);
        
        if(currentHabitacion != null){
            textFieldNumero.setText(String.valueOf(currentHabitacion.getNumero()));
            textFieldPrecio.setText(Float.toString(currentHabitacion.getPrecio()));
        }
    }
    
    public void resetTextFields(){
        textFieldNumero.setText("");
        textFieldPrecio.setText("");
    }
    
    public void enterIntoMenu(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Menú principal");
    }
    
}
