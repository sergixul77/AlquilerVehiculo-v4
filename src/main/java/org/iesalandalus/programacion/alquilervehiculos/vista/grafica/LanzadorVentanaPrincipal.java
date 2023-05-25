package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;


import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

	public class LanzadorVentanaPrincipal extends Application {
		
		private static final String Titulo = "Vista Ventanas Alquiler de Vehiculos";

		@Override
		public void start(Stage escenarioPrincipal) throws Exception {
			try {
//				FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/Ejemplo.fxml"));
//				Parent raiz = cargadorVentanaPrincipal.load();
//				Scene escena = new Scene(raiz);
//				escenarioPrincipal.setTitle("Vista Gráfica de Alquiler de Vehículos");
//				escenarioPrincipal.setScene(escena);
//				escenarioPrincipal.show();
				// añadir imagen de icono del proyecto en la imagen del logo. 
				
		        
				Controlador ventanaPrincipal = Controladores.get("vistas/Principal.fxml", Titulo, null);
				ventanaPrincipal.getEscenario().getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo_blanco.png")));
				ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e)); /*Para que nos pida confirmacion a la hora de salir. */
				ventanaPrincipal.getEscenario().show();
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}
		}

		public static void comenzar() {
			launch(LanzadorVentanaPrincipal.class);
		}
		
		
		private void confirmarSalida (Stage escenario, WindowEvent e) {
			if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estas seguro de que quieres salir de la aplicacion?", escenario)) {
				escenario.close();
			}
			else {
				e.consume();
			}
		}
		
		
		
		
		
	}

