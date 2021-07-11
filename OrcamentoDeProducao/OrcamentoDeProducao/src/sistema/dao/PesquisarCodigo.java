package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para pesquisar e retornar o codigo do insumo ou produto.
 * 
 * @author Matheus William
 *
 */
public class PesquisarCodigo {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	/**
	 * Realiza a pesquisa de um produto/insumo no banco de dados. Retorna o codigo
	 * do produto/insumo se encotrado ou -1 caso contrário.
	 * 
	 * @param nome
	 * @return codigo
	 */
	public int pesquisarCodigo(String tabela, String nome) {

		// Faz uma pesquisa case insesitive do nome no banco de dados e retorna o codigo
		// do insumo
		String pesquisar = "SELECT codigo FROM " + tabela + " WHERE nome ilike '%" + nome + "%'";

		try {
			PreparedStatement stmt = con.prepareStatement(pesquisar);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int codigo = rs.getInt(1);
				return codigo;
			}
		} catch (SQLException erro) {
			msgErro("Não foi possivel localizar o " + tabela + " com o nome " + nome + "!", "Pesquisar Produto/Insumo");
		}
		return -1;
	}
}
