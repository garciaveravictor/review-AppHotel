/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Cliente;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor Garcia Vera 2DAM
 */
public class SalonViewController implements Initializable {

    @FXML
    private TextField textFieldDNI;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private RadioButton radioButtonBanquete;
    @FXML
    private TextField textFieldNumeroPersonas;
    @FXML
    private ChoiceBox<String> choiceBoxTipoCocina;
    @FXML
    private RadioButton radioButtonJornada;
    @FXML
    private RadioButton radioButtonCongreso;
    @FXML
    private CheckBox checkBoxHabitaciones;
    @FXML
    private TextField textFieldNumeroHabitaciones;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private TextField textFieldNumeroDias;
    @FXML
    private Button buttonLimpiar;
    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelInformacion;
    private Cliente c = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final Tooltip dni = new Tooltip("Introduzca un DNI español valido");
        textFieldDNI.setTooltip(dni);
        final Tooltip nombre = new Tooltip("Nombre del cliente");
        textFieldNombre.setTooltip(nombre);
        final Tooltip direccion = new Tooltip("Direccion del cliente");
        textFieldDireccion.setTooltip(direccion);
        final Tooltip telefono = new Tooltip("Telefono del cliente");
        textFieldTelefono.setTooltip(telefono);
        final Tooltip banquete = new Tooltip("Tipo de evento: Banquete");
        radioButtonBanquete.setTooltip(banquete);
        final Tooltip numPersona = new Tooltip("Numero de asistentes");
        textFieldNumeroPersonas.setTooltip(numPersona);
        final Tooltip tipoCocina = new Tooltip("Tipo de servicio de cocina");
        choiceBoxTipoCocina.setTooltip(tipoCocina);
        final Tooltip jornada = new Tooltip("Tipo de evento: Jornada");
        radioButtonJornada.setTooltip(jornada);
        final Tooltip congreso = new Tooltip("Tipo de evento: Congreso");
        radioButtonCongreso.setTooltip(congreso);
        final Tooltip habitaciones = new Tooltip("Necesitas habitaciones");
        checkBoxHabitaciones.setTooltip(habitaciones);
        final Tooltip fecha = new Tooltip("Fecha del evento");
        datePickerFecha.setTooltip(fecha);
        final Tooltip dias = new Tooltip("Duracion del evento");
        textFieldNumeroDias.setTooltip(dias);
        final Tooltip limpiar = new Tooltip("Limpiar los campos");
        buttonLimpiar.setTooltip(limpiar);
        final Tooltip aceptar = new Tooltip("Aceptar y realizar reserva");
        buttonAceptar.setTooltip(aceptar);
        final Tooltip cancelar = new Tooltip("Cancelar la reserva");
        buttonCancelar.setTooltip(cancelar);
        final Tooltip numeroHab = new Tooltip("Numero de habitaciones a disponer");
        textFieldNumeroHabitaciones.setTooltip(numeroHab);

        ToggleGroup tg = new ToggleGroup();

        radioButtonBanquete.setToggleGroup(tg);
        radioButtonCongreso.setToggleGroup(tg);
        radioButtonJornada.setToggleGroup(tg);

        //Rellenar choicebox tipo de cocina
        choiceBoxTipoCocina.getItems().addAll("Buffet Vegetariano", "Buffet no Vegetariano", "Carta", "Cita con el Cheff", "No precisa");

        //Modificar date picker
        datePickerFecha.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        datePickerFecha.setEditable(false);

        textFieldDNI.focusedProperty().addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    BDHotel bdHotel = new BDHotel();

                    String dni = textFieldDNI.getText();
                    String pattern = "[0-9]{8}[A-Z a-z]{1}";

