package ar.edu.unq.po2.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class UbicacionTest {
	
	Ubicacion ubicacion1;
	Ubicacion ubicacion2;
	Ubicacion ubicacion3;
	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	Muestra muestra4;
	
	@Before
	public void SetUp() {
		ubicacion1 = new Ubicacion(4, 2);
		ubicacion2 = new Ubicacion(4, 7);
		ubicacion3 = new Ubicacion(8, 7);
		
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
	}
	
	
	@Test
	public void cuandoSeCreaUnaUbicacion_EstaTieneUnaLatitudYUnaLongitud() {
		Ubicacion ubicacion = new Ubicacion(10, 5);
		
		assertEquals(10, ubicacion.getLatitud(), 1);
		assertEquals(5, ubicacion.getLongitud(), 1);
	}
	
	@Test
	public void cuandoDosUbicacionesTienenLasMismasCoordenadas_EntoncesSuDistanciaEsCero() {
		
		assertEquals(0, ubicacion1.distanciaEntre(ubicacion1), 1);
	}
	
	@Test
	public void cuandoUnaUbicacionComparteLaCoordenadaDeLatitudConOtraUbicacion_EntoncesLaDistanciaEntreEllasEsLaDiferenciaEntreLaCoordenadaDeLongitud() {
		
		assertEquals(5, ubicacion1.distanciaEntre(ubicacion2), 1);
	}
	
	@Test
	public void cuandoUnaUbicacionComparteLaCoordenadaDeLongitudConOtraUbicacion_EntoncesLaDistanciaEntreEllasEsLaDiferenciaEntreLaCoordenadaDeLatitud() {

		assertEquals(4, ubicacion2.distanciaEntre(ubicacion3), 1);
	}
	
	@Test
	public void cuandoUnaUbicacionConLatitud4YLongitud2CalculaLaDistanciaEntreOtraUbicacionConLatitud8YLongitud7_EntoncesDa6() {
		
		assertEquals(6, ubicacion3.distanciaEntre(ubicacion1), 1);
	}
	
	@Test
	public void cuandoAUnaUbicacionLePidoLaCantidadDeUbicacionesAMenosDe5DeDistancia_YHay3UbicacionesAMenosDeEsaDistancia_EntoncesDevuelve3Ubicaciones() {
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacion1);
		ubicaciones.add(ubicacion1);
		ubicaciones.add(ubicacion1);
		ubicaciones.add(ubicacion3);
		ubicaciones.add(ubicacion2);
		
		assertEquals(3, ubicacion1.ubicacionesAMenosDe(ubicaciones, 5).size());
	}
	
	@Test
	public void cuandoAUnaUbicacionLePidoLasMuestrasAMenosDe5DeDistanciaDeLaMuestraDada_YHaySoloUna_EntoncesDevuelveUnaMuestra() {
		when(muestra1.getUbicacion()).thenReturn(ubicacion1);
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		when(muestra3.getUbicacion()).thenReturn(ubicacion3);
		when(muestra4.getUbicacion()).thenReturn(ubicacion1);
		
		List<Muestra> muestras = new ArrayList<Muestra>();
		muestras.add(muestra4);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
		assertEquals(1, ubicacion1.getMuestrasAMenosDe(muestra1, 5, muestras).size());
	}
	
	@Test
	public void cuandoAUnaUbicacionLePidoLasMuestrasAMenosDe3DeDistanciaDeLaMuestraDada_YNoHay_EntoncesDevuelveCeroMuestras() {
		when(muestra1.getUbicacion()).thenReturn(ubicacion1);
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		when(muestra3.getUbicacion()).thenReturn(ubicacion3);
		
		List<Muestra> muestras = new ArrayList<Muestra>();
		muestras.add(muestra2);
		muestras.add(muestra3);
		
		assertEquals(0, ubicacion1.getMuestrasAMenosDe(muestra1, 3, muestras).size());
	}
	
}
