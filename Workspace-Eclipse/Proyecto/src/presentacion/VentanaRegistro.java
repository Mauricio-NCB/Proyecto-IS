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
		setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        
        add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Direccion:"));
        JTextField txtDireccion = new JTextField();
        add(txtDireccion);
        
        add(new JLabel("Correo:"));
        JTextField txtCorreo = new JTextField();
        add(txtCorreo);

        JButton btnRegistrar = new JButton("Registrar");
        add(btnRegistrar);

        // Acción del botón de registro
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String correo = txtCorreo.getText();
                ControladorCliente.getInstance().registrarCliente(nombre, direccion, correo);
            }
        });
	}
}
