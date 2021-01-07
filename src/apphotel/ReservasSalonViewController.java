/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import apphotel.BDHotel;
import entidades.ReservasSalon;
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
public class ReservasSalonViewController implements Initializable {

    @FXML
    private TableView<ReservasSalon> tableViewHabitaciones;
    @FXML
    private TableColumn<ReservasSalon, String> columnDNI;
    @FXML
    private TableColumn<ReservasSalon, String> columnNombre;
    @FXML
    private TableColumn<ReservasSalon, String> columnTipoEvento;
    @FXML
    private TableColumn<ReservasSalon, Integer> columnNPersonas;
    @FXML
    private TableColumn<ReservasSalon, String> columnCocina;
    @FXML
    private TableColumn<ReservasSalon, Integer> columnNHabitaciones;
    @FXML
    private TableColumn<ReservasSalon, String> columnFecha;
    @FXML
    private TableColumn<ReservasSalon, Integer> columnDias;
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
        columnTipoEvento.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getTipoEvento().toString().equals("B")) {
                        property.setValue("Banquete");
                    }
                    if (cellData.getValue().getTipoEvento().toString().equals("J")) {
                        property.setValue("Jornada");
                    }
                    if (cellData.getValue().getTipoEvento().toString().equals("C")) {
                        property.setValue("Congreso");
                    }
                    return property;
                });
        columnNPersonas.setCellValueFactory(new PropertyValueFactory<>("numPersonas"));
        columnCocina.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    
                    if (cellData.getValue().getTipoCocina().toString().equals("V")) {
                        property.setValue("Buffet Vegetariano");
                    }
                    if (cellData.getValue().getTipoCocina().toString().equals("B")) {
                        property.setValue("Buffet no Vegetariano");
                    }
                    if (cellData.getValue().getTipoCocina().toString().equals("C")) {
                        property.setValue("Carta");
                    }
                    if (cellData.getValue().getTipoCocina().toString().equals("P")) {
                        property.setValue("Cita con el Chef");
                    }
                    if (cellData.getValue().getTipoCocina().toString().equals("N")) {
                        property.setValue("No precisa");
                    }
                    return property;
                });
        columnNHabitaciones.setCellValueFactory(new PropertyValueFactory<>("numHabitaciones"));
        columnFecha.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    property.setValue(sdf.format(cellData.getValue().getReservasSalonPK().getFecha()));
                    return property;
                });
        columnDias.setCellValueFactory(new PropertyValueFactory<>("numDias"));
        cargarReservas();
    }

    @FXML
    private void onActionButtonListo(ActionEvent event) {
        Stage stage = (Stage) this.buttonListo.getScene().getWindow();
        stage.close();
    }

    private void cargarReservas() {
        BDHotel bd = new BDHotel();
        tableViewHabitaciones.setItems(FXCollections.observableArrayList(bd.listaReservasSalon()));
        bd.cerrarConexion();
    }
}
