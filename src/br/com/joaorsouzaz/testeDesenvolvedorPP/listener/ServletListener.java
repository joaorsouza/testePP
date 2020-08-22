package br.com.joaorsouzaz.testeDesenvolvedorPP.listener;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.joaorsouzaz.testeDesenvolvedorPP.config.BDConfig;
import br.com.joaorsouzaz.testeDesenvolvedorPP.dao.SenhaDAO;
import br.com.joaorsouzaz.testeDesenvolvedorPP.handler.SenhaHandler;

@WebListener
public class ServletListener implements ServletContextListener {
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
			Connection conn = BDConfig.getConnection();
			SenhaDAO senhaDAO = new SenhaDAO();
			
			SenhaHandler.contadorSenhaNormal = senhaDAO.pegarUltimaSenhaNormal(conn).getIdSenha();
			SenhaHandler.contadorSenhaPref = senhaDAO.pegarUltimaSenhaPreferencial(conn).getIdSenha();
			conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
