package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.dto.TFactura;
import negocio.dto.TProducto;

public class VentanaDependiente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3027923627164736994L;

	public VentanaDependiente() {
		super("Panel del Dependiente");
		initGUI();
	}
	
	private void initGUI() {
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		// Panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100)); // Espaciado interno

		JButton btnVenta = new JButton("Nueva venta");
		JButton btnListarFacturas = new JButton("Listar facturas");


		panelBotones.add(btnVenta);
		panelBotones.add(Box.createHorizontalStrut(20)); 
		panelBotones.add(btnListarFacturas);
		
		add(panelBotones, BorderLayout.CENTER);
		
		btnVenta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<TProducto> listaProductos = ControladorProducto.getInstance().listarProductos();
				new VentanaCatalogo(listaProductos).setVisible(true);
			}
			
		});
		
		btnListarFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TFactura> listaFacturas = ControladorFactura.getInstance().listarFacturas();
				new VentanaListaFacturas(listaFacturas).setVisible(true);
			}
		});
	}
}
