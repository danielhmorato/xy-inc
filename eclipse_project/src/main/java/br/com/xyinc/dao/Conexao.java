package br.com.xyinc.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe para criar a conexao com a base de dados
 * @author Danie
 *
 */
public class Conexao {
	private static final Connection connection = construirConexao();
	
	public static Connection construirConexao() {
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection con = null;
		try {
			fis = new FileInputStream("jdbc.properties");
			props.load(fis);
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));
			con = DriverManager
					.getConnection(
							props.getProperty("DB_URL"),
							props.getProperty("DB_USERNAME"),
							props.getProperty("DB_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
    public static Connection getConnection() {
        return connection;
    }
    
}
