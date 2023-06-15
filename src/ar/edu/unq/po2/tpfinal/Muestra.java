package ar.edu.unq.po2.tpfinal;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Map;
import java.util.HashMap;

public class Muestra {
	
	
	
	//ATRIBUTOS.
	private Ubicacion ubicacion;
	private String imagen;
	private Usuario usuarioDeMuestra;
	private Opinion opinion;
	private Date fechaDeCreacion;
	private EstadoMuestra estadoDeLaMuestra;
	private List<Opinion> opiniones;
	private List<EntidadSistema> suscriptoresSistema;
	private Comentario resultadoActual;
	private Map<Comentario, Integer> mapVotaciones;
	
	
	//CONSTRUCTOR.
	public Muestra(String imagen, Ubicacion ubicacion, Opinion opinion) {
		this.ubicacion = ubicacion;
		this.imagen = imagen;
		this.usuarioDeMuestra = opinion.getUsuario();
		this.opinion = opinion;
		this.fechaDeCreacion = Date.from(Instant.now());
		this.setEstadoMuestraPara(opinion);
		this.opiniones = new ArrayList<Opinion>();
		this.opiniones.add(opinion);
		this.suscriptoresSistema = new ArrayList<EntidadSistema>();
		this.mapVotaciones = new HashMap<>();
		this.mapVotaciones.put(opinion.getComentario(), 1);
	}
	
	
	
	//GETTERS.
	public String getImagen() {
		return this.imagen;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public Usuario getUsuario() {
		return this.usuarioDeMuestra;
	}
	
	public Opinion getOpinion() {
		return this.opinion;
	}
	
	public Date getFechaCreacion() {
		return this.fechaDeCreacion;
	}
	
	public EstadoMuestra getEstado() {
		return this.estadoDeLaMuestra;
	}
	
	public List<Opinion> getOpiniones() {
		return this.opiniones;
	}
	
	public List<EntidadSistema> getSuscriptores() {
		return this.suscriptoresSistema;
	}
	
	public Comentario getResultadoActual() {
		this.setResultadoActual();
		return this.resultadoActual;
	}
	
	public Map<Comentario, Integer> getVotacion() {
		return this.mapVotaciones;
	}
	
	
	
	
	//SETTERS.
	public void setEstadoMuestraPara(Opinion opinion) {
		if(opinion.getUsuario().esExperto()) {
			this.setEstadoMuestra(new MuestraConOpinionesExpertas());
		}
		else {
			this.setEstadoMuestra(new MuestraSinOpinionesExpertas());
		}
	}
	
	public void setEstadoMuestra(EstadoMuestra estado) {
		this.estadoDeLaMuestra = estado;
		this.estadoDeLaMuestra.setMuestra(this); //Guardo la referencia a esta muestra.
	}
	
	
	
	
	//METODOS_PRINCIPALES.
	public void agregarSuscriptor(EntidadSistema sistema) {
		this.suscriptoresSistema.add(sistema);
	}
	
	public void agregarOpinion(Opinion opinion) {
		if(!(this.usuarioDeMuestra == opinion.getUsuario())&& this.noHayOpinionesRegistradasDeEseUsuario(opinion)) {
			this.estadoDeLaMuestra.agregarOpinion(opinion);
		}
	}
	
	public Boolean estaVerificada() {
		return this.estadoDeLaMuestra.estaVerificada();
	}
	
	public int cantVotacionesPara(Comentario comentario) {
		if(null == this.mapVotaciones.get(comentario)) {
			return 0;
		}
		else {
			return this.mapVotaciones.get(comentario);
		}
	}
	
	public void agregarOpinionAVotacionesYActualizarResultadoActual(Opinion opinion) {
		this.mapVotaciones.put(opinion.getComentario(), this.cantVotacionesPara(opinion.getComentario()) + 1);
		this.setResultadoActual();
	}
	
	public void darDeBajaASuscriptorEn(EntidadSistema sistema) {
		this.suscriptoresSistema.remove(sistema);
	}
	
	public void notificarSuscriptores() {
		for(EntidadSistema sistema: this.suscriptoresSistema) {
			sistema.notificarVerificacion(this);
		}
	}
	
	
	
	//AUXILIARES.
	private Boolean noHayOpinionesRegistradasDeEseUsuario(Opinion opinion) {
		int cantOpinionesDelUsuario = 0;
		for(int i=0; i < this.opiniones.size() && cantOpinionesDelUsuario < 1; i++) {
			cantOpinionesDelUsuario = cantOpinionesDelUsuario + this.unoSiCeroSino(this.opinadasPorElMismoUsuario(opinion, this.opiniones.get(i)));
		}
		
		return cantOpinionesDelUsuario < 1;
	}
	
	private Boolean opinadasPorElMismoUsuario(Opinion opinion1, Opinion opinion2) {
		return opinion1.getUsuario() == opinion2.getUsuario();
	}
	
	private int unoSiCeroSino(Boolean b) {
		if(b) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private void setResultadoActual() {
		Comentario resultado = null;
		int cantVotaciones = 0;
		for (Entry<Comentario, Integer> par : this.mapVotaciones.entrySet()) {
			if(par.getValue() > cantVotaciones) {
				resultado = par.getKey();
				cantVotaciones = par.getValue();
			}
			else if(par.getValue() == cantVotaciones) {
				resultado = Comentario.NoDefinido;
				cantVotaciones = par.getValue();
			}
		}
		this.resultadoActual = resultado;
	}
}
