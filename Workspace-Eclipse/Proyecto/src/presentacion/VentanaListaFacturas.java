package presentacion;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.dto.TFactura;

public class VentanaListaFacturas extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaListaFacturas(List<TFactura> facturas) {
		setTitle("Lista de facturas");
		setSize(600, 400);
		setLocationRelativeTo(null);

		String[] columnas = { "Código", "Fecha", "Hora", "Importe", "Cliente"};

		String[][] datos = new String[facturas.size()][columnas.length];
		for (int i = 0; i < facturas.size(); i++) {
			TFactura f = facturas.get(i);
			datos[i][0] = f.getCodigo();
			datos[i][1] = f.getFecha().toString();
			datos[i][2] = f.getHora().toString();
			datos[i][3] = String.valueOf(f.getImporte());
			datos[i][4] = f.getTiene().getNombre();
		}

		JTable tabla = new JTable(datos, columnas);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
	}
}
