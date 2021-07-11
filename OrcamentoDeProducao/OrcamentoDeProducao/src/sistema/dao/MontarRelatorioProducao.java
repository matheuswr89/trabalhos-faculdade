package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.model.RelatoriosProducoes;
import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para montar o relatorio de produção.
 * @author Matheus William
 *
 */
public class MontarRelatorioProducao {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	/**
	 * Faz a pesquisa na tabela producao do banco de dados e retorna os dados da
	 * pesquisa.
	 * 
	 * @return relatorioProducao
	 */
	public List<RelatoriosProducoes> getLista() {
		try {
			List<RelatoriosProducoes> relatorioProducao = new ArrayList<RelatoriosProducoes>();
			
			// realiza as pesquisas nescessarias para montar o relatorio de producao
			PreparedStatement stmt = con.prepareStatement(
					"SELECT produto.nome,insumo.nome as nomeinsumo,insumo.preco,producao.quantidade  FROM produto,insumo,"
							+ "producao WHERE produto.codigo = producao.codigo_produto and insumo.codigo = producao.codigo_insumo;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RelatoriosProducoes producao = new RelatoriosProducoes();

				producao.setNomeProduto(rs.getString("nome"));
				producao.setNomeInsumo(rs.getString("nomeinsumo"));
				producao.setPreco(rs.getFloat("preco"));
				producao.setQuantidade(rs.getInt("quantidade"));

				// adicionando o objeto à lista
				relatorioProducao.add(producao);
			}
			rs.close();
			stmt.close();
			return relatorioProducao;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
