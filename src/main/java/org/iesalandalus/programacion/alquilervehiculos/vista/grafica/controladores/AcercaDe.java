package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AcercaDe extends Controlador {

	@FXML
	void github(ActionEvent event) {

		String url = "https://github.com/sergixul77/AlquilerVehiculo-v4.git";

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

}
