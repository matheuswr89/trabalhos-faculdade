package sistema;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import javax.swing.JFrame;

import sistema.gui.MenuPrincipal;
import sistema.sgbd.BancoDeDados;

/**
 * Classe Principal do Programa Or�amento de Produ��o
 * 
 * @author Matheus William
 */
public class OrcamentoDeProducao {

	/**
	 * Inicia o Programa.
	 * 
	 * @param args (n�o utilizav�l)
	 */
	public static void main(String[] args) {
		// Chama a classe BancoDeDados e verifica se o banco est� conectado.
		BancoDeDados banco = new BancoDeDados();
		if (banco.getConnection() != null) {
			msgInfo("Banco de Dados conectado com sucesso!", "Or�amento de Produ��o");
			iniciar();
		} else {
			msgErro("Banco de Dados n�o est� conectado!", "Or�amento de Produ��o");
		}
	}

	/**
	 * Cria uma inst�ncia da classe MenuPrincipal que estende. � a janela principal
	 * do programa. Finaliza o programa quando a janela � fechada.
	 */
	public static void iniciar() {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// define o tamanho no qual o programa ser� exibido
		menuPrincipal.setSize(600, 300);
		// faz a janela do progama ficar visivel
		menuPrincipal.setVisible(true);
		// faz o programa n�o ser redimensionavel
		menuPrincipal.setResizable(false);
		// posiciona o programa no meio da tela
		menuPrincipal.setLocationRelativeTo(null);

	}
}
