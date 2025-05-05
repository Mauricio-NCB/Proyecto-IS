package presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.dto.TProducto;

public class VentanaFactura extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaFactura(List<Object[]> productosSeleccionados) {
		super("Formulario de Factura");

		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel lblCliente = new JLabel("ID Cliente:");
		JTextField txtCliente = new JTextField();
		panelCentral.add(lblCliente);
		panelCentral.add(txtCliente);

		JLabel lblDependiente = new JLabel("ID Dependiente:");
		JTextField txtDependiente = new JTextField();
		panelCentral.add(lblDependiente);
		panelCentral.add(txtDependiente);

		JLabel lblImporte = new JLabel("Importe total:");
		JLabel lblImporteValor = new JLabel("Calculando...");
		panelCentral.add(lblImporte);
		panelCentral.add(lblImporteValor);

		add(panelCentral, BorderLayout.CENTER);

		// Calcular importe
		float total = 0f;
		for (Object[] obj : productosSeleccionados) {
		    TProducto prod = (TProducto) obj[0];
		    int cantidad = (int) obj[1];
		    total += prod.getPrecio() * cantidad;
		}
		lblImporteValor.setText(String.format("%.2f €", total));

		JButton btnConfirmar = new JButton("Confirmar Factura");
		btnConfirmar.addActionListener(e -> {
			try {
				int idCliente = Integer.parseInt(txtCliente.getText());
				String idDependiente = txtDependiente.getText();

				ControladorFactura.getInstance().nuevaFactura(idCliente, idDependiente, productosSeleccionados);
				JOptionPane.showMessageDialog(null, "Factura creada correctamente.");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al crear la factura: " + ex.getMessage());
			}
		});
		add(btnConfirmar, BorderLayout.SOUTH);

		setVisible(true);
	}
}