package componentes;

import javax.swing.*;
import java.awt.FlowLayout;

// MOLECULA: etiqueta + campo + boton, juntos para aumentar stock.
public class MoleculaAumentarStock extends JPanel {

    public MoleculaAumentarStock(LogicaStock logica) {
        setLayout(new FlowLayout());

        JTextField campoCantidad = Atomos.crearCampoCantidad();
        JButton botonAumentar = Atomos.crearBoton("Aumentar stock");

        add(Atomos.crearEtiqueta("Cantidad:"));
        add(campoCantidad);
        add(botonAumentar);

        botonAumentar.addActionListener(e -> {
            // Solo limpiamos el campo si la operacion salio bien.
            if (logica.aumentarStock(campoCantidad.getText())) {
                campoCantidad.setText("");
            }
        });
    }
}
