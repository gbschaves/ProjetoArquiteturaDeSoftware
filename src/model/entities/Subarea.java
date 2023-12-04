package model.entities;

public class Subarea {
    private Integer idsubarea;
    private String nome;

    public Subarea(Integer idsubarea, String nome) {
        this.idsubarea = idsubarea;
        this.nome = nome;
    }

    public Subarea() {
    }

    public Integer getIdsubarea() {
        return idsubarea;
    }

    public void setIdsubarea(Integer idsubarea) {
        this.idsubarea = idsubarea;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Subarea{" +
                "idsubarea=" + idsubarea +
                ", name='" + nome + '\'' +
                '}';
    }
}
