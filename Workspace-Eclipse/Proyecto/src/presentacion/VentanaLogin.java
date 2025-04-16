package presentacion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1991943685986250192L;

	public VentanaLogin() {
		super("Inicio de sesión");
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
        
        //Acción del botón de login
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = txtId.getText();
        		String contrasena = new String(txtContrasena.getPassword());
        		
        		if (ControladorEmpleado.getInstance().loguearEmpleado(id, contrasena)) {
        			dispose();
        		}
        	}
        });
        
        setVisible(true);
	}
	
}
