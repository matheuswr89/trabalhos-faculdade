package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sistema.model.VendaProduto;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para inserir um regidtro de vendas.
 * 
 * @author Matheus William
 *
 */
public class RegistrosDeVenda {
	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	VendaProduto venda = new VendaProduto();

	/**
	 * Insere um registro na tabela venda.
	 * 
	 * @param produto
	 * @param quantidade
	 */
	public void inserirRegistros(Integer produto, Integer quantidade) {
		venda.setCodigo_produto(produto);
		venda.setQuantidade_vendida(quantidade);

		// insere um registro na tabela venda
		String sql = "insert into venda(codigo_produto,quantidade_vendida) VALUES (?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, venda.getCodigo_produto());
			stmt.setInt(2, venda.getQuantidade_vendida());

			stmt.execute();
			msgInfo("Venda realizada com sucesso!", "Venda de Produtos");
			stmt.close();

		} catch (SQLException u) {
			msgErro("Erro ao inserir um registro na tabela venda!", "Venda de Produtos");
		}
	}
}
