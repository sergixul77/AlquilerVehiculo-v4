package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DevolverAlquilerVehiculo extends Controlador {

	private boolean cancelado;

	@FXML
	private Button boton_aceptar;

	@FXML
	private VBox boton_cancelar;

	@FXML
	private DatePicker inputFecha;

	@FXML
	private TextField tfMatricula;

	@FXML
	void aceptar(ActionEvent event) {

		cancelado = false;
		getEscenario().close();

	}

	@FXML
	void cancelar(ActionEvent event) {

		cancelado = true;
		getEscenario().close();
	}

	private void compribarMatricula() {
		String nombre = tfMatricula.getText();
		if (nombre.matches("\\d{4}[B-DF-HJ-NP-TV-Z]{3}")) {
			tfMatricula.setStyle("-fx-border-color: green");
		} else {
			tfMatricula.setStyle("-fx-border-color: red");
		}
	}

	@FXML
	void initialize() {
		tfMatricula.textProperty().addListener((ob, ov, nv) -> compribarMatricula()); /* esto no lo utilizo */

	}

}
