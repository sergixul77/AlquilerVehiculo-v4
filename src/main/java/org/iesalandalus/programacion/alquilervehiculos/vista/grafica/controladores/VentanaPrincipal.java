
package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class VentanaPrincipal extends Controlador {

	@FXML
	void listarClientes(ActionEvent event) {

		ListarClientes listarClientes = (ListarClientes) Controladores.get("vistas/ListarClientes.fxml",
				"Listar Clientes", getEscenario());

		listarClientes.actualizar(VistaGrafica.getInstancia().getControlador().getClientes());
		listarClientes.getEscenario().showAndWait();
	}

	@FXML
	void listarVehiculos(ActionEvent event) {

		ListarVehiculos listarVehiculos = (ListarVehiculos) Controladores.get("vistas/ListarVehiculos.fxml",
				"Listar Vehiculos", getEscenario());
		listarVehiculos.actualizar(VistaGrafica.getInstancia().getControlador().getVehiculos());
		listarVehiculos.getEscenario().showAndWait();
	}

	@FXML
	void ListarAlquileres(ActionEvent event) {
		ListarAlquileres listarAlquileres = (ListarAlquileres) Controladores.get("vistas/ListarAlquileres.fxml",
				"Listar Alquileres", getEscenario());
		listarAlquileres.actualizar(VistaGrafica.getInstancia().getControlador().getAlquileres());
		listarAlquileres.getEscenario().showAndWait();
	}

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void acerca_de(ActionEvent event) {
		AcercaDe acercade = (AcercaDe) Controladores.get("vistas/Acercade.fxml", "Acerca de...", getEscenario());
		acercade.getEscenario().showAndWait();
	}

}
