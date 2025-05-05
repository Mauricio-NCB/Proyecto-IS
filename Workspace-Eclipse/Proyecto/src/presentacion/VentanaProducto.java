package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// Importar DTOs y Controlador
import negocio.dto.*;
import presentacion.ControladorProducto; // O ControladorProducto si lo tienes

public class VentanaProducto extends JDialog { // Usar JDialog es apropiado

    private static final long serialVersionUID = 1L;

    private JTabbedPane tabbedPane; // Panel con pestañas

    // Paneles y componentes para cada pestaña
    private JPanel panelCamiseta, panelEntrada, panelJuguete, panelPoster, panelCatalogo;

    // Componentes Camiseta
    private JTextField txtCamisetaId, txtCamisetaNombre, txtCamisetaPrecio, txtCamisetaStock, txtCamisetaTalla, txtCamisetaDorsal, txtCamisetaNumero;
    private JButton btnRegCamiseta, btnUpdCamiseta;

    // Componentes Entrada
    private JTextField txtEntradaId, txtEntradaNombre, txtEntradaPrecio, txtEntradaStock, txtEntradaFecha, txtEntradaHora, txtEntradaUbicacion, txtEntradaAsiento, txtEntradaPartido;
    private JButton btnRegEntrada, btnUpdEntrada;

    // Componentes Juguete
    private JTextField txtJugueteId, txtJugueteNombre, txtJuguetePrecio, txtJugueteStock, txtJugueteTipo, txtJugueteTamano;
    private JButton btnRegJuguete, btnUpdJuguete;

    // Componentes Poster
    private JTextField txtPosterId, txtPosterNombre, txtPosterPrecio, txtPosterStock, txtPosterTamano;
    private JButton btnRegPoster, btnUpdPoster;
    
    private JButton btnMostrarCatalogo;
    // Referencia al controlador
    private ControladorProducto controlador; // O ControladorProducto

    public VentanaProducto() {
        super((Frame)null, "Gestionar Productos", true); // true = Modal
        this.controlador = ControladorProducto.getInstance();
        initGUI();
    }

    private void initGUI() {
        tabbedPane = new JTabbedPane();

        // Crear cada panel/pestaña
        panelCamiseta = crearPanelCamiseta();
        panelEntrada = crearPanelEntrada();
        panelJuguete = crearPanelJuguete();
        panelPoster = crearPanelPoster();
        panelCatalogo = crearPanelCatalogo();
        

        // Añadir pestañas al JTabbedPane
        tabbedPane.addTab("Camisetas", panelCamiseta);
        tabbedPane.addTab("Entradas", panelEntrada);
        tabbedPane.addTab("Juguetes", panelJuguete);
        tabbedPane.addTab("Posters", panelPoster);
        tabbedPane.addTab("Catálogo", panelCatalogo);

        // Añadir JTabbedPane al JDialog
        add(tabbedPane);

        // Configuraciones finales
        pack(); // Ajustar tamaño a las pestañas
        setMinimumSize(new Dimension(600, 350)); // Establecer mínimo
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Cerrar solo este diálogo
        setLocationRelativeTo(null); // Centrar
    }

    // --- Métodos para crear cada panel de pestaña ---
    
