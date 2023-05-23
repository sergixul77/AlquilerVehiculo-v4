package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;


import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LeerCliente extends Controlador {
	
	
	
	@FXML private TextField tfDni;
	@FXML private TextField tfNombre;
	@FXML private TextField tfTelefono;
	
	private boolean cancelado;
	
	public Cliente getCliente() {
		String dni = tfDni.getText();
		String nombre = tfNombre.getText();
		String telefono = tfTelefono.getText();
		return cancelado ? null : new Cliente(nombre, dni, telefono);
	}
	
	@FXML
	void cancelar(ActionEvent event) {
		cancelado = true;
		getEscenario().close();
	}
	
	@FXML
	void aceptar(ActionEvent event) {
		cancelado = false;
		getEscenario().close();
	}
	
	private void comprobarNombre() {
		String nombre = tfNombre.getText();
		if(nombre.matches("[A-Z][a-zñ]+( [A-Z][a-zñ]+)*")) {
			tfNombre.setStyle("-fx-border-color: green");
		} else {
			tfNombre.setStyle("-fx-border-color: red");
		}
	}
	@FXML
	void initialize() {
		tfNombre.textProperty().addListener((ob,ov,nv) -> comprobarNombre()); /*esto no lo utilizo*/
		
	}
	
	
	 void limpiar () {
		tfDni.setText("");
		tfNombre.setText("");
		tfTelefono.setText("");
		cancelado= true;
	}
	

}
