package Presentacion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class VistaCliente {
	
	public VistaCliente() {
		// ------------------------------------
		// Diseño de la vista
		JFrame menuPrincipal = new JFrame();
		menuPrincipal.setTitle("Menu CRUDs Cliente");
		menuPrincipal.setBounds(100, 100, 450, 300);
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.getContentPane().setLayout(new FlowLayout());

		// Menu con botones para acceder a la funcionalidad de la
		// entidad cliente. Solo se muestran los CRUDs Alta y Mostrar.

		JButton botonAlta = new JButton();
		botonAlta.setText("Alta Cliente");
		botonAlta.setBounds(153, 180, 132, 32);
		menuPrincipal.getContentPane().add(botonAlta);

		JButton botonReadAll = new JButton();
		botonReadAll.setText("Mostrar clientes");
		botonReadAll.setBounds(253, 380, 132, 32);
		menuPrincipal.getContentPane().add(botonReadAll);

		menuPrincipal.setVisible(true);

		// ------------------------------------
		// Listeners

		botonAlta.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // TBD (To Be Developed)
		    }
		});

		botonReadAll.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Controlador.accion(Evento.MOSTRAR_CLIENTES, null);
		    }
		});
	}
	
	public static void actualizar(int evento, Object datos) {

	    if (evento == Evento.RES_MOSTRAR_CLIENTES_OK) {

	        JTextArea textArea = new JTextArea(datos.toString());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);

	        JScrollPane scrollPane = new JScrollPane(textArea);
	        scrollPane.setPreferredSize(new Dimension(500, 200));

	        JOptionPane.showMessageDialog(
	                null,
	                scrollPane,
	                "CLIENTES",
	                JOptionPane.DEFAULT_OPTION
	        );

	    } else if (evento == Evento.RES_MOSTRAR_CLIENTES_KO) {

	        JOptionPane.showMessageDialog(
	                null,
	                "No se ha podido encontrar ningún cliente"
	        );
	    }
	}
}

