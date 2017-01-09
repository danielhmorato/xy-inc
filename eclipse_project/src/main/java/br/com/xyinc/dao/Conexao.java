package br.com.xyinc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static final Connection connection = construirConexao();
	 
    private static Connection construirConexao() {
        try {           
            Class.forName("org.h2.Driver");  
            String driver = "jdbc:h2:file:C:/Desenvolvimento/xyinc/banco/xy_inc";
            return DriverManager.getConnection(driver, "sa", "sa");
 
        } catch (Exception ex) {
            System.err.println("Conex�o falhou: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
 
    public static Connection getConnection() {
        return connection;
    }
    
}
