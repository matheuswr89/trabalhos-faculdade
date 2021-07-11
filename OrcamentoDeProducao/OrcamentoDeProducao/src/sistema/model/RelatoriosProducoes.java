package sistema.model;

/**
 * Classe usada para representar um Relatorio de Produção
 * @author Matheus William
 */
public class RelatoriosProducoes {
	private String nomeProduto;
	private String nomeInsumo;
	private float preco;
	private int quantidade;
	
	public RelatoriosProducoes() {
	}
	
	public RelatoriosProducoes(String nomeProduto, String nomeInsumo, float preco, int quantidade) {
		super();
		this.nomeProduto = nomeProduto;
		this.nomeInsumo = nomeInsumo;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getNomeInsumo() {
		return nomeInsumo;
	}
	
	public void setNomeInsumo(String nomeInsumo) {
		this.nomeInsumo = nomeInsumo;
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
	
	@Override
	public String toString() {
		return String.format("%s - %s - %.2f - %d", nomeProduto, nomeInsumo, preco, quantidade);
	}
}
