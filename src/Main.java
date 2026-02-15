import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Screen ventana = new Screen();
                ventana.setVisible(true);
            }
        });
    }
}