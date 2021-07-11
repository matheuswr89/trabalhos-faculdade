package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistema.model.Insumo;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para inserir um registro de insumo.
 * 
 * @author Matheus William
 *
 */
public class RegistrosDeInsumo {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	Insumo insumo = new Insumo();

	/**
	 * Insere um registro na tabela insumo.
	 * 
	 * @param nome
	 * @param preco
	 */
	public void inserirRegistros(String nome, Double preco) {
		insumo.setNome(nome);
		insumo.setPreco(preco);

		// insere um registro na tabela insumo
		String sql = "insert into insumo(nome,preco) VALUES (?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, insumo.getNome());
			stmt.setDouble(2, insumo.getPreco());

			stmt.execute(); // executa comando
			msgInfo("Cadastro realizado com sucesso!", "Cadastro de Insumo");
			stmt.close();

		} catch (SQLException u) {
			msgErro("Erro ao inserir um registro na tabela insumo! ", "Cadastro de Insumo");
		}
	}
}
