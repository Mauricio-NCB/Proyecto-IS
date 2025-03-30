package presentacion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6732132126516180470L;

	public VentanaInicio() {
		super("Inicio");
		initGUI();
	}
	
	private void initGUI() {
        // Configuración de la ventana
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Botón de Iniciar Sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrir ventana de login");
            }
        });
        add(btnLogin);

        // Botón de Registro
        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrir ventana de registro");
            }
        });
        add(btnRegistro);

        // Hacer visible la ventana
        setVisible(true);
    }

}
