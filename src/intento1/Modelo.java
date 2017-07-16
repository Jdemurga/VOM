package intento1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
	private String url = "jdbc:mysql://theshadowhunters.es/mis_policia_lista";
	private String pwd = "Qwerty1231";
	private String usr = "mis";
	private Controlador controlador;
	private Connection conexion;
	private Statement stmt;
	private ResultSet rs;

	public Modelo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			System.out.println(" - Conexión con BBDD establecida -");
		} catch (Exception e) {
			System.out.println(" – Error de Conexión con BBDD -");
			e.printStackTrace();
		}
	}

	public boolean verificacion(String user, String clave) {
		try {
			String query = "SELECT * FROM administrador  WHERE user=? AND clave=?";
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, clave);
			ResultSet rset = pstmt.executeQuery();
			rset.next();
			
			if (user.equals(rset.getString("user")) && clave.equals(rset.getString("clave"))) {
				System.out.println("LOGIN CORRECTO");
				return true;
			}
			rset.close();
			pstmt.close();
		} catch (SQLException s) {
			System.out.println("FALLO DEL LOGIN");
		}
		return false;
	}

	public void insert(String UID, String NOMBRE, String RANGO, String FECHA,
			String ASCENSO, String NOTAS) {
		String query = "INSERT INTO ViveOMuere(UID, NOMBRE, RANGO, FECHA_ASCENSO, ASCENDIDO_POR, NOTAS_USUARIO) VALUES ('"
				+ UID + "','" + NOMBRE + "','" + RANGO + "','" + FECHA + "','" + ASCENSO
				+ "','" + NOTAS + "')";
		try {
			stmt = conexion.createStatement();
			int r = stmt.executeUpdate(query);
			System.out.println("insert correcto : " + r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
