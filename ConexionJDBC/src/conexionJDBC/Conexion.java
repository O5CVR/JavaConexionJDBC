package conexionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;
	private static String URL = "jdbc:mysql://localhost/";
	private static String user = "root";
	private static String pwd = "";

	//Conexion al localhost de mysql
	private Conexion() {
		try {
			conexion = DriverManager.getConnection(URL, user, pwd);
			System.out.println("Conexion establecida");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Conexion con a una Base de datos especifica
	private Conexion(String nombreBD) {
		try {
			conexion = DriverManager.getConnection(URL + nombreBD, user, pwd);
			System.out.println("Conexion establecida");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// -----------------------------------------------------------------------------------
	// 2 Metodos de conexion 
	public static Connection getConexion() {
		
		if(conexion == null)
			new Conexion();
		
		return conexion;
	}
	
	public static Connection getConexion(String nombreBD) {
		
		if(conexion == null)
			new Conexion(nombreBD);
		
		return conexion;
	}
	// ----------------------------------------------------------------------------------
	// Cerrar conexion
	public static void closeConexion() {
		if(conexion!= null)
			
			try {
				conexion.close();
				System.out.println("Conexion cerrada");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
