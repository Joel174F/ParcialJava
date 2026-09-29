package componentes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// ATOMOS: las piezas mas chicas, reutilizables por todo el grupo.
public class Atomos {

    // Atomo: un boton simple.
    public static JButton crearBoton(String texto) {
        return new JButton(texto);
    }

    // Atomo: un campo de texto para escribir cantidades.
    public static JTextField crearCampoCantidad() {
        return new JTextField(5);
    }

    // Atomo: una etiqueta simple.
    public static JLabel crearEtiqueta(String texto) {
        return new JLabel(texto);
    }

    // Átomo: Muestra mensajes de advertencia emergentes en pantalla
    public static void mostrarAlerta(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }
}
 

