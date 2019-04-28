/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.hotel.app.controlador;

import gestor.hotel.app.GestorHotelAPP;
import gestor.hotel.app.modelo.Usuario;
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
public class UsuarioGestorController extends FXController implements Initializable {

    //para gestionar objetos habitaciones
    private UsuarioManager usuarioManager;
    
    //main window
    @FXML private AnchorPane mainWindow;
    
    //textfields
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldClave;
    @FXML private TextField textFieldAdmin;
    
    //tables and table fields
    @FXML private TableView<Usuario> tablaGestorUsuarios;
    @FXML private TableColumn columnId;
    @FXML private TableColumn columnNombre;
    @FXML private TableColumn columnClave;
    @FXML private TableColumn columnAdmin;
    ObservableList<Usuario> listaUsuariosTabla;
    
    private int selectedRowNumber;//para controlar posicion del campo seleccionado
    
        //gestionar las filas seleccionadas por el usuario
    private ListChangeListener<Usuario> selectorTablaGestorUsuarios;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.usuarioManager = new UsuarioManager(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.setSelector();
        final ObservableList<Usuario> selectedTableGestorHabitaciones = tablaGestorUsuarios.getSelectionModel().getSelectedItems();
        selectedTableGestorHabitaciones.addListener(selectorTablaGestorUsuarios);
    }    
    
    private void loadTabla(){
        this.usuarioManager.load();
        
        this.columnId.setCellValueFactory(new PropertyValueFactory<Usuario,String>("id"));
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        this.columnClave.setCellValueFactory(new PropertyValueFactory<Usuario,String>("clave"));
        this.columnAdmin.setCellValueFactory(new PropertyValueFactory<Usuario,String>("admin"));
        
        listaUsuariosTabla = FXCollections.observableArrayList(this.usuarioManager.getUsuarioList());
        
        this.tablaGestorUsuarios.setItems(listaUsuariosTabla);
    }
  
    @FXML
    private void insert(ActionEvent event){
        Usuario insertedUsuario;
        String newNombre = textFieldNombre.getText();
        String newClave = textFieldClave.getText();
        int newAdmin = Integer.parseInt(textFieldAdmin.getText());
        
        insertedUsuario = new Usuario();
        insertedUsuario.setId(0);//autoincrement
        insertedUsuario.setNombre(newNombre);
        insertedUsuario.setClave(newClave);
        insertedUsuario.setAdmin(newAdmin);
        insertedUsuario.setPropertyMap();

        insertedUsuario.insert(GestorHotelAPP.mysqlC);
        this.loadTabla();
        this.resetTextFields();
        
    }
    
    @FXML
    private void update(ActionEvent event){
        Usuario updatedUsuario = this.getUsuarioSelected();
        
        if(updatedUsuario != null){
            updatedUsuario.setNombre(textFieldNombre.getText());
            updatedUsuario.setClave(textFieldClave.getText());
            updatedUsuario.setAdmin(Integer.parseInt(textFieldAdmin.getText()));
            updatedUsuario.setPropertyMap();

            updatedUsuario.update(GestorHotelAPP.mysqlC);
            this.loadTabla();
            this.resetTextFields();
        }
    }
    
    @FXML
    private void delete(ActionEvent event){
        Usuario currentUsuario = this.getUsuarioSelected();
        
        if(currentUsuario != null){
            currentUsuario.delete(GestorHotelAPP.mysqlC);
            this.loadTabla();
            this.resetTextFields();
        }

    }
    
    public void setSelector(){
        this.selectorTablaGestorUsuarios =
        new ListChangeListener<Usuario>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Usuario> c){
                showSelectedUsuario();
            }
        };
    }
    
    public Usuario getUsuarioSelected(){
        Usuario selectedRow = null;
        
        if(this.tablaGestorUsuarios != null){
            List<Usuario> selectedRows = this.tablaGestorUsuarios.getSelectionModel().getSelectedItems();
            if(selectedRows.size() == 1){
                selectedRow = selectedRows.get(0);
            }
        }
        
        return selectedRow;
    }
    
    public void showSelectedUsuario(){
        final Usuario currentUsuario = this.getUsuarioSelected();
        this.selectedRowNumber = listaUsuariosTabla.indexOf(currentUsuario);
        
        if(currentUsuario != null){
            textFieldNombre.setText(currentUsuario.getNombre());
            textFieldClave.setText(currentUsuario.getClave());
            textFieldAdmin.setText(String.valueOf(currentUsuario.getAdmin()));
        }
    }
    
    public void resetTextFields(){
        textFieldNombre.setText("");
        textFieldClave.setText("");
        textFieldAdmin.setText("");
    }
    
    public void enterIntoMenu(){
        changeWindow(mainWindow, "/gestor/hotel/app/vista/javafx/Menu.fxml", "Menú principal");
    }
    
}
