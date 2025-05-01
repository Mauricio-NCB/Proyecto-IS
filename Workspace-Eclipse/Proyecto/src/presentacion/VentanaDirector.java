package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import negocio.dto.TDirector;
import negocio.dto.TDependiente;
//import negocio.exceptions.*;

public class VentanaDirector extends JFrame {

    private static final long serialVersionUID = -3027923627164736994L;

    // --- Componentes de la UI ---
    // Paneles
    private JPanel panelNorte, panelCentro, panelSur;
    private JPanel panelRegistroDirector;
    private JPanel panelAccionesGenerales;
    private JPanel panelEliminarDirector;
    private JPanel panelActualizarDatos; // NUEVO PANEL

    // Labels
    private JLabel lblId, lblNombre, lblSueldo, lblContrasena;
    private JLabel lblIdEliminar;
    private JLabel lblIdActualizar, lblNuevoSueldo, lblNuevaContrasena; // NUEVOS LABELS
    private JLabel lblStatus;

    // Campos de Texto
    private JTextField txtId, txtNombre, txtSueldo;
    private JPasswordField txtContrasena;
    private JTextField txtIdEliminar;
    private JTextField txtIdActualizar, txtNuevoSueldo; // NUEVOS CAMPOS
    private JPasswordField txtNuevaContrasena; // NUEVO CAMPO PASSWORD

    // Botones
    private JButton btnRegistrarDirector;
    private JButton btnListarDirectores;
    private JButton btnEliminarDirector;
    private JButton btnActualizarDatos; // NUEVO BOTON

    // Área de Texto para Salida
    private JTextArea txtAreaOutput;
    private JScrollPane scrollPaneOutput;

    // Referencia al Controlador
    private ControladorDirector controlador;

