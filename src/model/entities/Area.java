package model.entities;

public class Area {

    private Integer id;
    private String nome;
    private Artigo[] artigos;

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artigo[] getArtigos() {
        return artigos;
    }

    public void setArtigos(Artigo[] artigos) {
        this.artigos = artigos;
    }
}
