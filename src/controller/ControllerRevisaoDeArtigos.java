package controller;

import model.dao.DAOArtigo;
import model.dao.DAOAvaliacao;
import model.dao.DAOSubarea;
import model.entities.*;

import java.util.ArrayList;

public class ControllerRevisaoDeArtigos {
    public ArrayList<Artigo> getArtigosParaRevisar(Pessoa pessoaLogada, Area area, Subarea subarea){
        DAOArtigo daoArtigo = new DAOArtigo();
        return daoArtigo.getArtigosParaRevisar(pessoaLogada, area, subarea);
    }

    public void avaliarArtigo(Pessoa pessoaLogada, Artigo artigo, Avaliacao avaliacao) {
        DAOAvaliacao daoAvaliacao = new DAOAvaliacao();

        daoAvaliacao.submeterAvaliacao(artigo, pessoaLogada, avaliacao);
    }

    public ArrayList<Subarea> getSubAreas(Area areaSelecionada){
        DAOSubarea daoSubarea = new DAOSubarea();

        return daoSubarea.getSubAreas(areaSelecionada);
    }
}
