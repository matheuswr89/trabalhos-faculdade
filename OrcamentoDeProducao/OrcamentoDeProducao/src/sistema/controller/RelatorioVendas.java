package sistema.controller;

import static sistema.gui.EntradaESaida.exibirTabela;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sistema.dao.MontarRelatorioVendas;
import sistema.dao.VerificarTabela;
import sistema.model.RelatorioVenda;

/**
 * Classe usada para gerar um relatorio de vendas.
 * 
 * @author Matheus William
 */
public class RelatorioVendas extends MontarRelatorioVendas {

	//armazena o nome da tabela que será ultilizada nessa classe
	private String tabela = "venda";

	/**
	 * Mostra um relatório contendo o nome, preço, quantidade e preco total da venda
	 * de um produto.
	 * 
	 * @throws IOException
	 */
	public void relatorio() throws IOException {

		/*
		 * Formata o preço para a moeda brasileira usando a classe NumberFormat em
		 * conjunto com a clase Locale.
		 */
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

		boolean verificarTabelaVenda = VerificarTabela.verificarTabela(tabela);

		if (verificarTabelaVenda != false) {
			List<RelatorioVenda> relatorio = getLista();
			String colunas[] = { "Nome", "Preço", "Quantidade", "Preço Total" };
			String linhas[][] = new String[relatorio.size()][4];

			for (int linha = 0; linha < relatorio.size(); linha++) {
				linhas[linha][0] = relatorio.get(linha).getNomeProduto();
				linhas[linha][1] = dinheiro.format(relatorio.get(linha).getPreco());
				linhas[linha][2] = relatorio.get(linha).getQuantidade() + " un";
				linhas[linha][3] = dinheiro.format(relatorio.get(linha).getPrecoTotal());
			}
			exibirTabela("Relatório de Vendas", linhas, colunas, 150, 550);
		}
	}
}
