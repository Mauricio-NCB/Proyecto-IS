package presentacion;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import negocio.dto.*;

public class VentanaVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private ControladorVenta controlador;
    

    private JTabbedPane tabbedPane;
    private JPanel panelNuevaVenta, panelBuscarVenta, panelListadoVentas;
    
    // Nueva Venta
    private JTextField txtClienteId, txtDependienteId;
    private JTable tablaProductos;
    private ProductoTableModel tableModel;
    private JButton btnAgregarProducto, btnCrearVenta;
    private JSpinner spinnerCantidad;
    
    //  Buscar Venta
    private JTextField txtCodigoVenta;
    private JButton btnBuscarVenta;
    private JTextArea txtDetalleVenta;
    
    // Listado Ventas
    private JTable tablaVentas;
    private VentaTableModel ventaTableModel;
    private JButton btnActualizarListado;
    
    public VentanaVenta() {
        super("Gestión de Ventas");
        this.controlador = ControladorVenta.getInstance();
        initComponents();
        initLayout();
        initListeners();
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Panel Nueva Venta
        panelNuevaVenta = new JPanel(new BorderLayout(10, 10));
        
        JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la Venta"));
        panelDatos.add(new JLabel("ID Cliente:"));
        txtClienteId = new JTextField();
        panelDatos.add(txtClienteId);
        panelDatos.add(new JLabel("ID Dependiente:"));
        txtDependienteId = new JTextField();
        panelDatos.add(txtDependienteId);
        
        tableModel = new ProductoTableModel();
        tablaProductos = new JTable((TableModel) tableModel);
        JScrollPane scrollProductos = new JScrollPane(tablaProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Productos en Venta"));
        
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelControles.add(new JLabel("Cantidad:"));
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panelControles.add(spinnerCantidad);
        btnAgregarProducto = new JButton("Agregar Producto");
        panelControles.add(btnAgregarProducto);
        btnCrearVenta = new JButton("Crear Venta");
        panelControles.add(btnCrearVenta);
        
        panelNuevaVenta.add(panelDatos, BorderLayout.NORTH);
        panelNuevaVenta.add(scrollProductos, BorderLayout.CENTER);
        panelNuevaVenta.add(panelControles, BorderLayout.SOUTH);
        
      
        panelBuscarVenta = new JPanel(new BorderLayout(10, 10));
        
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Código Venta:"));
        txtCodigoVenta = new JTextField(15);
        panelBusqueda.add(txtCodigoVenta);
        btnBuscarVenta = new JButton("Buscar");
        panelBusqueda.add(btnBuscarVenta);
        
        txtDetalleVenta = new JTextArea();
        txtDetalleVenta.setEditable(false);
        JScrollPane scrollDetalle = new JScrollPane(txtDetalleVenta);
        scrollDetalle.setBorder(BorderFactory.createTitledBorder("Detalle de Venta"));
        
        panelBuscarVenta.add(panelBusqueda, BorderLayout.NORTH);
        panelBuscarVenta.add(scrollDetalle, BorderLayout.CENTER);
        
       
        panelListadoVentas = new JPanel(new BorderLayout());
        
        ventaTableModel = new VentaTableModel();
        tablaVentas = new JTable((TableModel) ventaTableModel);
        JScrollPane scrollVentas = new JScrollPane(tablaVentas);
        scrollVentas.setBorder(BorderFactory.createTitledBorder("Listado de Ventas"));
        
        btnActualizarListado = new JButton("Actualizar Listado");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnActualizarListado);
        
        panelListadoVentas.add(scrollVentas, BorderLayout.CENTER);
        panelListadoVentas.add(panelBotones, BorderLayout.SOUTH);
        
        
        tabbedPane.addTab("Nueva Venta", panelNuevaVenta);
        tabbedPane.addTab("Buscar Venta", panelBuscarVenta);
        tabbedPane.addTab("Listado Ventas", panelListadoVentas);
    }
    
    private void initLayout() {
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private void initListeners() {
       
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        btnCrearVenta.addActionListener(e -> crearVenta());
        
        
        btnBuscarVenta.addActionListener(e -> buscarVenta());
        
        
        btnActualizarListado.addActionListener(e -> actualizarListadoVentas());
    }
    
    private void agregarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int cantidad = (Integer) spinnerCantidad.getValue();
        TProducto producto = tableModel.getProductoAt(filaSeleccionada);
        
       
    }
    
    private void crearVenta() {
        try {
            int idCliente = Integer.parseInt(txtClienteId.getText().trim());
            String idDependiente = txtDependienteId.getText().trim();
            
           
            List<TLineaVenta> lineasVenta = new ArrayList<>();
            
            if (controlador.abrirVenta(idCliente, idDependiente, lineasVenta)) {
                JOptionPane.showMessageDialog(this, "Venta creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de cliente debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarVenta() {
        String codigo = txtCodigoVenta.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un código de venta", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        TVenta venta = controlador.obtenerVenta(codigo);
        if (venta != null) {
            mostrarDetalleVenta(venta);
        } else {
            JOptionPane.showMessageDialog(this, "Venta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarDetalleVenta(TVenta venta) {
        StringBuilder sb = new StringBuilder();
        sb.append("Código: ").append(venta.getCodigo()).append("\n");
        sb.append("Fecha: ").append(venta.getFecha()).append(" ").append(venta.getHora()).append("\n");
        sb.append("Cliente: ").append(venta.getTiene().getNombre()).append("\n");
        sb.append("Dependiente: ").append(venta.getDependiente().getNombre()).append("\n\n");
        sb.append("Productos:\n");
        
        for (TLineaVenta linea : venta.getLineasVenta()) {
            sb.append("- ").append(linea.getProducto().getNombre())
             .append(" x").append(linea.getCantidad())
             .append(": ").append(linea.getProducto().getPrecio() * linea.getCantidad()).append("€\n");
        }
        
        sb.append("\nTotal: ").append(venta.getImporteTotal()).append("€\n");
        
        if (venta.getFactura() != null) {
            sb.append("\nFactura: ").append(venta.getFactura().getCodigo());
        }
        
        txtDetalleVenta.setText(sb.toString());
    }
    
    private void actualizarListadoVentas() {
        List<TVenta> ventas = controlador.obtenerTodasVentas();
        ventaTableModel.setVentas(ventas);
    }
    
    private void limpiarCampos() {
        txtClienteId.setText("");
        txtDependienteId.setText("");
       
    }
    

    private class ProductoTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<TProducto> productos;
        private String[] columnNames = {"ID", "Nombre", "Precio", "Stock"};
        
        public ProductoTableModel() {
            try {
				productos = ControladorProducto.getInstance().listarProductos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        public int getRowCount() {
            return productos.size();
        }
        
        
        public int getColumnCount() {
            return columnNames.length;
        }
        
        
        public Object getValueAt(int rowIndex, int columnIndex) {
            TProducto producto = productos.get(rowIndex);
            switch (columnIndex) {
                case 0: return producto.getID();
                case 1: return producto.getNombre();
                case 2: return producto.getPrecio();
                case 3: return producto.getStock();
                default: return null;
            }
        }
        
        
        public String getColumnName(int column) {
            return columnNames[column];
        }
        
        public TProducto getProductoAt(int row) {
            return productos.get(row);
        }
    }
    
    private class VentaTableModel extends AbstractTableModel {
        private List<TVenta> ventas;
        private String[] columnNames = {"Código", "Fecha", "Cliente", "Total", "Facturada"};
        
        public void setVentas(List<TVenta> ventas) {
            this.ventas = ventas;
        }

		public int getRowCount() {
            return ventas != null ? ventas.size() : 0;
        }
        
       
        public int getColumnCount() {
            return columnNames.length;
        }
        
       
        public Object getValueAt(int rowIndex, int columnIndex) {
            TVenta venta = ventas.get(rowIndex);
            switch (columnIndex) {
                case 0: return venta.getCodigo();
                case 1: return venta.getFecha();
                case 2: return venta.getTiene().getNombre();
                case 3: return venta.getImporteTotal();
                case 4: return venta.getFactura() != null ? "Sí" : "No";
                default: return null;
            }
        }
        
        
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}
