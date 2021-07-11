package sistema.controller;

import static javax.swing.JOptionPane.showConfirmDialog;
import static sistema.gui.EntradaESaida.lerNumeroInteiro;
import static sistema.gui.EntradaESaida.lerString;
import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.io.IOException;

import sistema.dao.RegistrosDeProducao;
import sistema.dao.PesquisarCodigo;
import sistema.dao.VerificarExistenciaTabela;
import sistema.dao.VerificarTabela;

/**
 * Classe criada para representar um cadastro de produção.
 * 
 * @author Matheus William
 *
 */
public class CadastroProducao extends RegistrosDeProducao {

	// armazena os nomes das tabelas que serão ultilizadas nessa classe
	private String tabela = "producao";
	private String tabela1 = "insumo";
	private String tabela2 = "produto";

	/**
	 * Insere um registro de produção na tabela producao do banco de dados.
	 */
	public void inserir() throws IOException {
		PesquisarCodigo pesquisa = new PesquisarCodigo();

		String nome;
		Integer quantidade = 0, resultadoBuscaInsumo = 0, resultadoBuscaProduto = 0, opcao;

		boolean verificar = VerificarExistenciaTabela.verificarTabela(tabela);
		boolean verificarTabelaInsumo = VerificarTabela.verificarTabela(tabela1);
		boolean verificarTabelaProduto = VerificarTabela.verificarTabela(tabela2);

		if (verificar != false) {
			// Verifica se as tabelas insumo e produto estão vazias
			if (verificarTabelaInsumo == true && verificarTabelaProduto == true) {
				nome = lerString("Nome do Insumo:", "Cadastro de Produção");

				// verifica se o campo está nulo
				if (nome == null)
					return;

				// Verifica se o nome do insumo esta vazio
				if (!nome.isEmpty()) {
					resultadoBuscaInsumo = pesquisa.pesquisarCodigo(tabela1, nome);

					// Verifica se achou o codigo do insumo fornecido anteriormente
					if (resultadoBuscaInsumo != -1) {

						String nomeProduto;
						nomeProduto = lerString("Nome do Produto:", "Cadastro de Produção");

						// verifica se o campo está nulo
						if (nomeProduto == null)
							return;

						// Verifica se o nome do produto esta vazio
						if (!nomeProduto.isEmpty()) {
							resultadoBuscaProduto = pesquisa.pesquisarCodigo(tabela2, nomeProduto);

							// Verifica se achou o codigo do produto fornecido anteriormente
							if (resultadoBuscaProduto != -1) {
								try {
									quantidade = lerNumeroInteiro("Quantidade de insumo ultilizada:",
											"Cadastro de Produção");

									// verifica se o campo está nulo
									if (quantidade != null) {

										opcao = showConfirmDialog(null, "Deseja adicionar o insumo " + nome
												+ " no cadastro de produção do produto " + nomeProduto + "?");

										// Verifica se o usuario pressionou o botao "Yes"
										if (opcao == 0) {
											inserirRegistros(resultadoBuscaInsumo, resultadoBuscaProduto, quantidade);
										} // if(opcao==0)
										else
											msgInfo("Operação cancelada!", "Cadastro de Produção");
									} else
										System.out.println("vazio");

								} catch (NumberFormatException e) {
									msgErro("Você deve fornecer um número real!", "Venda de Produtos");
								}
							} // if(resultadoBuscaProduto!=-1)
							else
								msgErro("O produto " + nomeProduto + " não está cadastrado", "Cadastro de Produção");

						} // if(nomeProduto!=null && !nomeProduto.isEmpty())
						else
							msgErro("O nome do produto não pode ficar vazio!", "Cadastro de Produção");

					} // if(resultadoBusca!=-1)
					else
						msgErro("O insumo " + nome + " não está cadastrado!", "Cadastro de Produção");

				} // if(nome!=null && !nome.isEmpty())
				else
					msgErro("O nome do insumo não pode ficar vazio!", "Cadastro de Produção");
			} // if(verificarTabelaInsumo()!=false && verificarTabelaProduto()!=false)
		} // if(verificarTabelaProducao()!=false)

	}// inserir()
}
