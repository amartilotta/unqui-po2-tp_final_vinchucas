package ar.edu.unq.po2.tpFinal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;


public class MuestraTest {
	Ubicacion ubicacion;
	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	Usuario usuario4;
	Usuario usuario5;
	
	Opinion opinion1;
	Opinion opinion2;
	Opinion opinion3;
	Opinion opinion4;
	Opinion opinion5;
	
	Muestra muestra;
	Muestra muestra2;
	Date fechaActual;
	EntidadSistema sistema1;
	
	
	@Before
	public void SetUp() {
		ubicacion = mock(Ubicacion.class);
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
		usuario4 = mock(Usuario.class);
		usuario5 = mock(Usuario.class);
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		opinion5 = mock(Opinion.class);
		
		fechaActual = Date.from(Instant.now());
		
		when(opinion1.getUsuario()).thenReturn(usuario1);
		when(opinion1.getComentario()).thenReturn(Comentario.ChincheFoliada);
		
		when(usuario5.esExperto()).thenReturn(true);
		when(opinion5.getUsuario()).thenReturn(usuario5);
		when(opinion5.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		muestra = new Muestra("imagen.jpg", ubicacion, opinion1);
		muestra2 = new Muestra("imagen.jpg", ubicacion, opinion5);
		sistema1 = spy(mock(EntidadSistema.class));
	}
	
	@Test
	public void cuandoSeCreaUnaMuestra_EstaTieneLaUbicacion_Imagen_Usuario_YLaOpinionDada_YUnaFechaDeCreacion() {
		when(opinion1.getUsuario()).thenReturn(usuario1);
		
		assertEquals("imagen.jpg", muestra.getImagen());
		assertEquals(ubicacion, muestra.getUbicacion());
		assertEquals(usuario1, muestra.getUsuario());
		assertEquals(opinion1, muestra.getOpinion());
		assertTrue(null != muestra.getFechaCreacion()); //Chequear esto...
	}
	
	@Test
	public void cuandoSeCreaUnaNuevaMuestra_EstaNoEstaVerificada_YTieneUnaOpinion() {
		
		assertFalse(muestra.getEstado().estaVerificada());
		assertEquals(1, muestra.getOpiniones().size());
	}

	@Test
	public void cuandoSeCreaUnaNuevaMuestra_EstaNoTieneSuscriptores() {
		
		assertEquals(0, muestra.getSuscriptores().size());
	}
	
	
	//SUSCRIPTORES.
	@Test
	public void cuandoSeAgregaUnSuscriptorAUnaMuestraRecienCreada_EntoncesSuCantidadDeSuscriptoresEsUno() {
		
		muestra.agregarSuscriptor(sistema1);
		assertEquals(1, muestra.getSuscriptores().size());
	}
	
	@Test
	public void cuandoSeDesuscribeUnSuscriptorAUnaMuestraQueTeníaUnSuscriptor_EntoncesSuCantidadDeSuscriptoresEsCero() {
		
		muestra.agregarSuscriptor(sistema1);
		muestra.darDeBajaASuscriptorEn(sistema1);		
		
		assertEquals(0, muestra.getSuscriptores().size());
		
	}
	
	//NOTIFICAR.
	@Test
	public void cuandoUnaMuestraConOpinionesDeExpertos_PasaAEstarVerificada_NotificaASusSuscriptores() {
		when(usuario2.esExperto()).thenReturn(true);
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion2.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		
		muestra2.agregarSuscriptor(sistema1);
		muestra2.agregarOpinion(opinion2);
		verify(sistema1, atLeast(1)).notificarVerificacion(muestra2);
		assertTrue(muestra2.estaVerificada());
	}
	
	
	
	//OPINION_REGLAS.
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestra_YElCreadorDeEsaOpinionEsElMismoUsuariQueCreoLaMuestra_EntoncesEsaOpinionNoSeRegistra() {
		when(opinion1.getUsuario()).thenReturn(usuario1); //usuario1 Se utilizó para crear la muestra en el SetUp().
		muestra.agregarOpinion(opinion1);
		
		assertEquals(1, muestra.getOpiniones().size());
	}
	
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestra_YElCreadorDeEsaOpinionYaHabiaOpinadoLaMuestra_EntoncesEsaOpinionNoSeRegistra() {
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion3.getUsuario()).thenReturn(usuario2);
		
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion2);
		
