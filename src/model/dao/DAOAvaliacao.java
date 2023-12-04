package model.dao;

import model.entities.Area;
import model.entities.Artigo;
import model.entities.Avaliacao;
import model.entities.Pessoa;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOAvaliacao {

    public void submeterAvaliacao(Artigo artigo, Pessoa pessoa, Avaliacao avaliacao) {

        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "avaliacao (artigo_idartigo, revisor_idrevisor, nota, observacao)" +
                "VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, artigo.getIdArtigo());
            ps.setInt(2, pessoa.getIdPessoa());
            ps.setInt(3, avaliacao.getNota());
            ps.setString(4, avaliacao.getObservacao());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
