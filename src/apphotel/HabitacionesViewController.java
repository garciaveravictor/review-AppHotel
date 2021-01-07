package apphotel;

import apphotel.BDHotel;
import entidades.Cliente;
import entidades.Provincia;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author raul-
 */
public class HabitacionesViewController implements Initializable {

    @FXML
    private TextField textFieldDNI;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private TextField textFieldLocalidad;
    @FXML
    private ChoiceBox<Provincia> choiceBoxProvincia;
    @FXML
    private DatePicker datePickerLlegada;
    @FXML
    private DatePicker datePickerSalida;
    @FXML
    private Spinner<?> spinnerNumHabitaciones;
    @FXML
    private ChoiceBox<String> choiceBoxTipoHabitacion;
    @FXML
    private CheckBox checkBoxFumador;
    @FXML
    private Button buttonLimpiar;
    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private RadioButton radioButtonAD;
    @FXML
    private RadioButton radioButtonMP;
    @FXML
    private RadioButton radioButtonPC;

    private Cliente c = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Tooltip dni = new Tooltip("Introduzca un DNI español valido");
        textFieldDNI.setTooltip(dni);
        final Tooltip nombre = new Tooltip("Nombre del cliente");
        textFieldNombre.setTooltip(nombre);
        final Tooltip direccion = new Tooltip("Direccion del cliente");
        textFieldDireccion.setTooltip(direccion);
        final Tooltip localidad = new Tooltip("Localidad del cliente");
        textFieldLocalidad.setTooltip(localidad);
        final Tooltip provincia = new Tooltip("Provincia del cliente");
        choiceBoxProvincia.setTooltip(provincia);
        final Tooltip llegada = new Tooltip("Fecha de ingreso");
        datePickerLlegada.setTooltip(llegada);
        final Tooltip salida = new Tooltip("Fecha de salida");
        datePickerSalida.setTooltip(salida);
        final Tooltip numHab = new Tooltip("Numero de habitaciones a disponer");
        spinnerNumHabitaciones.setTooltip(numHab);
        final Tooltip tipoHab = new Tooltip("Tipo de habitacion/es");
        choiceBoxTipoHabitacion.setTooltip(tipoHab);
        final Tooltip fumador = new Tooltip("Habitacion para fumador");
        checkBoxFumador.setTooltip(fumador);
        final Tooltip limpiar = new Tooltip("Limpiar los campos");
        buttonLimpiar.setTooltip(limpiar);
        final Tooltip aceptar = new Tooltip("Aceptar y realizar la reserva");
        buttonAceptar.setTooltip(aceptar);
        final Tooltip cancelar = new Tooltip("Cancelar la reserva");
        buttonCancelar.setTooltip(cancelar);
        final Tooltip ad = new Tooltip("Tipo de régimen: Almuerzo y desayuno");
        radioButtonAD.setTooltip(ad);
        final Tooltip mp = new Tooltip("Tipo de régimen: Media Pension");
        radioButtonMP.setTooltip(mp);
        final Tooltip pc = new Tooltip("Tipo de régimen: Pension Completa");
        radioButtonPC.setTooltip(mp);

        //Propiedades radiobuttons
        ToggleGroup tg = new ToggleGroup();

        radioButtonAD.setToggleGroup(tg);
        radioButtonMP.setToggleGroup(tg);
        radioButtonPC.setToggleGroup(tg);

        //Valores del spinner
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        spinnerNumHabitaciones.setValueFactory(svf);
        spinnerNumHabitaciones.setEditable(true);

        //Valores choiceBoxProvincia
        BDHotel bdhotel = new BDHotel();
        choiceBoxProvincia.setItems(FXCollections.observableList(bdhotel.listaProvincias()));
        bdhotel.cerrarConexion();

