package ar.edu.unq.po2.tpfinal;

import java.util.ArrayList;
import java.time.LocalDate;

public class Usuario {
	
	
	
	//ATRIBUTOS
	private Integer ID;
	private String nombre;
	private Nivel nivel;
	private ArrayList<Muestra> listaDeMuestrasPosteadas = new ArrayList<Muestra>();
	private ArrayList<Opinion> listaDeOpiniones = new ArrayList<Opinion>();
	
	
	
	//CONSTRUCTOR
	public Usuario (String nombre, Integer id) {
		this.nombre = nombre;
		this.ID = id;
		this.setNivel(new NivelBasico());
		
	}
	
	
	
	//SETTERS
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
		this.nivel.setUsuario(this);
	}
	
	
	
	//GETTERS
	public Nivel getNivel() {
		return this.nivel;
	}
	
	public ArrayList<Muestra> getListaMuestras(){
		return this.listaDeMuestrasPosteadas;
	}
	public ArrayList<Opinion> getListaOpiniones(){
		return this.listaDeOpiniones;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Integer getID() {
		return this.ID;
	}
	
	
	
	//METODOS
	public void cambiarNivel() {
		this.nivel.actualizarNivelUsuario(this);
	} 
	
	public void opinar(Muestra muestra, Comentario comentario) {
		this.nivel.opinar(muestra,comentario);
	}

	public void añadirMuestraEnviada(Muestra muestra) {
		this.listaDeMuestrasPosteadas.add(muestra);
		
	}
	public void añadirOpinionEnviada(Opinion opinion) {
		this.listaDeOpiniones.add(opinion);
		
	}

	public int cantidadMuestrasEnviadasEnElUltimoMes() {
		int contador = 0;
		LocalDate fechaActual = LocalDate.now();
		for ( Muestra muestra : listaDeMuestrasPosteadas) {
			if (muestra.getFechaCreacion().isAfter(fechaActual.minusDays(30))  ) {
				contador ++;
			}
		}
		return contador;
	}
	
	public int cantidadOpinionesEnviadasEnElUltimoMes() {
		int contador = 0;
		LocalDate fechaActual = LocalDate.now();
		for ( Opinion opinion : this.listaDeOpiniones) {
			if (opinion.getFechaDeOpinion().isAfter(fechaActual.minusDays(30))) {
				contador ++;
			}
		}
		return contador;
	}



	public boolean esExperto() {

		return this.nivel.esExperto();
	}
}
