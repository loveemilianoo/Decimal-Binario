import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame {
    private JTextField txtNumero;
    private JComboBox<String> comboBase;
    private JButton btnConvertir;
    private JTextArea txtResultado;
    private JTable tablaIteraciones;
    private DefaultTableModel modeloTabla;
    
    public Screen() {
        setTitle("Convertidor Decimal - Emiliano Hidalgo Gasca");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        crearInterfaz();
    }
    
    private void crearInterfaz() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelEntrada.setBackground(new Color(240, 240, 245));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos de Entrada"));
        
        JLabel lblNumero = new JLabel("Número decimal:");
        txtNumero = new JTextField(15);
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblBase = new JLabel("Base:");
        String[] bases = {"2 - Binario", "8 - Octal"};
        comboBase = new JComboBox<>(bases);
        comboBase.setFont(new Font("Arial", Font.PLAIN, 14));
        
        btnConvertir = new JButton("Convertir");
        btnConvertir.setFont(new Font("Arial", Font.BOLD, 14));
        btnConvertir.setBackground(new Color(70, 130, 180));
        btnConvertir.setForeground(Color.WHITE);
        btnConvertir.setFocusPainted(false);
        
        panelEntrada.add(lblNumero);
        panelEntrada.add(txtNumero);
        panelEntrada.add(lblBase);
        panelEntrada.add(comboBase);
        panelEntrada.add(btnConvertir);
        
        add(panelEntrada, BorderLayout.NORTH);
        
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Tabla de Iteraciones"));
        
        String[] columnas = {"Iteración", "Dígito", "Valor Aproximado", "Error Absoluto", "Error Relativo (%)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tablaIteraciones = new JTable(modeloTabla);
        tablaIteraciones.setFont(new Font("Monospaced", Font.PLAIN, 12));
        tablaIteraciones.setRowHeight(25);
        tablaIteraciones.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollTabla = new JScrollPane(tablaIteraciones);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);
        
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultado Final"));
        
        txtResultado = new JTextArea(5, 50);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        panelResultados.add(scrollResultado, BorderLayout.CENTER);
        
        add(panelResultados, BorderLayout.SOUTH);

        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConversion();
            }
        });
        
        txtNumero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConversion();
            }
        });
    }
    
    private void realizarConversion() {
        try {
            String numeroStr = txtNumero.getText().trim();
            
            if (numeroStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un número decimal", "Campo vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String baseSeleccionada = (String) comboBase.getSelectedItem();
            int base = Integer.parseInt(baseSeleccionada.split(" ")[0]);
            
            Numero num = new Numero(numeroStr, base);
            num.conversion();
            
            modeloTabla.setRowCount(0);
            
            for (Iteracion iter : num.iteraciones) {
                Object[] fila = {
                    iter.getNumero(),
                    iter.getBit(),
                    String.format("%.18f", iter.getValorAproxParcial()),
                    String.format("%.18e", iter.getErrorAbsoluto()),
                    String.format("%.18f", iter.getErrorRelativo())
                };
                modeloTabla.addRow(fila);
            }
            
            StringBuilder resultado = new StringBuilder();
            resultado.append("RESULTADO FINAL\n");
            resultado.append("Número original: ").append(numeroStr).append("\n");
            resultado.append("Base: ").append(base).append("\n");
            resultado.append("Representación: 0.").append(num.mostrarConversion()).append("\n");
            resultado.append("Valor aproximado: ").append(String.format("%.18f", num.getValorAprox())).append("\n");
            resultado.append("Error final: ").append(num.getError()).append("\n");
            
            if (Double.parseDouble(num.getError()) == 0) {
                resultado.append("Número EXACTO - Sin error de representación\n");
            } else {
                resultado.append("Número APROXIMADO - Con error de representación\n");
            }
            
            txtResultado.setText(resultado.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número decimal válido (ej: 0.23)", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}