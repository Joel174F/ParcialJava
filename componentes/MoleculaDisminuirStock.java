package componentes;

import javax.swing.*;
import java.awt.FlowLayout;

// MOLECULA: etiqueta + campo + boton, juntos para disminuir stock.
public class MoleculaDisminuirStock extends JPanel {

    public MoleculaDisminuirStock(LogicaStock logica) {
        setLayout(new FlowLayout());

        JTextField campoCantidad = Atomos.crearCampoCantidad();
        JButton botonDisminuir = Atomos.crearBoton("Disminuir stock");

        add(Atomos.crearEtiqueta("Cantidad:"));
        add(campoCantidad);
        add(botonDisminuir);

        botonDisminuir.addActionListener(e -> {
            // Solo limpiamos el campo si la operacion salio bien.
            if (logica.disminuirStock(campoCantidad.getText())) {
                campoCantidad.setText("");
            }
        });
    }
}