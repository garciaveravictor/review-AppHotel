/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class NewMainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnClickedHabitacion(MouseEvent event) throws IOException {
                //Conectamos y cargamos el archivo fxml de HabitacionesView y lo asociamos como elemento raíz de la ventana. 
        FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("HabitacionesView.fxml"));
        Pane Habitaciones=fxmlLoader.load();
        
        Scene scene = new Scene(Habitaciones, 600, 500);
        Stage habitaciones = new Stage();
        
        habitaciones.getIcons().add(new Image("img/icono.png"));
        habitaciones.initModality(Modality.APPLICATION_MODAL);
        habitaciones.setTitle("Habitaciones");
        habitaciones.setScene(scene);
        habitaciones.show();
    }

    @FXML
    private void OnClickedSalon(MouseEvent event) throws IOException {
                //Conectamos y cargamos el archivo fxml de HabitacionesView y lo asociamos como elemento raíz de la ventana. 
        FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("SalonView.fxml"));
        Pane salon=fxmlLoader.load();
        
        Scene scene = new Scene(salon, 600, 500);
        Stage salonHabana = new Stage();
        
        salonHabana.getIcons().add(new Image("img/icono.png"));
        salonHabana.initModality(Modality.APPLICATION_MODAL);
        salonHabana.setTitle("Salon Habana");
        salonHabana.setScene(scene);
        salonHabana.show();
        
    }

    @FXML
    private void OnClickedSalir(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void OnClickedAbout(MouseEvent event) {
        
        //Implemetar ventana About
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Sobre nostros");
        info.setHeaderText("Proyecto: AppHotel\n"
                + "Realizado por: Grupo Cosmic\n"
                + "Integrantes: \n"
                + "\t\t\tRaúl Moreno\n"
                + "\t\t\tVíctor García\n"
                + "\t\t\tSergio García\n"
                + "\t\t\tRafael Aguilera\n"
                + "Version: 1.0");
        info.showAndWait();
    }

    @FXML
    private void OnClickedVerClientes(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("ClientesView.fxml"));
        Pane clientes=fxmlLoader.load();
        
        Scene scene = new Scene(clientes);
        Stage salonHabana = new Stage();
        salonHabana.initModality(Modality.APPLICATION_MODAL);
        salonHabana.setTitle("Clientes");
        salonHabana.setScene(scene);
        salonHabana.show();
    }

    @FXML
    private void OnClickedVerHabitaciones(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("ReservasHabitacionView.fxml"));
        Pane reservasHab=fxmlLoader.load();
        
        Scene scene = new Scene(reservasHab);
        Stage salonHabana = new Stage();
        salonHabana.initModality(Modality.APPLICATION_MODAL);
        salonHabana.setTitle("Reservas de habitaciones");
        salonHabana.setScene(scene);
        salonHabana.show();
    }

    @FXML
    private void OnClickedVerSalon(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader=new
            FXMLLoader(getClass().getResource("ReservasSalonView.fxml"));
        Pane reservasSalon=fxmlLoader.load();
        
        Scene scene = new Scene(reservasSalon);
        Stage salonHabana = new Stage();
        salonHabana.initModality(Modality.APPLICATION_MODAL);
        salonHabana.setTitle("Reservas de Salón");
        salonHabana.setScene(scene);
        salonHabana.show();
        
    }
    
}
