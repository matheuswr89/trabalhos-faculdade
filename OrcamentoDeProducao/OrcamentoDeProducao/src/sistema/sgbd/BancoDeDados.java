package sistema.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe usada para fazer a conex�o com o banco de dados.
 * 
 * @author Matheus William
 *
 */

public class BancoDeDados {

	// define a url de conex�o, o usu�rio e a senha.
	final private String url = "jdbc:postgresql:producaodb";
	final private String usuario = "producaoadmin";
	final private String senha = "Produc@o#FBBM2";

	Connection con;

	/**
	 * Chama a classe java.sql.Connection para fazer a conex�o com o banco de dados.
	 * 
	 * @return con
	 */
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}