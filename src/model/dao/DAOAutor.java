package model.dao;

import model.entities.Pessoa;
import model.entities.Autor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAutor {

    public void cadastroAutor(Pessoa pessoa){
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "autor (idautor, cpf)" +
                "VALUES (?,?)";
        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pessoa.getIdPessoa());
            ps.setString(2, pessoa.getAutor().getCpf());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Autor> cadastrarAutores(ArrayList<Autor> autores) {

        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "autor (idautor, cpf)" +
                "VALUES (?, ?)";

        int index = 0;
        for (Autor autor: autores){
            if (index != 0){
                try {
                    PreparedStatement ps = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, autor.getIdautor());
                    ps.setString(2, autor.getCpf());

                    int affectedRows = ps.executeUpdate();

                    if (affectedRows > 0) {
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            autor.setIdautor(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Falha ao obter o ID gerado para a pessoa.");
                        }
                    } else {
                        throw new SQLException("Falha ao inserir a pessoa, nenhum registro foi afetado.");
                    }
                    ps.close();
                } catch (SQLException e) {
                    continue;
                }
            }
            index++;
        }
        return autores;
    }

}
