package br.com.joaorsouzaz.testeDesenvolvedorPP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.joaorsouzaz.testeDesenvolvedorPP.handler.Senha;

public class SenhaDAO {

	

	public SenhaDAO() throws Exception {

		

	}

	public void criarSenhaNormal(Connection conexao,Senha senha) throws Exception {
		
		String sql = "INSERT INTO senhanormal (idSenha, descricao) VALUES (?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, senha.getIdSenha());
		statement.setString(2, senha.getDescricao());
		statement.execute();
		
	}
	
	public void criarSenhaPreferencial(Connection conexao,Senha senha) throws Exception {
		
		String sql = "INSERT INTO senhapref (idSenha, descricao) VALUES (?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, senha.getIdSenha());
		statement.setString(2, senha.getDescricao());
		statement.execute();
		
	}

	public void removerSenhaNormal(Connection conexao) throws Exception {
		
		String sql = "DELETE FROM senhanormal WHERE idSenha = "
				+ "(SELECT idSenha FROM senhanormal ORDER BY idSenha DESC LIMIT 1)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		
	}

	public void removerSenhaPreferencial(Connection conexao) throws Exception {
		
		String sql = "DELETE FROM senhapref WHERE idSenha = "
				+ "(SELECT idSenha FROM senhapref ORDER BY idSenha DESC LIMIT 1)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		
	}

	
	public Senha pegarUltimaSenhaNormal(Connection conexao) throws Exception {
		Senha senha = new Senha();
		PreparedStatement statement = conexao
				.prepareStatement("SELECT idSenha,descricao FROM senhanormal ORDER BY idSenha DESC LIMIT 1");
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			senha.setIdSenha(rs.getInt(1));
			senha.setDescricao(rs.getString(2));
			
		}
		return senha;
	}
	
	public Senha pegarUltimaSenhaPreferencial(Connection conexao) throws Exception {
		Senha senha = new Senha();
		PreparedStatement statement = conexao
				.prepareStatement("SELECT idSenha, descricao FROM senhapref ORDER BY idSenha DESC LIMIT 1");
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			senha.setIdSenha(rs.getInt(1));
			senha.setDescricao(rs.getString(2));
			
		}
		return senha;
	}

	public List<Senha> listarSenhas(Connection conexao) throws Exception {
		
		List<Senha> lista = new ArrayList<>();

		String sql = "SELECT * FROM senhanormal";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Senha Senha = new Senha();
			Senha.setIdSenha(rs.getInt("idSenha"));

			lista.add(Senha);
		}

		return lista;
	}
	
	public void removerTodasSemhas(Connection conexao) throws Exception {
		String sql = "DELETE FROM senhanormal";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		
		sql = "DELETE FROM senhapref";

		statement = conexao.prepareStatement(sql);
		statement.execute();
		
	}

}
