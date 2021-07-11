package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para fazer uma verificação em determinadas tabelas.
 * 
 * @author Matheus William
 *
 */
public class VerificarTabela {
	static // chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	/**
	 * Verifica se tem algo cadastrado na tabela passada por parametro no banco de
	 * dados. Retorna true se tem algo cadastrado ou false caso contrário.
	 */
	public static boolean verificarTabela(String nome) {
		boolean valido = false;

		// Verifica se tem algo cadastrado na tabela passada por parametro
		String verifica = "SELECT * FROM " + nome + ";";

		try {
			PreparedStatement stmt = con.prepareStatement(verifica);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst())
				msgErro("Não há " + nome + " cadastrados(as)!", "Verificar Tabela");
			else
				valido = true;
		} catch (SQLException erro) {
			msgErro("Erro ao consultar a tabela " + nome + "!", "Verificar Tabela");
		}
		return valido;
	}
}
