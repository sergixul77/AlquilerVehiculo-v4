package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ListarAlquileres extends Controlador {

	private boolean cancelar;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfFechaAlquiler;

	@FXML
	private TextField tfFechaDevolucion;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfPrecio;

	@FXML
	private TableColumn<Alquiler, String> tcDni;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaAlquiler;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaDevololucion;

	@FXML
	private TableColumn<Alquiler, String> tcMatricula;
	@FXML
	private TableColumn<Alquiler, String> tcPrecio;

	@FXML
	private TableView<Alquiler> tvAlquileres;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	void acerca_de(ActionEvent event) {
		AcercaDe acercade = (AcercaDe) Controladores.get("vistas/Acercade.fxml", "Acerca de...", getEscenario());
		acercade.getEscenario().showAndWait();
	}

	@FXML
	void insertarAlquiler(ActionEvent event) {
		LeerAlquiler insertarAlquiler = (LeerAlquiler) Controladores.get("vistas/LeerAlquiler.fxml",
				"Insertar Alquiler", getEscenario());
		insertarAlquiler.limpiar();
		insertarAlquiler.getEscenario().showAndWait();
		try {
			Alquiler alquiler = insertarAlquiler.getAlquiler();
			if (alquiler != null) {
				VistaGrafica.getInstancia().getControlador().insertar(alquiler);
				Dialogos.mostrarDialogoAdvertencia("Insertar Alquiler", "Alquiler insertado correctamente",
						getEscenario());
				tvAlquileres.getItems().add(alquiler);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Insertar Alquiler", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void borrarAlquiler(ActionEvent event) {

		BorrarAlquiler borrarAlquiler = (BorrarAlquiler) Controladores.get("vistas/BorrarAlquiler.fxml",
				"Borrar Alquiler", getEscenario());
		borrarAlquiler.limpiar();
		borrarAlquiler.getEscenario().showAndWait();

		try {
			Alquiler alquiler = borrarAlquiler.getAlquiler();
			if (alquiler != null) {
				VistaGrafica.getInstancia().getControlador().borrar(alquiler);
				Dialogos.mostrarDialogoAdvertencia("Insertar Alquiler", "Alquiler borrado Correctamente",
						getEscenario());
				tvAlquileres.getItems().remove(alquiler);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Insertar Alquiler", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void buscarAlquiler(ActionEvent event) {

		BuscarAlquiler buscarAlquiler = (BuscarAlquiler) Controladores.get("vistas/buscarAlquiler.fxml",
				"Buscar Alquiler", getEscenario());
		buscarAlquiler.limpiar();
		buscarAlquiler.getEscenario().showAndWait();

		try {
			Alquiler alquiler = buscarAlquiler.getAlquiler();
			tfDni.setText(alquiler.getCliente().getDni());
			tfMatricula.setText(alquiler.getVehiculo().getMatricula());
			tfFechaAlquiler.setText(String.format("%s", alquiler.getFechaAlquiler()));
			LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
			if (fechaDevolucion != null) {
				tfFechaDevolucion.setText(String.format("%s", fechaDevolucion));
				tfPrecio.setText(String.format("%s", alquiler.getPrecio()));
			} else {
				tfFechaDevolucion.setText("Alquiler sin devolver");
				tfPrecio.setText("");
			}
			tfFechaDevolucion.setText(String.format("%s", alquiler.getFechaDevolucion()));

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void salir(ActionEvent event) {
		cancelar = true;
		getEscenario().close();
	}

	@FXML
	void initialize() {
		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
		tcMatricula.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
		tcFechaAlquiler.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getFechaAlquiler()));
		tcFechaDevololucion
				.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getFechaDevolucion()));
		tcPrecio.setCellValueFactory(fila -> new SimpleStringProperty(formateoPrecio(fila.getValue())));
		Controles.deshabilitarCamposTexto(tfDni, tfMatricula, tfFechaAlquiler, tfFechaDevolucion, tfPrecio);
	}

	@FXML
	private String formateoPrecio(Alquiler alquiler) {
		String precio = "";
		if (alquiler.getFechaDevolucion() != null) {
			precio = String.format("%s", alquiler.getPrecio());
		}
		return precio;
	}

	@FXML
	public void actualizar(List<Alquiler> alquileres) {
		tvAlquileres.setItems(FXCollections.observableArrayList(alquileres));
	}

}