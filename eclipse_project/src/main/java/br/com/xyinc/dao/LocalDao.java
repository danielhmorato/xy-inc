package br.com.xyinc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.xyinc.modelo.Local;

/**
 * Classe para manipulacao do banco de dados
 * @author Daniel
 *
 */
public class LocalDao {
	private static final String NOME_TABELA = " LOCAL ";
	public int inserirLocal(Local local) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO")
				.append(NOME_TABELA)
				.append("(descricao, latitude, longitude) VALUES (?, ?, ?) ");
        int idNovoLocal = 0;
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        try {
            ps.setString(1, local.getDescricao());
            ps.setInt(2, local.getLatitude());
            ps.setInt(3, local.getLongitude());
            ps.executeUpdate(); 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
            	idNovoLocal = rs.getInt(1);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        } 
        return idNovoLocal;
    }

	public Local consultarLocal(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM")
				.append(NOME_TABELA)
				.append("WHERE idlocal = ?");
        Local local = null;
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                local = new Local();
                local.setId(rs.getLong("idlocal"));
                local.setDescricao(rs.getString("descricao"));
                local.setLatitude(rs.getInt("latitude"));
                local.setLongitude(rs.getInt("longitude"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
        }
        return local;
    }
 
    public ArrayList<Local> listarTodos() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM")
				.append(NOME_TABELA);
        ArrayList<Local> locais = new ArrayList<Local>();
        Connection con = Conexao.getConnection();
        PreparedStatement st = con.prepareStatement(sql.toString());
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getLong("idlocal"));
                local.setDescricao(rs.getString("descricao"));
                local.setLatitude(rs.getInt("latitude"));
                local.setLongitude(rs.getInt("longitude"));
                locais.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
        }
        return locais;
    }
 
}
