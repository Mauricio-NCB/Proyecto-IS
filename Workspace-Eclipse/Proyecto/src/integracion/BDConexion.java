package integracion;
import java.sql.*;
public class BDConexion {

    private static BDConexion instancia;
    private Connection connection;

    private static final String BD = "tienda";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "equipo9";
    private static final String URL = "jdbc:mysql://localhost/" + BD;

    private BDConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            connection = null;
        }
    }

    public static BDConexion getInstance() {
        if (instancia == null) {
            instancia = new BDConexion();
        }
        return instancia;
    }

    public Connection getConnection() {
    	try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            connection = null;
            instancia = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}