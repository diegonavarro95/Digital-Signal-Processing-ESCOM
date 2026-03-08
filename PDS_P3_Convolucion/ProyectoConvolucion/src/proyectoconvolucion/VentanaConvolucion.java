package proyectoconvolucion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ItemEvent;

public class VentanaConvolucion extends JFrame {
    
    private JTextField txtF, txtG;
    private JTextField txtMinF, txtMaxF, txtMinG, txtMaxG;
    private JCheckBox chkLimF, chkLimG;
    private JComboBox<String> comboEjemplos;
    private JButton btnCalcular;

    public VentanaConvolucion() {
        setTitle("Práctica 03. Motor de Convolución Analítica");
        setSize(700, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- PANEL SUPERIOR ---
        JPanel panelTop = new JPanel(new BorderLayout(5, 10));
        panelTop.setBackground(Color.WHITE);
        panelTop.setBorder(new EmptyBorder(15, 20, 5, 20));

        // Sub-panel de Título y el Botón de Ayuda
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Calculadora de Convolución", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(242, 114, 0));
        
        // --- BOTÓN DE AYUDA ---
        JButton btnAyuda = new JButton("?");
        btnAyuda.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAyuda.setBackground(new Color(70, 130, 180));
        btnAyuda.setForeground(Color.WHITE);
        btnAyuda.setUI(new BasicButtonUI());
        btnAyuda.setFocusPainted(false);
        btnAyuda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAyuda.setPreferredSize(new Dimension(35, 35));
        btnAyuda.setBorder(new LineBorder(new Color(50, 100, 150), 2, true));
        btnAyuda.setToolTipText("Guía de sintaxis para las funciones");
        
        btnAyuda.addActionListener(e -> mostrarAyuda());

        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        panelTitulo.add(btnAyuda, BorderLayout.EAST);
        
        panelTop.add(panelTitulo, BorderLayout.NORTH);

        // Opciones sugeridas
        String[] ejemplos = {
            "Personalizado...",
            "Escalón * Escalón",
            "Pulso Rectangular * Pulso Rectangular",
            "Exponencial Decreciente * Pulso Rectangular",
            "Seno Truncado (Medio ciclo) * Pulso",
            "Señal Bipolar",
            "Doble Exponencial"
        };
        comboEjemplos = new JComboBox<>(ejemplos);
        comboEjemplos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboEjemplos.setBackground(Color.WHITE);
        comboEjemplos.addActionListener(e -> cargarEjemplo());
        
        JPanel panelCombo = new JPanel(new BorderLayout(10, 0));
        panelCombo.setBackground(Color.WHITE);
        panelCombo.add(new JLabel("Ejemplos comunes:"), BorderLayout.WEST);
        panelCombo.add(comboEjemplos, BorderLayout.CENTER);
        panelTop.add(panelCombo, BorderLayout.SOUTH);

        add(panelTop, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Entradas y Límites) ---
        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        Font fontMath = new Font("Serif", Font.ITALIC, 22);
        Font fontInput = new Font("Monospaced", Font.PLAIN, 16);
        LineBorder borderCaja = new LineBorder(new Color(200, 200, 200), 1, true);

        // ---- FUNCIÓN F(t) ----
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.05;
        JLabel lblF = new JLabel("f(t) = ");
        lblF.setFont(fontMath);
        panelCentro.add(lblF, gbc);

        gbc.gridx = 1; gbc.weightx = 0.95;
        txtF = new JTextField();
        txtF.setFont(fontInput);
        txtF.setBorder(BorderFactory.createCompoundBorder(borderCaja, new EmptyBorder(5, 5, 5, 5)));
        panelCentro.add(txtF, gbc);

        // Límites F(t)
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.95;
        panelCentro.add(crearPanelLimites(true), gbc);

        // ---- FUNCIÓN G(t) ----
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.05;
        JLabel lblG = new JLabel("g(t) = ");
        lblG.setFont(fontMath);
        panelCentro.add(lblG, gbc);

        gbc.gridx = 1; gbc.weightx = 0.95;
        txtG = new JTextField();
        txtG.setFont(fontInput);
        txtG.setBorder(BorderFactory.createCompoundBorder(borderCaja, new EmptyBorder(5, 5, 5, 5)));
        panelCentro.add(txtG, gbc);

        // Límites G(t)
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.95;
        panelCentro.add(crearPanelLimites(false), gbc);

        add(panelCentro, BorderLayout.CENTER);

        // --- PANEL INFERIOR (Botón) ---
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setBorder(new EmptyBorder(5, 20, 20, 20));

        btnCalcular = new JButton("Calcular y Visualizar");
        btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCalcular.setBackground(new Color(242, 114, 0));
        btnCalcular.setForeground(Color.WHITE);
        
        btnCalcular.setUI(new BasicButtonUI()); 
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 90, 0), 2, true),
                new EmptyBorder(10, 30, 10, 30)));
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCalcular.addActionListener(e -> ejecutarMatlabConCarga());
        panelBottom.add(btnCalcular);

        add(panelBottom, BorderLayout.SOUTH);
        
        comboEjemplos.setSelectedIndex(2); 
    }

    private JPanel crearPanelLimites(boolean isF) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        JCheckBox chk = new JCheckBox("Restringir intervalo:");
        chk.setBackground(Color.WHITE);
        chk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JTextField txtMin = new JTextField(4);
        JTextField txtMax = new JTextField(4);
        txtMin.setEnabled(false);
        txtMax.setEnabled(false);
        txtMin.setHorizontalAlignment(JTextField.CENTER);
        txtMax.setHorizontalAlignment(JTextField.CENTER);
        
        chk.addItemListener(e -> {
            boolean selected = e.getStateChange() == ItemEvent.SELECTED;
            txtMin.setEnabled(selected);
            txtMax.setEnabled(selected);
        });

        panel.add(chk);
        panel.add(new JLabel(" [ "));
        panel.add(txtMin);
        panel.add(new JLabel(" , "));
        panel.add(txtMax);
        panel.add(new JLabel(" ] "));

        if(isF) {
            chkLimF = chk; txtMinF = txtMin; txtMaxF = txtMax;
        } else {
            chkLimG = chk; txtMinG = txtMin; txtMaxG = txtMax;
        }
        return panel;
    }

    private void limpiarLimites() {
        chkLimF.setSelected(false);
        chkLimG.setSelected(false);
        txtMinF.setText(""); txtMaxF.setText("");
        txtMinG.setText(""); txtMaxG.setText("");
    }

    private void cargarEjemplo() {
        limpiarLimites();
        int index = comboEjemplos.getSelectedIndex();
        switch (index) {
            case 1: // Escalón * Escalón
                txtF.setText("heaviside(t)");
                txtG.setText("heaviside(t)");
                break;
            case 2: // Pulso * Pulso
                txtF.setText("1");
                chkLimF.setSelected(true); txtMinF.setText("0"); txtMaxF.setText("2");
                txtG.setText("1");
                chkLimG.setSelected(true); txtMinG.setText("0"); txtMaxG.setText("2");
                break;
            case 3: // Exponencial * Pulso
                txtF.setText("exp(-0.5*t)");
                chkLimF.setSelected(true); txtMinF.setText("0"); txtMaxF.setText("10");
                txtG.setText("1");
                chkLimG.setSelected(true); txtMinG.setText("-1"); txtMaxG.setText("1");
                break;
            case 4: // Seno Truncado * Pulso
                txtF.setText("sin(pi*t)");
                chkLimF.setSelected(true); txtMinF.setText("0"); txtMaxF.setText("1");
                txtG.setText("2");
                chkLimG.setSelected(true); txtMinG.setText("0"); txtMaxG.setText("0.5");
                break;
            case 5: // Señal Bipolar
                txtF.setText("(t>=0 & t<10).*(5) + (t>=10 & t<20).*(-5)");
                txtG.setText("(t>=0 & t<10).*(0.5.*t) + (t>=10 & t<20).*(-0.5.*t + 10)");
                break;
            case 6: // Doble Exponencial
                txtF.setText("exp(-t).*heaviside(t)");
                txtG.setText("exp(-2*t).*heaviside(t)");
                break;
            default: // Personalizado
                txtF.setText("");
                txtG.setText("");
                break;
        }
    }
    
    private void mostrarAyuda() {
        String mensajeHtml = "<html><body style='width: 380px; font-family: Segoe UI, sans-serif; color: #333333;'>" +
                "<h2 style='color: #F27200; margin-bottom: 5px;'>Guía de Sintaxis Matemática del Programa</h2>" +
                "<p style='margin-top: 0px;'>Esta herramienta utiliza el motor de MATLAB. Para evitar errores de cálculo, seguir estas reglas básicas al escribir las funciones:</p>" +
                
                "<ul style='margin-top: 10px; margin-bottom: 10px;'>" +
                "<li><b>Multiplicación:</b> Usar siempre el punto antes del asterisco <code>.*</code> para multiplicar variables. <br><i>Ejemplo:</i> <code>2.*t</code> (No usar solo 2*t)</li>" +
                "<li style='margin-top: 5px;'><b>Potencias:</b> Usar el punto y el acento circunflejo <code>.^</code> <br><i>Ejemplo:</i> <code>t.^2</code> para t al cuadrado.</li>" +
                "<li style='margin-top: 5px;'><b>División:</b> Usar <code>./</code> para dividir entre variables que dependen del tiempo.</li>" +
                "</ul>" +
                
                "<h3 style='color: #4682B4; margin-bottom: 5px;'>Funciones Comunes:</h3>" +
                "<table style='width: 100%; text-align: left; border-collapse: collapse;'>" +
                "<tr><td style='border-bottom: 1px solid #ddd; padding: 4px;'><b>Escalón Unitario u(t)</b></td><td style='border-bottom: 1px solid #ddd;'><code>heaviside(t)</code></td></tr>" +
                "<tr><td style='border-bottom: 1px solid #ddd; padding: 4px;'><b>Exponencial e^t</b></td><td style='border-bottom: 1px solid #ddd;'><code>exp(t)</code></td></tr>" +
                "<tr><td style='border-bottom: 1px solid #ddd; padding: 4px;'><b>Seno / Coseno</b></td><td style='border-bottom: 1px solid #ddd;'><code>sin(t)</code> / <code>cos(t)</code></td></tr>" +
                "<tr><td style='border-bottom: 1px solid #ddd; padding: 4px;'><b>Valor de Pi (\u03C0)</b></td><td style='border-bottom: 1px solid #ddd;'><code>pi</code> <i>(ej. sin(pi.*t))</i></td></tr>" +
                "</table>" +
                
                "<div style='background-color: #f9f9f9; padding: 10px; border-left: 4px solid #F27200; margin-top: 15px;'>" +
                "<b>Nota:</b> Al utilizar las casillas de <i>'Restringir intervalo'</i>, no se necesitan programar los límites manualmente con operadores lógicos." +
                "</div>" +
                "</body></html>";

        JOptionPane.showMessageDialog(this, 
                new JLabel(mensajeHtml), 
                "¿Cómo escribir las funciones?", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Armar el string final interpretando las casillas de límites
    private String procesarFuncion(String funcionOriginal, JCheckBox chk, JTextField txtMin, JTextField txtMax) throws Exception {
        if (!chk.isSelected()) return funcionOriginal;
        
        String min = txtMin.getText().trim();
        String max = txtMax.getText().trim();
        
        if (min.isEmpty() || max.isEmpty()) {
            throw new Exception("Límites incompletos. Por favor llena el intervalo [min, max].");
        }
        // Construir automáticamente la sintaxis lógica de MATLAB
        return String.format("(t>=%s & t<=%s).*(%s)", min, max, funcionOriginal);
    }

    private void ejecutarMatlabConCarga() {
        String fRaw = txtF.getText().trim();
        String gRaw = txtG.getText().trim();

        if (fRaw.isEmpty() || gRaw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa ambas funciones.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Procesamiento de las funciones para inyectarles los límites si las casillas están marcadas
            final String fStr = procesarFuncion(fRaw, chkLimF, txtMinF, txtMaxF);
            final String gStr = procesarFuncion(gRaw, chkLimG, txtMinG, txtMaxG);

            // --- ESTADO DE CARGA ---
            btnCalcular.setEnabled(false);
            btnCalcular.setText("Procesando en MATLAB...");
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            // Se corre MATLAB en un hilo separado para no congelar la interfaz
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    String comando = String.format(
                        "matlab -nosplash -batch \"visualizarConvolucion('%s', '%s');\"", 
                        fStr, gStr
                    );
                    Process process = Runtime.getRuntime().exec(comando);
                    process.waitFor();
                    return null;
                }

                @Override
                protected void done() {
                    // --- RESTAURAR INTERFAZ ---
                    setCursor(Cursor.getDefaultCursor());
                    btnCalcular.setEnabled(true);
                    btnCalcular.setText("Calcular y Visualizar");
                }
            };
            worker.execute();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new VentanaConvolucion().setVisible(true));
    }
}