package sistema.model;

/**
 * Classe usada para representar a Venda de um Produto.
 * @author Matheus William
 */
public class VendaProduto {
	private int codigo;
	private int codigo_produto;
	private int quantidade_vendida;
	
	public VendaProduto() {
	}
	
	public VendaProduto(int codigo, int codigo_produto, int quantidade_vendida) {
		super();
		this.codigo = codigo;
		this.codigo_produto = codigo_produto;
		this.quantidade_vendida = quantidade_vendida;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo_produto() {
		return codigo_produto;
	}
	
	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}
	
	public int getQuantidade_vendida() {
		return quantidade_vendida;
	}
	
	public void setQuantidade_vendida(int quantidade_vendida) {
		this.quantidade_vendida = quantidade_vendida;
	}
	
	@Override
	public String toString() {
		return String.format("%d - %d - %d ", codigo,codigo_produto, quantidade_vendida);
	}
	
	
}
