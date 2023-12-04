package model.dao;

import model.entities.Autor;
import model.entities.Pessoa;
import model.entities.Revisor;
import model.error.ExcecaoLoginIncorreto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOPessoa {
    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO pessoa (nome, email, login, senha) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getLogin());
            ps.setString(4, pessoa.getSenha());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pessoa.setIdPessoa(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para a pessoa.");
                }
            } else {
                throw new SQLException("Falha ao inserir a pessoa, nenhum registro foi afetado.");
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    public ArrayList<Pessoa> cadastrarPessoas(ArrayList<Pessoa> pessoas) {
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO pessoa (nome, email, login, senha) VALUES (?,?,?,?)";

        for (Pessoa pessoa: pessoas){
            try {
                PreparedStatement ps = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, pessoa.getNome());
                ps.setString(2, pessoa.getEmail());
                ps.setString(3, pessoa.getLogin());
                ps.setString(4, pessoa.getSenha());

                int affectedRows = ps.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next() && pessoa.getIdPessoa() == null) {
                        pessoa.setIdPessoa(generatedKeys.getInt(1));
                    }
                } else {
                    throw new SQLException("Falha ao inserir a pessoa, nenhum registro foi afetado.");
                }

                ps.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return pessoas;
    }

    public Pessoa login(String login, String senha) throws ExcecaoLoginIncorreto {
        Conexao conexao = new Conexao();

        String sql = "SELECT * FROM pessoa p INNER JOIN autor a ON p.idpessoa = a.idautor INNER JOIN revisor r ON a.idautor = r.idrevisor WHERE login = ? AND senha = ?";

        ResultSet rset = null;

        Pessoa pessoa = new Pessoa();

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            rset = ps.executeQuery();

            if (rset.next()) {
                pessoa.setIdPessoa(rset.getInt("idpessoa"));
                pessoa.setNome(rset.getString("nome"));
                pessoa.setEmail(rset.getString("email"));
                pessoa.setLogin(login);
                pessoa.setSenha(senha);
                Autor autor = new Autor(rset.getString("cpf"));
                pessoa.setAutor(autor);
                Revisor revisor = new Revisor();
                revisor.setTelefone(rset.getString("telefone"));
                revisor.setCpf(rset.getString("cpf"));
                revisor.setRne(rset.getString("rne"));
                revisor.setLattes(rset.getString("lattes"));
                revisor.setInstituicaoQueTrabalha(rset.getString("instituicaoQueTrabalha"));
                revisor.setResearchId(rset.getString("researchId"));
                pessoa.setRevisor(revisor);
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return pessoa;
    }
}
