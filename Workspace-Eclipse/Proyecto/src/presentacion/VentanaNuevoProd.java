package presentacion;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaNuevoProd extends JFrame{

	
	/**
	 * 
	 */ 
	private static final long serialVersionUID = -4608822854679946123L;

	public VentanaNuevoProd() {
		super("Nuevo Producto");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel label = new JLabel("Producto añadido");
		add(label);
	}
}
