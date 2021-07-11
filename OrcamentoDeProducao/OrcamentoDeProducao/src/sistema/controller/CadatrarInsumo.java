package sistema.controller;

import static javax.swing.JOptionPane.showConfirmDialog;
import static sistema.gui.EntradaESaida.lerNumeroReal;
import static sistema.gui.EntradaESaida.lerString;
import static sistema.gui.EntradaESaida.msgErro;
import static sistema.gui.EntradaESaida.msgInfo;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import sistema.dao.PesquisarCodigo;
import sistema.dao.RegistrosDeInsumo;
import sistema.dao.VerificarExistenciaTabela;

/**
 * Classe usada para cadastrar um insumo.
 * 
 * @author Matheus William
 */
public class CadatrarInsumo extends RegistrosDeInsumo {

	// armazena o nome da tabela que ser� ultilizada nessa classe
	private String tabela = "insumo";

	/**
	 * Insere um registro de Insumo na tabela insumo do banco de dados
	 * 
	 * @throws IOException
	 */
	public void inserir() throws IOException {

		/*
		 * Formata o pre�o para a moeda brasileira usando a classe NumberFormat em
		 * conjunto com a clase Locale.
		 */
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

		// cria objeto
		String nome;
		Double preco;
		int opcao;

		PesquisarCodigo codigo = new PesquisarCodigo();

		boolean verificar = VerificarExistenciaTabela.verificarTabela(tabela);

		if (verificar != false) {
			nome = lerString("Nome do Insumo:", "Cadastro de Insumo");
			// verifica se o campo est� nulo
			if (nome == null)
				return;

			// Verifica se o nome est� vazio
			if (!nome.isEmpty()) {

				if (codigo.pesquisarCodigo(tabela, nome) == -1) {

					try {
						preco = lerNumeroReal("Pre�o do Insumo:\n(use o ponto para os centavos)", "Cadastro de Insumo");

						// verifica se o campo est� nulo
						if (preco != null) {

							opcao = showConfirmDialog(null,
									String.format("Deseja cadastrar o insumo %s a %s?", nome, dinheiro.format(preco)));
							if (opcao == 0) {
								inserirRegistros(nome, preco);
							} // if(opcao==0)
							else
								msgInfo("Opera��o cancelada!", "Cadastro de Insumo");
						} else
							System.out.println("vazio");
					} catch (NumberFormatException e) {
						msgErro("Voc� deve fornecer um n�mero real!", "Cadastro de Insumo");
					}

				} // if(nome !=null && !nome.isEmpty())
				else
					msgErro("O insumo " + nome + " j� existe!", "Cadastro de Insumo");
			} else
				msgErro("O nome do insumo n�o pode ficar vazio!", "Cadastro de Insumo");

		} // if(verificarTabelaInsumo()!=false)

	}// inserir()

}// CadastrarInsumo
