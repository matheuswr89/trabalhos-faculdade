package sistema.model;

/**
 * Classe usada para representar um Relátorio de Venda.
 * @author Matheus William
 */
public class RelatorioVenda {
	private String nomeProduto;
	private float preco;
	private int quantidade;
	private float precoTotal;
	
	public RelatorioVenda() {
	}
	
	public RelatorioVenda(String nomeProduto, float preco, int quantidade,
			float precoTotal) {
		this();
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public float getPrecoTotal() {
		return precoTotal;
	}
	
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %.2f - %d - %.2f", nomeProduto,preco,quantidade,precoTotal);
	}
	
	
}
