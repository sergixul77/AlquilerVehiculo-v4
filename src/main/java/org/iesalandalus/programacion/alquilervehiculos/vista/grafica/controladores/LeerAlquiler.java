package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class LeerAlquiler extends Controlador {

	private boolean cancelar;

	@FXML
	private TextField tfDni;

	@FXML
	private DatePicker tfFechaAlquiler;

	@FXML
	private TextField tfMatricula;

	@FXML
	public Alquiler getAlquiler() {

		Vehiculo vehiculo = VistaGrafica.getInstancia().getControlador()
				.buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText())); //Busca el vehiculo que se pasa por el input 
		Cliente cliente = VistaGrafica.getInstancia().getControlador()
				.buscar(Cliente.getClienteConDni(tfDni.getText()));
		LocalDate fechaAlquiler = tfFechaAlquiler.getValue();
		return cancelar ? null : new Alquiler(cliente, vehiculo, fechaAlquiler); // Tiene que ser asi, si no da error
	}

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
		tfMatricula.textProperty().addListener((ob, ov, nv) -> comprobarMatirucla());
		tfDni.textProperty().addListener((ob, ov, nv) -> comprobarDni());

	}

	void limpiar() {

		tfMatricula.setText("");
		tfDni.setText("");
		tfFechaAlquiler.setAccessibleText("");
		cancelar = true;
	}

}
