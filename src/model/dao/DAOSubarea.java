package model.dao;

import model.entities.Area;
import model.entities.Subarea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSubarea {

    public ArrayList<Subarea> getSubAreas(Area areaSelecionada){
        ArrayList<Subarea> subareas = new ArrayList<Subarea>();

        String sql = "SELECT * FROM subarea WHERE area_idarea = ?";
        Conexao conexao = new Conexao();
        ResultSet rset = null;

        try {
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, areaSelecionada.getId());
            rset = ps.executeQuery();
            while (rset.next()) {
                Subarea subarea = new Subarea();
                subarea.setIdsubarea(rset.getInt("idsubarea"));
                subarea.setNome(rset.getString("nome"));
                subareas.add(subarea);
            }

            ps.execute();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return subareas;
    }

}
