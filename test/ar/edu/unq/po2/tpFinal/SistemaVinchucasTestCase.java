package ar.edu.unq.po2.tpFinal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpfinal.IEntidad;
import ar.edu.unq.po2.tpfinal.Muestra;
import ar.edu.unq.po2.tpfinal.SistemaVinchucas;
import ar.edu.unq.po2.tpfinal.Usuario;
import ar.edu.unq.po2.tpfinal.ZonaDeCobertura;


public class SistemaVinchucasTestCase {
	
	
	private SistemaVinchucas sistemaVinchuca;
	private Muestra muestra1;
	private Usuario usuario1;
	private IEntidad entidad;
	private ZonaDeCobertura zona1;
	private ZonaDeCobertura zona2;
	private ZonaDeCobertura zona3;
	private ArrayList<Muestra> listaDeMuestras;
	private ArrayList<ZonaDeCobertura> listaDeZonas;
	private Map<IEntidad, ArrayList<ZonaDeCobertura>> listaDeSubscriptores;
	
	@BeforeEach
	public void setUp(){
		
		muestra1	=	mock(Muestra.class);	
		usuario1	=	mock(Usuario.class);
		entidad		=	mock(IEntidad.class);
		zona1 		= mock(ZonaDeCobertura.class);
		zona2 		= mock(ZonaDeCobertura.class);
		zona3 		= mock(ZonaDeCobertura.class);
		listaDeMuestras			= new ArrayList<Muestra>();		
		listaDeZonas			= new ArrayList<ZonaDeCobertura>();
		sistemaVinchuca			=	new SistemaVinchucas();
		listaDeSubscriptores	=	new HashMap<IEntidad, ArrayList<ZonaDeCobertura>>();
	}
	
	
	@Test
	public void agrego1MuestraAlSistemaYVerificoQueElSistemaTieneUnaListaDeMuestrasConTamaño1(){

		sistemaVinchuca.agregarMuestra(muestra1);
		assertEquals(1, sistemaVinchuca.getMuestras().size());
	}
	
	@Test
	public void agrego1UsuarioAlSistemaYVerificoQueElSistemaTieneUnaListaDeUsuarioConTamaño1(){

		sistemaVinchuca.agregarUsuario(usuario1);
		assertEquals(1, sistemaVinchuca.getUsuarios().size());
	}
	
	
	
	@Test
	public void agrego1SubscriptorAlSistemaYVerificoQueElSistemaTieneUnaListaDeSubscriptoresConTamaño1(){

		sistemaVinchuca.agregarNuevoSubscriptor(entidad, zona1);
		assertEquals(1, sistemaVinchuca.getSubscriptores().size());
	} 
	
	@Test
	public void doyDeBajaZona1DeSubscriptor1_YVerificoQueElSubscriptor1NoContieneLaZona1(){ 
		
		listaDeZonas.add(zona1);
		listaDeZonas.add(zona2);
		sistemaVinchuca.actualizarSubscriptores_(entidad, listaDeZonas);
		
		sistemaVinchuca.darDeBajaSubscriptor(entidad, zona1);
		
		assertFalse(sistemaVinchuca.zonasDeCoberturaDe_(entidad).contains(zona1));
	} 

	
	
} 

