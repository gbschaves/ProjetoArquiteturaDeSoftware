package controller;

import model.dao.*;
import model.entities.*;
import model.error.ExcecaoLoginIncorreto;

import java.util.ArrayList;

public class ControllerSubsmissaoDeArtigos {

    public Pessoa login(String login, String senha) throws ExcecaoLoginIncorreto {
        DAOPessoa daoPessoa = new DAOPessoa();
        Pessoa pessoa = daoPessoa.login(login, senha);
        if(pessoa.getNome() != null){
            return pessoa;
        } else {
            throw new ExcecaoLoginIncorreto("Login ou senha incorretos");
        }
    }

    public Pessoa cadastroPessoa(Pessoa pessoa) {
        DAOPessoa daoPessoa = new DAOPessoa();
        pessoa = daoPessoa.cadastrarPessoa(pessoa);

        return pessoa;
    }

    public void cadastroAutor(Pessoa pessoa) {
        DAOAutor daoAutor = new DAOAutor();
        daoAutor.cadastroAutor(pessoa);
    }

    public void cadastroRevisor(Pessoa pessoa) {
        DAORevisor daoRevisor = new DAORevisor();
        daoRevisor.cadastroRevisor(pessoa);
    }

    public ArrayList<Autor> cadastroAutores(ArrayList<Autor> autores) {
        DAOAutor daoAutor = new DAOAutor();
        return daoAutor.cadastrarAutores(autores);
    }

    public ArrayList<Pessoa> cadastrarPessoas(ArrayList<Pessoa> pessoas) {
        DAOPessoa daoPessoa = new DAOPessoa();
        return daoPessoa.cadastrarPessoas(pessoas);
    }

    public ArrayList<Area> carregarAreas() {
        DAOAreas daoAreas = new DAOAreas();
        ArrayList<Area> areas = daoAreas.carregarArea();
        return areas;
    }

    public Artigo submeterArtigo(Artigo artigo, Area areaSelecionada, Subarea subareaSelecionada) {
        DAOArtigo daoArtigo = new DAOArtigo();
        return daoArtigo.submeterArtigo(artigo, areaSelecionada, subareaSelecionada);
    }

    public void cadastroAutorHasArtigo(Artigo artigo, Pessoa pessoa) {
        DAOAutorHasArtigo daoAutorHasArtigo = new DAOAutorHasArtigo();
        daoAutorHasArtigo.cadastroAutorHasArtigo(pessoa, artigo);
    }

    public ArrayList<Artigo> getArtigosDoAutor(Pessoa pessoa) {
        DAOArtigo daoArtigo = new DAOArtigo();
        ArrayList<Artigo> artigosDoAutor = daoArtigo.getArtigosDoAutor(pessoa);

        return artigosDoAutor;
    }

    public Artigo getArtigoPeloId(Integer artigoId) {
        DAOArtigo daoArtigo = new DAOArtigo();
        Artigo artigo = daoArtigo.getArtigoPeloId(artigoId);
        return artigo;
    }

    public void sobrescreveArtigo(Artigo artigo) {
        DAOArtigo daoArtigo = new DAOArtigo();
        daoArtigo.sobrescreveArtigo(artigo);
    }

    public ArrayList<Subarea> getSubAreas(Area areaSelecionada) {
        DAOSubarea daoSubarea = new DAOSubarea();

        return daoSubarea.getSubAreas(areaSelecionada);
    }

}
