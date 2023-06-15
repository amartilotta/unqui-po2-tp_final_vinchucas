package ar.edu.unq.po2.tpfinal;

import java.util.List;

public class Ubicacion {
	
	
	
	//ATRIBUTOS.
	private double latitud;
	private double longitud;
	
	
	
	//CONSTRUCTOR.
	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	
	
	//GETTERS.
	public double getLatitud() {
		return this.latitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
	
	
	
	//METODOS_PRINCIPALES.
	public double distanciaEntre(Ubicacion otraUbicacion) {
		if(this.comparteCoordenadasCon(otraUbicacion)) {
			return this.distanciaConCoordenadaCompartida(otraUbicacion);
		}
		else {
			return this.distanciaEntreSinCoordenadasCompartidasCon(otraUbicacion);
		}
	}
	
	public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, int distancia) {
		List<Ubicacion> ubicacionesAMenosDe = ubicaciones.stream()
														 .filter(u -> this.distanciaEntre(u) < distancia)
														 .toList();
		return ubicacionesAMenosDe;
	}
	
	public List<Muestra> getMuestrasAMenosDe(Muestra muestra, int distancia, List<Muestra> muestras) {
		Ubicacion ubicacionDeMuestra = muestra.getUbicacion();
		List<Muestra> muestrasFiltradas = muestras.stream()
												  .filter(m -> ubicacionDeMuestra.distanciaEntre(m.getUbicacion()) < distancia)
												  .toList();
		
		return muestrasFiltradas;
	}
	
	
	
	//AUXILIARES.
	private Boolean comparteCoordenadasCon(Ubicacion otraUbicacion) {
		return(this.comparteLongitudCon(otraUbicacion)
			|| this.comparteLatitudCon(otraUbicacion));
	}
	
	private double distanciaConCoordenadaCompartida(Ubicacion otraUbicacion) {
		//PRECOND: La Ubicacion actual debe compartir latitud o longitud con otraUbicacion.
		if(this.comparteLatitudCon(otraUbicacion)) {
			return Math.abs(this.longitud - otraUbicacion.getLongitud());
		}
		else {
			return Math.abs(this.latitud - otraUbicacion.getLatitud());
		}
	}
	
	private Boolean comparteLongitudCon(Ubicacion otraUbicacion) {
		return this.longitud == otraUbicacion.getLongitud();
	}
	
	private Boolean comparteLatitudCon(Ubicacion otraUbicacion) {
		return this.latitud == otraUbicacion.getLatitud();
	}
	
	private double distanciaEntreSinCoordenadasCompartidasCon(Ubicacion otraUbicacion) {
		//PRECOND: La Ubicacion actual no debe tener coordenadas compartidas con otraUbicacion.
		return Math.abs(this.resultadoFinalDeDistanciaCon(otraUbicacion));
	}
	
	private double resultadoFinalDeDistanciaCon(Ubicacion otraUbicacion) {
		return Math.pow((this.sumaDeOperacionConCoordenadasDe(otraUbicacion)), 0.5);
	}
	
	private double sumaDeOperacionConCoordenadasDe(Ubicacion otraUbicacion) {
		return(Math.pow((this.latitud - otraUbicacion.getLatitud()), 2)
				+
			   Math.pow((this.longitud - otraUbicacion.getLongitud()), 2));
	}
}
