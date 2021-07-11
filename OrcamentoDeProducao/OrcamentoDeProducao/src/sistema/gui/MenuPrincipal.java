package sistema.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistema.controller.CadastrarProduto;
import sistema.controller.CadastroProducao;
import sistema.controller.CadatrarInsumo;
import sistema.controller.RelatorioOrcamento;
import sistema.controller.RelatorioProducao;
import sistema.controller.RelatorioVendas;
import sistema.controller.CadastrarVendas;

/**
 * Classe criada para representar o menu do programa.
 * 
 * @author Matheus William
 *
 */
public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public MenuPrincipal() {
		super("Orçamento de Produção");
		try {
			criarMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria e mostra os botões que serão mostrados para o usuario.
	 * 
	 * @throws IOException
	 */
	private void criarMenu() throws IOException {

		CadastrarProduto produto = new CadastrarProduto();
		CadatrarInsumo insumo = new CadatrarInsumo();
		CadastroProducao producao = new CadastroProducao();
		CadastrarVendas venda = new CadastrarVendas();
		RelatorioProducao relatorioProducao = new RelatorioProducao();
		RelatorioVendas relatorioVenda = new RelatorioVendas();
		RelatorioOrcamento orcamento = new RelatorioOrcamento();

		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(null);

		JLabel titulo = new JLabel("Selecione uma opção:");
		titulo.setLocation(225, 10);
		titulo.setSize(150, 40);

		JButton btnproduto = new JButton("Cadastrar Produto");
		btnproduto.setLocation(10, 50);
		btnproduto.setSize(180, 40);
		btnproduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					produto.inserir();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btninsumo = new JButton("Cadastrar Insumo");
		btninsumo.setLocation(200, 50);
		btninsumo.setSize(180, 40);
		btninsumo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insumo.inserir();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		JButton btnproducao = new JButton("Cadastrar Produção");
		btnproducao.setLocation(390, 50);
		btnproducao.setSize(180, 40);
		btnproducao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					producao.inserir();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnvenda = new JButton("Vender Produtos");
		btnvenda.setLocation(10, 100);
		btnvenda.setSize(180, 40);
		btnvenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					venda.vendas();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnrelatorioProducao = new JButton("Relatório de Produção");
		btnrelatorioProducao.setLocation(200, 100);
		btnrelatorioProducao.setSize(180, 40);
		btnrelatorioProducao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					relatorioProducao.relatorio();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnrelatorioVenda = new JButton("Relatório de Vendas");
		btnrelatorioVenda.setLocation(390, 100);
		btnrelatorioVenda.setSize(180, 40);
		btnrelatorioVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					relatorioVenda.relatorio();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnorcamento = new JButton("Orçamento");
		btnorcamento.setLocation(200, 150);
		btnorcamento.setSize(180, 40);
		btnorcamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					orcamento.mostrarOrcamento();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		panelMenu.add(titulo);
		panelMenu.add(btninsumo);
		panelMenu.add(btnproducao);
		panelMenu.add(btnproduto);
		panelMenu.add(btnvenda);
		panelMenu.add(btnrelatorioProducao);
		panelMenu.add(btnrelatorioVenda);
		panelMenu.add(btnorcamento);

		setContentPane(panelMenu);
	}
}
