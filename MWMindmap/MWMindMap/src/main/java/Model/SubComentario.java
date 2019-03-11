package Model;

public class SubComentario {
    long id, idusuario, idcomentario;
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

    public long getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(long idcomentario) {
        this.idcomentario = idcomentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


}
