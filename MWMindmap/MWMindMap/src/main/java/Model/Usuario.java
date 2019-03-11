package Model;

import java.util.ArrayList;

public class Usuario {
    private long id;
    private String nome;
    private String senha;
    private String login;
    private String email;

    ArrayList papeis= new ArrayList<PapelEnum>();

    public Usuario(){

    }
    public Usuario(String name, String login, String senha, String email){
        this.nome=name;
        this.login=login;
        this.senha=senha;
        this.email=email;
    }

    public Usuario(long id, String name, String login, String senha, String email){
        this.id=id;
        this.nome=name;
        this.login=login;
        this.senha=senha;
        this.email=email;
    }
    public Usuario(long id, String name, String login, String senha, String email,ArrayList papeis){
        this.id=id;
        this.nome=name;
        this.login=login;
        this.senha=senha;
        this.email=email;
        this.papeis=papeis;
    }
    public Usuario(String name, String login, String senha, String email,ArrayList papeis){
        this.nome=name;
        this.login=login;
        this.senha=senha;
        this.email=email;
        this.papeis=papeis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList getPapeis() {
        return papeis;
    }

    public void setPapeis(ArrayList papeis) {
        this.papeis = papeis;
    }
    public void addPapel(PapelEnum p){
        this.papeis.add(p);
    }
    public void excluirPapel(PapelEnum p){
        this.papeis.remove(p);
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", papeis=" + papeis +
                '}';
    }
}
