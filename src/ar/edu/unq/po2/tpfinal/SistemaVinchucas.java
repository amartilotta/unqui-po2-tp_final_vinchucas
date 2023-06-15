package ar.edu.unq.po2.tpfinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemaVinchucas {
	private ArrayList<Muestra> listaDeMuestras;
	private ArrayList<Usuario> listaDeUsuarios;
	private IFiltroDeBusqueda filtroDeBusqueda;
	private Map<IEntidad, ArrayList<ZonaDeCobertura>> listaDeSubscriptores;
	private IEntidad[] getSubscriptores;
	
	public  SistemaVinchucas() {
		this.listaDeMuestras		=	new ArrayList<Muestra>();
		this.listaDeUsuarios		=	new ArrayList<Usuario>();
		this.listaDeSubscriptores	=	new HashMap<IEntidad, ArrayList<ZonaDeCobertura>>();	
	}
	
	 
	//Setter
	public void agregarMuestra(Muestra muestra){
		this.listaDeMuestras.add(muestra);
	}
	
	public void agregarUsuario(Usuario usuario){
		this.listaDeUsuarios.add(usuario);
	}
	
	public void agregarSubscriptor(IEntidad entidad, ZonaDeCobertura zonaDeCobertura) {
		
		if(this.existeEntidad(entidad)) {
			this.agregarZona_AEntidad(zonaDeCobertura, entidad);
		} else {
			agregarNuevoSubscriptor(entidad, zonaDeCobertura);
		}
		 
	}
	

	//Getter
	public Map<IEntidad, ArrayList<ZonaDeCobertura>> getSubscriptores(){
		return this.listaDeSubscriptores;
	}

	public ArrayList<Muestra> getMuestras(){
		return this.listaDeMuestras;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return this.listaDeUsuarios;
	} 
	 
	
	//Metodos indicaos en UML:
	 
	public void darDeBajaSubscriptor(IEntidad entidad, ZonaDeCobertura zonaDeCobertura) {
		ArrayList<ZonaDeCobertura> zonasActuales = zonasDeCoberturaDe_(entidad);
		zonasActuales.remove(zonaDeCobertura);
		this.actualizarSubscriptores_(entidad, zonasActuales);
	}
	
	 
	public void notificarSubscriptores(Muestra muestra, Evento evento) {
		
		for (IEntidad entidad : this.getSubscriptores) {
			entidad.notificarMuestra(muestra, evento);
		}
	}
	
	public void postearMuestra(Muestra muestra) {
		this.agregarMuestra(muestra);
		this.notificarSubscriptores(muestra, Evento.NUEVA);
		this.actualizarNivelUsuario(muestra.getUsuario());
	}
	
	public void opinarMuestra(Muestra muestra, Opinion opinion) {
		
	}
	
	public void actualizarNivelUsuario(Usuario usuario) {
		usuario.cambiarNivel();
	}
	
	
	//Mètodos extras
	 
	
	public void agregarNuevoSubscriptor(IEntidad entidad, ZonaDeCobertura zonaDeCobertura) {
		ArrayList<ZonaDeCobertura> zonasActuales = new ArrayList<ZonaDeCobertura>();
		zonasActuales.add(zonaDeCobertura);
		this.actualizarSubscriptores_(entidad, zonasActuales);
	} 

	
	public void agregarZona_AEntidad(ZonaDeCobertura zona, IEntidad entidad) {
		ArrayList<ZonaDeCobertura> zonasActuales = zonasDeCoberturaDe_(entidad);
		zonasActuales.add(zona);
		this.actualizarSubscriptores_(entidad, zonasActuales);
	}
	
	public Boolean existeEntidad(IEntidad entidad) {
		return this.getSubscriptores().containsKey(entidad);
	}
	
	public ArrayList<ZonaDeCobertura> zonasDeCoberturaDe_(IEntidad entidad){
		return this.getSubscriptores().get(entidad);
	}
	
	public void actualizarSubscriptores_(IEntidad entidad, ArrayList<ZonaDeCobertura> listaDeZonas) {
		this.getSubscriptores().put(entidad, listaDeZonas);
	}
}
