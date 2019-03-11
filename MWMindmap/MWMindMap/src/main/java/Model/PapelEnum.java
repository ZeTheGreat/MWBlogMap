package Model;

public enum PapelEnum {
    ADM("ADM"),
    USU("USU"),
    VIS("VIS");

    private final String descricao;

    PapelEnum(String valor) {
        this.descricao=valor;
    }

    public static PapelEnum buscarEnum(String valor){
        for(PapelEnum papel:PapelEnum.values()){
            if(papel.toString().equals(valor.toUpperCase()))
                return papel;
        }
        return null;
    }

    @Override
    public String toString(){return this.descricao;}


}
