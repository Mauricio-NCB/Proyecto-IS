package presentacion;

import javax.swing.*;

import negocio.dto.TEmpleado;
import negocio.dto.TProducto;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

// Importar Excepciones 
// import negocio.exceptions.*;

public class VentanaEmpleado extends JFrame {

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

    // ElecciÃ³n registro
    private JRadioButton rbDirector;
    private JRadioButton rbDependiente;
    private ButtonGroup bgTipoEmpleado;

    // Botones
    private JButton btnRegistrarDirector;
    private JButton btnListarDirectores;
    private JButton btnListarEmpleados;
    private JButton btnEliminarEmpleado;
    private JButton btnActualizarDatos;
    private JButton btnListarClientes;  
    private JButton btnCerrarSesion; 
    private JButton btnMostrarCatalogo;      
    private JButton btnGestionarProductos; 
      
    // Ã�rea de Texto para Salida
    private JTextArea txtAreaOutput;
    private JScrollPane scrollPaneOutput;
    private ControladorEmpleado controladorEm;

    // Constructor
    public VentanaEmpleado() {
        super("Panel de Control del empleado");
        controladorEm = ControladorEmpleado.getInstance();
        initComponents();
        initLayout();
        initListeners();
        redirectSystemStreams();

        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // --- InicializaciÃ³n de Componentes ---
    private void initComponents() {
        // Paneles
        panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS)); // Layout vertical
        panelCentro = new JPanel(new BorderLayout());
        panelSur = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelRegistroDirector = new JPanel(new GridBagLayout());
        panelRegistroDirector.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Director/Dependiente"));
        panelActualizarDatos = new JPanel(new GridBagLayout());
        panelActualizarDatos.setBorder(BorderFactory.createTitledBorder("Actualizar Datos Director"));
        panelEliminarDirector = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEliminarDirector.setBorder(BorderFactory.createTitledBorder("Eliminar empleado"));
        panelAccionesGenerales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAccionesGenerales.setBorder(BorderFactory.createTitledBorder("Acciones Generales"));

        // Labels
        lblId = new JLabel("ID Empleado:");
        lblNombre = new JLabel("Nombre:");
        lblSueldo = new JLabel("Sueldo:");
        lblContrasena = new JLabel("ContraseÃ±a:");
        lblIdEliminar = new JLabel("ID a Eliminar:");
        lblIdActualizar = new JLabel("ID a Actualizar:");
        lblNuevoSueldo = new JLabel("Nuevo Sueldo (opcional):");
        lblNuevaContrasena = new JLabel("Nueva ContraseÃ±a (opcional):");
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
        
        // SelecciÃ³n registro usuario
        rbDirector = new JRadioButton("Director", true); // Seleccionado por defecto
        rbDependiente = new JRadioButton("Dependiente");
        bgTipoEmpleado = new ButtonGroup();
        bgTipoEmpleado.add(rbDirector);
        bgTipoEmpleado.add(rbDependiente);

        btnRegistrarDirector = new JButton("Registrar Empleado");
        btnListarDirectores = new JButton("Listar Empleado");
        btnListarEmpleados = new JButton("Listar Empleados");
        btnEliminarEmpleado = new JButton("Eliminar Empleado");
        btnActualizarDatos = new JButton("Actualizar Datos");
        btnListarClientes = new JButton("Listar Clientes"); 
        btnCerrarSesion = new JButton("Cerrar SesiÃ³n"); 
        btnMostrarCatalogo = new JButton("Mostrar CatÃ¡logo");  
        btnGestionarProductos = new JButton("Gestionar Productos"); 
        btnGestionarProductos = new JButton("Gestionar Productos"); 

