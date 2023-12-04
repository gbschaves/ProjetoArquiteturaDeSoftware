package model.entities;

public class Pessoa {

    private Integer idPessoa;
    private String nome;
    private String email;
    private String login;
    private String senha;

    private Autor autor;

    private Revisor revisor;

    public Pessoa(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Pessoa(String nome, String email, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Revisor getRevisor() {
        return revisor;
    }

    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    public Pessoa(String nome, String email, String login, String senha, Autor autor, Revisor revisor) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.autor = autor;
        this.revisor = revisor;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idPessoa=" + idPessoa +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", autor=" + autor +
                ", revisor=" + revisor +
                '}';
    }
}