		assertEquals(2, muestra.getOpiniones().size());
	}
	
	
	//RESULTADO_ACTUAL
	@Test
	public void cuandoAUnaMuestraSinOpinionesExpertasRecienCreada_SeLePreguntaSuResultadoActual_EntoncesDevuelveElComentarioDeLaOpinion() {
		
		assertEquals(muestra.getOpinion().getComentario(), muestra.getResultadoActual());
	}
	
	@Test
	public void cuandoAUnaMuestraSeLePideSuResultadoActual_YNoHayUnComentarioGanador_EntoncesElResultadoActualEsNoDefinido() {
		when(usuario2.esExperto()).thenReturn(false);
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion2.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		
		muestra.agregarOpinion(opinion2);
		
		assertEquals(Comentario.NoDefinido, muestra.getResultadoActual());
	}
	
	@Test
	public void cuandoAUnaMuestraSinOpinionesExpertas_SeLeAgregaUnaOpinionExperta_ElResultadoActualSeráLaOpinionDeEseExperto() {
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion2.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		
		when(usuario3.esExperto()).thenReturn(true);
		when(opinion3.getUsuario()).thenReturn(usuario3);
		when(opinion3.getComentario()).thenReturn(Comentario.Vinchuca);
		
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		
		assertEquals(Comentario.Vinchuca, muestra.getResultadoActual()); //Cambiar cuando esté el Enum Comentario completo.
	}
	
	@Test
	public void cuandoAUnaMuestraConOpinionesExpertas_SeLePideElResultadoActual_YNoHayUnResultadoGanador_EntoncesElResultadoActualEsNoDefinido() {
		when(usuario3.esExperto()).thenReturn(true);
		when(opinion3.getUsuario()).thenReturn(usuario3);
		when(opinion3.getComentario()).thenReturn(Comentario.ChincheFoliada);
		
		muestra2.agregarOpinion(opinion3);
		assertEquals(Comentario.NoDefinido, muestra2.getResultadoActual());
	}
	
	
	//MUESTRA_SIN_OPINIONES_EXPERTAS.
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestraSinOpinionesExpertas_EntoncesSeRegistraEsaOpinionSinImportarElNivelDelUsuarioDeLaOpinion() {
		when(opinion2.getUsuario()).thenReturn(usuario3);
		when(usuario3.esExperto()).thenReturn(true);
		
		int cantOpinionesRegistradas = muestra.getOpiniones().size();
		muestra.agregarOpinion(opinion2);
		assertEquals(cantOpinionesRegistradas + 1, muestra.getOpiniones().size());
	}
	
	@Test
	public void cuandoSeLePreguntaAUnaMuestraSinOpinionesExpertas_SiEstaVerificada_EntoncesDaFalse() {
		
		muestra.setEstadoMuestra(new MuestraSinOpinionesExpertas());
		
		assertFalse(muestra.estaVerificada());
	}
	
	
	//MUESTRA_CON_OPINIONES_EXPERTAS.
	@Test
	public void cuandoSeLePreguntaAUnaMuestraConOpinionesExpertas_SiEstaVerificada_EntoncesDaFalse() {
		muestra.setEstadoMuestra(new MuestraConOpinionesExpertas());
		
		assertFalse(muestra.estaVerificada());
	}
	
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestraConOpinionesExpertas_YLaOpinionEsDeUnUsuarioNoExperto_EntoncesNoSeRegistraLaOpinion() {
		when(usuario1.esExperto()).thenReturn(false);
		when(opinion1.getUsuario()).thenReturn(usuario1);
		
		muestra.setEstadoMuestra(new MuestraConOpinionesExpertas());
		int cantOpinionesRegistradas = muestra.getOpiniones().size();
		muestra.agregarOpinion(opinion1);
		assertEquals(cantOpinionesRegistradas, muestra.getOpiniones().size());
	}
	
	
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestraConOpinionesExpertas_YLaOpinionEsDeUnUsuarioExperto_EntoncesSeRegistraEsaOpinion() {
		when(usuario2.esExperto()).thenReturn(true);
		when(opinion2.getUsuario()).thenReturn(usuario2);
		
		muestra.setEstadoMuestra(new MuestraConOpinionesExpertas());
		int cantOpinionesRegistradas = muestra.getOpiniones().size();
		muestra.agregarOpinion(opinion2);
		assertEquals(cantOpinionesRegistradas + 1, muestra.getOpiniones().size());
	}
	
	@Test
	public void cuandoSeLePideAgregarUnaOpinionAUnaMuestraConOpinionesExpertas_YLaOpinionEsDeUnUsuarioExperto_YAdemasExistiaUnaOpinionDeUnExpertoConElMismoComentario_EntoncesLaMuestraPasaAEstarVerificada() {		
		when(usuario2.esExperto()).thenReturn(true);
		when(usuario3.esExperto()).thenReturn(true);
		when(usuario4.esExperto()).thenReturn(true);
		
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion3.getUsuario()).thenReturn(usuario3);
		when(opinion4.getUsuario()).thenReturn(usuario4);
		
		when(opinion2.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		when(opinion3.getComentario()).thenReturn(Comentario.ChincheFoliada);
		when(opinion4.getComentario()).thenReturn(Comentario.ChincheFoliada);
		
		muestra.setEstadoMuestra(new MuestraConOpinionesExpertas());
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		muestra.agregarOpinion(opinion4);
		
		assertTrue(muestra.estaVerificada());
	}
	
	
	//MUESTRA_VERIFICADA.
	@Test
	public void cuandoUnaMuestraEstaVerificada_NingunUsuarioPuedeOpinarSobreLaMisma() {
		when(usuario2.esExperto()).thenReturn(true);
		when(usuario3.esExperto()).thenReturn(true);
		when(usuario4.esExperto()).thenReturn(true);
		
		when(opinion2.getUsuario()).thenReturn(usuario2);
		when(opinion3.getUsuario()).thenReturn(usuario3);
		when(opinion4.getUsuario()).thenReturn(usuario4);
		
		when(opinion2.getComentario()).thenReturn(Comentario.PhiyiaChinche);
		when(opinion3.getComentario()).thenReturn(Comentario.ChincheFoliada);
		when(opinion4.getComentario()).thenReturn(Comentario.ChincheFoliada);
		
		muestra.setEstadoMuestra(new MuestraVerificada());
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		muestra.agregarOpinion(opinion4);
		
		assertEquals(1, muestra.getOpiniones().size());
	}
	
}
