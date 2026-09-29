package componentes;

import javax.swing.*;

public class MoleculaDisminuirStock extends JPanel {

    private final LogicaStock logica;
    private final JTextField txtCantidad;

    public MoleculaDisminuirStock(LogicaStock logica) {
        this.logica = logica;

        txtCantidad = Atomos.crearCampoCantidad();
        JButton btnDisminuir = Atomos.crearBoton("Disminuir Stock");

        btnDisminuir.addActionListener(e -> {
            logica.disminuirStock(txtCantidad.getText());
            txtCantidad.setText("");
        });

        add(Atomos.crearEtiqueta("Cantidad:"));
        add(txtCantidad);
        add(btnDisminuir);
    }
}