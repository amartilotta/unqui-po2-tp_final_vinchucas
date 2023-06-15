package ar.edu.unq.po2.tpfinal;

public class MuestraConOpinionesExpertas implements EstadoMuestra{
	
	//ATRIBUTOS.
	private Muestra muestra;
	
	
	
	//ESTADOMUESTRA METODOS.
	@Override
	public void agregarOpinion(Opinion opinion) {
		if(opinion.getUsuario().esExperto()) {
			this.registrarOpinion(opinion);
			this.muestra.agregarOpinionAVotacionesYActualizarResultadoActual(opinion);
		}
	}

	@Override
	public Boolean estaVerificada() {
		return false;
	}

	@Override
	public void setMuestra(Muestra muestra) {
		this.muestra = muestra;
	}
	
	
	
	//AUXILIARES.
	private void registrarOpinion(Opinion opinion) {
		if(this.hayOpinionDeExpertoQueConcuerdeCon(opinion)) {
			muestra.getOpiniones().add(opinion);
			muestra.setEstadoMuestra(new MuestraVerificada());
			muestra.notificarSuscriptores();
		}
		else {
			muestra.getOpiniones().add(opinion);
		}
	}
	
	private Boolean hayOpinionDeExpertoQueConcuerdeCon(Opinion opinion) {
		int cantOpinionesExpertas = 0;
		for(int i=0; i < muestra.getOpiniones().size() && cantOpinionesExpertas < 1; i++) {
			cantOpinionesExpertas = cantOpinionesExpertas + this.unoSiCeroSino(esOpinionExpertaYOpinaLoMismoQue(opinion, muestra.getOpiniones().get(i)));
		}
		
		return cantOpinionesExpertas == 1;		
	}
	
	private Boolean esOpinionExpertaYOpinaLoMismoQue(Opinion opinion1, Opinion opinion2) {
		return (opinion1.getUsuario().esExperto()) && (opinion1.getComentario() == opinion2.getComentario());
	}
	
	private int unoSiCeroSino(Boolean b) {
		if(b) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
