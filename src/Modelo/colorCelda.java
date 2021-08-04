package Modelo;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class colorCelda extends JTable {

	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int ColumnIndex) {
		Component Componente = super.prepareRenderer(renderer, rowIndex, ColumnIndex);

		if (getValueAt(rowIndex, ColumnIndex) == null) {

		}
		else {

		if (getValueAt(rowIndex, ColumnIndex).getClass().equals(String.class)) {
			String valor = (String) this.getValueAt(rowIndex, ColumnIndex);
			{
				switch (valor) {
				case "VENDIDO":
					Componente.setForeground(Color.red);
					break;
				case "PAGADO":
					Componente.setForeground(Color.blue);
					break;
				case "NO PAGO":
					Componente.setForeground(Color.black);
					break;
				case "NO VENDIDO":
					Componente.setForeground(Color.black);
					break;
				default:
					Componente.setForeground(Color.black);
				}
			}
		}else {
			Componente.setForeground(Color.black);
		}
		return Componente;
		}
		return Componente;
	}
}
