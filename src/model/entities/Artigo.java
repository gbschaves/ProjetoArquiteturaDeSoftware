package model.entities;

import java.util.ArrayList;

public class Artigo {
    private Integer idArtigo;
    private String titulo;
    private ArrayList<Pessoa> pessoas;
    private String resumo;
    private String palavrasChave;
    private boolean envolveHumano;
    private String processoPlataformaBrasil;
    private Avaliacao avalicao;
    private Autor autorResponsavel;

    public Artigo(){};


    public Artigo(Integer idArtigo, String titulo, String resumo, String palavrasChave, boolean envolveHumano, String processoPlataformaBrasil) {
        this.idArtigo = idArtigo;
        this.titulo = titulo;
        this.resumo = resumo;
        this.palavrasChave = palavrasChave;
        this.envolveHumano = envolveHumano;
        this.processoPlataformaBrasil = processoPlataformaBrasil;
    }

    public Artigo(String titulo, ArrayList<Pessoa> pessoas, String resumo, String palavrasChave, boolean envolveHumano, String processoPlataformaBrasil) {
        this.titulo = titulo;
        this.pessoas = pessoas;
        this.resumo = resumo;
        this.palavrasChave = palavrasChave;
        this.envolveHumano = envolveHumano;
        this.processoPlataformaBrasil = processoPlataformaBrasil;
    }

    public Integer getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(Integer idArtigo) {
        this.idArtigo = idArtigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public boolean isEnvolveHumano() {
        return envolveHumano;
    }

    public void setEnvolveHumano(boolean envolveHumano) {
        this.envolveHumano = envolveHumano;
    }

    public String getProcessoPlataformaBrasil() {
        return processoPlataformaBrasil;
    }

    public void setProcessoPlataformaBrasil(String processoPlataformaBrasil) {
        this.processoPlataformaBrasil = processoPlataformaBrasil;
    }

    public Avaliacao getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(Avaliacao avalicao) {
        this.avalicao = avalicao;
    }

    public Autor getAutorResponsavel() {
        return autorResponsavel;
    }

    public void setAutorResponsavel(Autor autorResponsavel) {
        this.autorResponsavel = autorResponsavel;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "idArtigo=" + idArtigo +
                ", titulo='" + titulo + '\'' +
                ", autores=" + pessoas +
                ", resumo='" + resumo + '\'' +
                ", palavrasChave='" + palavrasChave + '\'' +
                ", envolveHumano=" + envolveHumano +
                ", processoPlataformaBrasil='" + processoPlataformaBrasil + '\'' +
                ", avalicao=" + avalicao +
                ", autorResponsavel=" + autorResponsavel +
                '}';
    }
}
