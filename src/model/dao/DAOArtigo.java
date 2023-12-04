package model.dao;

import model.entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOArtigo {
    public Artigo submeterArtigo(Artigo artigo, Area areaselecionada, Subarea subareaSelecionada) {

        Conexao conexao = new Conexao();

        String sql = "INSERT INTO " +
                "artigo (titulo, resumo, palavrasChave, envolveHumano, processoPlataformaBrasil, idarea, idsubarea)" +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, artigo.getTitulo());
            ps.setString(2, artigo.getResumo());
            ps.setString(3, artigo.getPalavrasChave());
            ps.setBoolean(4, artigo.isEnvolveHumano());
            ps.setString(5, artigo.getProcessoPlataformaBrasil());
            ps.setInt(6, areaselecionada.getId());
            ps.setInt(7, subareaSelecionada.getIdsubarea());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    artigo.setIdArtigo(generatedKeys.getInt(1));
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
        return artigo;
    }

    public ArrayList<Artigo> getArtigosDoAutor(Pessoa pessoa) {
        ArrayList<Artigo> artigosDoAutor = new ArrayList<Artigo>();

        String sql = "SELECT * FROM artigo ar INNER JOIN autor_has_artigo aa ON ar.idartigo = aa.artigo_idartigo WHERE aa.autor_idautor = ?";
        Conexao conexao = new Conexao();
        ResultSet rset = null;
        PreparedStatement ps = null;
        try {
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pessoa.getIdPessoa());
            rset = ps.executeQuery();

            while (rset.next()) {
                Artigo artigo = new Artigo();
                artigo.setIdArtigo(rset.getInt("idartigo"));
                artigo.setTitulo(rset.getString("titulo"));
                artigo.setResumo(rset.getString("resumo"));
                artigo.setPalavrasChave(rset.getString("palavrasChave"));
                artigo.setEnvolveHumano(rset.getBoolean("envolveHumano"));
                artigo.setProcessoPlataformaBrasil(rset.getString("processoPlataformaBrasil"));

                artigosDoAutor.add(artigo);
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigosDoAutor;
    }

    public Artigo getArtigoId(Artigo artigoSemId) {
        Artigo artigo = new Artigo();

        String sql = "SELECT * FROM artigo WHERE titulo = ? AND resumo = ? AND palavrasChave = ?";
        Conexao conexao = new Conexao();
        ResultSet rset = null;
        PreparedStatement ps = null;
        try {
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, artigoSemId.getTitulo());
            ps.setString(2, artigoSemId.getResumo());
            ps.setString(3, artigoSemId.getPalavrasChave());
            rset = ps.executeQuery();

            if (rset.next()) {
                artigo.setIdArtigo(rset.getInt("idartigo"));
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigo;
    }

    public Artigo getArtigoPeloId(Integer artigoId) {
        Artigo artigo = new Artigo();

        String sql = "SELECT * FROM artigo WHERE idartigo = ? ";
        Conexao conexao = new Conexao();
        ResultSet rset = null;
        PreparedStatement ps = null;
        try {
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, artigoId);
            rset = ps.executeQuery();

            if (rset.next()) {
                artigo.setIdArtigo(rset.getInt("idartigo"));
                artigo.setTitulo(rset.getString("titulo"));
                artigo.setResumo(rset.getString("resumo"));
                artigo.setPalavrasChave(rset.getString("palavrasChave"));
                artigo.setEnvolveHumano(rset.getBoolean("envolveHumano"));
                artigo.setProcessoPlataformaBrasil(rset.getString("processoPlataformaBrasil"));
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigo;
    }

    public void sobrescreveArtigo(Artigo artigoEditado) {
        String sql = "UPDATE artigo SET titulo = ?, resumo = ?, palavrasChave = ?, envolveHumano = ?, processoPlataformaBrasil = ? WHERE idartigo = ?";
        Conexao conexao = new Conexao();
        PreparedStatement ps = null;
        try {
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, artigoEditado.getTitulo());
            ps.setString(2, artigoEditado.getResumo());
            ps.setString(3, artigoEditado.getPalavrasChave());
            ps.setBoolean(4, artigoEditado.isEnvolveHumano());
            ps.setString(5, artigoEditado.getProcessoPlataformaBrasil());
            ps.setInt(6, artigoEditado.getIdArtigo());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Artigo> getArtigosParaRevisar(Pessoa pessoaLogada, Area area, Subarea subarea) {
        ArrayList<Artigo> artigos = new ArrayList<Artigo>();

        System.out.println(pessoaLogada.getIdPessoa());
        String sql = "SELECT DISTINCT artigo.*\n" +
                "FROM artigo\n" +
                "LEFT JOIN autor_has_artigo ON artigo.idartigo = autor_has_artigo.artigo_idartigo\n" +
                "WHERE (autor_has_artigo.autor_idautor IS NULL OR autor_has_artigo.autor_idautor != ?)\n" +
                "  AND artigo.idartigo NOT IN (\n" +
                "    SELECT artigo_idartigo FROM autor_has_artigo WHERE autor_idautor = ?\n" +
                "  )\n" +
                "  AND artigo.idarea = ?\n" +
                "  AND artigo.idsubarea = ?";


        Conexao conexao = new Conexao();
        ResultSet rset = null;
        PreparedStatement ps = null;
        try {
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pessoaLogada.getIdPessoa());
            ps.setInt(2, pessoaLogada.getIdPessoa());
            ps.setInt(3, area.getId());
            ps.setInt(4, subarea.getIdsubarea());


            rset = ps.executeQuery();

            while (rset.next()) {
                Artigo artigo = new Artigo();
                artigo.setIdArtigo(rset.getInt("idartigo"));
                artigo.setTitulo(rset.getString("titulo"));
                artigo.setResumo(rset.getString("resumo"));
                artigo.setPalavrasChave(rset.getString("palavrasChave"));
                artigo.setEnvolveHumano(rset.getBoolean("envolveHumano"));
                artigo.setProcessoPlataformaBrasil(rset.getString("processoPlataformaBrasil"));
                artigos.add(artigo);
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigos;
    }
}
