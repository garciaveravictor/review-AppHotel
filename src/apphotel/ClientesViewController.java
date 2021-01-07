/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import apphotel.BDHotel;
import entidades.Cliente;
import entidades.Provincia;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author sergio
 */
public class ClientesViewController implements Initializable {

    
    private EntityManager entityManager;
    @FXML
    private Button buttonListo;
    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> columnDNI;
    @FXML
    private TableColumn<Cliente, String> columnNombre;
    @FXML
    private TableColumn<Cliente, String> columnDireccion;
    @FXML
    private TableColumn<Cliente, String> columnLocalidad;
    @FXML
    private TableColumn<Cliente, String> columnProvincia;
    @FXML
    private TableColumn<Cliente, String> columnTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnProvincia.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getProvincia() != null) {
                        property.setValue(cellData.getValue().getProvincia().getNombre());
                    }
                    return property;
                });
        cargarClientes();
    }    

    @FXML
    private void onActionButtonListo(ActionEvent event) {
        Stage stage = (Stage) this.buttonListo.getScene().getWindow();
        stage.close();
    }
    
    private void cargarClientes() {
        BDHotel bd = new BDHotel();
        tableViewClientes.setItems(FXCollections.observableArrayList(bd.listaClientes()));
        bd.cerrarConexion();
    }
    
}
