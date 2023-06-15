package ar.edu.unq.po2.tpfinal;

public class NivelEspecialista implements Nivel {
	private Usuario usuario;

	@Override
	public void actualizarNivelUsuario(Usuario usuario) {

	}
	
	@Override
	public void opinar(Muestra muestra, Comentario comentario) {
		
			Opinion opinionUsuario = new Opinion(this.usuario, comentario);
			muestra.agregarOpinion(opinionUsuario); //si esta verificada, este metodo no hara nada, si tiene opinion de algun experto, tampoco
			
		}
		

	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
	}
	
	@Override
	public Boolean esExperto() {
		return true;
	}


}