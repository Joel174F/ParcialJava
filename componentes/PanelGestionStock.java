package componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;

// ORGANISMO: la seccion completa de gestion de stock.
// Es lo unico que se engancha en la ventana del profe.
public class PanelGestionStock extends JPanel {

    public PanelGestionStock(JTable tabla, DefaultTableModel modelo, JLabel lblTotal) {
        setLayout(new FlowLayout());

        LogicaStock logica = new LogicaStock(tabla, modelo, lblTotal);

        add(new MoleculaAumentarStock(logica));
        // Aca los companeros van sumando sus moleculas (disminuir, etc.)
    }
}
