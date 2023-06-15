package ar.edu.unq.po2.tpfinal;

import java.time.LocalDate;

public class Opinion {
    
    //ATRIBUTOS
    private LocalDate fechaDeOpinion;
    private Usuario usuario;
    private Comentario comentario;
    
    

    //CONSTRUCTOR
    public Opinion(Usuario usuario, Comentario comentario) {
        this.fechaDeOpinion = LocalDate.now();
        this.usuario = new Usuario(usuario.getNombre(), usuario.getID());
        this.comentario = comentario;
        
    }
    
    

    //GETTERS
    public LocalDate getFechaDeOpinion() {
        return this.fechaDeOpinion;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Comentario getComentario() {
        return this.comentario;
    }
}