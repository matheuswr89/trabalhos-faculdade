package sistema.model;

/**
 * Classe usada para representar uma Produção de produtos.
 * @author Matheus William
 */
public class Producao {
	private int codigo_produto;
	private int codigo_insumo;
	private int quantidade;

	public Producao() {
	}

	public Producao(int codigo_produto, int codigo_insumo, int quantidade) {
		super();
		this.codigo_produto = codigo_produto;
		this.codigo_insumo = codigo_insumo;
		this.quantidade = quantidade;
	}

	public int getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public int getCodigo_insumo() {
		return codigo_insumo;
	}

	public void setCodigo_insumo(int codigo_insumo) {
		this.codigo_insumo = codigo_insumo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return String.format("%d - %d - %d", codigo_insumo, codigo_produto, quantidade);
	}
	
	
	
}
