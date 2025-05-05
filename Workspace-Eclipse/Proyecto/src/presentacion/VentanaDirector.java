package presentacion;

import javax.swing.*;

import negocio.dto.TProducto;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

// Importar Excepciones 
// import negocio.exceptions.*;

public class VentanaDirector extends JFrame {

    private static final long serialVersionUID = -3027923627164736994L;

    // --- Componentes de la UI ---
    // Paneles
    private JPanel panelNorte, panelCentro, panelSur;
    private JPanel panelRegistroDirector;
    private JPanel panelAccionesGenerales;
    private JPanel panelEliminarDirector;
    private JPanel panelActualizarDatos;

    // Labels
    private JLabel lblId, lblNombre, lblSueldo, lblContrasena;
    private JLabel lblIdEliminar;
    private JLabel lblIdActualizar, lblNuevoSueldo, lblNuevaContrasena;
    private JLabel lblStatus;

    // Campos de Texto
    private JTextField txtId, txtNombre, txtSueldo;
    private JPasswordField txtContrasena;
    private JTextField txtIdEliminar;
    private JTextField txtIdActualizar, txtNuevoSueldo;
    private JPasswordField txtNuevaContrasena;

    // Elección registro
    private JRadioButton rbDirector;
    private JRadioButton rbDependiente;
    private ButtonGroup bgTipoEmpleado;

    // Botones
    private JButton btnRegistrarDirector;
    private JButton btnListarDirectores;
    private JButton btnEliminarDirector;
    private JButton btnEliminarDependiente;
    private JButton btnActualizarDatos;
    private JButton btnListarClientes;  
    private JButton btnCerrarSesion; 
    private JButton btnMostrarCatalogo;      
    private JButton btnGestionarProductos; 
      
    // Área de Texto para Salida
    private JTextArea txtAreaOutput;
    private JScrollPane scrollPaneOutput;

    private ControladorDirector controlador;
    private ControladorDependiente controladorDep;
    private ControladorEmpleado controladorEm;

    // Constructor
    public VentanaDirector() {
        super("Panel de Control del Director");

        controlador = ControladorDirector.getInstance();
        controladorEm = ControladorEmpleado.getInstance();
        controladorDep = ControladorDependiente.getInstance();
        initComponents();
        initLayout();
        initListeners();
        redirectSystemStreams();

        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // --- Inicialización de Componentes ---
    private void initComponents() {
        // Paneles
        panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS)); // Layout vertical
        panelCentro = new JPanel(new BorderLayout());
        panelSur = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelRegistroDirector = new JPanel(new GridBagLayout());
        panelRegistroDirector.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Director/Dependiente"));
        panelActualizarDatos = new JPanel(new GridBagLayout());
        panelActualizarDatos.setBorder(BorderFactory.createTitledBorder("Actualizar Datos Empleado/Director"));
        panelEliminarDirector = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEliminarDirector.setBorder(BorderFactory.createTitledBorder("Eliminar Director"));
        panelAccionesGenerales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAccionesGenerales.setBorder(BorderFactory.createTitledBorder("Acciones Generales"));

        // Labels
        lblId = new JLabel("ID Director:");
        lblNombre = new JLabel("Nombre:");
        lblSueldo = new JLabel("Sueldo:");
        lblContrasena = new JLabel("Contraseña:");
        lblIdEliminar = new JLabel("ID a Eliminar:");
        lblIdActualizar = new JLabel("ID a Actualizar:");
        lblNuevoSueldo = new JLabel("Nuevo Sueldo (opcional):");
        lblNuevaContrasena = new JLabel("Nueva Contraseña (opcional):");
        lblStatus = new JLabel("Listo.");

        // Campos de Texto
        txtId = new JTextField(10);
        txtNombre = new JTextField(15);
        txtSueldo = new JTextField(8);
        txtContrasena = new JPasswordField(10);
        txtIdEliminar = new JTextField(10);
        txtIdActualizar = new JTextField(10);
        txtNuevoSueldo = new JTextField(8);
        txtNuevaContrasena = new JPasswordField(10);
        
