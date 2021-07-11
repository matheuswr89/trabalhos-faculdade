package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.model.RelatorioVenda;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para montar o relatorio de vendas.
 * 
 * @author Matheus William
 *
 */
public class MontarRelatorioVendas {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	/**
	 * Faz a pesquisa na tabela venda do banco de dados e retorna os dados da
	 * pesquisa.
	 * 
	 * @return relatorioVendas
	 */
	public List<RelatorioVenda> getLista() {
		try {
			List<RelatorioVenda> relatorioVendas = new ArrayList<RelatorioVenda>();

			// realiza as pesquisas nescessarias para montar o relatorio de vendas
			PreparedStatement stmt = con
					.prepareStatement("SELECT produto.nome, produto.preco, venda.quantidade_vendida, "
							+ "(venda.quantidade_vendida*produto.preco) as multiplicacao FROM produto,  venda WHERE produto.codigo = venda.codigo_produto;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RelatorioVenda venda = new RelatorioVenda();

				venda.setNomeProduto(rs.getString("nome"));
				venda.setPreco(rs.getFloat("preco"));
				venda.setQuantidade(rs.getInt("quantidade_vendida"));
				venda.setPrecoTotal(rs.getFloat("multiplicacao"));

				// adicionando o objeto à lista
				relatorioVendas.add(venda);
			}
			rs.close();
			stmt.close();
			return relatorioVendas;
		} catch (SQLException e) {
			msgErro("Erro ao carregar as tabelas venda e produto! ", "Relatório de Vendas");
			throw new RuntimeException(e);
		}
	}
}
