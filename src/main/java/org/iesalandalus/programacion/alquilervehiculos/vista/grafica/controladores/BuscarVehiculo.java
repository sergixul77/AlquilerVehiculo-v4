package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BuscarVehiculo extends Controlador {

	
	private boolean cancelado;
	 @FXML
	    private TextField tfMatricula;
	

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
	
		void limpiar () {
			tfMatricula.setText("");
			cancelado= true;
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
		
		@FXML
		public String getMatricula () {
			return cancelado ? "": tfMatricula.getText();
			
		}

}
