package com.br.matheusEgabriel.naonda.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String USER = "root";
	private static final String SENHA = "";
	
	
	public static Connection conectar() {
		try {
			
			// CARREGAR A CLASSE DO DRIVER NA MEMÓRIA
			Class.forName("com.mysql.jdbc.Driver");			// PROTOCOLO DO CONECTOR jdbc:mysql:, IGUAL AO http://, 3306 PORTA EM QUE O mysql roda
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojanaonda", USER, SENHA);
			return conexao;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}