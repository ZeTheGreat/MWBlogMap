package Model;

public class Comentario {
    long id, idusuario,idpost;
    String texto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public long getIdpost() {
        return idpost;
    }

    public void setIdpost(long idpost) {
        this.idpost = idpost;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Comentario(long idusu,long idpost, String texto){
        this.idusuario=idusu;
        this.idpost=idpost;
        this.texto=texto;
    }
    public Comentario(){

    }
}
