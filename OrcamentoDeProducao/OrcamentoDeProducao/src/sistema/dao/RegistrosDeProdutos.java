package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistema.model.Produto;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para inserir um registro de produto.
 * 
 * @author Matheus William
 *
 */
public class RegistrosDeProdutos {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	Produto produto = new Produto();

	/**
	 * Insere um registro na tabela produto.
	 * 
	 * @param nome
	 * @param preco
	 */
	public void inserirRegistros(String nome, Double preco) {
		produto.setNome(nome);
		produto.setPreco(preco);

		// insere um registro na tabela produto
		String sql = "insert into produto(nome,preco) VALUES (?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());

			stmt.execute(); // executa comando
			msgInfo("Cadastro realizado com sucesso!!", "Cadastro de Produto");
			stmt.close();

		} catch (SQLException u) {
			msgErro("Erro ao inserir um registro na tabela produto!", "Cadastro de Produto");
		}
	}
}
