package sistema.controller;

import static javax.swing.JOptionPane.showConfirmDialog;
import static sistema.gui.EntradaESaida.lerNumeroInteiro;
import static sistema.gui.EntradaESaida.lerString;
import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.io.IOException;
import java.sql.SQLException;

import sistema.dao.RegistrosDeVenda;
import sistema.dao.PesquisarCodigo;
import sistema.dao.VerificarExistenciaTabela;
import sistema.dao.VerificarTabela;

/**
 * Classe criada para representar uma venda.
 * 
 * @author Matheus William
 *
 */
public class CadastrarVendas extends RegistrosDeVenda {

	// armazena os nomes das tabelas que serão ultilizadas nessa classe
	private String tabela = "venda";
	private String tabela1 = "produto";

	/**
	 * Cadastra um registro de venda na tabela venda.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public void vendas() throws IOException {
		String nome;
		Integer quantidade = 0, resultadoBusca, opcao;

		PesquisarCodigo pesquisa = new PesquisarCodigo();

		boolean verificar = VerificarExistenciaTabela.verificarTabela(tabela);
		boolean verificarTabelaProduto = VerificarTabela.verificarTabela(tabela1);

		if (verificar != false) {
			// Verifica se a tabela produto está vazia
			if (verificarTabelaProduto != false) {
				nome = lerString("Nome do Produto:", "Venda de Produtos");
				// verifica se o campo está nulo
				if (nome == null)
					return;

				// Verifica se o nome esta vazio ou é nulo
				if (!nome.isEmpty()) {
					resultadoBusca = pesquisa.pesquisarCodigo(tabela1, nome);

					// Verifica se achou o codigo do produto
					if (resultadoBusca != -1) {
						try {
							quantidade = lerNumeroInteiro("Quantidade a ser vendida:", "Venda de Produtos");
							// verifica se o campo está nulo
							if (quantidade != null) {
								opcao = showConfirmDialog(null,
										"Deseja vender " + quantidade + "un do produto " + nome + "?");
								// Verifica se o usuario clicou no botao "Yes"
								if (opcao == 0) {
									inserirRegistros(resultadoBusca, quantidade);
								} // if(opcao==0)
								else
									msgInfo("Operação cancelada!", "Venda de Produtos");
							} else
								System.out.println("vazio");
						} catch (NumberFormatException e) {
							msgErro("Você deve fornecer um número real!", "Venda de Produtos");
						}
					} // if(resultadoBusca!=-1)
					else
						msgErro("O produto " + nome + " não está cadastrado", "Venda de Produtos");
				} // if(nome !=null && !nome.isEmpty())
				else
					msgErro("O nome do produto não pode ficar vazio!", "Venda de Produtos");
			} // if(verificarTabelaProduto()!=false)
		}
	}
}
