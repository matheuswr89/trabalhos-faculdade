package sistema;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import javax.swing.JFrame;

import sistema.gui.MenuPrincipal;
import sistema.sgbd.BancoDeDados;

/**
 * Classe Principal do Programa Orçamento de Produção
 * 
 * @author Matheus William
 */
public class OrcamentoDeProducao {

	/**
	 * Inicia o Programa.
	 * 
	 * @param args (não utilizavél)
	 */
	public static void main(String[] args) {
		// Chama a classe BancoDeDados e verifica se o banco está conectado.
		BancoDeDados banco = new BancoDeDados();
		if (banco.getConnection() != null) {
			msgInfo("Banco de Dados conectado com sucesso!", "Orçamento de Produção");
			iniciar();
		} else {
			msgErro("Banco de Dados não está conectado!", "Orçamento de Produção");
		}
	}

	/**
	 * Cria uma instância da classe MenuPrincipal que estende. É a janela principal
	 * do programa. Finaliza o programa quando a janela é fechada.
	 */
	public static void iniciar() {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// define o tamanho no qual o programa será exibido
		menuPrincipal.setSize(600, 300);
		// faz a janela do progama ficar visivel
		menuPrincipal.setVisible(true);
		// faz o programa não ser redimensionavel
		menuPrincipal.setResizable(false);
		// posiciona o programa no meio da tela
		menuPrincipal.setLocationRelativeTo(null);

	}
}
