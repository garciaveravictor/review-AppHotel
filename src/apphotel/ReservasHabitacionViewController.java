/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import apphotel.BDHotel;
import entidades.ReservasHab;
import java.net.URL;
import java.text.SimpleDateFormat;
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

/**
 * FXML Controller class
 *
 * @author sergy
 */
public class ReservasHabitacionViewController implements Initializable {

    @FXML
    private TableView<ReservasHab> tableViewHabitaciones;
    @FXML
    private TableColumn<ReservasHab, String> columnDNI;
    @FXML
    private TableColumn<ReservasHab, String> columnNombre;
    @FXML
    private TableColumn<ReservasHab, String> columnNHabitaciones;
    @FXML
    private TableColumn<ReservasHab, String> columnTipo;
    @FXML
    private TableColumn<ReservasHab, String> columnFumador;
    @FXML
    private TableColumn<ReservasHab, String> columnRegimen;
    @FXML
    private TableColumn<ReservasHab, String> columnFechaIn;
    @FXML
    private TableColumn<ReservasHab, String> columnFechaOut;
    @FXML
    private Button buttonListo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnDNI.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    property.setValue(cellData.getValue().getCliente().getDni());
                    return property;
                });
        columnNombre.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    property.setValue(cellData.getValue().getCliente().getNombre());
                    return property;
                });
        columnNHabitaciones.setCellValueFactory(new PropertyValueFactory<>("nHab"));
        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnFumador.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getFumador().toString().equals("V")) {
                        property.setValue("Sí");
                    }
                    if (cellData.getValue().getFumador().toString().equals("F")) {
                        property.setValue("No");
                    }
                    return property;
                });
        columnRegimen.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getRegimen().toString().equals("D")) {
                        property.setValue("Alojamiento y desayuno");
                    }
                    if (cellData.getValue().getRegimen().toString().equals("M")) {
                        property.setValue("Media Pensión");
                    }
                    if (cellData.getValue().getRegimen().toString().equals("P")) {
                        property.setValue("Pensión Completa");
                    }
                    return property;
                });
        columnFechaIn.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    property.setValue(sdf.format(cellData.getValue().getReservasHabPK().getFechaIn()));
                    return property;
                });
        columnFechaOut.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    property.setValue(sdf.format(cellData.getValue().getReservasHabPK().getFechaOut()));
                    return property;
                });
        cargarReservas();
    }    

    @FXML
    private void onActionButtonListo(ActionEvent event) {
        Stage stage = (Stage) this.buttonListo.getScene().getWindow();
        stage.close();
    }
    
    private void cargarReservas(){
        BDHotel bd = new BDHotel();
        tableViewHabitaciones.setItems(FXCollections.observableArrayList(bd.listaReservasHab()));
        bd.cerrarConexion();
    }
    
}
