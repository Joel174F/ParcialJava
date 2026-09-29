package componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// LOGICA: lo que pasa con el stock cuando se aprieta un boton.
// Trabaja sobre la tabla del profe, sin modificar su codigo.
public class LogicaStock {

    private final JTable tabla;
    private final DefaultTableModel modelo;
    private final JLabel lblTotal;
    private static final int UMBRAL_MINIMO = 5;

    public LogicaStock(JTable tabla, DefaultTableModel modelo, JLabel lblTotal) {
        this.tabla = tabla;
        this.modelo = modelo;
        this.lblTotal = lblTotal;
    }

    // Suma la cantidad al stock de la fila seleccionada.
    // Devuelve true si se pudo hacer, false si hubo algun error.
    public boolean aumentarStock(String textoCantidad) {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(tabla,
                    "Debe seleccionar un producto.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(textoCantidad.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(tabla,
                    "La cantidad debe ser un número entero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(tabla,
                    "La cantidad debe ser mayor que cero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        double precio = Double.parseDouble(modelo.getValueAt(fila, 1).toString());
        int stockActual = Integer.parseInt(modelo.getValueAt(fila, 2).toString());
        int nuevoStock = stockActual + cantidad;

        // Actualizamos stock y valor stock de esa fila.
        modelo.setValueAt(nuevoStock, fila, 2);
        modelo.setValueAt(precio * nuevoStock, fila, 4);

        actualizarTotal();
        return true;
    }

    // Resta la cantidad al stock de la fila seleccionada.
    public boolean disminuirStock(String textoCantidad) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(tabla, "Debe seleccionar un producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(textoCantidad.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(tabla, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String nombreProducto = modelo.getValueAt(fila, 0).toString();
        double precio = Double.parseDouble(modelo.getValueAt(fila, 1).toString());
        int stockActual = Integer.parseInt(modelo.getValueAt(fila, 2).toString());

        if (cantidad <= 0 || cantidad > stockActual) {
            JOptionPane.showMessageDialog(tabla, "Cantidad inválida. Stock disponible: " + stockActual, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int nuevoStock = stockActual - cantidad;
        modelo.setValueAt(nuevoStock, fila, 2);
        modelo.setValueAt(precio * nuevoStock, fila, 4);
        actualizarTotal();

        // Evalúa automáticamente si el stock restante queda por debajo de 5
        evaluarStockBajo(nombreProducto, nuevoStock);

        return true;
    }

    // Recalcula el valor total del stock y lo muestra en la etiqueta.
    public void actualizarTotal() {
        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            total += Double.parseDouble(modelo.getValueAt(i, 4).toString());
        }
        lblTotal.setText(String.format("Valor total del stock: $%.2f", total));
    }

    /**
     * Evalúa si el stock es menor a 5 y dispara la alerta visual.
     */
    public static void evaluarStockBajo(String nombreProducto, int stockActual) {
        if (stockActual < UMBRAL_MINIMO) {
            String mensaje = "⚠ ¡ALERTA DE STOCK BAJO!\n\n" +
                             "El producto '" + nombreProducto + "' tiene solo " + 
                             stockActual + " unidad(es) disponible(s).";
            
            // Llama al Átomo para mostrar el diálogo
            Atomos.mostrarAlerta(mensaje, "Stock Crítico");
        }
    }
}
