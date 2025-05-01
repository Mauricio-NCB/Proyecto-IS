package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		add(new JLabel("Bienvenido dependiente"));
		
		JButton btnVenta = new JButton("Nueva venta");
		add(btnVenta);
		
		btnVenta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<TProducto> listaProductos = ControladorProducto.getInstance().listarProductos();
				new VentanaCatalogo(listaProductos).setVisible(true);
			}
			
		});
		
	}
}
