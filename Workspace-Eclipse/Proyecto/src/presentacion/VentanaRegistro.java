package presentacion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5632384030745242073L;

	public VentanaRegistro() {
		super("Registro de cliente");
		initGUI();
	}
	
	private void initGUI() {
		//Configuracion de la ventana
		setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        //
        add(new JLabel("DNI:"));
        JTextField txtDNI = new JTextField();
        add(txtDNI);

        add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        add(txtNombre);

        JButton btnRegistrar = new JButton("Registrar");
        add(btnRegistrar);

        // Acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dni = txtDNI.getText();
                String nombre = txtNombre.getText();
                ControladorUsuario.getInstance().registrarCliente(dni, nombre);
            }
        });
	}
}
