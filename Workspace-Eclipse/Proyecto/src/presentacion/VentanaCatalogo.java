package presentacion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.dto.TCamiseta;
import negocio.dto.TEntrada;
import negocio.dto.TJuguete;
import negocio.dto.TPoster;
import negocio.dto.TProducto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaCatalogo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842322808225530185L;
	
	private List<TProducto> productos;
	
	public VentanaCatalogo(List<TProducto> lista) {
		super("Catalogo de productos");
		productos = lista;
		initGUI();
	}

	private void initGUI() {
		setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear modelo de tabla
        String[] columnas = {"Tipo", "ID", "Nombre", "Precio", "Stock", "Detalles", "Cantidad"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            
            /**
			 * 
			 */
			private static final long serialVersionUID = -9049944760654233543L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// Solo la columna de cantidad es editable
				return column == 6;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnIndex == 6 ? Integer.class : String.class;
			}
        };
        
        // Llenar la tabla
		for (TProducto producto : productos) {
			Object[] fila = {
				producto.getClass().getSimpleName(),
				producto.getID(),
				producto.getNombre(),
				producto.getPrecio() + " €",
				producto.getStock(),
				getDetalles(producto),
				0 // Cantidad inicial
			};
			modeloTabla.addRow(fila);
		}
        
        //Configurar tabla
        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(60);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        // Configurar scroll pane
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel lblTotal = new JLabel("Total de productos: " + productos.size());
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelInferior.add(lblTotal);
        
        JButton btnConfirmar = new JButton("Confirmar selección");
        btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<int[]> seleccionados = new ArrayList<>();

				for (int i = 0; i < modeloTabla.getRowCount(); i++) {
					int cantidad = 0;
					try {
						Object valor = modeloTabla.getValueAt(i, 6);
						if (valor instanceof Integer)
							cantidad = (Integer) valor;
						else if (valor != null)
							cantidad = Integer.parseInt(valor.toString());
					} catch (NumberFormatException ex) {
						cantidad = 0;
					}

					if (cantidad > 0) {
						int id = (int) modeloTabla.getValueAt(i, 1);
						seleccionados.add(new int[]{id, cantidad});
					}
				}

				if (seleccionados.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has seleccionado ninguna cantidad.");
				} else {
					// Aquí se llamaría al controlador de ventas pasando la lista de arrays
					ControladorVenta.getInstance().nuevaVenta(seleccionados); // método debe aceptar List<int[]>
					JOptionPane.showMessageDialog(null, "Venta iniciada con " + seleccionados.size() + " productos.");
					dispose();
				}
			}
        	
        });
        panelInferior.add(btnConfirmar);
        
        add(panelInferior, BorderLayout.SOUTH);
	}
	
	private String getDetalles(TProducto producto) {
		
        if (producto instanceof TCamiseta) {
            TCamiseta camiseta = (TCamiseta) producto;
            return ("Talla: " + camiseta.getTalla() + " Jugador: " + camiseta.getDorsalJugador() + " Dorsal: " + camiseta.getNumeroJugador());
        } 
        else if (producto instanceof TEntrada) {
            TEntrada entrada = (TEntrada) producto;
            return ("Fecha: " + entrada.getFecha().toString() + " Hora: " + entrada.getHora() + " Partido: "
            		+ entrada.getPartido() + " Asiento: " + entrada.getNumeroAsiento());
        }
        else if (producto instanceof TJuguete) {
            TJuguete juguete = (TJuguete) producto;
            return ("Tipo: " + juguete.getTipo() + " Tamaño: " + juguete.getTamano());
        }
        else if (producto instanceof TPoster) {
            TPoster poster = (TPoster) producto;
            return ("Tamaño: " + poster.getTamano());
        }
        return "";
    }
}