                    if (Pattern.matches(pattern, dni)) {
                        c = bdHotel.buscarCliente(dni);
                        if (c != null) {
                            textFieldNombre.setText(c.getNombre());
                            textFieldNombre.setDisable(true);
                            textFieldNombre.setStyle("-fx-opacity: 1.0;");

                            textFieldDireccion.setText(c.getDireccion());
                            textFieldDireccion.setDisable(true);
                            textFieldDireccion.setStyle("-fx-opacity: 1.0;");

                            textFieldTelefono.setText(c.getTelefono());
                            textFieldTelefono.setDisable(true);
                            textFieldTelefono.setStyle("-fx-opacity: 1.0;");
                        }
                        if (c == null) {
                            textFieldDireccion.setDisable(false);
                            textFieldDireccion.setText("");
                            textFieldDireccion.setPromptText("Introduce direccion...");

                            textFieldNombre.setDisable(false);
                            textFieldNombre.setText("");
                            textFieldNombre.setPromptText("Introduce nombre...");

                            textFieldTelefono.setDisable(false);
                            textFieldTelefono.setText("");
                            textFieldTelefono.setPromptText("Introduce telefono...");
                        }

                    }
                    bdHotel.cerrarConexion();

                }

            }

        });

    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {

        //Vaciar todos los textField
        textFieldDNI.setText("");
        textFieldNombre.setText("");
        textFieldDireccion.setText("");
        textFieldNumeroDias.setText("");
        textFieldNumeroHabitaciones.setText("");
        textFieldTelefono.setText("");
        textFieldNumeroPersonas.setText("");

        datePickerFecha.setValue(null);
        choiceBoxTipoCocina.setValue(null);
        checkBoxHabitaciones.setSelected(false);

        //Vaciar todos los radio button
        radioButtonBanquete.setSelected(false);
        radioButtonCongreso.setSelected(false);
        radioButtonJornada.setSelected(false);

        labelInformacion.setText("");

    }

    @FXML
    private void onActionAceptar(ActionEvent event) {
        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar solicitud");
        confirmacion.setHeaderText("Seleccione CONFIRMAR si desea realizar la reserva, \n"
                + "Seleccione CANCELAR si quiere volver a la realización de la reserva.\n");
        confirmacion.setContentText("Resumen de la Reserva: \n"
                + "\tCliente: " + textFieldNombre.getText() + ", " + textFieldDNI.getText() + "\n");

        if (radioButtonBanquete.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de evento: Banquete, " + textFieldNumeroPersonas.getText() + " invitados\n"
                    + "\tTipo de cocina: " + choiceBoxTipoCocina.getValue() + "\n");
        } else if (radioButtonCongreso.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de evento: Congreso, " + textFieldNumeroPersonas.getText() + " invitados"
                    + "Numero de Habitaciones: " + textFieldNumeroHabitaciones.getText() + "\n"
                    + "\tNumero de dias: " + textFieldNumeroDias.getText() + "\n");
        } else if (radioButtonJornada.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de evento: Jornada, " + textFieldNumeroPersonas.getText() + " invitados\n");
        }

        confirmacion.setContentText(confirmacion.getContentText() + "\tFecha de reserva: " + datePickerFecha.getValue());

        ButtonType buttonTypeAceptar = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacion.getButtonTypes().setAll(buttonTypeCancel, buttonTypeAceptar);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de formato");
        alert.setHeaderText("");

        boolean errorFormato = comprobarErrores(alert);
        String tCocina = null;

        if (errorFormato) {
            alert.showAndWait();
        }

        if (!errorFormato) {

            Optional<ButtonType> result = confirmacion.showAndWait();

            if (result.get() == buttonTypeCancel) {
                confirmacion.close();

            } else if (result.get() == buttonTypeAceptar) {
                BDHotel bdhotel = new BDHotel();

                if (c == null) {
                    String[] cliente = {textFieldDNI.getText(), textFieldNombre.getText(), textFieldDireccion.getText(), textFieldTelefono.getText()};

                    c = bdhotel.altaClienteTfno(cliente);
                }

                if (radioButtonBanquete.isSelected()) {
                    if (choiceBoxTipoCocina.getValue().equals("Buffet Vegetariano")) {
                        tCocina = "V";
                    } else if (choiceBoxTipoCocina.getValue().equals("Buffet no Vegetariano")) {
                        tCocina = "B";
                    } else if (choiceBoxTipoCocina.getValue().equals("Carta")) {
                        tCocina = "C";
                    } else if (choiceBoxTipoCocina.getValue().equals("Cita con el Cheff")) {
                        tCocina = "P";
                    } else if (choiceBoxTipoCocina.getValue().equals("No precisa")) {
                        tCocina = "N";
                    }

                    String[] banquete = {textFieldNumeroPersonas.getText(), tCocina};

                    bdhotel.registroReservaSalonBanquete(c, datePickerFecha.getValue(), banquete);

                } else if (radioButtonJornada.isSelected()) {
                    bdhotel.registroReservaSalonJornada(c, datePickerFecha.getValue(), textFieldNumeroPersonas.getText());

                } else if (radioButtonCongreso.isSelected()) {
                    String[] congreso = {textFieldNumeroPersonas.getText(), textFieldNumeroHabitaciones.getText(), textFieldNumeroDias.getText()};
                    if (textFieldNumeroHabitaciones.getText().equals("")) {
                        congreso[1] = "0";
                    }

                    bdhotel.registroReservaSalonCongreso(c, datePickerFecha.getValue(), congreso);

                }
                bdhotel.cerrarConexion();
            }
        }

    }

    private boolean comprobarErrores(Alert alert) {
        boolean errorFormato = false;

        String patternDNI = "[0-9]{8}[A-Z a-z]{1}";
        String patternTfno = "[0-9]{9}";
        String patternEnteros = "[0-9]*";

        if (textFieldDNI.getText().isEmpty() || !Pattern.matches(patternDNI, textFieldDNI.getText())) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo DNI\n");
        }
        if (textFieldNombre.getText().isEmpty()) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo nombre\n");
        }
        if (textFieldDireccion.getText().isEmpty()) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo direccion\n");
        }
        if (textFieldTelefono.getText().isEmpty() || !Pattern.matches(patternTfno, textFieldTelefono.getText())) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo telefono\n");
        }
        if (!radioButtonBanquete.isSelected() && !radioButtonCongreso.isSelected() && !radioButtonJornada.isSelected()) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error debe de seleccionar un tipo de servicio\n");
        }
        if (radioButtonBanquete.isSelected()) {
            if (choiceBoxTipoCocina.getValue() == null) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Debe seleccionar un tipo de cocina\n");
            }
        }
        if (radioButtonBanquete.isSelected()) {
            if (textFieldNumeroPersonas.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que estar relleno\n");
            } else if (!Pattern.matches(patternEnteros, textFieldNumeroPersonas.getText())) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que ser un numero\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) < 0) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser menor de 0\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) > 100) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser mayor de 100 para banquete\n");
            }
        }
        if (radioButtonCongreso.isSelected()) {
            if (textFieldNumeroPersonas.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que estar relleno\n");
            } else if (!Pattern.matches(patternEnteros, textFieldNumeroPersonas.getText())) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que ser un numero natural\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) < 0) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser menor de 0\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) > 50) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser mayor de 50 para jornada y congreso\n");
            } else if (textFieldNumeroDias.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de dias no puede estar vacio\n");
            } else if (Integer.parseInt(textFieldNumeroDias.getText()) < 1) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de dias no puede ser menor de 1\n");
            }
        }

        if (radioButtonJornada.isSelected()) {
            if (textFieldNumeroPersonas.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que estar relleno\n");
            } else if (!Pattern.matches(patternEnteros, textFieldNumeroPersonas.getText())) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas tiene que ser un numero natural\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) < 0) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser menor de 0\n");
            } else if (Integer.parseInt(textFieldNumeroPersonas.getText()) > 50) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de personas no puede ser mayor de 50 para jornada y congreso\n");
            }
        }

        if (checkBoxHabitaciones.isSelected()) {
            if (textFieldNumeroHabitaciones.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error en el campo numero de habitaciones\n");
            } else if (!Pattern.matches(patternEnteros, textFieldNumeroHabitaciones.getText())) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el campo numero de habitaciones tiene que ser un numero natural\n");
            } else if (Integer.parseInt(textFieldNumeroHabitaciones.getText()) == 0) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error el numero de habitaciones no puede ser cero\n");
            }
        }
        if (datePickerFecha.getValue() == null) {
            errorFormato = true;
            alert.setHeaderText(alert.getHeaderText() + "Error en la fecha de reserva\n");
        }
        if (radioButtonCongreso.isSelected()) {
            if (textFieldNumeroDias.getText().isEmpty()) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error en el numero de dias de reserva\n");
            } else if (!Pattern.matches(patternEnteros, textFieldNumeroDias.getText())) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error en el numero de dias de reserva, debe de ser un numero natural\n");
            } else if (Integer.parseInt(textFieldNumeroDias.getText()) < 0) {
                errorFormato = true;
                alert.setHeaderText(alert.getHeaderText() + "Error en el numero de dias de reserva, no puede ser cero\n");
            }
        }

        return errorFormato;
    }

    @FXML
    private void onActionCancelar(ActionEvent event
    ) {
        Stage stage = (Stage) this.buttonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionSelecionadoBanquete(ActionEvent event
    ) {
        labelInformacion.setText("Tipo: Banquete \n Selecione personas...");
        labelInformacion.setTextFill(Color.RED);
        checkBoxHabitaciones.setDisable(true);
        checkBoxHabitaciones.setSelected(false);
        textFieldNumeroHabitaciones.setDisable(true);
        textFieldNumeroDias.setDisable(true);
        choiceBoxTipoCocina.setDisable(false);
    }

    @FXML
    private void onActionSelecionadoJornada(ActionEvent event
    ) {
        labelInformacion.setText("Tipo: Jornada \n Selecione personas...");
        labelInformacion.setTextFill(Color.BLUE);
        checkBoxHabitaciones.setDisable(true);
        checkBoxHabitaciones.setSelected(false);
        textFieldNumeroHabitaciones.setDisable(true);
        textFieldNumeroDias.setDisable(true);
        choiceBoxTipoCocina.setDisable(true);
    }

    @FXML
    private void onActionSelecionadoCongreso(ActionEvent event
    ) {
        labelInformacion.setText("Tipo: Congreso \n Selecione personas...");
        labelInformacion.setTextFill(Color.CORAL);
        checkBoxHabitaciones.setDisable(false);
        textFieldNumeroDias.setDisable(false);
        choiceBoxTipoCocina.setDisable(true);
    }

    @FXML
    private void onActionCheckBoxHabitaciones(ActionEvent event
    ) {
        if (checkBoxHabitaciones.isSelected()) {
            textFieldNumeroHabitaciones.setDisable(false);
        } else {
            textFieldNumeroHabitaciones.setDisable(true);
        }
    }

}
