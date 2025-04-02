package integracion;

public class DAODirectorImp {
    @Override
    public boolean createDirector(Director director) {
        String sql = "INSERT INTO Director (identificador, nombre, sueldo) VALUES (?, ?, ?)";

        try (Connection conn = BDConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, director.getId());
            pstmt.setString(2, director.getNombre());
            pstmt.setString(3, director.getSueldo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
}
