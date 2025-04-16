package presentacion;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaDirector extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3027923627164736994L;

	public VentanaDirector() {
		super("Panel del Director");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel label = new JLabel("Bienvenido director");
		add(label);
	}
}