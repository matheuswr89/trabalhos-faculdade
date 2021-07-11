package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema.sgbd.BancoDeDados;

/**
 * Classe Criada para verificar se determinada tabela existe.
 * 
 * @author Matheus William
 *
 */
public class VerificarExistenciaTabela {

	// chama a classe BancoDeDados para fazer conexao com o banco
	static Connection con = new BancoDeDados().getConnection();

	/**
	 * Verifica se a tabela passada por parametro existe no banco de dados. Retorna
	 * true se existe ou false caso contrário.
	 */
	public static boolean verificarTabela(String nome) {
		boolean valido = false;
		// Verifica se a tabela existe no banco de dados
		String verificar = "select relname from pg_class where relname = '" + nome + "';";

		try {
			PreparedStatement stmt = con.prepareStatement(verificar);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst())
				msgErro("A tabela " + nome + " não existe!", "Verificar Existencia Tabela");
			else
				valido = true;
		} catch (SQLException erro) {
			msgErro("Erro ao conectar com o banco de dados!", "Verificar Existencia Tabela");
		}
		return valido;
	}

}
