package model.dao;

import model.entities.Artigo;
import model.entities.Autor;
import model.entities.Pessoa;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOAutorHasArtigo {
    public void cadastroAutorHasArtigo(Pessoa pessoa, Artigo artigo){
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "autor_has_artigo (artigo_idartigo, autor_idautor, eResponsavelPeloArtigo, telefone)" +
                "VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, artigo.getIdArtigo());
            ps.setInt(2, pessoa.getIdPessoa());
            ps.setBoolean(3, pessoa.getAutor().iseResponsavelPeloArtigo());
            ps.setString(4, pessoa.getAutor().getTelefone());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
