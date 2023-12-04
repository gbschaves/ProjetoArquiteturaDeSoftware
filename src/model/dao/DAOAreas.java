package model.dao;

import model.entities.Area;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAreas {

    public ArrayList<Area> carregarArea() {
        ArrayList<Area> areas = new ArrayList<Area>();

        String sql = "SELECT * FROM area";
        Conexao conexao = new Conexao();
        ResultSet rset = null;
        PreparedStatement ps = null;

        try {
            ps = conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();
            while (rset.next()) {
                Area area = new Area();
                area.setId(rset.getInt("idarea"));
                area.setNome(rset.getString("nome"));
                areas.add(area);
            }

            ps.execute();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return areas;
    }
}
