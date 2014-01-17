
package pl.c0.la.pozyskiwanie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 * klasa ma umo¿liwic tworzenie elementów wyœwiatlajacych wartoœci tekstowe w tabeli (renderer-ów) z zawijaniem wierszy
 * @author Luk
 *
 */
public class WrappingCellRenderer extends JTextArea implements
		TableCellRenderer {

	public WrappingCellRenderer(){
		//pozwól na zawijanie wierszy
		this.setLineWrap(true);
		
		//zawijaj ca³e s³owa
		this.setWrapStyleWord(true);
		
		//ustaw czcionkê
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setForeground(Color.black);
		
		this.setMargin(new Insets(5, 0, 5, 0));
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object val,
			boolean arg2, boolean arg3, int row, int column) {
		
		//ustaw treœæ pola tekstowego
		this.setText(val.toString());
		
		//okreœl wysokoœæ pola tekstowego
		this.setSize(table.getColumnModel().getColumn(column).getWidth(), 1);		
		int cellHeight = this.getPreferredSize().height; // + 10;
		
		//zmienñ wysokoœæ odpowiedniego wiersza tabeli
		if(table.getRowHeight(row) != cellHeight){
			table.setRowHeight(row, cellHeight);
		}
		
		
		//this.setBounds(10, 5, this.getWidth(), this.getHeight());
		
		
		
		/*
		// szerokoœæ pojedynczej kolumny w JTextArea 
		int colWidth = this.getColumnWidth();
		//szerokoœæ komórki tabeli
		int cellWidth = table.getColumnModel().getColumn(column).getWidth();
		
		//iloœæ kolumn JTextAArea = szer komórki / szer kolumny
		int colNum = cellWidth/colWidth;
		
		this.setColumns(colNum);
		
		//int areaHeight = this.getHeight();
		
		int rowsNum = this.getLineCount();
		
		int cellHeight = rowsNum * this.getRowHeight();

		table.setRowHeight(row, cellHeight);
		*/
		
		//ustaw rozmiar pola tekstowego na szerokoœæ kolumny i domyœln¹ wysokoœæ
		//this.setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight());

		
		return this;
	}

}
