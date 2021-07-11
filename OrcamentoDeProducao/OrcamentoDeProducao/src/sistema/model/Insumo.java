package sistema.model;

/**
 * Classe usada para representar um Insumo.
 * @author Matheus William
 */
public class Insumo {
	private int codigo;
	private String nome;
	private double preco;
	
	
	public Insumo() {
	}

	public Insumo(int id, String nome, double preco) {
		super();
		this.codigo = id;
		this.nome = nome;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int id) {
		this.codigo = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return String.format("%d - %s - %.2f", codigo,nome,preco);
	}
	
	
	
}
