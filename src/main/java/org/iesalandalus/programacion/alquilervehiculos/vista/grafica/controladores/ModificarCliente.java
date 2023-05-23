package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModificarCliente extends Controlador {

	private boolean cancelado;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	/* Preguntar mañana */

	public void getClienteModificado() {
		String dni = tfDni.getText(); /* Almaceno lo que le pasa el usuario por teclado a la variable dni */
		String nombre = tfNombre.getText();
		String telefono = tfTelefono.getText();
		/*
		 * Creo un nuevo cliente segun los parametros que me introduce el usuario por
		 * teclado en los inputs
		 */
	}

	private void comprobarDni() {
		String dni = tfDni.getText();
		if (dni.matches(
				"\\d{8}[A-HJ-NP-TV-Z]")) { /*
											 * Si el dni que le paso por el input es igual a la de la expresion regular
											 */
			tfDni.setStyle("-fx-border-color: green; -fx-transition: all 0.3s ease;");
		} else {
			tfDni.setStyle("-fx-border-color: red; -fx-transition: all 0.3s ease;");
		}
	}

	private void comprobarNombre() {
		String nombre = tfNombre.getText();
		if (nombre.matches("[A-Z][a-zñ]+( [A-Z][a-zñ]+)*")) {
			tfNombre.setStyle("-fx-border-color: green; -fx-transition: all 0.3s ease;");
		} else {
			tfNombre.setStyle("-fx-border-color: red; -fx-transition: all 0.3s ease;");
		}

	}

	private void comprobarTelefono() {
		String telefono = tfTelefono.getText();
		if (telefono.matches("[6-9]\\d{8}")) {
			tfTelefono.setStyle("-fx-border-color: green;");
		} else {
			tfTelefono.setStyle("-fx-border-color: red;");
		}
	}

	@FXML
	void initialize() {
		tfDni.textProperty().addListener((ob, ov, nv) -> comprobarDni());
		tfNombre.textProperty().addListener((ob, ov, nv) -> comprobarNombre());
		tfTelefono.textProperty().addListener((ob, ov, nv) -> comprobarTelefono());

	}

	@FXML
	void aceptar(ActionEvent event) {
		cancelado = false;
		getEscenario().close();

	}

	public Cliente devolverCliente() {

		Cliente cliente = VistaGrafica.getInstancia().getControlador()
				.buscar(Cliente.getClienteConDni(tfDni.getText())); /* deveuvel el cliente que tiene ese dni */

		return cliente;

	}

	public String devolverNombre() {

		return tfNombre.getText();
	}

	public String devolverTelefono() {

		return tfTelefono.getText();
	}

	@FXML
	void cancelar(ActionEvent event) {

		cancelado = true;
		getEscenario().close();

	}

}


