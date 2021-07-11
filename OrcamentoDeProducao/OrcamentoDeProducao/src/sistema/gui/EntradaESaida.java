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
	 * Lê uma string. Retorna a string lida em caso de sucesso. Se o usuário
	 * cancelar a operação retorna null.
	 */
	public static String lerString(String prompt, String titulo) {
		return showInputDialog(null, prompt, titulo, QUESTION_MESSAGE);
	}

	/**
	 * Lê um número real. Retorna o número lido em caso de sucesso. Se o usuário
	 * cancelar a operação retorna null.
	 */
	public static Double lerNumeroReal(String prompt, String titulo) throws NumberFormatException {
		// Lê o número real.
		String numero = lerString(prompt, titulo);
		
		if(numero==null)
			return null;
		
		if (Float.parseFloat(numero) < 0) {
			msgErro("O preço não pode ser negativo!", "Validação Preço");
			return null;
		}

		return Double.parseDouble(numero);
	}

	/**
	 * Lê um número inteiro. Retorna o número lido em caso de sucesso. Se o usuário
	 * cancelar a operação retorna null.
	 */
	public static Integer lerNumeroInteiro(String prompt, String titulo) throws NumberFormatException {
		// Lê o número real.
		String numero = lerString(prompt, titulo);
		
		if(numero==null)
			return null;

		if (Integer.parseInt(numero) < 0) {
			msgErro("A quantidade não pode ser negativa!", "Validação Quantidade");
			return null;
		}

		return Integer.parseInt(numero);
	}

	/**
	 * Exibe uma mensagem informativa em uma caixa de diálogo com título.
	 */
	public static void msgInfo(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);
	}

	/**
	 * Exibe uma mensagem informativa em uma caixa de diálogo com título.
	 */
	public static void msgInfo(Object mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);
	}

	/**
	 * Exibe uma mensagem de erro em uma caixa de diálogo com título.
	 */
	public static void msgErro(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, ERROR_MESSAGE);
	}

	/**
	 * Exibe uma tabela em uma caixa de diálogo. Os parâmetros são:
	 * 
	 * titulo: texto da barra de título da caixa de diálogo; linhas: conteúdo a ser
	 * exibido nas linhas da tabela; colunas: nomes das colunas da tabela;
	 * alturaTabela: altura, em pixels, da área de visualização da tabela;
	 * larguraTabela: largura, em pixels, da área de visualização da tabela.
	 */
	public static void exibirTabela(String titulo, String[][] linhas, String[] colunas, int alturaTabela,
			int larguraTabela) {
		// Cria a tabela.
		JTable relatorioTable = new JTable(linhas, colunas);
		relatorioTable.setEnabled(false);

		// Cria um painel rolável (JScrollPane) para exibir a tabela. A tabela é
		// adicionada ao painel rolável.
		JScrollPane scrollPane = new JScrollPane(relatorioTable);

		// Define o tamanho (largura e altura), em pixels, do painel rolável.
		scrollPane.setPreferredSize(new Dimension(larguraTabela, alturaTabela));

		// Exibe tabela colocando o painel rolável na caixa de diálogo.
		msgInfo(scrollPane, titulo);
	}
} // class EntradaESaida