        // Ã�rea de Texto y ScrollPane
        txtAreaOutput = new JTextArea(18, 75); 
        txtAreaOutput.setEditable(false);
        txtAreaOutput.setLineWrap(true);
        txtAreaOutput.setWrapStyleWord(true);
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 12); 
        txtAreaOutput.setFont(consoleFont);
        scrollPaneOutput = new JScrollPane(txtAreaOutput);
        scrollPaneOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // --- ConfiguraciÃ³n del Layout ---
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
        panelEliminarDirector.add(btnEliminarEmpleado);
        panelEliminarDirector.setAlignmentX(Component.LEFT_ALIGNMENT); 
        panelAccionesGenerales.add(btnListarDirectores);
        panelAccionesGenerales.add(btnListarEmpleados);
        panelAccionesGenerales.add(btnListarClientes); 
        panelAccionesGenerales.add(btnMostrarCatalogo); 
        panelAccionesGenerales.add(btnGestionarProductos);
        panelAccionesGenerales.add(btnCerrarSesion);    
        panelAccionesGenerales.setAlignmentX(Component.LEFT_ALIGNMENT); 
        panelAccionesGenerales.add(btnCerrarSesion);    
        panelAccionesGenerales.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // AÃ±adir sub-paneles al panel norte (verticalmente)
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

    // --- InicializaciÃ³n de Listeners --- (Se ha usado Lambda)
    private void initListeners() {
        // --- Registrar Director/Dependiente ---
        btnRegistrarDirector.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            char[] contrasenaChars = txtContrasena.getPassword();
            String contrasena = new String(contrasenaChars);
            java.util.Arrays.fill(contrasenaChars, ' ');
            txtContrasena.setText("");

            if (id.isEmpty() || nombre.isEmpty() || txtSueldo.getText().trim().isEmpty() || contrasena.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Todos los campos son requeridos para registrar.\n");
                lblStatus.setText("Error: campos incompletos.");
                return;
            }
            
            Float sueldo;

            try {
            	sueldo = Float.parseFloat(txtSueldo.getText().trim()); 
            } catch (NumberFormatException ex) {
                txtAreaOutput.append("ERROR UI: El sueldo debe ser un número válido.\n");
                lblStatus.setText("Error: sueldo no válido.");
                return;
            }
            
            String tipoEmpleado;
            
            if (rbDirector.isSelected()) {
                tipoEmpleado = "DIRECTOR";
            } else if (rbDependiente.isSelected()) {
                tipoEmpleado = "DEPENDIENTE";
            } else {
                appendOutput("ERROR UI: Debe seleccionar un tipo de empleado (Director o Dependiente).\n");
                updateStatus("Error: seleccione tipo.");
                return;
            }

            lblStatus.setText("Registrando empleado...");
            try {
                controladorEm.registrarEmpleado(id, nombre, sueldo, contrasena, tipoEmpleado);
                lblStatus.setText("Proceso de registro finalizado");
                limpiarCamposRegistro(); 
            } catch (Exception ex) { 
                lblStatus.setText("Error durante el registro: " + ex.getMessage());
            }
    	});

        // --- Listar Empleados ---
        btnListarEmpleados.addActionListener(e -> {
            updateStatus("Listando empleados...");
            appendOutput("\n--- Listado de empleados ---\n");
            try {
                List<TEmpleado> empleados = controladorEm.mostrarEmpleados();
                if (empleados.isEmpty()) {
                    appendOutput("No hay empleados registrados.\n");
                } else {
                    for (TEmpleado empleado : empleados) {
                        appendOutput(empleado.toString() + "\n");
                    }
                }
                updateStatus("Listado de empleados mostrado.");
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
                //controlador.mostrarTodosLosClientes(); 
                updateStatus("Listado de clientes mostrado (ver salida).");
            } catch (Exception ex) {
                updateStatus("Error al listar clientes (ver salida).");
                appendOutput("ERROR: " + ex.getMessage() + "\n");
            }
        });

        // --- Eliminar Empleado ---
        btnEliminarEmpleado.addActionListener(e -> {
            String idEliminar = txtIdEliminar.getText().trim();
            if (idEliminar.isEmpty()) {
                appendOutput("ERROR UI: Debe ingresar el ID del empleado a eliminar.\n");
                updateStatus("Error: ID a eliminar vacio."); return;
            }
            int confirm = JOptionPane.showConfirmDialog(VentanaEmpleado.this,
                    "¿Está seguro de que desea eliminar al director con ID: " + idEliminar + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                updateStatus("Eliminando empleado...");
                appendOutput("\n--- Solicitando eliminación del empleado ID: " + idEliminar + " ---\n");
                try {
                    if (controladorEm.eliminarEmpleado(idEliminar)) {
                        appendOutput("¡Empleado con ID: " + idEliminar + " eliminado correctamente!");
                    }
                        
                } catch (Exception ex) {
                    updateStatus("Error durante la eliminación (ver salida).");
                    appendOutput("ERROR: " + ex.getMessage() + "\n");
                }
                finally{
                    updateStatus("Proceso de eliminación finalizado (ver salida).");
                    txtIdEliminar.setText("");
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

            // ValidaciÃ³n bÃ¡sica ID
            if (idActualizar.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Debe ingresar el ID del empleado a actualizar.\n");
                lblStatus.setText("Error: ID a actualizar vacio.");
                return;
            }

            // Verificar si se proporcionÃ³ al menos un dato para actualizar
            if (nuevoSueldo == null && nuevaContrasena.isEmpty()) {
                 txtAreaOutput.append("INFO UI: No se ingresó nuevo sueldo ni nueva contraseña para actualizar.\n");
                 lblStatus.setText("Nada para actualizar.");
                 return;
            }

            lblStatus.setText("Actualizando datos...");
            txtAreaOutput.append("\n--- Solicitando actualización de datos para ID: " + idActualizar + " ---\n");
            try {
                if (controladorEm.actualizarDatos(idActualizar, nuevoSueldo, nuevaContrasena)){
                    appendOutput("¡Empleado con ID: " + idActualizar + " eliminado correctamente!");
                }
                ;
                lblStatus.setText("Proceso de actualización finalizado (ver salida).");
                limpiarCamposActualizacion();
            } catch (Exception ex) {
                 lblStatus.setText("Error durante la actualización (ver salida).");
            }
        });

        // --- Cerrar SesiÃ³n ---
        btnCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(VentanaEmpleado.this,
                    "Â¿EstÃ¡ seguro de que desea cerrar la sesiÃ³n?",
                    "Confirmar Cierre de SesiÃ³n", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                appendOutput("\n--- Cerrando sesiÃ³n ---\n");
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
            appendOutput("\n--- Abriendo ventana de gestiÃ³n de productos ---\n");
            updateStatus("Abriendo gestiÃ³n de productos...");
            // Crear y mostrar la nueva ventana, pasando el controlador
            SwingUtilities.invokeLater(() -> {
                // Necesitas crear la clase VentanaProducto
                new VentanaProducto().setVisible(true);
            });
        });

    }

    // --- MÃ©todos para limpiar campos ---
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


    // --- RedirecciÃ³n de System.out y System.err ---
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            // Usa appendOutput para asegurar que la actualizaciÃ³n se haga en el EDT
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