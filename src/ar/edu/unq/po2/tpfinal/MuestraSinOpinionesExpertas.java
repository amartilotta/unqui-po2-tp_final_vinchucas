package ar.edu.unq.po2.tpfinal;

public class MuestraSinOpinionesExpertas implements EstadoMuestra{
	
	//ATRIBUTOS.
	private Muestra muestra;
	
	
	
	//ESTADOMUESTRA METODOS.
	@Override
	public void agregarOpinion(Opinion opinion) {
		if(opinion.getUsuario().esExperto()) {
			this.muestra.getOpiniones().add(opinion);
			this.muestra.getVotacion().clear(); //Elimino todas las votaciones previas, ya que desde ahora, solo importan las votaciones de Expertos.
			this.muestra.agregarOpinionAVotacionesYActualizarResultadoActual(opinion);
			this.muestra.setEstadoMuestra(new MuestraConOpinionesExpertas());
		}
		else {
			this.muestra.getOpiniones().add(opinion);
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
	
}
