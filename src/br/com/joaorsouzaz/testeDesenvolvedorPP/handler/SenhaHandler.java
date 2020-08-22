package br.com.joaorsouzaz.testeDesenvolvedorPP.handler;

public class SenhaHandler {

	public static int contadorSenhaNormal;
	public static int contadorSenhaPref;

	public static Senha gerarSenhaNormal() {
		contadorSenhaNormal++;
		Senha senha = new Senha();
		senha.setIdSenha(contadorSenhaNormal);
		senha.setDescricao("N" + String.format("%04d", contadorSenhaNormal));
		return senha;  
	}

	

	public static Senha gerarSenhaPref() {
		contadorSenhaPref++;
		Senha senha = new Senha();
		senha.setIdSenha(contadorSenhaPref);
		senha.setDescricao("P" + String.format("%04d", contadorSenhaPref));
		return senha;
	}
	
	
	
	
	public static void reiniciarSenhas() {
		contadorSenhaNormal = 0;
		contadorSenhaPref = 0 ;
		
	}
	
}
