package model.entities;

public class Autor {
    private Integer idautor;
    private String nome;
    private String telefone;
    private String cpf;
    private boolean eResponsavelPeloArtigo;

    public Autor () {

    }

    public Autor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Autor(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdautor() {
        return idautor;
    }

    public void setIdautor(Integer idautor) {
        this.idautor = idautor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean iseResponsavelPeloArtigo() {
        return eResponsavelPeloArtigo;
    }



    public void seteResponsavelPeloArtigo(boolean eResponsavelPeloArtigo) {
        this.eResponsavelPeloArtigo = eResponsavelPeloArtigo;
    }


}
