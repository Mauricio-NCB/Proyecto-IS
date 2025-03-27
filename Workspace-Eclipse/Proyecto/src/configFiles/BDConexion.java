package configFiles;
import java.sql.*;
public class BDConexion {
	
	/**Parametros de conexion**/
	static String bd = "e9clubshop";
	static String login = "root";
	static String password = "equipo9";
	static String url = "jdbc:mysql://localhost/" + bd;
	
	Connection connection = null;
		
	public BDConexion() {
		try {
			 //String url = " jdbc : mysql :// hostname / database - name ";
			
			// Conexion a la BBDD
			connection = DriverManager.getConnection (url,login,password);
			Statement stmt = connection.createStatement();
			
			// Sentencia SQL
            String sql = "SELECT * FROM Producto"; 
            
            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(sql);
            
            // Recorrer resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Stock: " + stock);
            }
            
            // Cerrar conexion
            rs.close();
            stmt.close();
            connection.close(); 

            
		}
		catch ( SQLException ex) {
			connection = null ;
			ex. printStackTrace ();
			System.out.println (" SQLException : " + ex. getMessage ());
			System.out.println (" SQLState : " + ex. getSQLState ());
			System.out.println (" VendorError : " + ex. getErrorCode ());
		}
	}
		
	public Connection getConnection() {
		return connection;
	}
		
	public void desconectar() {
		connection = null;
	}
	
}
	
