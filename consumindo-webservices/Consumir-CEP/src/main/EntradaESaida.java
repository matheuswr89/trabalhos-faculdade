package main;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Classe usada para definir alguns metodos de entrada e saída.
 * 
 * @author Matheus William
 * @version 0.2
 */
public class EntradaESaida {
	/**
	 * Exibe uma mensagem informativa em uma caixa de diálogo com título.
	 * 
	 * @param mensagem mensagem a ser exibida
	 * @param titulo   titulo da janela
	 */
	public static void msgInfo(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);
	}

	/**
	 * Exibe um componente em uma caixa de diálogo com o texto da barra de título
	 * definido em titulo.
	 * 
	 * @param componente componente a ser mostrado
	 * @param titulo     titulo do componente
	 */
	public static void msgInfo(Object componente, String titulo) {
		showMessageDialog(null, componente, titulo, INFORMATION_MESSAGE);

	}

	/**
	 * Exibe uma mensagem de erro em uma caixa de diálogo com título.
	 * 
	 * @param mensagem mensagem a ser exibida
	 * @param titulo   titulo da janela
	 */
	public static void msgErro(String mensagem, String titulo) {
		showMessageDialog(null, mensagem, titulo, ERROR_MESSAGE);
	}
} // class EntradaESaida
