package sistema.controller;

import static javax.swing.JOptionPane.showConfirmDialog;
import static sistema.gui.EntradaESaida.lerNumeroReal;
import static sistema.gui.EntradaESaida.lerString;
import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import sistema.dao.PesquisarCodigo;
import sistema.dao.RegistrosDeProdutos;
import sistema.dao.VerificarExistenciaTabela;

/**
 * Classe criada para representar o cadastro de um produto.
 * 
 * @author Matheus William
 *
 */
public class CadastrarProduto extends RegistrosDeProdutos {

	// armazena o nome da tabela que será ultilizada nessa classe
	private String tabela = "produto";

	/**
	 * Cadastra um registro de um produto na tabela produto.
	 *
	 * @throws IOException
	 * @throws SQLException
	 */
	public void inserir() throws IOException {

		/*
		 * Formata o preço para a moeda brasileira usando a classe NumberFormat em
		 * conjunto com a clase Locale.
		 */
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

		String nome;
		Double preco;
		int opcao;

		PesquisarCodigo codigo = new PesquisarCodigo();

		boolean verificar = VerificarExistenciaTabela.verificarTabela(tabela);

		// verifica se a tabela produto existe
		if (verificar != false) {
			nome = lerString("Nome do Produto:", "Cadastro de Produto");

			// verifica se o campo está nulo
			if (nome == null)
				return;

			// Verifica se o nome esta vazio
			if (!nome.isEmpty()) {

				if (codigo.pesquisarCodigo(tabela, nome) == -1) {
					try {
						preco = lerNumeroReal("Preço do Produto:\n(use o ponto para os centavos)",
								"Cadastro de Produto");

						// verifica se o campo está nulo
						if (preco != null) {
							opcao = showConfirmDialog(null,
									String.format("Deseja cadastrar o produto %s a %s?", nome, dinheiro.format(preco)));

							// Verifica se o usuario clicou no botao "Yes"
							if (opcao == 0) {
								inserirRegistros(nome, preco);
							} // if(opcao==0)
							else
								msgInfo("Operação cancelada!", "Cadastro de Produtos");
						}
					} catch (NumberFormatException e) {
						msgErro("Você deve fornecer um número real.", "Cadastro de Produto");
					}

				} // if(nome !=null && !nome.isEmpty())
				else
					msgErro("O produto " + nome + " já existe!", "Cadastro de Produto");
			} else
				msgErro("O nome do produto não pode ficar vazio!!", "Cadastro de Produto");
		} // if(verificarTabelaProduto()!=false)
	}
}
