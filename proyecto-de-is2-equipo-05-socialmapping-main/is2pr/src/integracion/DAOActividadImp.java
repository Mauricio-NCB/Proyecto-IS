package integracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TActividad;

public class DAOActividadImp implements DAOActividad {

	@Override
	public void crearActividad(TActividad actividad) throws Exception {

		String sql = "INSERT INTO Actividad (id, nombre, descripcion, localizacion, anyo, mes, dia, integrantes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = BDConexion.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, actividad.getId());
			pstmt.setString(2, actividad.getNombre());
			pstmt.setString(3, actividad.getDescripcion());
			pstmt.setString(4, actividad.getLocalizacion());
			pstmt.setInt(5, actividad.getAnyo());
			pstmt.setInt(6, actividad.getMes());
			pstmt.setInt(7, actividad.getDia());
			pstmt.setInt(8, actividad.getIntegrantes());

			pstmt.executeUpdate();
		}
	}

	@Override
	public void actualizarActividad(TActividad actividad) throws Exception {

		String sql = "UPDATE Actividad SET nombre=?, descripcion=?, localizacion=?, anyo=?, mes=?, dia=?, integrantes=? WHERE id=?";

		try (Connection conn = BDConexion.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, actividad.getNombre());
			pstmt.setString(2, actividad.getDescripcion());
			pstmt.setString(3, actividad.getLocalizacion());
			pstmt.setInt(4, actividad.getAnyo());
			pstmt.setInt(5, actividad.getMes());
			pstmt.setInt(6, actividad.getDia());
			pstmt.setInt(7, actividad.getIntegrantes());
			pstmt.setString(8, actividad.getId());

			pstmt.executeUpdate();
		}
	}

	@Override
	public TActividad obtenerActividad(String id) throws Exception {

		String sql = "SELECT * FROM Actividad WHERE id=?";

		try (Connection conn = BDConexion.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				TActividad actividad = new TActividad();

				actividad.setId(rs.getString("id"));
				actividad.setNombre(rs.getString("nombre"));
				actividad.setDescripcion(rs.getString("descripcion"));
				actividad.setLocalizacion(rs.getString("localizacion"));
				actividad.setAnyo(rs.getInt("anyo"));
				actividad.setMes(rs.getInt("mes"));
				actividad.setDia(rs.getInt("dia"));
				actividad.setIntegrantes(rs.getInt("integrantes"));

				return actividad;
			}

			return null;
		}
	}

	@Override
	public List<TActividad> obtenerTodasActividades() throws Exception {

		String sql = "SELECT * FROM Actividad";
		List<TActividad> actividades = new ArrayList<>();

		try (Connection conn = BDConexion.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				TActividad actividad = new TActividad();

				actividad.setId(rs.getString("id"));
				actividad.setNombre(rs.getString("nombre"));
				actividad.setDescripcion(rs.getString("descripcion"));
				actividad.setLocalizacion(rs.getString("localizacion"));
				actividad.setAnyo(rs.getInt("anyo"));
				actividad.setMes(rs.getInt("mes"));
				actividad.setDia(rs.getInt("dia"));
				actividad.setIntegrantes(rs.getInt("integrantes"));

				actividades.add(actividad);
			}
		}

		return actividades;
	}

	@Override
	public void eliminarActividad(String id) throws Exception {

		String sql = "DELETE FROM Actividad WHERE id=?";

		try (Connection conn = BDConexion.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}
	}
}