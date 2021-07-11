package sistema.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EntradaESaida {
	/**
	 * L� uma string. Retorna a string lida em caso de sucesso. Se o usu�rio
	 * cancelar a opera��o retorna null.
	 */
	public static String lerString(String prompt, String titulo) {
		return showInputDialog(null, prompt, titulo, QUESTION_MESSAGE);
	}

	/**
	 * L� um n�mero real. Retorna o n�mero lido em caso de sucesso. Se o usu�rio
	 * cancelar a opera��o retorna null.
	 */
	public static Double lerNumeroReal(String prompt, String titulo) throws NumberFormatException {
		// L� o n�mero real.
		String numero = lerString(prompt, titulo);
		
		if(numero==null)
			return null;
		
		if (Float.parseFloat(numero) < 0) {
			msgErro("O pre�o n�o pode ser negativo!", "Valida��o Pre�o");
			return null;
		}

		return Double.parseDouble(numero);
	}

	/**
	 * L� um n�mero inteiro. Retorna o n�mero lido em caso de sucesso. Se o usu�rio
	 * cancelar a opera��o retorna null.
	 */
	public static Integer lerNumeroInteiro(String prompt, String titulo) throws NumberFormatException {
		// L� o n�mero real.
		String numero = lerString(prompt, titulo);
		
		if(numero==null)
			return null;

		if (Integer.parseInt(numero) < 0) {
			msgErro("A quantidade n�o pode ser negativa!", "Valida��o Quantidade");
			return null;
		}

		return Integer.parseInt(numero);
	}

	/**
	 * Exibe uma mensagem informativa em uma caixa de di�logo com t�tulo.
	 */
	public static void msgInfo(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);
	}

	/**
	 * Exibe uma mensagem informativa em uma caixa de di�logo com t�tulo.
	 */
	public static void msgInfo(Object mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);
	}

	/**
	 * Exibe uma mensagem de erro em uma caixa de di�logo com t�tulo.
	 */
	public static void msgErro(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, ERROR_MESSAGE);
	}

	/**
	 * Exibe uma tabela em uma caixa de di�logo. Os par�metros s�o:
	 * 
	 * titulo: texto da barra de t�tulo da caixa de di�logo; linhas: conte�do a ser
	 * exibido nas linhas da tabela; colunas: nomes das colunas da tabela;
	 * alturaTabela: altura, em pixels, da �rea de visualiza��o da tabela;
	 * larguraTabela: largura, em pixels, da �rea de visualiza��o da tabela.
	 */
	public static void exibirTabela(String titulo, String[][] linhas, String[] colunas, int alturaTabela,
			int larguraTabela) {
		// Cria a tabela.
		JTable relatorioTable = new JTable(linhas, colunas);
		relatorioTable.setEnabled(false);

		// Cria um painel rol�vel (JScrollPane) para exibir a tabela. A tabela �
		// adicionada ao painel rol�vel.
		JScrollPane scrollPane = new JScrollPane(relatorioTable);

		// Define o tamanho (largura e altura), em pixels, do painel rol�vel.
		scrollPane.setPreferredSize(new Dimension(larguraTabela, alturaTabela));

		// Exibe tabela colocando o painel rol�vel na caixa de di�logo.
		msgInfo(scrollPane, titulo);
	}
} // class EntradaESaida
