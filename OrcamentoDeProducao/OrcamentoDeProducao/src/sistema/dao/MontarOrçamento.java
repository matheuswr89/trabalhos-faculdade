package sistema.dao;

import static sistema.gui.EntradaESaida.msgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistema.sgbd.BancoDeDados;

/**
 * Classe criada para montar o relatorio de or�amento.
 * 
 * @author Matheus William
 *
 */
public class MontarOr�amento {

	// chama a classe BancoDeDados para fazer conexao com o banco
	Connection con = new BancoDeDados().getConnection();

	/**
	 * M�todo para calcular a despesa. Caso seja possivel calcular retornar� o
	 * total, caso contrario retornar� -1.
	 */
	public double calcularDespesas() {
		// Soma os precos inseridos na coluna preco da tabela insumo
		String soma = "SELECT sum(producao.quantidade*insumo.preco) as multiplicacao FROM producao, insumo WHERE producao.codigo_insumo = insumo.codigo";
		try {
			PreparedStatement stmt = con.prepareStatement(soma);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				double soma1 = rs.getDouble(1);
				return soma1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	/**
	 * M�todo para calcular a receita. Caso seja possivel calcular retornar� o
	 * total, caso contr�rio retornar� -1.
	 */
	public double calcularReceita() {
		// Multiplica e depois soma os precos inseridos na coluna preco da tabela
		// produto
		String soma = "SELECT sum(venda.quantidade_vendida*produto.preco) as multiplicacao FROM produto, venda WHERE produto.codigo = venda.codigo_produto";
		try {
			PreparedStatement stmt = con.prepareStatement(soma);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				double soma1 = rs.getDouble(1);
				return soma1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	/**
	 * Conta quantas vendas foram realizadas, retorna o numero de vendas realizadas
	 * se for possivel calcular ou -1 caso contrario.
	 * 
	 * @return
	 */
	public int contarVendas() {
		// Conta quantas vendas foi realizadas
		String count = "SELECT COUNT(venda.codigo) as count FROM venda";
		try {
			PreparedStatement stmt = con.prepareStatement(count);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int soma = rs.getInt(1);
				return soma;
			} else
				msgErro("Nenhuma venda foi cadastrada!", "Or�amento de Produ��o");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
}
