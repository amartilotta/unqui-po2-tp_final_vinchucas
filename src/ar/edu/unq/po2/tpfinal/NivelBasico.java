package ar.edu.unq.po2.tpfinal;

public class NivelBasico implements Nivel {
	private Usuario usuario;
	
	@Override
	public void actualizarNivelUsuario(Usuario usuario) {
		
		if (usuario.cantidadMuestrasEnviadasEnElUltimoMes() > 10 && usuario.cantidadOpinionesEnviadasEnElUltimoMes() > 20) {
			this.usuario.setNivel(new NivelExperto());
		}
		//si la cantidad de muestras enviadas es mayor a 10 Y si opino sobre 20 muestras existentes en los ultimos 30 dias

		
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
		return false;
	}


}