        //Buscar DNI introducido en base de datos
        textFieldDNI.focusedProperty().addListener(new ChangeListener<Boolean>() {

            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    BDHotel bdhotel = new BDHotel();
                    String dni = textFieldDNI.getText();
                    String pattern = "[0-9]{8}[A-Z a-z]{1}";

                    if (Pattern.matches(pattern, dni)) {
                        c = bdhotel.buscarCliente(dni);
                        if (c != null) {
                            textFieldNombre.setText(c.getNombre());
                            textFieldDireccion.setText(c.getDireccion());
                            textFieldLocalidad.setText(c.getLocalidad());
                            choiceBoxProvincia.setValue(c.getProvincia());
                            textFieldNombre.setDisable(true);
                            textFieldNombre.setStyle("-fx-opacity: 1.0;");
                            textFieldDireccion.setDisable(true);
                            textFieldDireccion.setStyle("-fx-opacity: 1.0;");
                            textFieldLocalidad.setDisable(true);
                            textFieldLocalidad.setStyle("-fx-opacity: 1.0;");
                            choiceBoxProvincia.setDisable(true);

                        }
                        if (c == null) {
                            textFieldNombre.setDisable(false);
                            textFieldDireccion.setDisable(false);
                            textFieldLocalidad.setDisable(false);
                            choiceBoxProvincia.setDisable(false);
                            textFieldNombre.setText("");
                            textFieldDireccion.setText("");
                            textFieldLocalidad.setText("");
                            choiceBoxProvincia.setValue(null);
                            textFieldNombre.setPromptText("Introcude nombre...");
                            textFieldDireccion.setPromptText("Introduce dirección...");
                            textFieldLocalidad.setPromptText("Introduce localidad...");
                        }
                    }
                    bdhotel.cerrarConexion();

                }
            }
        });

        //Propierdades datePickers 
        datePickerLlegada.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);

            }
        });

        datePickerLlegada.setOnAction(e -> {
            if (datePickerLlegada.getValue() != null) {
                datePickerSalida.setDisable(false);

                datePickerSalida.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (datePickerLlegada.getValue() != null) {
                            LocalDate entrada = datePickerLlegada.getValue();

                            setDisable(empty || date.compareTo(entrada) < 0);
                        }
                    }
                });
            }

        });

        //Rellenar choicebox tipo de habitacion
        choiceBoxTipoHabitacion.getItems().addAll("Doble de uso individual", "Individual", "Suite", "Suite Presidencial");
    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {
        textFieldDNI.setText("");
        textFieldNombre.setText("");
        textFieldDireccion.setText("");
        textFieldLocalidad.setText("");
        choiceBoxProvincia.setValue(null);
        datePickerLlegada.setValue(null);
        datePickerSalida.setValue(null);
        SpinnerValueFactory svfNuevo = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        spinnerNumHabitaciones.setValueFactory(svfNuevo);
        spinnerNumHabitaciones.setEditable(true);
        choiceBoxTipoHabitacion.setValue(null);
        checkBoxFumador.setSelected(false);
        radioButtonAD.setSelected(false);
        radioButtonMP.setSelected(false);
        radioButtonPC.setSelected(false);

    }

    @FXML
    private void onActionAceptar(ActionEvent event) {
        //Variable booleana para saber si todos los datos introducidos son correctos 
        boolean errorFormato = false;

        String pattern = "[0-9]{8}[A-Z a-z]{1}";
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error de formato");
        alert.setHeaderText("");

        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar solicitud");
        confirmacion.setHeaderText("Seleccione CONFIRMAR si desea realizar la reserva, \nSeleccione CANCELAR si quiere volver a la realización de la reserva.");

        confirmacion.setContentText("Resumen de la Reserva: \n"
                + "\tCliente: " + textFieldNombre.getText() + ", " + textFieldDNI.getText() + "\n"
                + "\tFecha de llegada: " + datePickerLlegada.getValue() + "\n"
                + "\tFecha de salida: " + datePickerSalida.getValue() + "\n"
                + "\tNumero de Habitaciones: " + spinnerNumHabitaciones.getValue() + "\n"
                + "\tTipo de habitacione: " + choiceBoxTipoHabitacion.getValue() + "\n");

        if (radioButtonAD.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de regimen: Alojamiento y desayuno\n");
        } else if (radioButtonMP.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de regimen: Media pension\n");
        } else if (radioButtonPC.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tTipo de regimen: Pension completa\n");
        }

        if (checkBoxFumador.isSelected()) {
            confirmacion.setContentText(confirmacion.getContentText() + "\tFumador: Sí\n");
        } else {
            confirmacion.setContentText(confirmacion.getContentText() + "\tFumador: No\n");
        }

        ButtonType buttonTypeAceptar = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacion.getButtonTypes().setAll(buttonTypeCancel, buttonTypeAceptar);

        if (textFieldDNI.getText().isEmpty() || !Pattern.matches(pattern, textFieldDNI.getText())) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo DNI\n");
            errorFormato = true;
        }

        if (textFieldNombre.getText().isEmpty()) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo nombre\n");
            errorFormato = true;
        }

        if (textFieldDireccion.getText().isEmpty()) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo dirección\n");
            errorFormato = true;
        }

        if (textFieldLocalidad.getText().isEmpty()) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo localidad\n");
            errorFormato = true;
        }

        if (choiceBoxProvincia.getValue() == null) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el campo provincia\n");
            errorFormato = true;
        }

        if (datePickerLlegada.getValue() == null) {
            alert.setHeaderText(alert.getHeaderText() + "Error en la fecha de llegada\n");
            errorFormato = true;
        }

        if (datePickerSalida.getValue() == null) {
            alert.setHeaderText(alert.getHeaderText() + "Error en la fecha de salida\n");
            errorFormato = true;
        }

        if ((Integer) spinnerNumHabitaciones.getValue() == 0) {
            alert.setHeaderText(alert.getHeaderText() + "Error en el número de habitaciones\n");
            errorFormato = true;
        }

        if (choiceBoxTipoHabitacion.getValue() == null) {
            alert.setHeaderText(alert.getHeaderText() + "Error, no ha elegido ningún tipo de habitación\n");
            errorFormato = true;
        }

        if (!radioButtonAD.isSelected() && !radioButtonMP.isSelected() && !radioButtonPC.isSelected()) {
            alert.setHeaderText(alert.getHeaderText() + "Error, no ha seleccionado ningún régimen.\n");
            errorFormato = true;
        }

        if (errorFormato == true) {
            alert.showAndWait();
        }

        if (errorFormato == false) {
            Optional<ButtonType> result = confirmacion.showAndWait();
            if (result.get() == buttonTypeCancel) {
                confirmacion.close();
            } else if (result.get() == buttonTypeAceptar) {
                BDHotel bdhotel = new BDHotel();

                if (c == null) {
                    String[] cliente = {textFieldDNI.getText(), textFieldNombre.getText(), textFieldDireccion.getText(), textFieldLocalidad.getText(), choiceBoxProvincia.getValue().getId().toString()};
                    c = bdhotel.altaCliente(cliente);
                }
                String[] reserva = {spinnerNumHabitaciones.getValue().toString(), choiceBoxTipoHabitacion.getValue(), "", ""};

                if (radioButtonAD.isSelected()) {
                    reserva[2] = "D";
                }
                if (radioButtonMP.isSelected()) {
                    reserva[2] = "M";
                }
                if (radioButtonPC.isSelected()) {
                    reserva[2] = "P";
                }
                if (checkBoxFumador.isSelected()) {
                    reserva[3] = "V";
                } else {
                    reserva[3] = "F";
                }

                bdhotel.registroReservaHab(datePickerLlegada.getValue(), datePickerSalida.getValue(), c, reserva);
                bdhotel.cerrarConexion();
            }
        }

    }

    @FXML
    private void onActionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.buttonCancelar.getScene().getWindow();
        stage.close();
    }

}
