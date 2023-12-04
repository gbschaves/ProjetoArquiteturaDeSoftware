package model.dao;

import model.entities.Pessoa;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAORevisor {
    public void cadastroRevisor(Pessoa pessoa){
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "revisor (idrevisor, telefone, cpf, rne, lattes, instituicaoQueTrabalha, researchId)" +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pessoa.getIdPessoa());
            ps.setString(2, pessoa.getRevisor().getTelefone());
            ps.setString(3, pessoa.getRevisor().getCpf());
            ps.setString(4, pessoa.getRevisor().getRne());
            ps.setString(5, pessoa.getRevisor().getLattes());
            ps.setString(6, pessoa.getRevisor().getInstituicaoQueTrabalha());
            ps.setString(7, pessoa.getRevisor().getResearchId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
