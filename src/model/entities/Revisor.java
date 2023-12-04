package model.entities;

public class Revisor {
    private String telefone;
    private String cpf;
    private String rne;
    private String lattes;
    private String instituicaoQueTrabalha;
    private String researchId;

    public Revisor(){}
    public Revisor(String telefone, String cpf, String rne, String lattes, String instituicaoQueTrabalha, String researchId) {
        this.telefone = telefone;
        this.cpf = cpf;
        this.rne = rne;
        this.lattes = lattes;
        this.instituicaoQueTrabalha = instituicaoQueTrabalha;
        this.researchId = researchId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRne() {
        return rne;
    }

    public void setRne(String rne) {
        this.rne = rne;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getInstituicaoQueTrabalha() {
        return instituicaoQueTrabalha;
    }

    public void setInstituicaoQueTrabalha(String instituicaoQueTrabalha) {
        this.instituicaoQueTrabalha = instituicaoQueTrabalha;
    }

    public String getResearchId() {
        return researchId;
    }

    public void setResearchId(String researchId) {
        this.researchId = researchId;
    }
}
