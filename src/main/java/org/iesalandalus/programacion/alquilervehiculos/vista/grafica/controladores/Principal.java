package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Principal  extends Controlador{
	
	 @FXML
	    private Button acceder;

	    @FXML
	    private MenuItem acerca_de;

	    @FXML
	    private Menu boton_ayuda;

	    @FXML
	    private MenuItem boton_cerrar;

	    @FXML
	    private Menu boton_salir;


	    @FXML
	   public void acceder(ActionEvent event) {
	    	
	    	VentanaPrincipal principal = (VentanaPrincipal) Controladores.get("vistas/VentanaPrincipal.fxml", "Principal", getEscenario());
	    	principal.getEscenario().showAndWait();
	    }
	    
	    
	    void salir() {
	    	getEscenario().close();
	    }
	    
	    @FXML
	    void acerca_de(ActionEvent event) {
	    	AcercaDe acercade = (AcercaDe) Controladores.get("vistas/Acercade.fxml", "Acerca de...", getEscenario());
			acercade.getEscenario().showAndWait();
	    }
	    
	    @FXML
	    void salir(ActionEvent event) {
	    	getEscenario().close();
	    }

	}