    public VentanaDirector() {
        super("Panel de Control del Director");
        controlador = ControladorDirector.getInstance();
        initComponents();
        initLayout();
        initListeners();
        redirectSystemStreams();

        setSize(800, 650); // Aumentar tamaño para el nuevo panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Paneles (existentes)
        panelNorte = new JPanel(); // Usaremos BoxLayout vertical para el norte
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelCentro = new JPanel(new BorderLayout());
        panelSur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRegistroDirector = new JPanel(new GridBagLayout());
        panelRegistroDirector.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Director"));
        panelEliminarDirector = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEliminarDirector.setBorder(BorderFactory.createTitledBorder("Eliminar Director"));
        panelAccionesGenerales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAccionesGenerales.setBorder(BorderFactory.createTitledBorder("Acciones Generales"));

        // NUEVO Panel para actualizar datos
        panelActualizarDatos = new JPanel(new GridBagLayout());
        panelActualizarDatos.setBorder(BorderFactory.createTitledBorder("Actualizar Datos Empleado/Director"));

        // Labels (existentes)
        lblId = new JLabel("ID Director:");
        lblNombre = new JLabel("Nombre:");
        lblSueldo = new JLabel("Sueldo:");
        lblContrasena = new JLabel("Contraseña:");
        lblIdEliminar = new JLabel("ID a Eliminar:");
        lblStatus = new JLabel("Listo.");

        // NUEVOS Labels para actualizar
        lblIdActualizar = new JLabel("ID a Actualizar:");
        lblNuevoSueldo = new JLabel("Nuevo Sueldo (opcional):");
        lblNuevaContrasena = new JLabel("Nueva Contraseña (opcional):");

        // Campos de Texto (existentes)
        txtId = new JTextField(10);
        txtNombre = new JTextField(15);
        txtSueldo = new JTextField(8);
        txtContrasena = new JPasswordField(10);
        txtIdEliminar = new JTextField(10);

        // NUEVOS Campos para actualizar
        txtIdActualizar = new JTextField(10);
        txtNuevoSueldo = new JTextField(8);
        txtNuevaContrasena = new JPasswordField(10);

        // Botones (existentes)
        btnRegistrarDirector = new JButton("Registrar Director");
        btnListarDirectores = new JButton("Listar Directores");
        btnEliminarDirector = new JButton("Eliminar Director");

        // NUEVO Botón para actualizar
        btnActualizarDatos = new JButton("Actualizar Datos");

        // Área de Texto y ScrollPane (existentes)
        txtAreaOutput = new JTextArea(15, 70); // Más ancho
        txtAreaOutput.setEditable(false);
        txtAreaOutput.setLineWrap(true);
        txtAreaOutput.setWrapStyleWord(true);
        scrollPaneOutput = new JScrollPane(txtAreaOutput);
        scrollPaneOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void initLayout() {
        setLayout(new BorderLayout(5, 5));

        // --- Panel Norte (con BoxLayout vertical) ---
        // Configurar panel de registro (sin cambios internos)
        GridBagConstraints gbcReg = new GridBagConstraints(); /* ... (como antes) ... */
        gbcReg.insets = new Insets(3, 5, 3, 5); gbcReg.anchor = GridBagConstraints.WEST;
        gbcReg.gridx = 0; gbcReg.gridy = 0; panelRegistroDirector.add(lblId, gbcReg);
        gbcReg.gridx = 1; gbcReg.gridy = 0; panelRegistroDirector.add(txtId, gbcReg);
        gbcReg.gridx = 2; gbcReg.gridy = 0; panelRegistroDirector.add(lblNombre, gbcReg);
        gbcReg.gridx = 3; gbcReg.gridy = 0; panelRegistroDirector.add(txtNombre, gbcReg);
        gbcReg.gridx = 0; gbcReg.gridy = 1; panelRegistroDirector.add(lblSueldo, gbcReg);
        gbcReg.gridx = 1; gbcReg.gridy = 1; panelRegistroDirector.add(txtSueldo, gbcReg);
        gbcReg.gridx = 2; gbcReg.gridy = 1; panelRegistroDirector.add(lblContrasena, gbcReg);
        gbcReg.gridx = 3; gbcReg.gridy = 1; panelRegistroDirector.add(txtContrasena, gbcReg);
        gbcReg.gridx = 0; gbcReg.gridy = 2; gbcReg.gridwidth = 4; gbcReg.anchor = GridBagConstraints.CENTER;
        panelRegistroDirector.add(btnRegistrarDirector, gbcReg);

        // Configurar panel de eliminar (sin cambios internos)
        panelEliminarDirector.add(lblIdEliminar);
        panelEliminarDirector.add(txtIdEliminar);
        panelEliminarDirector.add(btnEliminarDirector);

        // Configurar NUEVO panel de actualizar datos
        GridBagConstraints gbcUpd = new GridBagConstraints();
        gbcUpd.insets = new Insets(3, 5, 3, 5);
        gbcUpd.anchor = GridBagConstraints.WEST;

        // Fila 1
        gbcUpd.gridx = 0; gbcUpd.gridy = 0; panelActualizarDatos.add(lblIdActualizar, gbcUpd);
        gbcUpd.gridx = 1; gbcUpd.gridy = 0; panelActualizarDatos.add(txtIdActualizar, gbcUpd);
        gbcUpd.gridx = 2; gbcUpd.gridy = 0; panelActualizarDatos.add(lblNuevoSueldo, gbcUpd);
        gbcUpd.gridx = 3; gbcUpd.gridy = 0; panelActualizarDatos.add(txtNuevoSueldo, gbcUpd);

        // Fila 2
        gbcUpd.gridx = 0; gbcUpd.gridy = 1; // Vacío
        gbcUpd.gridx = 1; gbcUpd.gridy = 1; // Vacío
        gbcUpd.gridx = 2; gbcUpd.gridy = 1; panelActualizarDatos.add(lblNuevaContrasena, gbcUpd);
        gbcUpd.gridx = 3; gbcUpd.gridy = 1; panelActualizarDatos.add(txtNuevaContrasena, gbcUpd);

         // Fila 3: Botón Actualizar
        gbcUpd.gridx = 0; gbcUpd.gridy = 2; gbcUpd.gridwidth = 4; gbcUpd.anchor = GridBagConstraints.CENTER;
        panelActualizarDatos.add(btnActualizarDatos, gbcUpd);

        // Configurar panel de acciones generales
        panelAccionesGenerales.add(btnListarDirectores);

        // Añadir sub-paneles al panel norte (verticalmente)
        panelNorte.add(panelRegistroDirector);
        panelNorte.add(panelActualizarDatos); // Añadir nuevo panel
        panelNorte.add(panelEliminarDirector);
        panelNorte.add(panelAccionesGenerales);

        // --- Panel Centro ---
        panelCentro.add(scrollPaneOutput, BorderLayout.CENTER);

        // --- Panel Sur ---
        panelSur.add(lblStatus);

        // Añadir paneles principales
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void initListeners() {
        // Listener Registrar Director (con conversión float aquí)
    	btnRegistrarDirector.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            Float sueldo = Float.parseFloat(txtSueldo.getText().trim()); 
            char[] contrasenaChars = txtContrasena.getPassword();
            String contrasena = new String(contrasenaChars);
            java.util.Arrays.fill(contrasenaChars, ' ');
            txtContrasena.setText("");

            if (id.isEmpty() || nombre.isEmpty() || sueldo == null || contrasena.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Todos los campos son requeridos para registrar.\n");
                lblStatus.setText("Error: campos incompletos.");
                return;
            }

            lblStatus.setText("Registrando director...");
            try {
                // *** LLAMAR AL CONTROLADOR CON EL STRING DEL SUELDO ***
                controlador.registrarDirector(id, nombre, sueldo, contrasena);
                // Si llega aquí sin excepción, asumimos que el controlador imprimió éxito
                lblStatus.setText("Proceso de registro finalizado (ver salida).");
                limpiarCamposRegistro(); // Limpiar solo en caso de éxito (o intento sin error grave)
            } catch (Exception ex) { // *** CAPTURAR EXCEPCIONES ***
                // El controlador ya imprime en System.err (que va a txtAreaOutput)
                // Podemos poner un mensaje genérico en el status label
                lblStatus.setText("Error durante el registro (ver salida).");
                // No limpiamos campos si hubo error, para que el usuario vea qué puso
            }
    	});

        // Listener Listar Directores (sin cambios)
        btnListarDirectores.addActionListener(e -> {
            lblStatus.setText("Listando directores...");
            txtAreaOutput.append("\n--- Solicitando listado de directores ---\n");
            try {
                controlador.mostrarTodosLosDirectores();
                lblStatus.setText("Listado de directores mostrado (ver salida).");
            } catch (Exception ex) {
                lblStatus.setText("Error al listar directores (ver salida).");
            }
        });

        // Listener Eliminar Director (sin cambios)
        btnEliminarDirector.addActionListener(e -> {
            String idEliminar = txtIdEliminar.getText().trim();
            if (idEliminar.isEmpty()) {
                txtAreaOutput.append("ERROR UI: Debe ingresar el ID del director a eliminar.\n");
                lblStatus.setText("Error: ID a eliminar vacío."); return;
            }
            int confirm = JOptionPane.showConfirmDialog(VentanaDirector.this,
                    "¿Está seguro de que desea eliminar al director con ID: " + idEliminar + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                lblStatus.setText("Eliminando director...");
                txtAreaOutput.append("\n--- Solicitando eliminación del director ID: " + idEliminar + " ---\n");
                try {
                    controlador.eliminarDirector(idEliminar);
                    lblStatus.setText("Proceso de eliminación finalizado (ver salida).");
                    txtIdEliminar.setText("");
                } catch (Exception ex) {
                    lblStatus.setText("Error durante la eliminación (ver salida).");
                }
            } else {
                lblStatus.setText("Eliminación cancelada.");
                txtAreaOutput.append("--- Eliminación cancelada por el usuario ---\n");
            }
        });

        // --- NUEVO ActionListener para Actualizar Datos ---
        btnActualizarDatos.addActionListener(e -> { // Usando Lambda
            String idActualizar = txtIdActualizar.getText().trim();
            Float nuevoSueldo = null;
            if (!txtNuevoSueldo.getText().trim().isEmpty()) {
            	nuevoSueldo = Float.parseFloat(txtNuevoSueldo.getText().trim());
            }
            char[] nuevaContrasenaChars = txtNuevaContrasena.getPassword();
            String nuevaContrasena = new String(nuevaContrasenaChars);
            java.util.Arrays.fill(nuevaContrasenaChars, ' '); // Limpiar array
            txtNuevaContrasena.setText(""); // Limpiar campo visual

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
                // Llamar al controlador. Este maneja la conversión del sueldo y el paso de nulls si están vacíos.
                controlador.actualizarDatos(idActualizar, nuevoSueldo, nuevaContrasena);
                lblStatus.setText("Proceso de actualización finalizado (ver salida).");
                // Limpiar campos de actualización
                limpiarCamposActualizacion();
            } catch (Exception ex) {
                 lblStatus.setText("Error durante la actualización (ver salida).");
                 // No limpiamos campos en caso de error para que el usuario vea
            }
        });
    }

    // Métodos helper para limpiar campos
    private void limpiarCamposRegistro() {
        txtId.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtContrasena.setText("");
    }
    // NUEVO helper para limpiar campos de actualización
    private void limpiarCamposActualizacion() {
        txtIdActualizar.setText("");
        txtNuevoSueldo.setText("");
        txtNuevaContrasena.setText("");
    }


    // --- Redirección de System.out y System.err (sin cambios) ---
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(() -> {
            txtAreaOutput.append(text);
            txtAreaOutput.setCaretPosition(txtAreaOutput.getDocument().getLength());
        });
    }
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override public void write(int b) throws IOException { updateTextArea(String.valueOf((char) b)); }
            @Override public void write(byte[] b, int off, int len) throws IOException { updateTextArea(new String(b, off, len)); }
            @Override public void write(byte[] b) throws IOException { write(b, 0, b.length); }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
        System.out.println("--- Salida de consola redirigida a la ventana ---");
    }

    // --- Método Main (sin cambios) ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaDirector ventana = new VentanaDirector();
            ventana.setVisible(true);
        });
    }
}