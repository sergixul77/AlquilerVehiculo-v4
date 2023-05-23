package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BorrarCliente extends Controlador {

	private boolean cancelar;

	@FXML
	private TextField tfDni;

	@FXML
	void aceptar(ActionEvent event) {

		cancelar = false;
		getEscenario().close();

	}

	@FXML
	void cancelar(ActionEvent event) {

		cancelar = true;
		getEscenario().close();
	}

	private void comprobarDni() {
		String dni = tfDni.getText();
		if (dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
			tfDni.setStyle("-fx-border-color: green; -fx-transition: all 0.3s ease;");
		} else {
			tfDni.setStyle("-fx-border-color: red; -fx-transition: all 0.3s ease;");
		}

	}

	@FXML
	void initialize() {
		tfDni.textProperty().addListener((ob, ov, nv) -> comprobarDni());

	}

	public Cliente getCliente() {
		String dni = tfDni.getText();

		return cancelar ? null : VistaGrafica.getInstancia().getControlador().buscar(Cliente.getClienteConDni(dni));

	}

	void limpiar() {
		tfDni.setText("");
		cancelar = true;
	}

}
