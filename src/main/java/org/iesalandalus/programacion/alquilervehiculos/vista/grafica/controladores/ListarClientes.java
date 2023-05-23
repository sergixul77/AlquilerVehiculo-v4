//package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;
//
//import java.util.List;
//
//import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
//import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
//import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//public class ListarClientes extends Controlador {
//
//	
//	
//	  @FXML
//	    private Button btBoton;
//
//	    @FXML
//	    private TableColumn<Cliente, String> tcDni;
//
//	    @FXML
//	    private TableColumn<Cliente, String> tcNombre;
//
//	    @FXML
//	    private TableColumn<Cliente, String> tcTelefono;
//
//	    @FXML
//	    private TableView<Cliente> tvClientes;
//
//	    @FXML
//	    private Font x1;
//
//	    @FXML
//	    private Color x2;
//
//	    @FXML
//	    private Font x3;
//
//	    @FXML
//	    private Color x4;
//
//	    @FXML
//	    void aceptar(ActionEvent event) {
//
//	    	getEscenario().close();
//	    	
//	    }
//	    @FXML
//	    void initialize() {
//			tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//			
//			tcDni.setCellValueFactory (fila -> new SimpleStringProperty(fila.getValue().getDni()));
//			
//			tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
//			
//		}
//	    @FXML
//	    public void actualizar (List<Cliente> cliente) {
//	    	tvClientes.setItems(FXCollections.observableArrayList(cliente));
//	    	
//		}
//	    
//	    
//
//		@FXML
//
//		void modificarCliente() {
//
//			ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("vistas/ModificarCliente.fxml",
//					"ModificarCliente", getEscenario());
//			modificarCliente.limpiar();
//			modificarCliente.getEscenario().showAndWait();
//
//		}
//	    
//	    
//	
//	}

package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ListarClientes extends Controlador {

	@FXML
	private Menu boton_ayuda;

	@FXML
	private Button btBoton;

	@FXML
	private TableColumn<Cliente, String> tcDni;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;

	@FXML
	private TableView<Cliente> tvClientes;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	@FXML
	void aceptar(ActionEvent event) {

		getEscenario().close();

	}

	@FXML
	void modificar(ActionEvent event) {

		// tvClientes.getItems().add(cliente que inserto.)

		ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("vistas/ModificarCliente.fxml",
				"ModificarCliente", getEscenario());
		// smodificarCliente.limpiar();
		modificarCliente.getEscenario().showAndWait();

		try {
//			Cliente modificar = modificarCliente.getClienteModificado(); /*preguntar mañana*/
			VistaGrafica.getInstancia().getControlador().modificar(modificarCliente.devolverCliente(),
					modificarCliente.devolverNombre(), modificarCliente.devolverTelefono());
			Dialogos.mostrarDialogoAdvertencia("Modificar Cliente", "Cliente modificado de forma correcta",
					getEscenario());
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Modificar cliente", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void buscarCliente(ActionEvent event) {
		BuscarCliente buscarCliente = (BuscarCliente) Controladores.get("vistas/BuscarCliente.fxml", "Buscar Cliente",
				getEscenario());
		buscarCliente.limpiar();
		buscarCliente.getEscenario().showAndWait();

		try {
			String dni = buscarCliente.getDni();
			if (!dni.isBlank()) {
				Cliente cliente = VistaGrafica.getInstancia().getControlador().buscar(Cliente.getClienteConDni(dni));
				tfDni.setText(cliente.getDni());
				tfNombre.setText(cliente.getNombre());
				tfTelefono.setText(cliente.getTelefono());

			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void devolverAlquilerCliente(ActionEvent event) {
		DevolverAlquilerCliente devolverAlquilerCliente = (DevolverAlquilerCliente) Controladores
				.get("vistas/DevolverAlquilerCliente.fxml", "Devolver Alquiler Cliente", getEscenario());
		devolverAlquilerCliente.getEscenario().showAndWait();
		try {

		} catch (IllegalArgumentException | NullPointerException e) {

//			VistaGrafica.getInstancia().getControlador().devolver(null, null);
		}
	}

	@FXML
	void borrar(ActionEvent event) {

		BorrarCliente borrarCliente = (BorrarCliente) Controladores.get("vistas/BorrarCliente.fxml", "Borrar Cliente",
				getEscenario());
		borrarCliente.limpiar();
		borrarCliente.getEscenario().showAndWait();

		try {
			Cliente cliente = borrarCliente.getCliente();
			if (cliente != null) {
				VistaGrafica.getInstancia().getControlador().borrar(cliente);
				tvClientes.getItems().remove(cliente);
				Dialogos.mostrarDialogoAdvertencia("Borrar Cliente", "Cliente borrado de forma correcta",
						getEscenario());
			}

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Borrar Cliente ", e.getMessage(), getEscenario());
		}

	}

	@FXML /* Metodo que inserta el cliente */
	void leerCliente(ActionEvent event) {

		LeerCliente leerCliente = (LeerCliente) Controladores.get("vistas/LeerCliente.fxml", "Leer Cliente",
				getEscenario()); /* Leo la vista */
		leerCliente.limpiar();
		leerCliente.getEscenario().showAndWait();
		/* System.out.println(leerCliente.getCliente()); */
		try {
			Cliente cliente = leerCliente.getCliente(); /* Leo el cliente, puede lanzar excepcion */
			if (cliente != null) { /* Si es diferente de null lo inserta */
				VistaGrafica.getInstancia().getControlador().insertar(cliente);
				Dialogos.mostrarDialogoAdvertencia("Insertar Cliente", "Cliente insertado de forma correcta",
						getEscenario());
				tvClientes.getItems().add(cliente);
			}
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Insertar Cliente", e.getMessage(), getEscenario());
		}

	}

	@FXML
	public void actualizar(List<Cliente> cliente) {
		tvClientes.setItems(FXCollections.observableArrayList(cliente));
	}

	@FXML
	void initialize() {
		tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getDni()));

		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		Controles.deshabilitarCamposTexto(tfDni, tfNombre, tfTelefono); // para que no se pueda escribir en ellos

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