    private JPanel crearPanelCatalogo() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        btnMostrarCatalogo = new JButton("Catálogo");
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnMostrarCatalogo, gbc);
        btnMostrarCatalogo.addActionListener(e -> mostrarCatalogo());

		return panel;
    }

    private JPanel crearPanelCamiseta() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels y TextFields para Camiseta
        lblId = new JLabel("ID (sólo actualizar):");          txtCamisetaId = new JTextField(5);
        lblNombre = new JLabel("Nombre:");                    txtCamisetaNombre = new JTextField(15);
        lblPrecio = new JLabel("Precio:");                    txtCamisetaPrecio = new JTextField(7);
        lblStock = new JLabel("Stock:");                      txtCamisetaStock = new JTextField(5);
        lblTalla = new JLabel("Talla (número):");             txtCamisetaTalla = new JTextField(5);
        lblDorsal = new JLabel("Dorsal (nombre):");           txtCamisetaDorsal = new JTextField(12);
        lblNumero = new JLabel("Número (jugador):");          txtCamisetaNumero = new JTextField(5);
        btnRegCamiseta = new JButton("Registrar Camiseta"); btnUpdCamiseta = new JButton("Actualizar Camiseta");

        // Añadir componentes con GBC (ejemplo simplificado, ajustar layout)
        // Fila 0
        gbc.gridx=0; gbc.gridy=0; panel.add(lblId, gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(txtCamisetaId, gbc);
        gbc.gridx=2; gbc.gridy=0; panel.add(lblNombre, gbc);
        gbc.gridx=3; gbc.gridy=0; gbc.gridwidth=3; panel.add(txtCamisetaNombre, gbc); gbc.gridwidth=1; // Reset
        // Fila 1
        gbc.gridx=0; gbc.gridy=1; panel.add(lblPrecio, gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(txtCamisetaPrecio, gbc);
        gbc.gridx=2; gbc.gridy=1; panel.add(lblStock, gbc);
        gbc.gridx=3; gbc.gridy=1; panel.add(txtCamisetaStock, gbc);
        // Fila 2
        gbc.gridx=0; gbc.gridy=2; panel.add(lblTalla, gbc);
        gbc.gridx=1; gbc.gridy=2; panel.add(txtCamisetaTalla, gbc);
        gbc.gridx=2; gbc.gridy=2; panel.add(lblDorsal, gbc);
        gbc.gridx=3; gbc.gridy=2; panel.add(txtCamisetaDorsal, gbc);
        // Fila 3
        gbc.gridx=0; gbc.gridy=3; panel.add(lblNumero, gbc);
        gbc.gridx=1; gbc.gridy=3; panel.add(txtCamisetaNumero, gbc);
        // Fila 4 - Botones
        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnRegCamiseta, gbc);
        gbc.gridx=2; gbc.gridy=4; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnUpdCamiseta, gbc);

        // Listeners para Camiseta
        btnRegCamiseta.addActionListener(e -> registrarCamisetaAction());
        btnUpdCamiseta.addActionListener(e -> actualizarCamisetaAction());

        return panel;
    }

    private JPanel crearPanelEntrada() {
         JPanel panel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(4, 4, 4, 4);
         gbc.anchor = GridBagConstraints.WEST;

        // Labels y TextFields para Entrada
        lblId = new JLabel("ID (sólo actualizar):");      txtEntradaId = new JTextField(5);
        lblNombre = new JLabel("Nombre Evento:");        txtEntradaNombre = new JTextField(15);
        lblPrecio = new JLabel("Precio:");                txtEntradaPrecio = new JTextField(7);
        lblStock = new JLabel("Stock:");                  txtEntradaStock = new JTextField(5);
        lblFecha = new JLabel("Fecha (YYYY-MM-DD):");    txtEntradaFecha = new JTextField(10);
        lblHora = new JLabel("Hora (HH:MM):");          txtEntradaHora = new JTextField(5);
        lblUbicacion = new JLabel("Ubicación:");          txtEntradaUbicacion = new JTextField(12);
        lblAsiento = new JLabel("Nº Asiento:");          txtEntradaAsiento = new JTextField(8);
        lblPartido = new JLabel("Partido/Detalle:");      txtEntradaPartido = new JTextField(15);
        btnRegEntrada = new JButton("Registrar Entrada"); btnUpdEntrada = new JButton("Actualizar Entrada");

        // Añadir componentes con GBC (simplificado)
        gbc.gridx=0; gbc.gridy=0; panel.add(lblId, gbc); gbc.gridx=1; panel.add(txtEntradaId, gbc);
        gbc.gridx=2; gbc.gridy=0; panel.add(lblNombre, gbc); gbc.gridx=3; panel.add(txtEntradaNombre, gbc);
        // ... Añadir resto de filas con GBC ...
         gbc.gridx=0; gbc.gridy=1; panel.add(lblPrecio, gbc); gbc.gridx=1; panel.add(txtEntradaPrecio, gbc);
         gbc.gridx=2; gbc.gridy=1; panel.add(lblStock, gbc); gbc.gridx=3; panel.add(txtEntradaStock, gbc);
         gbc.gridx=0; gbc.gridy=2; panel.add(lblFecha, gbc); gbc.gridx=1; panel.add(txtEntradaFecha, gbc);
         gbc.gridx=2; gbc.gridy=2; panel.add(lblHora, gbc); gbc.gridx=3; panel.add(txtEntradaHora, gbc);
         gbc.gridx=0; gbc.gridy=3; panel.add(lblUbicacion, gbc); gbc.gridx=1; panel.add(txtEntradaUbicacion, gbc);
         gbc.gridx=2; gbc.gridy=3; panel.add(lblAsiento, gbc); gbc.gridx=3; panel.add(txtEntradaAsiento, gbc);
         gbc.gridx=0; gbc.gridy=4; panel.add(lblPartido, gbc); gbc.gridx=1; gbc.gridwidth=3; panel.add(txtEntradaPartido, gbc); gbc.gridwidth=1;
        // Botones
        gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnRegEntrada, gbc);
        gbc.gridx=2; gbc.gridy=5; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnUpdEntrada, gbc);


        // Listeners para Entrada
        btnRegEntrada.addActionListener(e -> registrarEntradaAction());
        btnUpdEntrada.addActionListener(e -> actualizarEntradaAction());

        return panel;
    }

    private JPanel crearPanelJuguete() {
         JPanel panel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(4, 4, 4, 4);
         gbc.anchor = GridBagConstraints.WEST;

        // Labels y TextFields para Juguete
        lblId = new JLabel("ID (sólo actualizar):");  txtJugueteId = new JTextField(5);
        lblNombre = new JLabel("Nombre:");            txtJugueteNombre = new JTextField(15);
        lblPrecio = new JLabel("Precio:");            txtJuguetePrecio = new JTextField(7);
        lblStock = new JLabel("Stock:");              txtJugueteStock = new JTextField(5);
        lblTipo = new JLabel("Tipo:");                txtJugueteTipo = new JTextField(10);
        lblTamano = new JLabel("Tamaño:");            txtJugueteTamano = new JTextField(10);
        btnRegJuguete = new JButton("Registrar Juguete"); btnUpdJuguete = new JButton("Actualizar Juguete");

        // Añadir componentes con GBC (simplificado)
        gbc.gridx=0; gbc.gridy=0; panel.add(lblId, gbc); gbc.gridx=1; panel.add(txtJugueteId, gbc);
        gbc.gridx=2; gbc.gridy=0; panel.add(lblNombre, gbc); gbc.gridx=3; panel.add(txtJugueteNombre, gbc);
        // ... Añadir resto de filas ...
        gbc.gridx=0; gbc.gridy=1; panel.add(lblPrecio, gbc); gbc.gridx=1; panel.add(txtJuguetePrecio, gbc);
        gbc.gridx=2; gbc.gridy=1; panel.add(lblStock, gbc); gbc.gridx=3; panel.add(txtJugueteStock, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(lblTipo, gbc); gbc.gridx=1; panel.add(txtJugueteTipo, gbc);
        gbc.gridx=2; gbc.gridy=2; panel.add(lblTamano, gbc); gbc.gridx=3; panel.add(txtJugueteTamano, gbc);
        // Botones
        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnRegJuguete, gbc);
        gbc.gridx=2; gbc.gridy=3; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnUpdJuguete, gbc);

        // Listeners para Juguete
        btnRegJuguete.addActionListener(e -> registrarJugueteAction());
        btnUpdJuguete.addActionListener(e -> actualizarJugueteAction());

        return panel;
    }

     private JPanel crearPanelPoster() {
         JPanel panel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(4, 4, 4, 4);
         gbc.anchor = GridBagConstraints.WEST;

        // Labels y TextFields para Poster
        lblId = new JLabel("ID (sólo actualizar):");  txtPosterId = new JTextField(5);
        lblNombre = new JLabel("Nombre:");            txtPosterNombre = new JTextField(15);
        lblPrecio = new JLabel("Precio:");            txtPosterPrecio = new JTextField(7);
        lblStock = new JLabel("Stock:");              txtPosterStock = new JTextField(5);
        lblTamano = new JLabel("Tamaño:");            txtPosterTamano = new JTextField(10);
        btnRegPoster = new JButton("Registrar Poster"); btnUpdPoster = new JButton("Actualizar Poster");

        // Añadir componentes con GBC (simplificado)
        gbc.gridx=0; gbc.gridy=0; panel.add(lblId, gbc); gbc.gridx=1; panel.add(txtPosterId, gbc);
        gbc.gridx=2; gbc.gridy=0; panel.add(lblNombre, gbc); gbc.gridx=3; panel.add(txtPosterNombre, gbc);
        // ... Añadir resto de filas ...
        gbc.gridx=0; gbc.gridy=1; panel.add(lblPrecio, gbc); gbc.gridx=1; panel.add(txtPosterPrecio, gbc);
        gbc.gridx=2; gbc.gridy=1; panel.add(lblStock, gbc); gbc.gridx=3; panel.add(txtPosterStock, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(lblTamano, gbc); gbc.gridx=1; gbc.gridwidth=3; panel.add(txtPosterTamano, gbc); gbc.gridwidth=1;
        // Botones
        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnRegPoster, gbc);
        gbc.gridx=2; gbc.gridy=3; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER; panel.add(btnUpdPoster, gbc);


        // Listeners para Poster
        btnRegPoster.addActionListener(e -> registrarPosterAction());
        btnUpdPoster.addActionListener(e -> actualizarPosterAction());

        return panel;
    }

    // --- Métodos de Acción (Listeners) ---
     
     private void mostrarCatalogo() {
 		List<TProducto> listaProductos = ControladorProducto.getInstance().listarProductos();
 		new VentanaCatalogo(listaProductos).setVisible(true);
     }

    private void registrarCamisetaAction() {
        // Leer datos de txtCamisetaNombre, txtCamisetaPrecio, etc.
        String nombre = txtCamisetaNombre.getText().trim();
        String precioStr = txtCamisetaPrecio.getText().trim();
        String stockStr = txtCamisetaStock.getText().trim();
        String tallaStr = txtCamisetaTalla.getText().trim();
        String dorsal = txtCamisetaDorsal.getText().trim();
        String numeroStr = txtCamisetaNumero.getText().trim();

        // Llamar al controlador (que valida y llama al SA)
        try {
             // El controlador se encarga de la conversión numérica y validación
             controlador.registrarCamiseta(nombre, precioStr, stockStr, tallaStr, dorsal, numeroStr);
             JOptionPane.showMessageDialog(this, "Solicitud de registro de camiseta enviada.\nVerifique la salida en la ventana principal.", "Registro Iniciado", JOptionPane.INFORMATION_MESSAGE);
             // Podrías limpiar campos aquí si quieres
        } catch (Exception ex) { // Captura muy genérica, el controlador debería loggear/imprimir
            JOptionPane.showMessageDialog(this, "Error al intentar registrar camiseta:\n" + ex.getMessage(), "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCamisetaAction() {
        // Leer datos de TODOS los campos, INCLUYENDO txtCamisetaId
        String idStr = txtCamisetaId.getText().trim();
        String nombre = txtCamisetaNombre.getText().trim();
        String precioStr = txtCamisetaPrecio.getText().trim();
        String stockStr = txtCamisetaStock.getText().trim();
        String tallaStr = txtCamisetaTalla.getText().trim();
        String dorsal = txtCamisetaDorsal.getText().trim();
        String numeroStr = txtCamisetaNumero.getText().trim();

        if (idStr.isEmpty()) {
             JOptionPane.showMessageDialog(this, "El campo ID es requerido para actualizar.", "ID Faltante", JOptionPane.WARNING_MESSAGE);
             return;
        }

         try {
             // El controlador se encarga de conversión y llamada al SA/DAO
             controlador.actualizarCamiseta(idStr, nombre, precioStr, stockStr, tallaStr, dorsal, numeroStr);
             JOptionPane.showMessageDialog(this, "Solicitud de actualización de camiseta enviada.\nVerifique la salida en la ventana principal.", "Actualización Iniciada", JOptionPane.INFORMATION_MESSAGE);
             // Podrías cerrar ventana o limpiar campos
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar actualizar camiseta:\n" + ex.getMessage(), "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Implementar métodos de acción similares para Entrada, Juguete y Poster ---
    // registrarEntradaAction(), actualizarEntradaAction()
    // registrarJugueteAction(), actualizarJugueteAction()
    // registrarPosterAction(), actualizarPosterAction()
    // Estos métodos leerán los datos de sus respectivos JTextFields y llamarán
    // a los métodos correspondientes del controlador (que creamos antes).
    // Recuerda manejar la conversión de fecha para Entrada.

     private void registrarEntradaAction() {
        String nombre = txtEntradaNombre.getText().trim();
        String precioStr = txtEntradaPrecio.getText().trim();
        String stockStr = txtEntradaStock.getText().trim();
        String fechaStr = txtEntradaFecha.getText().trim();
        String hora = txtEntradaHora.getText().trim();
        String ubicacion = txtEntradaUbicacion.getText().trim();
        String asiento = txtEntradaAsiento.getText().trim();
        String partido = txtEntradaPartido.getText().trim();

        try {
            // Parsear fecha (el controlador también valida formato)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date fecha = sdf.parse(fechaStr);

            controlador.registrarEntrada(nombre, precioStr, stockStr, fecha, hora, ubicacion, asiento, partido);
             JOptionPane.showMessageDialog(this, "Solicitud de registro de entrada enviada.", "Registro Iniciado", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException pe) {
             JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar entrada:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
     }

     private void actualizarEntradaAction() {
        String idStr = txtEntradaId.getText().trim();
        String nombre = txtEntradaNombre.getText().trim();
        String precioStr = txtEntradaPrecio.getText().trim();
        String stockStr = txtEntradaStock.getText().trim();
        String fechaStr = txtEntradaFecha.getText().trim();
        String hora = txtEntradaHora.getText().trim();
        String ubicacion = txtEntradaUbicacion.getText().trim();
        String asiento = txtEntradaAsiento.getText().trim();
        String partido = txtEntradaPartido.getText().trim();

         if (idStr.isEmpty()) { JOptionPane.showMessageDialog(this, "ID requerido.", "Error", JOptionPane.WARNING_MESSAGE); return; }

        try {
            Date fecha = null;
             if (!fechaStr.isEmpty()) {
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 sdf.setLenient(false);
                 fecha = sdf.parse(fechaStr);
             }

            controlador.actualizarEntrada(idStr, nombre, precioStr, stockStr, fecha, hora, ubicacion, asiento, partido);
             JOptionPane.showMessageDialog(this, "Solicitud de actualización de entrada enviada.", "Actualización Iniciada", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException pe) {
             JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Error al actualizar entrada:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
     }

     private void registrarJugueteAction() {
         String nombre = txtJugueteNombre.getText().trim();
         String precioStr = txtJuguetePrecio.getText().trim();
         String stockStr = txtJugueteStock.getText().trim();
         String tipo = txtJugueteTipo.getText().trim();
         String tamano = txtJugueteTamano.getText().trim();
         try {
             controlador.registrarJuguete(nombre, precioStr, stockStr, tipo, tamano);
             JOptionPane.showMessageDialog(this, "Solicitud de registro de juguete enviada.", "Registro Iniciado", JOptionPane.INFORMATION_MESSAGE);
         } catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Error al registrar juguete:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
     }

     private void actualizarJugueteAction() {
         String idStr = txtJugueteId.getText().trim();
         String nombre = txtJugueteNombre.getText().trim();
         String precioStr = txtJuguetePrecio.getText().trim();
         String stockStr = txtJugueteStock.getText().trim();
         String tipo = txtJugueteTipo.getText().trim();
         String tamano = txtJugueteTamano.getText().trim();
         if (idStr.isEmpty()) { JOptionPane.showMessageDialog(this, "ID requerido.", "Error", JOptionPane.WARNING_MESSAGE); return; }
         try {
             // La llamada a actualizarJuguete debe existir en el controlador
             controlador.actualizarJuguete(idStr, nombre, precioStr, stockStr, tipo, tamano); // NECESITAS ESTE MÉTODO EN ControladorDirector
             JOptionPane.showMessageDialog(this, "Solicitud de actualización de juguete enviada.", "Actualización Iniciada", JOptionPane.INFORMATION_MESSAGE);
         } catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Error al actualizar juguete:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
     }

     private void registrarPosterAction() {
         String nombre = txtPosterNombre.getText().trim();
         String precioStr = txtPosterPrecio.getText().trim();
         String stockStr = txtPosterStock.getText().trim();
         String tamano = txtPosterTamano.getText().trim();
         try {
             controlador.registrarPoster(nombre, precioStr, stockStr, tamano);
             JOptionPane.showMessageDialog(this, "Solicitud de registro de poster enviada.", "Registro Iniciado", JOptionPane.INFORMATION_MESSAGE);
         } catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Error al registrar poster:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
     }

     private void actualizarPosterAction() {
        String idStr = txtPosterId.getText().trim();
        String nombre = txtPosterNombre.getText().trim();
        String precioStr = txtPosterPrecio.getText().trim();
        String stockStr = txtPosterStock.getText().trim();
        String tamano = txtPosterTamano.getText().trim();
        if (idStr.isEmpty()) { JOptionPane.showMessageDialog(this, "ID requerido.", "Error", JOptionPane.WARNING_MESSAGE); return; }
        try {
            controlador.actualizarPoster(idStr, nombre, precioStr, stockStr, tamano);
            JOptionPane.showMessageDialog(this, "Solicitud de actualización de poster enviada.", "Actualización Iniciada", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Error al actualizar poster:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
     }

    // --- Variables miembro para labels/textfields (añadir las que faltan) ---
    private JLabel lblId, lblNombre, lblPrecio, lblStock; // Comunes
    private JLabel lblTalla, lblDorsal, lblNumero; // Camiseta
    private JLabel lblFecha, lblHora, lblUbicacion, lblAsiento, lblPartido; // Entrada
    private JLabel lblTipo, lblTamano; // Juguete/Poster (reusar o separar)

} // Fin clase VentanaProducto