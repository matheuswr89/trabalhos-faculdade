package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistema.model.Producao;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para inserir um registro de producao.
 * 
 * @author Matheus William
 *
 */
public class RegistrosDeProducao {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	Producao producao = new Producao();

	/**
	 * Insere um registro na tabela producao.
	 * 
	 * @param insumo
	 * @param produto
	 * @param quantidade
	 */
	public void inserirRegistros(int insumo, int produto, Integer quantidade) {
		producao.setCodigo_insumo(insumo);
		producao.setCodigo_produto(produto);
		producao.setQuantidade(quantidade);

		// insere um registro na tabela producao
		String sql = "insert into producao(codigo_produto,codigo_insumo,quantidade) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, producao.getCodigo_produto());
			stmt.setInt(2, producao.getCodigo_insumo());
			stmt.setInt(3, producao.getQuantidade());

			stmt.execute(); // executa comando
			msgInfo("Cadastro realizado com sucesso!", "Cadastro de Produção");
			stmt.close();

		} catch (SQLException e) {
			msgErro("Erro ao inserir um registro na tabela producao! ", "Cadastro de Produção");
		}
	}
}
