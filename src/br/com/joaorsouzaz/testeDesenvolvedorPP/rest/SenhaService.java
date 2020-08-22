package br.com.joaorsouzaz.testeDesenvolvedorPP.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.joaorsouzaz.testeDesenvolvedorPP.config.BDConfig;
import br.com.joaorsouzaz.testeDesenvolvedorPP.dao.SenhaDAO;
import br.com.joaorsouzaz.testeDesenvolvedorPP.handler.Senha;
import br.com.joaorsouzaz.testeDesenvolvedorPP.handler.SenhaHandler;

@Path("/senha")
public class SenhaService {
	private SenhaDAO senhaDAO;
	Connection conn;

	@PostConstruct
	private void init() {

		try {

			senhaDAO = new SenhaDAO();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Senha> listarSenhas() {
		List<Senha> lista = null;
		try {
			conn = BDConfig.getConnection();
			lista = senhaDAO.listarSenhas(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return lista;
	}

	@POST
	@Path("/createnormal")
	@Produces(MediaType.APPLICATION_JSON)
	public Senha gerarSenhaNomal() {
		Senha senha = new Senha();
		try {
			conn = BDConfig.getConnection();
			senha = SenhaHandler.gerarSenhaNormal();

			senhaDAO.criarSenhaNormal(conn, senha);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeConn();
		}

		return senha;
	}

	@POST
	@Path("/createpref")
	@Produces(MediaType.APPLICATION_JSON)
	public Senha gerarSenhaPreferencial() {
		Senha senha = new Senha();
		try {

			conn = BDConfig.getConnection();
			senha = SenhaHandler.gerarSenhaPref();
			senhaDAO.criarSenhaPreferencial(conn, senha);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeConn();
		}

		return senha;
	}

	@DELETE
	@Path("/chamar/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Senha removerSenha() {
		Senha senha = new Senha();

		try {
			conn = BDConfig.getConnection();
			senha = verificarTipoeRemover();

		} catch (Exception e) {

			System.out.println(e.getMessage());;
		} finally {
			closeConn();
		}

		return senha;
	}

	@DELETE
	@Path("/reiniciar/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String reiniciarSenhas() {
		
		try {
			conn = BDConfig.getConnection();

			senhaDAO.removerTodasSemhas(conn);
			SenhaHandler.reiniciarSenhas();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeConn();
		}

		return "Senhas Reiniciadas";
	}

	private void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private Senha verificarTipoeRemover() throws Exception {
		Senha senha = senhaDAO.pegarUltimaSenhaPreferencial(conn);
		if(senha.getIdSenha() != 0) {
			senhaDAO.removerSenhaPreferencial(conn);
		}
		else{
			senha = senhaDAO.pegarUltimaSenhaNormal(conn);
			if(senha.getIdSenha() != 0) {
				senhaDAO.removerSenhaNormal(conn);
			}
			else {
				throw new Exception(
					"Não exitem mais senhas!");
			}
		}
		

		return senha;
	}
	
   
    

}
