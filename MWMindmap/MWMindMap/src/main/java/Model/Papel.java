package Model;

public class Papel {
    private PapelEnum descricao;
    private long idpap;

    public long getIdpap() {
        return idpap;
    }

    public void setIdpap(long idpap) {
        this.idpap = idpap;
    }

    public Papel(long idpap){
        this.idpap=idpap;
    }

    public Papel(long idpap, PapelEnum papelEnum){
        this.idpap=idpap;
        this.descricao=papelEnum;
    }

    public Papel(){}


    public PapelEnum buscarPapel(){return descricao;}

    public void setDescricao(PapelEnum papelEnum){
        this.descricao=papelEnum;
    }
}
