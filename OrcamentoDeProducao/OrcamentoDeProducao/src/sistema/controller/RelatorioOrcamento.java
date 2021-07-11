package sistema.controller;

import static sistema.gui.EntradaESaida.exibirTabela;
import static sistema.gui.EntradaESaida.msgErro;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import sistema.dao.MontarOrçamento;

/**
 * Classe usada para gerar um orçamento.
 * 
 * @author Matheus William
 */
public class RelatorioOrcamento extends MontarOrçamento {

	public void mostrarOrcamento() throws IOException {

		/*
		 * Formata o preço para a moeda brasileira usando a classe NumberFormat em
		 * conjunto com a clase Locale.
		 */
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

		double receitas = calcularReceita(), despesas = calcularDespesas();
		int count = contarVendas();

		// Verifica se foi possivel calcular o total de vendas,a receita e a despesa.
		if ((count != -1) && (receitas != -1) && (despesas != -1)) {
			String colunas[] = { "Total Vendas", "Receita", "Despesas", "Lucro" };
			String linhas[][] = new String[1][4];

			linhas[0][0] = "" + count;
			linhas[0][1] = dinheiro.format(receitas);
			linhas[0][2] = dinheiro.format(despesas);
			linhas[0][3] = dinheiro.format((receitas - despesas));

			exibirTabela("Relatorio de Vendas", linhas, colunas, 39, 400);
		} else
			msgErro("Não foi possivel calcular o orçamento!", "Orcamento de Produção");
	}
}