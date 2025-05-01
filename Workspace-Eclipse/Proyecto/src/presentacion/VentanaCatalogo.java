package presentacion;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear modelo de tabla
        String[] columnas = {"Tipo", "ID", "Nombre", "Precio", "Stock", "Detalles"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
     // Llenar la tabla
        for (TProducto producto : productos) {
            Object[] fila = new Object[6];
            fila[0] = producto.getClass().getSimpleName();
            fila[1] = producto.getID();
            fila[2] = producto.getNombre();
            fila[3] = producto.getPrecio() + "€";
            fila[4] = producto.getStock();
            fila[5] = getDetalles(producto);

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
