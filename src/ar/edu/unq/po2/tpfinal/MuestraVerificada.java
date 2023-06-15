package ar.edu.unq.po2.tpfinal;

public class MuestraVerificada implements EstadoMuestra{

	//ATRIBUTOS.
	private Muestra muestra;

	
	
	//ESTADOMUESTRA METODOS.
	@Override
	public void agregarOpinion(Opinion opinion) {		
	}

	@Override
	public Boolean estaVerificada() {
		return true;
	}

	@Override
	public void setMuestra(Muestra muestra) {
		this.muestra = muestra;
	}
	
}
