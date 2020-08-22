package br.com.joaorsouzaz.testeDesenvolvedorPP.config;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDConfig {
	
	public static Connection getConnection() throws Exception {
		InitialContext cxt = new InitialContext();
		
		DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/ds" );
		if ( ds == null ) {
			   throw new Exception("Data source not found!");
			}
		Connection c = ds.getConnection();
		
		return c;
		
	
	}
}
