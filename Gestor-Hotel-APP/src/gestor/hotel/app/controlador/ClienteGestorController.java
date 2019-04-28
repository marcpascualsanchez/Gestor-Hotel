/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import gestor.hotel.app.modelo.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ClienteGestorController extends FXController implements Initializable {

    //para gestionar objetos habitaciones
    private ClienteManager clienteManager;
    
    //main window
    @FXML private AnchorPane mainWindow;
    
    //textfields
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldDNI;
    @FXML private TextField textFieldNacionalidad;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldOcupacion;
    @FXML private TextField textFieldEstadoCivil;
    
    //tables and table fields
    @FXML private TableView<Cliente> tablaGestorClientes;
    @FXML private TableColumn columnId;
    @FXML private TableColumn columnNombre;
    @FXML private TableColumn columnDNI;
    @FXML private TableColumn columnNacionalidad;
    @FXML private TableColumn columnTelefono;
    @FXML private TableColumn columnEmail;
    @FXML private TableColumn columnOcupacion;
    @FXML private TableColumn columnEstadoCivil;
    
    ObservableList<Cliente> listaClientesTabla;
    
    private int selectedRowNumber;//para controlar posicion del campo seleccionado
    
        //gestionar las filas seleccionadas por el usuario
    private ListChangeListener<Cliente> selectorTablaGestorClientes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.clienteManager = new ClienteManager(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.setSelector();
        final ObservableList<Cliente> selectedTableGestorClientees = tablaGestorClientes.getSelectionModel().getSelectedItems();
        selectedTableGestorClientees.addListener(selectorTablaGestorClientes);
    }    
    
    private void loadTabla(){
        this.clienteManager.load();
        
        this.columnId.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnDNI.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnOcupacion.setCellValueFactory(new PropertyValueFactory("ocupacion"));
        this.columnNacionalidad.setCellValueFactory(new PropertyValueFactory("nacionalidad"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory("email"));
        this.columnOcupacion.setCellValueFactory(new PropertyValueFactory("ocupacion"));
        this.columnEstadoCivil.setCellValueFactory(new PropertyValueFactory("estado_civil"));
        
        listaClientesTabla = FXCollections.observableArrayList(this.clienteManager.getClienteList());
        
        this.tablaGestorClientes.setItems(listaClientesTabla);
    }
    
    @FXML
    private void insert(ActionEvent event){
        Cliente insertedCliente;
        String newNombre = textFieldNombre.getText();
        String newDNI = textFieldDNI.getText();
        String newNacionalidad = textFieldNacionalidad.getText();
        String newTelefono= textFieldTelefono.getText();
        String newEmail= textFieldEmail.getText();
        String newOcupacion= textFieldOcupacion.getText();
        String newEstadoCivil = textFieldEstadoCivil.getText();
        
        insertedCliente = new Cliente();
        insertedCliente.setId(0);//autoincrement
        insertedCliente.setNombre(newNombre);
        insertedCliente.setDni(newDNI);
        insertedCliente.setNacionalidad(newNacionalidad);
        insertedCliente.setTelefono(newTelefono);
        insertedCliente.setEmail(newEmail);
        insertedCliente.setOcupacion(newOcupacion);
        insertedCliente.setEstado_civil(newEstadoCivil);
        insertedCliente.setId_usuario_creador(14);
        insertedCliente.setPropertyMap();

        insertedCliente.insert(GestorHotelAPP.mysqlC);
        this.resetTextFields();
        this.loadTabla();
    }
    
    @FXML
    private void update(ActionEvent event){
        Cliente updatedCliente = this.getClienteSelected();
        
        if(updatedCliente != null){
            updatedCliente.setNombre(textFieldNombre.getText());
            updatedCliente.setDni(textFieldDNI.getText());
            updatedCliente.setNacionalidad(textFieldNacionalidad.getText());
            updatedCliente.setTelefono(textFieldTelefono.getText());
            updatedCliente.setEmail(textFieldEmail.getText());
            updatedCliente.setOcupacion(textFieldOcupacion.getText());
            updatedCliente.setEstado_civil(textFieldEstadoCivil.getText());
            updatedCliente.setId_usuario_creador(14);
            updatedCliente.setPropertyMap();

            updatedCliente.update(GestorHotelAPP.mysqlC);
            this.resetTextFields();
            this.loadTabla();
        }
    }
    
    @FXML
    private void delete(ActionEvent event){
        Cliente currentCliente = this.getClienteSelected();
        
        if(currentCliente != null){
            currentCliente.delete(GestorHotelAPP.mysqlC);
            this.resetTextFields();
            this.loadTabla();
        }

    }
    
    public void setSelector(){
        this.selectorTablaGestorClientes =
        new ListChangeListener<Cliente>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Cliente> c){
                showSelectedCliente();
            }
        };
    }
    
    public Cliente getClienteSelected(){
        Cliente selectedRow = null;
        
        if(this.tablaGestorClientes != null){
            List<Cliente> selectedRows = this.tablaGestorClientes.getSelectionModel().getSelectedItems();
            if(selectedRows.size() == 1){
                selectedRow = selectedRows.get(0);
            }
        }
        
        return selectedRow;
    }
    
    public void showSelectedCliente(){
        final Cliente currentCliente = this.getClienteSelected();
        this.selectedRowNumber = listaClientesTabla.indexOf(currentCliente);
        
        if(currentCliente != null){
            textFieldNombre.setText(currentCliente.getNombre());
            textFieldDNI.setText(currentCliente.getDni());
            textFieldNacionalidad.setText(currentCliente.getNacionalidad());
            textFieldTelefono.setText(currentCliente.getTelefono());
            textFieldEmail.setText(currentCliente.getEmail());
            textFieldOcupacion.setText(currentCliente.getOcupacion());
            textFieldEstadoCivil.setText(currentCliente.getEstado_civil());
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
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Menú principal");
    }
    
}
