package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class LeerVehiculo extends Controlador {
	
	private static final String FURGONETA = "Furgoneta";
	private static final String TURISMO = "Turismo";
	private static final String AUTOBUS = "Autobus";
	
	private boolean cancelar; 
	
	 @FXML
	    private RadioButton tfAutobus;

	    @FXML
	    private TextField tfCilindrada;

	    @FXML
	    private RadioButton tfFurgoneta;

	    @FXML
	    private TextField tfMarca;

	    @FXML
	    private TextField tfMatricula;

	    @FXML
	    private TextField tfModelo;

	    @FXML
	    private TextField tfPeso;

	    @FXML
	    private TextField tfPlazas;

	    @FXML
	    private RadioButton tfTurismo;


		@FXML
		void cancelar(ActionEvent event) {
			cancelar = true;
			getEscenario().close();
		}
		
		@FXML
		void aceptar(ActionEvent event) {
			cancelar = false;
			getEscenario().close();
		}
		
		public Vehiculo getVehiculo() {
		    Vehiculo vehiculo = null;
		    String marca = tfMarca.getText();
		    String modelo = tfModelo.getText();
		    String matricula = tfMatricula.getText();

		    if (tfTurismo.isSelected()) {
		        if (tfCilindrada.getText().isEmpty()) {
		            
		          
		        } else {
		            int cilindrada = Integer.parseInt(tfCilindrada.getText()); /*Convierto la cadena de String a int*/
		            vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
		        }
		    } else if (tfAutobus.isSelected()) {
		        if (tfPlazas.getText().isEmpty()) {
		        } else {
		            int plazas = Integer.parseInt(tfPlazas.getText()); /*Convierto la cadena de String a int*/
		            vehiculo = new Autobus(marca, modelo, plazas, matricula);
		        }
		    } else if (tfFurgoneta.isSelected()) {
		        if (tfPlazas.getText().isEmpty() || tfPeso.getText().isEmpty()) { /*Si estan vacias los input de plazas y pma */
		        } else {
		            int plazas = Integer.parseInt(tfPlazas.getText()); /*Convierto la cadena de String a int*/
		            int pesoMaximo = Integer.parseInt(tfPeso.getText());/*Convierto la cadena de String a int*/
		            vehiculo = new Furgoneta(marca, modelo, pesoMaximo, plazas, matricula);
		        }
		    }

		    return vehiculo;
		}

		
		@FXML
		private void initialize() {
			ToggleGroup toggleGroup = new ToggleGroup();
			tfTurismo.setToggleGroup(toggleGroup);
			tfAutobus.setToggleGroup(toggleGroup);
			tfFurgoneta.setToggleGroup(toggleGroup);
			tfTurismo.setOnAction(event -> {
				if (tfTurismo.isSelected()) {
					tfPlazas.setDisable(true); // Deshabilitar el campo de número de plazas
					tfPeso.setDisable(true); // Deshabilitar el campo de peso máximo
					tfCilindrada.setDisable(false); // Habilitar el campo de cilindrada
				} else {
					tfPlazas.setDisable(false); // Habilitar el campo de número de plazas
					tfPeso.setDisable(false); // Habilitar el campo de peso máximo
					tfCilindrada.setDisable(true); // Deshabilitar el campo de cilindrada
				}
			});
				// Si esta en false es porque esta activo, si esta true es porque esta deshabilitado
			tfAutobus.setOnAction(event -> {
				if (tfAutobus.isSelected()) {
					tfPlazas.setDisable(false); // Habilitar el campo de numero de plazas
					tfPeso.setDisable(true); // Deshabilitar el campo de peso maximo
					tfCilindrada.setDisable(true); // Bloquar el campo de cilindrada
				} else {
					tfPlazas.setDisable(false); // Habilitar el campo de numero de plazas
					tfPeso.setDisable(false); // Habilitar el campo de peso maximo
					tfCilindrada.setDisable(false); // Habilitar el campo de cilindrada
				}
			});

			tfFurgoneta.setOnAction(event -> {
				if (tfFurgoneta.isSelected()) {
					tfCilindrada.setDisable(true);// Deshabilitar el campo de cilindrada
					tfPeso.setDisable(false);// Habilitar el campo de peso
				} else {
					tfCilindrada.setDisable(false);// Habilitar el campo de cilindrada
					tfPeso.setDisable(false); // Habilitar el campo de cilindrada
				}
			});
		}




	    void limpiar () {
			tfMarca.setText("");
			tfCilindrada.setText("");
			tfMatricula.setText("");
			tfModelo.setText("");
			tfPeso.setText("");
			tfPlazas.setText("");
			cancelar= true;
		}
	    
	    
	
	

}
