package ar.edu.unq.po2.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpfinal.Muestra;
import ar.edu.unq.po2.tpfinal.Ubicacion;
import ar.edu.unq.po2.tpfinal.ZonaDeCobertura;


class ZonaDeCoberturaTestCase {

	private Ubicacion ubicacion;
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Muestra muestra1;
	private ZonaDeCobertura zonaDeCobertura1;
	private ZonaDeCobertura zonaDeCobertura2;
	private ZonaDeCobertura zonaDeCobertura3;
	private ZonaDeCobertura zonaDeCobertura4;
	private ArrayList<Muestra> listaDeMuestras;
	private ArrayList<ZonaDeCobertura> listaDeZonas;
	private ArrayList<Muestra> muestrasDeLaZona;
	
	
	@BeforeEach
	public void setUp(){
		ubicacion	=	mock(Ubicacion.class);
		ubicacion1	=	mock(Ubicacion.class);
		ubicacion2	=	mock(Ubicacion.class);

		muestra1	=	mock(Muestra.class);
		
		zonaDeCobertura1 = new ZonaDeCobertura("zonaA", ubicacion, 10d);
		zonaDeCobertura2 = mock(ZonaDeCobertura.class);
		zonaDeCobertura3 = mock(ZonaDeCobertura.class);
		zonaDeCobertura4 = mock(ZonaDeCobertura.class);
		
		listaDeMuestras	 = new ArrayList<Muestra>();		
		muestrasDeLaZona = new ArrayList<Muestra>();
		listaDeZonas	 = new ArrayList<ZonaDeCobertura>();
	}
	
	
	@Test
	public void verificaQueLaMuestra1SeEncuentraDentroDeLaZonaA() {
		
		 listaDeMuestras.add(muestra1);
		  //indico que la muestra pertenece a la ubicacion
		 when(muestra1.getUbicacion()).thenReturn(ubicacion1);
		 
		 muestrasDeLaZona = zonaDeCobertura1.muestrasDeLaZona(listaDeMuestras);
		 assertEquals(1, muestrasDeLaZona.size());
		  
	} 
	
	@Test
	public void verificoQueLaDistanciaEntreUbicacion1YUbicacion2SonIguales() {
		when(ubicacion1.getLatitud()).thenReturn(25d);
		when(ubicacion2.getLatitud()).thenReturn(30d);
		when(ubicacion1.getLatitud()).thenReturn(1d);
		when(ubicacion2.getLatitud()).thenReturn(1d);
		Double distancia = zonaDeCobertura1.distanciaEntre(ubicacion1, ubicacion2);
		assertTrue(distancia == 0);
		
	}
	 
	@Test 
	public void verificoQueLaZonaDeCobertura2_SeSolapaCon_ZonaDeCobertura3() {
		listaDeZonas.add(zonaDeCobertura2);
		listaDeZonas.add(zonaDeCobertura3);
		listaDeZonas.add(zonaDeCobertura4);
		
		when(zonaDeCobertura2.getRadioEnKM()).thenReturn(300d);
		when(zonaDeCobertura3.getRadioEnKM()).thenReturn(280d);
		when(zonaDeCobertura4.getRadioEnKM()).thenReturn(1d);
		when(zonaDeCobertura2.getEpicentro()).thenReturn(ubicacion1);
		when(zonaDeCobertura3.getEpicentro()).thenReturn(ubicacion2);
		when(zonaDeCobertura4.getEpicentro()).thenReturn(ubicacion);

		
		ArrayList<ZonaDeCobertura> zonasSolapadas = new ArrayList<ZonaDeCobertura>();
		zonasSolapadas	=	zonaDeCobertura1.zonasQueSeSolapan(listaDeZonas);
		 

		assertTrue(zonasSolapadas.contains(zonaDeCobertura2));
		assertTrue(zonasSolapadas.contains(zonaDeCobertura3));
		 
	}   
	
	
	
	
	
	
} 

