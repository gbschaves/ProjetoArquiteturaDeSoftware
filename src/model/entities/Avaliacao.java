package model.entities;

public class Avaliacao {
    private Integer nota;
    private String observacao;

    public Avaliacao(Integer nota, String observacao) {
        this.nota = nota;
        this.observacao = observacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
