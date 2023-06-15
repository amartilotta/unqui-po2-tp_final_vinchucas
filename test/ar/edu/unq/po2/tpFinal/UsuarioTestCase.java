package ar.edu.unq.po2.tpFinal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class UsuarioTestCase {
	//aca van las declaraciones de las variables
	private Usuario usuarioTest;
	private Muestra muestraTest;
	private Opinion opinionTest;
	private SistemaVinchuca sistema;
	private Nivel nivelBasicoTest;
	private Nivel nivelExpertoTest;
	//private Comentario comentario;
	@BeforeEach
	public void setUp() {
		//aca le doy valor a todo lo necesario
		usuarioTest = new Usuario("Pepe", 1);
		muestraTest = mock(Muestra.class); 
		opinionTest = mock(Opinion.class);
		sistema = mock(SistemaVinchuca.class);
		nivelBasicoTest = mock(NivelBasico.class);
		nivelExpertoTest = mock(NivelExperto.class);
		
		
	}
	
//	@Test
//	void cuandoUnUsarioOpinaDelegaASuNivelLaAccionDeOpinar() {
//		Comentario comentario = Comentario.ChicheFoliada;
//		usuarioTest.opinar(muestraTest, comentario);
//		verify(usuarioTest).methodCall
//	} 
	

	@Test
	void cuandoUnUsuarioAñadeUnaMuestraLalistaDeMuestrasIncrementaEn1() {
		usuarioTest.añadirMuestraEnviada(muestraTest);
		assertEquals(1,usuarioTest.getListaMuestras().size()); 
	} 
	
	@Test
	void cuandoUnUsuarioAñadeUnaOpinionLalistaDeOpinonesIncrementaEn1() {
		usuarioTest.añadirOpinionEnviada(opinionTest);
		assertEquals(1,usuarioTest.getListaOpiniones().size()); 
	} 
	
	@Test
	void cuandoUnUsuarioLeConsultoSuNivelMeLoRetorna() {
		usuarioTest.setNivel(nivelBasicoTest);
		assertEquals(nivelBasicoTest,usuarioTest.getNivel()); 
	} 
	
	@Test
	void cuandoUnUsuarioLeSeteoNivelExpertoElUsuarioPasaANivelExperto() {
		usuarioTest.setNivel(nivelExpertoTest);
		assertEquals(nivelExpertoTest,usuarioTest.getNivel()); 
	} 
	
	@Test
	void cuandoUnUsuarioLeConsultoSuNombreMeLoDevuelve() {
		String nombreUsuarioEsperado = "Pepe";
		assertEquals(nombreUsuarioEsperado,usuarioTest.getNombre()); 
	} 
	
	
	@Test
	void cuandoUnUsuarioLeConsultoSuIDMeLoDevuelve() {
		Integer idEsperado = 1;
		assertEquals(idEsperado,usuarioTest.getID()); 
	} 
	
	@Test
	void cuandoUnUsuarioPosteo0MuestrasEnElUltimoMesYLeConsultanCuantasMuestrasPosteoDevuelve1() {
		usuarioTest.añadirMuestraEnviada(muestraTest);
		when(muestraTest.fechaCreacion()).thenReturn(LocalDate.now().minusDays(40));
		
		assertEquals(0,usuarioTest.cantidadMuestrasEnviadasEnElUltimoMes()); 
	} 
	@Test
	void cuandoUnUsuarioPosteo1MuestrasEnElUltimoMesYLeConsultanCuantasMuestrasPosteoDevuelve1() {
		usuarioTest.añadirMuestraEnviada(muestraTest);
		when(muestraTest.fechaCreacion()).thenReturn(LocalDate.now().minusDays(10));
		
		assertEquals(1,usuarioTest.cantidadMuestrasEnviadasEnElUltimoMes()); 
	} 
	
	@Test
	void cuandoUnUsuarioOpino0MuestrasEnElUltimoMesYLeConsultanCuantasOpinionesEnvioDevuelve1() {
		usuarioTest.añadirOpinionEnviada(opinionTest);
		when(opinionTest.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(40));
		
		assertEquals(0,usuarioTest.cantidadOpinionesEnviadasEnElUltimoMes()); 
	} 
	@Test
	void cuandoUnUsuarioPosteo1MuestraEnElUltimoMesYLeConsultanCuantasOpinionesEnvioDevuelve1() {
		usuarioTest.añadirOpinionEnviada(opinionTest);
		when(opinionTest.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(10));
		
		assertEquals(1,usuarioTest.cantidadOpinionesEnviadasEnElUltimoMes()); 
	} 
	
	


}
