package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mariadb.FuenteDatosMariaDB;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FactoriaFuenteDatos {

	FICHEROS {
		@Override
		public IFuenteDatos crear() {
			
			return new FuenteDatosFicheros();
		}
	},
	
	MONGODB{

		@Override
		IFuenteDatos crear() {
			
			return new FuenteDatosMongoDB();
		}
		
	},
	
	MARIADB{

		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			return new FuenteDatosMariaDB();
		}
		
	}
	
	;
	
	 abstract IFuenteDatos crear ();
	
}


