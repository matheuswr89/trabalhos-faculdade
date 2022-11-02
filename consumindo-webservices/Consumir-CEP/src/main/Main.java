package main;

import static main.EntradaESaida.msgErro;
import static main.EntradaESaida.msgInfo;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField formattedTextField;
	private static ArrayList<String> array;

	public static void main(String[] args) {
		try {
			array = new ArrayList<String>();
			Main frame;
			frame = new Main();
			frame.setVisible(true);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Main() throws ParseException {
		setTitle("Consumindo CEP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 114);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Forneça o CEP a ser pesquisado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 213, 24);
		contentPane.add(lblNewLabel);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(86, 46, 137, 23);
		btnBuscar.addActionListener((e) -> buscarCEP());
		contentPane.add(btnBuscar);

		MaskFormatter formatter = new MaskFormatter("##.###-###");
		formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setBounds(219, 15, 83, 20);
		formattedTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					buscarCEP();
				}
			}
		});
		contentPane.add(formattedTextField);
		setLocationRelativeTo(null);
	}

	private void buscarCEP() {
		try {
			String cep = formattedTextField.getText().replaceAll("\\.|-", "") + "/json";
			String json = verificaConsultas(cep) != null ? verificaConsultas(cep) : consultaAPI(cep);
			if (json != null) {
				array.add(json);
				JSONObject jsonObject = new JSONObject(json);
				String mensagem = String.format(
						"CEP: %s\nLogradouro: %s\nComplemento: %s\nBairro: %s\nCidade: %s\nUF: %s\nIBGE: %s\nGIA: %s\nDDD: %s\nSIAFI: %d\n",
						jsonObject.getString("cep"), jsonObject.getString("logradouro"),
						jsonObject.getString("complemento"), jsonObject.getString("bairro"),
						jsonObject.getString("localidade"), jsonObject.getString("uf"), jsonObject.getString("ibge"),
						jsonObject.getString("gia"), jsonObject.getString("ddd"), jsonObject.getInt("siafi"));
				msgInfo(mensagem, "Consumindo CEP");
			} else
				msgErro("Não foi possivel completar a requisição.", "Consumindo CEP");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private String verificaConsultas(String cep) throws JSONException {
		for (String i : array) {
			JSONObject jsonObject = new JSONObject(i);
			if (cep.replace("/json", "").equals(jsonObject.getString("cep").replace("-", ""))) {
				return i;
			}
		}
		return null;
	}

	private String consultaAPI(String cep) {
		String urlCep = "https://viacep.com.br/ws/";
		try {
			URL url = new URL(urlCep + cep);
			URLConnection con = url.openConnection();
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line;
			StringBuilder source = new StringBuilder();
			while ((line = input.readLine()) != null)
				source.append(line);
			input.close();
			return source.toString();
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
	}
}
