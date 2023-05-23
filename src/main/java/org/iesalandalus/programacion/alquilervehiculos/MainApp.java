package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVista;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class MainApp {

	public static void main(String[] args) {

		Vista vista = procesarArgumentosVistas(args);

		FactoriaFuenteDatos fuenteDatos = FactoriaFuenteDatos.FICHEROS;

		Modelo modeloCascada = new ModeloCascada(fuenteDatos);

		Controlador controlador = new Controlador(modeloCascada, vista);

		controlador.comenzar();
	}

	private static Vista procesarArgumentosVistas(String[] args) {
		Vista vista = FactoriaVista.GRAFICA.crear();
		for (String argumento : args) {

			if (argumento.equals("-vgrafica")) {
				vista = FactoriaVista.GRAFICA.crear();
			} else {
				vista = FactoriaVista.TEXTO.crear();
			}

		
		}

		return vista;
	}
}