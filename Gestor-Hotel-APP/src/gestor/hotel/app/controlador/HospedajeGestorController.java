/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import gestor.hotel.app.modelo.Cliente;
import gestor.hotel.app.modelo.Habitacion;
import gestor.hotel.app.modelo.Hospedaje;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HospedajeGestorController extends FXController implements Initializable{
 
    //para gestionar objetos hospedajes
    private HospedajeManager hospedajeManager;
    private ClienteManager clienteManager;
    private HabitacionManager habitacionManager;
    
    private ArrayList<Cliente> clienteList;
    private ArrayList<Habitacion> habitacionList;
    
    //main window
    @FXML private AnchorPane mainWindow;
    
    //choice boxes
    @FXML private ChoiceBox<String> choiceBoxCliente;
    @FXML private ChoiceBox<String> choiceBoxHabitacion;
    
    //textfields
    @FXML private DatePicker datePickerFechaInicio;
    @FXML private DatePicker datePickerFechaFinal;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.hospedajeManager = new HospedajeManager(GestorHotelAPP.mysqlC);
        this.clienteManager = new ClienteManager(GestorHotelAPP.mysqlC);
        this.habitacionManager = new HabitacionManager(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.loadExternalManagers();
        this.loadChoiceBoxes();
        this.setSelector();
        final ObservableList<Hospedaje> selectedTableGestorHospedajes = tablaGestorHospedajes.getSelectionModel().getSelectedItems();
        selectedTableGestorHospedajes.addListener(selectorTablaGestorHospedajes);
    }    
    
    private void loadTabla(){
        this.hospedajeManager.load();
        /*
        System.out.println(this.hospedajeManager.getHospedajeList());
        System.out.println(this.hospedajeManager.getHospedajeList().get(0).toString());
        System.out.println(this.hospedajeManager.getHospedajeList().get(0).getIdCliente());
        */
        this.columnId.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnIdCliente.setCellValueFactory(new PropertyValueFactory("id_cliente"));
        this.columnIdHabitacion.setCellValueFactory(new PropertyValueFactory("id_habitacion"));
        this.columnFechaInicio.setCellValueFactory(new PropertyValueFactory("fecha_inicio"));
        this.columnFechaFinal.setCellValueFactory(new PropertyValueFactory("fecha_final"));
        
        listaHospedajesTabla = FXCollections.observableArrayList(this.hospedajeManager.getHospedajeList());
        //System.out.println(listaHospedajesTabla.toString());
        
        this.tablaGestorHospedajes.setItems(listaHospedajesTabla);
    }
    
    private void loadExternalManagers(){
        this.clienteManager.load();
        this.clienteList = this.clienteManager.getClienteList();
        this.habitacionManager.load();
        this.habitacionList = this.habitacionManager.getHabitacionList();
    }
    
    private void loadChoiceBoxes(){
        this.loadClienteChoiceBox(this.choiceBoxCliente, this.clienteList);
        this.loadHabitacionChoiceBox(this.choiceBoxHabitacion, this.habitacionList);
    }
    
    @FXML
    private void insert(ActionEvent event){
        Hospedaje insertedHospedaje;
        int newIdCliente = this.getChoiceValue(choiceBoxCliente);
        int newIdHabitacion = this.getChoiceValue(choiceBoxHabitacion);
        Date newFechaInicio = Date.valueOf(datePickerFechaInicio.getValue());
        Date newFechaFinal = Date.valueOf(datePickerFechaFinal.getValue());
        
        insertedHospedaje = new Hospedaje();
        insertedHospedaje.setId(0);//autoincrement
        insertedHospedaje.setIdCliente(newIdCliente);
        insertedHospedaje.setIdHabitacion(newIdHabitacion);
        insertedHospedaje.setFechaInicio(newFechaInicio);
        insertedHospedaje.setFechaFinal(newFechaFinal);
        
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
            updatedHospedaje.setIdCliente(this.getChoiceValue(choiceBoxCliente));
            updatedHospedaje.setIdHabitacion(this.getChoiceValue(choiceBoxHabitacion));
            updatedHospedaje.setFechaInicio(Date.valueOf(datePickerFechaInicio.getValue()));
            updatedHospedaje.setFechaFinal(Date.valueOf(datePickerFechaFinal.getValue()));
           
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
        int idCliente;
        int idHabitacion;
        
        if(currentHospedaje != null){
            idCliente = currentHospedaje.getIdCliente();
            idHabitacion = currentHospedaje.getIdHabitacion();
            Cliente currentCliente = this.clienteManager.getClienteById(idCliente);
            Habitacion currentHabitacion = this.habitacionManager.getHabitacionById(idHabitacion);
            
            choiceBoxCliente.setValue(currentHospedaje.getIdCliente() + ", " + currentCliente.getNombre() + " - " + currentCliente.getDni());
            choiceBoxHabitacion.setValue(currentHospedaje.getIdHabitacion() + ", " + currentHabitacion.getNumero() + " - " + currentHabitacion.getPrecio() + "€");

            datePickerFechaInicio.setValue(currentHospedaje.getFechaInicio().toLocalDate());
            datePickerFechaFinal.setValue(currentHospedaje.getFechaFinal().toLocalDate());
            
            
        }
    }
    
    public void resetTextFields(){
        this.datePickerFechaInicio.setValue(null);
        this.datePickerFechaFinal.setValue(null);
        this.choiceBoxCliente.setValue(null);
        this.choiceBoxHabitacion.setValue(null);
    }
    
    public void enterIntoMenu(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Menú principal");
    }
    
    public int getChoiceValue(ChoiceBox<String> choiceBox){
        String data = choiceBox.getValue();
        String[] res = data.split(",");
        return Integer.parseInt(res[0]);
    }
    
    public void loadClienteChoiceBox(ChoiceBox<String> choiceBox, ArrayList<Cliente> list){
        for(Cliente currentCliente : list)
        {
            choiceBox.getItems().add(currentCliente.getId() + ", " + currentCliente.getNombre() + " - " + currentCliente.getDni());
        }
    }
    
    public void loadHabitacionChoiceBox(ChoiceBox<String> choiceBox, ArrayList<Habitacion> list){
        for(Habitacion currentHabitacion : list)
        {
            choiceBox.getItems().add(currentHabitacion.getId() + ", " + currentHabitacion.getNumero() + " - " + currentHabitacion.getPrecio() + "€");
        }
    }
}