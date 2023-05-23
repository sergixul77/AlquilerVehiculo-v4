package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BorrarVehiculo extends Controlador {

	private boolean cancelar;

	@FXML
	private TextField tfMatricula;

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

	private void comprobarMatirucla() {
		String matricula = tfMatricula.getText();
		if (matricula.matches("\\d{4}[B-DF-HJ-NP-TV-Z]{3}")) {
			tfMatricula.setStyle("-fx-border-color: green; -fx-transition: all 0.3s ease;");
		} else {
			tfMatricula.setStyle("-fx-border-color: red; -fx-transition: all 0.3s ease;");
		}

	}

	@FXML
	void initialize() {
		tfMatricula.textProperty().addListener((ob, ov, nv) -> comprobarMatirucla());

	}

	void limpiar() {
		tfMatricula.setText("");
		cancelar = true;
	}

	public Vehiculo getVehiculo() {
		String matricula = tfMatricula.getText();

		return cancelar ? null
				: VistaGrafica.getInstancia().getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula));

	}

}
