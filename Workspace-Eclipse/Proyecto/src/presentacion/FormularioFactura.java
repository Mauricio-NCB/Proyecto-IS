package presentacion;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormularioFactura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3181000727446073005L;

	public FormularioFactura() {
		super("Generar factura");
		initGUI();
	}
	
	private void initGUI() {
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(7, 2, 10, 10));
		
		add(new JLabel("Fecha:"));
		JLabel lblFecha = new JLabel(LocalDate.now().toString());
		add(lblFecha);
		
		add(new JLabel("Hora:"));
		JLabel lblHora = new JLabel(LocalTime.now().withNano(0).toString());
		add(lblHora);
		
		add(new JLabel("Importe total:"));
		JTextField txtImporte = new JTextField();
		add(txtImporte);
		
		add(new JLabel("ID del cliente:"));
		JTextField txtIDCliente = new JTextField();
		add(txtIDCliente);
		
		JButton btnGuardar = new JButton("Guardar factura");
		btnGuardar.addActionListener(new ActionListener() {
			String fecha = lblFecha.getText();
			String hora = lblHora.getText();
			Float importe = Float.parseFloat(txtImporte.getText().trim());
			
			
		});
	}
}
