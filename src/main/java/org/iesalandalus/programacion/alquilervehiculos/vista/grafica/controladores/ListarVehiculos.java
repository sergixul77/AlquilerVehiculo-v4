package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ListarVehiculos extends Controlador {

	private static final String AUTOBUS = "Autobus";

	private static final String FURGONETA = "Furgoneta";

	private static final String TURISMO = "Turismo";

	@FXML
	private TextField tfCilindrada;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfPlazas;

	@FXML
	private TextField tfPma;

	@FXML
	private TableColumn<Vehiculo, String> tcCilindrada;

	@FXML
	private TableColumn<Vehiculo, String> tcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcModelo;

	@FXML
	private TableColumn<Vehiculo, String> tcPlazas;

	@FXML
	private TableColumn<Vehiculo, String> tcPma;

	@FXML
	private TableView<Vehiculo> tvVehiculo;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	private String formateoCilindrada(Vehiculo vehiculo) {
		return vehiculo instanceof Turismo turismo ? String.format("%s", turismo.getCilindrada()) : "";

	}

	@FXML
	private String formateoPlazas(Vehiculo vehiculo) {
		String plazas = "";
		if (vehiculo instanceof Autobus autobus) {
			plazas = String.format("%s", autobus.getPlazas());
		} else if (vehiculo instanceof Furgoneta furgoneta) {
			plazas = String.format("%s", furgoneta.getPlazas());
		}
		return plazas;
	}

	private String formatearPma(Vehiculo vehiculo) {
		return vehiculo instanceof Furgoneta furgoneta ? String.format("%s", furgoneta.getPma()) : "";
	}

	@FXML
	void initialize() {
		tcMarca.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getMarca()));
		tcModelo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getModelo()));
		tcMatricula.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getMatricula()));
		tcCilindrada.setCellValueFactory(fila -> new SimpleStringProperty(formateoCilindrada(fila.getValue())));
		tcPlazas.setCellValueFactory(fila -> new SimpleStringProperty(formateoPlazas(fila.getValue())));
		tcPma.setCellValueFactory(fila -> new SimpleStringProperty(formatearPma(fila.getValue())));
		Controles.deshabilitarCamposTexto(tfCilindrada, tfMarca, tfMatricula, tfModelo, tfPlazas, tfPma); // para que no
																											// se pueda
																											// escribir
																											// en ellos

	}

	@FXML
	public void actualizar(List<Vehiculo> vehiculos) {
		tvVehiculo.setItems(FXCollections.observableArrayList(vehiculos));
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

	@FXML
	void insertar_vehiculo(ActionEvent event) { /* Leer Vehiculo */
		LeerVehiculo insertarVehiculo = (LeerVehiculo) Controladores.get("vistas/LeerVehiculo.fxml",
				"Insertar Vehiculo", getEscenario());
		insertarVehiculo.limpiar();
		insertarVehiculo.getEscenario().showAndWait();
		try {
			Vehiculo vehiculo = insertarVehiculo.getVehiculo();
			if (vehiculo != null) {
				VistaGrafica.getInstancia().getControlador().insertar(vehiculo);
				Dialogos.mostrarDialogoAdvertencia("Insertar Vehiculo", "Vehiculo insertado correctamente",
						getEscenario());
				tvVehiculo.getItems().add(vehiculo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Dialogos.mostrarDialogoError("Insertar Vehiculo", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void borrar_vehiculo(ActionEvent event) {

		BorrarVehiculo borrarVehiculo = (BorrarVehiculo) Controladores.get("vistas/BorrarVehiculo.fxml",
				"Borrar Vehiculo", getEscenario());
		borrarVehiculo.limpiar();
		borrarVehiculo.getEscenario().showAndWait();

		try {
			Vehiculo vehiculo = borrarVehiculo.getVehiculo();
			if (vehiculo != null) {
				VistaGrafica.getInstancia().getControlador().borrar(vehiculo);
				tvVehiculo.getItems().remove(vehiculo);
				Dialogos.mostrarDialogoAdvertencia("Borrar Vehiculo", "Vehiculo borrado correctamente", getEscenario());
			}

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar Vehiculo ", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void buscar_vehiculo(ActionEvent event) {
		BuscarVehiculo buscarVehiculo = (BuscarVehiculo) Controladores.get("vistas/BuscarVehiculo.fxml",
				"Buscar Vehiculo", getEscenario());
		buscarVehiculo.limpiar();
		buscarVehiculo.getEscenario().showAndWait();

		try {
			String matricula = buscarVehiculo.getMatricula();
			if (!matricula.isBlank()) {
				Vehiculo vehiculo = VistaGrafica.getInstancia().getControlador()
						.buscar(Vehiculo.getVehiculoConMatricula(matricula));
				tfMarca.setText(vehiculo.getMarca());
				tfModelo.setText(vehiculo.getModelo());
				tfMatricula.setText(vehiculo.getMatricula());
				// Formatero y establezco la cilindrada del vehiculo
				String cilindradaFormateada = formateoCilindrada(vehiculo);
				tfCilindrada.setText(cilindradaFormateada);
				// Formatero y establezco las plazas del vehiculo
				String plazasFormateadas = formateoPlazas(vehiculo);
				tfPlazas.setText(plazasFormateadas);
				// Formateo y establezco el pma del vehiculo
				String pmaFormateado = formatearPma(vehiculo);
				tfPma.setText(pmaFormateado);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void devolver_vehiculo(ActionEvent event) {

		DevolverAlquilerVehiculo devolverAlquiler = (DevolverAlquilerVehiculo) Controladores
				.get("vistas/DevolverAlquilerVehiculo.fxml", "Devolver Alquiler Vehiculo", getEscenario());
		devolverAlquiler.getEscenario().showAndWait();

		try {

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}

	}

}
