
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {

	private Connection con;

	public Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feria", "root", "");
		} catch (Exception e) {
			System.out.println("Erorr en base de datos " + e);
		}
	}

	public Connection getConexion() {
		return con;
	}
}

