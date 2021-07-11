package sistema.controller;

import static sistema.gui.EntradaESaida.exibirTabela;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sistema.dao.MontarRelatorioProducao;
import sistema.dao.VerificarTabela;
import sistema.model.RelatoriosProducoes;

/**
 * Classe usada para gerar um relatorio de produ��o.
 * 
 * @author Matheus William
 *
 */
public class RelatorioProducao extends MontarRelatorioProducao {

	//armazena o nome da tabela que ser� ultilizada nessa classe
	private String tabela = "producao";

	/**
	 * Mostra um relat�rio contendo o nome do produto, o nome, preco, e a quantidade
	 * de um insumo.
	 * 
	 * @throws IOException
	 */
	public void relatorio() throws IOException {

		/*
		 * Formata o pre�o para a moeda brasileira usando a classe NumberFormat em
		 * conjunto com a clase Locale.
		 */
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

		boolean verificarTabelaProducao = VerificarTabela.verificarTabela(tabela);

		if (verificarTabelaProducao != false) {
			List<RelatoriosProducoes> relatorio = getLista();
			String colunas[] = { "Nome do Produto", "Nome do Insumo", "Pre�o", "Quantidade" };
			String linhas[][] = new String[relatorio.size()][4];

			for (int linha = 0; linha < relatorio.size(); linha++) {
				linhas[linha][0] = relatorio.get(linha).getNomeProduto();
				linhas[linha][1] = relatorio.get(linha).getNomeInsumo();
				linhas[linha][2] = dinheiro.format(relatorio.get(linha).getPreco());
				linhas[linha][3] = relatorio.get(linha).getQuantidade() + "";
			}
			exibirTabela("Relat�rio de Produ��o", linhas, colunas, 150, 550);
		}
	}
}
