package ddl;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

import conexionJDBC.Conexion;

	public class DDL {

		private Connection conexion = null;
		private String nameDataBase;

		public DDL() {
			conexion = Conexion.getConexion();
		}
		

		public DDL(String nameDataBase) throws SQLException{
			conexion = Conexion.getConexion();

			try {
				conexion.setAutoCommit(false); 
				createDataBase(nameDataBase);
				useDataBase();
				conexion.setAutoCommit(true);

			} catch (SQLException e) {
				System.out.println("Base de datos no creada");
				conexion.rollback(); 
			}
		}


	// -----------------------------------------------------DDL----------------------------------------------------------------------------
		public void createDataBase(String nameDataBase) throws SQLException {
			String sentencia = String.format("Create database if not exists %s", nameDataBase);
			this.nameDataBase = nameDataBase;

			try (PreparedStatement ps = conexion.prepareStatement(sentencia);) {
				ps.executeUpdate(sentencia);
				System.out.println("Creacion correcta de la BD " + this.getNameDataBase());
			}
		}

		public void useDataBase() throws SQLException {
			String use = String.format("Use " + this.getNameDataBase());

			try (PreparedStatement ps = conexion.prepareStatement(use)) {
				ps.execute(use);
				System.out.println("Use " + this.getNameDataBase());
			}
		}

		public void dropDataBase() throws SQLException {
			String use = String.format("Drop database " + this.getNameDataBase());

			try (PreparedStatement ps = conexion.prepareStatement(use)) {
				ps.execute(use);
				System.out.println("BD borrada");
			}
		}
		// ---------------------------------------------CREATE TABLES------------------------------------------------------------------------------------

		public void createTable(String tableName, String table) throws SQLException {
			String sentencia = "Create table " + tableName + "(" + table + ");";

			try (PreparedStatement ps = conexion.prepareStatement(sentencia)) {
				ps.executeUpdate(sentencia);
				System.out.println("Create table ok");
			}
		}



		// ---------------------------------------------------------------------------------------------------------------------------------
		public String getNameDataBase() {
			return nameDataBase;
		}

	}

