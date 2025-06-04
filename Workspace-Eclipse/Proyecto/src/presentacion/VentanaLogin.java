package presentacion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1991943685986250192L;
	private ControladorEmpleado controladorEmpleado;

	public VentanaLogin() {
		super("Inicio de sesi�n");
		controladorEmpleado = ControladorEmpleado.getInstance();
		
		initGUI();
	}
	
	private void initGUI() {
		//Configuracion de la ventana
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 2));
		
		add(new JLabel("Identificador:"));
		JTextField txtId = new JTextField();
		add(txtId);
		
		add(new JLabel("Contraseña:"));
		JPasswordField txtContrasena = new JPasswordField();
		add(txtContrasena);
		
		JButton btnLogin = new JButton("Iniciar sesión");
        add(btnLogin);
        
        
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = txtId.getText();
        		String contrasena = new String(txtContrasena.getPassword());
        		
        		if (id.isEmpty() || contrasena.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Por favor, rellena los campos");
        			return;
        		}
        		
        		try {
        			if (controladorEmpleado.loguearEmpleado(id, contrasena)) dispose();
        		}
        		catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage());
        		}        		
        	}
        });
        
        setVisible(true);
	}
	
}
