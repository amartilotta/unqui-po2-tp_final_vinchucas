package ar.edu.unq.po2.tpfinal;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;



public class ZonaDeCobertura {
	private String nombre;
	private Ubicacion epicentro;
	private Double radioEnKm;
	
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, Double radioEnKm) {
		this.nombre		=	nombre;
		this.epicentro	=	epicentro;
		this.radioEnKm	=	radioEnKm;
	} 
	
	public Ubicacion getEpicentro() {
		return this.epicentro;
	}
	public Double getRadioEnKM() {
		return this.radioEnKm;
	}
	
	
	public ArrayList<Muestra> muestrasDeLaZona(ArrayList<Muestra> listaDeMuestras){
		
		ArrayList<Muestra> muestrasEnLaZona = new ArrayList<Muestra>();
		
		listaDeMuestras.forEach(muestra -> {
			if (this.perteneceUbicacionALaZona(muestra.getUbicacion(), this.getEpicentro())) {
				muestrasEnLaZona.add(muestra);
			}
		});	 
		return muestrasEnLaZona;
				    
	} 
	
	
	public Boolean perteneceUbicacionALaZona(Ubicacion ubicacionaAVerificar, Ubicacion ubicacionDeZona) {
		double distancia = this.distanciaEntre(ubicacionaAVerificar, ubicacionDeZona);
        Boolean perteneceAUbicacion = false;
        if (distancia <= this.getRadioEnKM()) {
        	perteneceAUbicacion = true;
        }
        return perteneceAUbicacion;
	} 
	 
	
	public Double distanciaEntre(Ubicacion ubicacion1, Ubicacion ubicacion2) {
		double latitud_AVerificar = ubicacion1.getLatitud();
		double longitud_AVerificar = ubicacion1.getLongitud();
		double longitudDeZona = ubicacion2.getLongitud();
		double latitudDeZona = ubicacion2.getLatitud();
		
		double radioTierra = 6371; // Radio de la Tierra en kilómetros
		
		 // Conversión de grados a radianes
        double lat1Rad = Math.toRadians(latitud_AVerificar);
        double lon1Rad = Math.toRadians(longitud_AVerificar);
        double lat2Rad = Math.toRadians(latitudDeZona);
        double lon2Rad = Math.toRadians(longitudDeZona);

        // Diferencias de latitud y longitud
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // sacar distancia entre ambas ubicaciones con la formula de Distancia euclidiana
        double distancia = 2 * radioTierra * Math.asin(Math.sqrt(Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2)));
        
        return distancia; 
        
	}
	 
	
	public ArrayList<ZonaDeCobertura> zonasQueSeSolapan(ArrayList<ZonaDeCobertura> zonasDeCobertura){
		
		ArrayList<ZonaDeCobertura> listaDeZonasQueSeSolapan = new ArrayList<ZonaDeCobertura>();

		
		for(int i=0; i < zonasDeCobertura.size(); i++) {
			ZonaDeCobertura zonaActual = zonasDeCobertura.get(i);
			 
			ArrayList<ZonaDeCobertura> listaDeZonasAComparar = new ArrayList<>(zonasDeCobertura);
			ZonaDeCobertura zonaEliminada =  listaDeZonasAComparar.remove(i); //elimina la zona actual.
	
			listaDeZonasAComparar.forEach(zonaAComparar ->{
				Double sumaDeRadios = zonaActual.getRadioEnKM() + zonaAComparar.getRadioEnKM();
				Double distancia = this.distanciaEntre(zonaActual.getEpicentro(), zonaAComparar.getEpicentro());
				
				if (distancia <= sumaDeRadios && !listaDeZonasQueSeSolapan.contains(zonaAComparar)) {
					listaDeZonasQueSeSolapan.add(zonaAComparar);
				} 
				
			})  
			;
		} 
		return listaDeZonasQueSeSolapan; 
	}
	
}
































