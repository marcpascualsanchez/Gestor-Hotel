/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import gestor.hotel.app.modelo.Hospedaje;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HospedajeGestorController extends FXController {
 /*
    //para gestionar objetos hospedajes
    private HospedajeManager hospedajeManager;
    
    //main window
    @FXML private AnchorPane mainWindow;
    
    //choice boxes
    @FXML private ChoiceBox choiceBoxCliente;
    @FXML private ChoiceBox choiceBoxHabitacion;
    
    //textfields
    @FXML private TextField textFieldFechaInicio;
    @FXML private TextField textFieldFechaFinal;
    
    //tables and table fields
    @FXML private TableView<Hospedaje> tablaGestorHospedajes;
    @FXML private TableColumn columnId;
    @FXML private TableColumn columnIdCliente;
    @FXML private TableColumn columnIdHabitacion;
    @FXML private TableColumn columnFechaInicio;
    @FXML private TableColumn columnFechaFinal;
    
    ObservableList<Hospedaje> listaHospedajesTabla;
    
    private int selectedRowNumber;//para controlar posicion del campo seleccionado
    
        //gestionar las filas seleccionadas por el usuario
    private ListChangeListener<Hospedaje> selectorTablaGestorHospedajes;
    
    /**
     * Initializes the controller class.
     */
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.hospedajeManager = new HospedajeManager(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.setSelector();
        final ObservableList<Hospedaje> selectedTableGestorHospedajes = tablaGestorHospedajes.getSelectionModel().getSelectedItems();
        selectedTableGestorHospedajes.addListener(selectorTablaGestorHospedajes);
    }    
    
    private void loadTabla(){
        this.hospedajeManager.load();
        
        this.columnId.setCellValueFactory(new PropertyValueFactory<Hospedaje,String>("id"));
        this.columnIdHabitacion.setCellValueFactory(new PropertyValueFactory<Hospedaje,String>("id_habitacion"));
        this.columnIdCliente.setCellValueFactory(new PropertyValueFactory<Hospedaje,String>("id_cliente"));
        this.columnFechaInicio.setCellValueFactory(new PropertyValueFactory<Hospedaje,String>("fecha_inicio"));
        this.columnFechaFinal.setCellValueFactory(new PropertyValueFactory<Hospedaje,String>("fecha_final"));

        
        listaHospedajesTabla = FXCollections.observableArrayList(this.hospedajeManager.getHospedajeList());
        
        this.tablaGestorHospedajes.setItems(listaHospedajesTabla);
    }
    
    @FXML
    private void insert(ActionEvent event){
        Hospedaje insertedHospedaje;
        int newIdCliente = choiceBoxCliente.getValue();
        String newIdHabitacion = choiceBoxHabitacion.getText();
        String newFechaInicio = textFieldFechaInicio.getText();
        String newFechaFinal = textFieldFechaFinal.getText();
        
        insertedHospedaje = new Hospedaje();
        insertedHospedaje.setId(0);//autoincrement
        insertedHospedaje.setIdCliente(newNombre);
        
        insertedHospedaje.setId_usuario_creador(14);
        insertedHospedaje.setPropertyMap();

        insertedHospedaje.insert(GestorHotelAPP.mysqlC);
        this.resetTextFields();
        this.loadTabla();
    }
    
    @FXML
    private void update(ActionEvent event){
        Hospedaje updatedHospedaje = this.getHospedajeSelected();
        
        if(updatedHospedaje != null){
            updatedHospedaje.setNombre(textFieldNombre.getText());
            updatedHospedaje.setDni(textFieldDNI.getText());
            updatedHospedaje.setNacionalidad(textFieldNacionalidad.getText());
            updatedHospedaje.setTelefono(textFieldTelefono.getText());
            updatedHospedaje.setEmail(textFieldEmail.getText());
            updatedHospedaje.setOcupacion(textFieldOcupacion.getText());
            updatedHospedaje.setEstado_civil(textFieldEstadoCivil.getText());
            updatedHospedaje.setId_usuario_creador(14);
            updatedHospedaje.setPropertyMap();

            updatedHospedaje.update(GestorHotelAPP.mysqlC);
            this.resetTextFields();
            this.loadTabla();
        }
    }
    
    @FXML
    private void delete(ActionEvent event){
        Hospedaje currentHospedaje = this.getHospedajeSelected();
        
        if(currentHospedaje != null){
            currentHospedaje.delete(GestorHotelAPP.mysqlC);
            this.resetTextFields();
            this.loadTabla();
        }

    }
    
    public void setSelector(){
        this.selectorTablaGestorHospedajes =
        new ListChangeListener<Hospedaje>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Hospedaje> c){
                showSelectedHospedaje();
            }
        };
    }
    
    public Hospedaje getHospedajeSelected(){
        Hospedaje selectedRow = null;
        
        if(this.tablaGestorHospedajes != null){
            List<Hospedaje> selectedRows = this.tablaGestorHospedajes.getSelectionModel().getSelectedItems();
            if(selectedRows.size() == 1){
                selectedRow = selectedRows.get(0);
            }
        }
        
        return selectedRow;
    }
    
    public void showSelectedHospedaje(){
        final Hospedaje currentHospedaje = this.getHospedajeSelected();
        this.selectedRowNumber = listaHospedajesTabla.indexOf(currentHospedaje);
        
        if(currentHospedaje != null){
            textFieldNombre.setText(currentHospedaje.getNombre());
            textFieldDNI.setText(currentHospedaje.getDni());
            textFieldNacionalidad.setText(currentHospedaje.getNacionalidad());
            textFieldTelefono.setText(currentHospedaje.getTelefono());
            textFieldEmail.setText(currentHospedaje.getEmail());
            textFieldOcupacion.setText(currentHospedaje.getOcupacion());
            textFieldEstadoCivil.setText(currentHospedaje.getEstado_civil());
        }
    }
    
    public void resetTextFields(){
        textFieldNombre.setText("");
        textFieldDNI.setText("");
        textFieldNacionalidad.setText("");
        textFieldTelefono.setText("");
        textFieldEmail.setText("");
        textFieldOcupacion.setText("");
        textFieldEstadoCivil.setText("");
    }
    
    public void enterIntoMenu(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Login");
    }
 */   
}