        // Selección registro usuario
        rbDirector = new JRadioButton("Director", true); // Seleccionado por defecto
        rbDependiente = new JRadioButton("Dependiente");
        bgTipoEmpleado = new ButtonGroup();
        bgTipoEmpleado.add(rbDirector);
        bgTipoEmpleado.add(rbDependiente);

        // Botones
        btnRegistrarDirector = new JButton("Registrar Empleado");
        btnListarDirectores = new JButton("Listar Empleado");
        btnEliminarDirector = new JButton("Eliminar Director");
        btnEliminarDependiente = new JButton("Eliminar Dependiente");
        btnActualizarDatos = new JButton("Actualizar Datos");
        btnListarClientes = new JButton("Listar Clientes"); 
        btnCerrarSesion = new JButton("Cerrar Sesión"); 
        btnMostrarCatalogo = new JButton("Mostrar Catálogo");  
        btnGestionarProductos = new JButton("Gestionar Productos"); 

        // Área de Texto y ScrollPane
        txtAreaOutput = new JTextArea(18, 75); 
        txtAreaOutput.setEditable(false);
        txtAreaOutput.setLineWrap(true);
        txtAreaOutput.setWrapStyleWord(true);
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 12); 
        txtAreaOutput.setFont(consoleFont);
        scrollPaneOutput = new JScrollPane(txtAreaOutput);
        scrollPaneOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // --- Configuración del Layout ---
    private void initLayout() {
        setLayout(new BorderLayout(5, 5)); // Layout principal

        // --- Panel Norte (vertical) ---
        // Configurar panel de registro
        GridBagConstraints gbcReg = new GridBagConstraints();
        gbcReg.insets = new Insets(3, 5, 4, 5); gbcReg.anchor = GridBagConstraints.WEST;
        // Panel para botones
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelTipo.add(new JLabel("Tipo Empleado:"));
        panelTipo.add(rbDirector);
        panelTipo.add(rbDependiente);
        gbcReg.gridx = 0; gbcReg.gridy = 0; gbcReg.gridwidth = 4; gbcReg.fill = GridBagConstraints.HORIZONTAL; 
        gbcReg.anchor = GridBagConstraints.LINE_START;
        panelRegistroDirector.add(panelTipo, gbcReg); 
        gbcReg.gridwidth = 1; gbcReg.fill = GridBagConstraints.NONE; gbcReg.anchor = GridBagConstraints.WEST; 
        gbcReg.gridx = 0; gbcReg.gridy = 1; panelRegistroDirector.add(lblId, gbcReg);
        gbcReg.gridx = 1; gbcReg.gridy = 1; panelRegistroDirector.add(txtId, gbcReg);
        gbcReg.gridx = 2; gbcReg.gridy = 1; panelRegistroDirector.add(lblNombre, gbcReg);
        gbcReg.gridx = 3; gbcReg.gridy = 1; panelRegistroDirector.add(txtNombre, gbcReg);
        gbcReg.gridx = 0; gbcReg.gridy = 2; panelRegistroDirector.add(lblSueldo, gbcReg);
        gbcReg.gridx = 1; gbcReg.gridy = 2; panelRegistroDirector.add(txtSueldo, gbcReg);
        gbcReg.gridx = 2; gbcReg.gridy = 2; panelRegistroDirector.add(lblContrasena, gbcReg);
        gbcReg.gridx = 3; gbcReg.gridy = 2; panelRegistroDirector.add(txtContrasena, gbcReg);
        gbcReg.gridx = 0; gbcReg.gridy = 3; gbcReg.gridwidth = 4; gbcReg.anchor = GridBagConstraints.CENTER;
        panelRegistroDirector.add(btnRegistrarDirector, gbcReg);
        panelRegistroDirector.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear panel

        // Configurar panel de actualizar datos
        GridBagConstraints gbcUpd = new GridBagConstraints();
        gbcUpd.insets = new Insets(3, 5, 3, 5); gbcUpd.anchor = GridBagConstraints.WEST;
        gbcUpd.gridx = 0; gbcUpd.gridy = 0; panelActualizarDatos.add(lblIdActualizar, gbcUpd);
        gbcUpd.gridx = 1; gbcUpd.gridy = 0; panelActualizarDatos.add(txtIdActualizar, gbcUpd);
        gbcUpd.gridx = 2; gbcUpd.gridy = 0; panelActualizarDatos.add(lblNuevoSueldo, gbcUpd);
        gbcUpd.gridx = 3; gbcUpd.gridy = 0; panelActualizarDatos.add(txtNuevoSueldo, gbcUpd);
        gbcUpd.gridx = 0; gbcUpd.gridy = 1; gbcUpd.gridx = 1; gbcUpd.gridy = 1;
        gbcUpd.gridx = 2; gbcUpd.gridy = 1; panelActualizarDatos.add(lblNuevaContrasena, gbcUpd);
        gbcUpd.gridx = 3; gbcUpd.gridy = 1; panelActualizarDatos.add(txtNuevaContrasena, gbcUpd);
        gbcUpd.gridx = 0; gbcUpd.gridy = 2; gbcUpd.gridwidth = 4; gbcUpd.anchor = GridBagConstraints.CENTER;
        panelActualizarDatos.add(btnActualizarDatos, gbcUpd);
        panelActualizarDatos.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Configurar panel de eliminar
        panelEliminarDirector.add(lblIdEliminar);
        panelEliminarDirector.add(txtIdEliminar);
        panelEliminarDirector.add(btnEliminarDirector);
        panelEliminarDirector.add(btnEliminarDependiente);
        panelEliminarDirector.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Configurar panel de acciones generales
        panelAccionesGenerales.add(btnListarDirectores);
        panelAccionesGenerales.add(btnListarClientes); 
        panelAccionesGenerales.add(btnMostrarCatalogo); 
        panelAccionesGenerales.add(btnGestionarProductos);
        panelAccionesGenerales.add(btnCerrarSesion);    
        panelAccionesGenerales.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Añadir sub-paneles al panel norte (verticalmente)
        panelNorte.add(panelRegistroDirector);
        panelNorte.add(panelActualizarDatos);
        panelNorte.add(panelEliminarDirector);
        panelNorte.add(panelAccionesGenerales);

        // --- Panel Centro ---
        panelCentro.add(scrollPaneOutput, BorderLayout.CENTER);

        // --- Panel Sur ---
        panelSur.add(lblStatus);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    // --- Inicialización de Listeners --- (Se ha usado Lambda)
    private void initListeners() {
        // --- Registrar Director/Dependiente ---
        btnRegistrarDirector.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            Float sueldo = Float.parseFloat(txtSueldo.getText().trim()); 
            char[] contrasenaChars = txtContrasena.getPassword();
            String contrasena = new String(contrasenaChars);
            java.util.Arrays.fill(contrasenaChars, ' ');
            txtContrasena.setText("");

            String tipoEmpleado;
            if (rbDirector.isSelected()) {
                tipoEmpleado = "DIRECTOR";
            } else if (rbDependiente.isSelected()) {
                tipoEmpleado = "DEPENDIENTE";
            } else {
                // Caso improbable si ambos están en un ButtonGroup, pero por seguridad
                appendOutput("ERROR UI: Debe seleccionar un tipo de empleado (Director o Dependiente).\n");
                updateStatus("Error: seleccione tipo.");
                return;
            }

            if (id.isEmpty() || nombre.isEmpty() || sueldo == null || contrasena.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Todos los campos son requeridos para registrar.\n");
                lblStatus.setText("Error: campos incompletos.");
                return;
            }

            lblStatus.setText("Registrando empleado...");
            try {
                controladorEm.registrarEmpleado(id, nombre, sueldo, contrasena, tipoEmpleado);
                lblStatus.setText("Proceso de registro finalizado (ver salida).");
                limpiarCamposRegistro(); 
            } catch (Exception ex) { 
                lblStatus.setText("Error durante el registro (ver salida).");
            }
    	});

        // --- Listar Directores ---
        btnListarDirectores.addActionListener(e -> {
            updateStatus("Listando empleados...");
            appendOutput("\n--- Solicitando listado de empleados ---\n");
            try {
                controlador.mostrarTodosLosDirectores();
                controladorDep.mostrarTodosLosDependientes();
                updateStatus("Listado de empleados mostrado (ver salida).");
            } catch (Exception ex) {
                updateStatus("Error al listar empleados (ver salida).");
                appendOutput("ERROR: " + ex.getMessage() + "\n"); 
            }
        });

         // --- Listar Clientes ---
        btnListarClientes.addActionListener(e -> {
            updateStatus("Listando clientes...");
            appendOutput("\n--- Solicitando listado de clientes ---\n");
            try {
                controlador.mostrarTodosLosClientes(); 
                updateStatus("Listado de clientes mostrado (ver salida).");
            } catch (Exception ex) {
                updateStatus("Error al listar clientes (ver salida).");
                appendOutput("ERROR: " + ex.getMessage() + "\n");
            }
        });

        // --- Eliminar Director ---
        btnEliminarDirector.addActionListener(e -> {
            String idEliminar = txtIdEliminar.getText().trim();
            if (idEliminar.isEmpty()) {
                appendOutput("ERROR UI: Debe ingresar el ID del director a eliminar.\n");
                updateStatus("Error: ID a eliminar vacío."); return;
            }
            int confirm = JOptionPane.showConfirmDialog(VentanaDirector.this,
                    "¿Está seguro de que desea eliminar al director con ID: " + idEliminar + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                updateStatus("Eliminando director...");
                appendOutput("\n--- Solicitando eliminación del director ID: " + idEliminar + " ---\n");
                try {
                    controlador.eliminarDirector(idEliminar); 
                    updateStatus("Proceso de eliminación finalizado (ver salida).");
                    txtIdEliminar.setText("");
                } catch (Exception ex) {
                    updateStatus("Error durante la eliminación (ver salida).");
                    appendOutput("ERROR: " + ex.getMessage() + "\n");
                }
            } else {
                updateStatus("Eliminación cancelada.");
                appendOutput("--- Eliminación cancelada por el usuario ---\n");
            }
        });

        // --- Eliminar Dependiente ---
        btnEliminarDependiente.addActionListener(e -> {
            String idEliminar = txtIdEliminar.getText().trim();
            if (idEliminar.isEmpty()) {
                appendOutput("ERROR UI: Debe ingresar el ID del dependiente a eliminar.\n");
                updateStatus("Error: ID a eliminar vacío."); return;
            }
            int confirm = JOptionPane.showConfirmDialog(VentanaDirector.this,
                    "¿Está seguro de que desea eliminar al dependiente con ID: " + idEliminar + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                updateStatus("Eliminando dependiente...");
                appendOutput("\n--- Solicitando eliminación del dependiente ID: " + idEliminar + " ---\n");
                try {
                    controladorDep.eliminarDependiente(idEliminar); 
                    updateStatus("Proceso de eliminación finalizado (ver salida).");
                    txtIdEliminar.setText("");
                } catch (Exception ex) {
                    updateStatus("Error durante la eliminación (ver salida).");
                    appendOutput("ERROR: " + ex.getMessage() + "\n");
                }
            } else {
                updateStatus("Eliminación cancelada.");
                appendOutput("--- Eliminación cancelada por el usuario ---\n");
            }
        });

        // --- Actualizar Datos ---
        btnActualizarDatos.addActionListener(e -> { 
            String idActualizar = txtIdActualizar.getText().trim();
            Float nuevoSueldo = null;
            if (!txtNuevoSueldo.getText().trim().isEmpty()) {
            	nuevoSueldo = Float.parseFloat(txtNuevoSueldo.getText().trim());
            }
            char[] nuevaContrasenaChars = txtNuevaContrasena.getPassword();
            String nuevaContrasena = new String(nuevaContrasenaChars);
            java.util.Arrays.fill(nuevaContrasenaChars, ' '); 
            txtNuevaContrasena.setText(""); 

            // Validación básica ID
            if (idActualizar.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Debe ingresar el ID del empleado/director a actualizar.\n");
                lblStatus.setText("Error: ID a actualizar vacío.");
                return;
            }

            // Verificar si se proporcionó al menos un dato para actualizar
            if (nuevoSueldo == null && nuevaContrasena.isEmpty()) {
                 txtAreaOutput.append("INFO UI: No se ingresó nuevo sueldo ni nueva contraseña para actualizar.\n");
                 lblStatus.setText("Nada para actualizar.");
                 return;
            }

            lblStatus.setText("Actualizando datos...");
            txtAreaOutput.append("\n--- Solicitando actualización de datos para ID: " + idActualizar + " ---\n");
            try {
                controlador.actualizarDatos(idActualizar, nuevoSueldo, nuevaContrasena);
                lblStatus.setText("Proceso de actualización finalizado (ver salida).");
                limpiarCamposActualizacion();
            } catch (Exception ex) {
                 lblStatus.setText("Error durante la actualización (ver salida).");
            }
        });

        // --- Cerrar Sesión ---
        btnCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(VentanaDirector.this,
                    "¿Está seguro de que desea cerrar la sesión?",
                    "Confirmar Cierre de Sesión", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                appendOutput("\n--- Cerrando sesión ---\n");
                this.dispose(); // Cierra la ventana Director

                // Crea la ventana Login
                System.out.println("Mostrando nueva ventana de login...");
                SwingUtilities.invokeLater(() -> {
                    new VentanaLogin();
                });
            }
        });

        // --- Mostrar Productos ---
        btnMostrarCatalogo.addActionListener(e -> {
			List<TProducto> listaProductos = ControladorProducto.getInstance().listarProductos();
			new VentanaCatalogo(listaProductos).setVisible(true);
        });

        btnGestionarProductos.addActionListener(e -> {
            appendOutput("\n--- Abriendo ventana de gestión de productos ---\n");
            updateStatus("Abriendo gestión de productos...");
            // Crear y mostrar la nueva ventana, pasando el controlador
            SwingUtilities.invokeLater(() -> {
                // Necesitas crear la clase VentanaProducto
                new VentanaProducto().setVisible(true);
            });
        });

    }

    // --- Métodos para limpiar campos ---
    private void limpiarCamposRegistro() {
        txtId.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtContrasena.setText("");
    }
    private void limpiarCamposActualizacion() {
        txtIdActualizar.setText("");
        txtNuevoSueldo.setText("");
        txtNuevaContrasena.setText("");
    }

    // Actualiza el JTextArea 
    private void appendOutput(final String text) {
        SwingUtilities.invokeLater(() -> {
            txtAreaOutput.append(text);
            txtAreaOutput.setCaretPosition(txtAreaOutput.getDocument().getLength());
        });
    }

    // Actualiza la barra de estado 
    private void updateStatus(final String text) {
        SwingUtilities.invokeLater(() -> lblStatus.setText(text));
    }


    // --- Redirección de System.out y System.err ---
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            // Usa appendOutput para asegurar que la actualización se haga en el EDT
            @Override public void write(int b) throws IOException { appendOutput(String.valueOf((char) b)); }
            @Override public void write(byte[] b, int off, int len) throws IOException { appendOutput(new String(b, off, len)); }
            @Override public void write(byte[] b) throws IOException { write(b, 0, b.length); }
        };
        // Crear PrintStream que usa nuestro OutputStream personalizado
        PrintStream consoleStream = new PrintStream(out, true); 
        System.setOut(consoleStream);
        System.setErr(consoleStream);
        System.out.println("--- Salida de consola redirigida a la ventana ---"); // Mensaje inicial
    }
} 