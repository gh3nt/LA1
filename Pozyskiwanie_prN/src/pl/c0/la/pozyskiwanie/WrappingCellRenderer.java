
package pl.c0.la.pozyskiwanie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 * klasa ma umo�liwic tworzenie element�w wy�wiatlajacych warto�ci tekstowe w tabeli (renderer-�w) z zawijaniem wierszy
 * @author Luk
 *
 */
public class WrappingCellRenderer extends JTextArea implements
		TableCellRenderer {

	public WrappingCellRenderer(){
		//pozw�l na zawijanie wierszy
		this.setLineWrap(true);
		
		//zawijaj ca�e s�owa
		this.setWrapStyleWord(true);
		
		//ustaw czcionk�
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setForeground(Color.black);
		
		this.setMargin(new Insets(5, 0, 5, 0));
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object val,
			boolean arg2, boolean arg3, int row, int column) {
		
		//ustaw tre�� pola tekstowego
		this.setText(val.toString());
		
		//okre�l wysoko�� pola tekstowego
		this.setSize(table.getColumnModel().getColumn(column).getWidth(), 1);		
		int cellHeight = this.getPreferredSize().height; // + 10;
		
		//zmien� wysoko�� odpowiedniego wiersza tabeli
		if(table.getRowHeight(row) != cellHeight){
			table.setRowHeight(row, cellHeight);
		}
		
		
		//this.setBounds(10, 5, this.getWidth(), this.getHeight());
		
		
		
		/*
		// szeroko�� pojedynczej kolumny w JTextArea 
		int colWidth = this.getColumnWidth();
		//szeroko�� kom�rki tabeli
		int cellWidth = table.getColumnModel().getColumn(column).getWidth();
		
		//ilo�� kolumn JTextAArea = szer kom�rki / szer kolumny
		int colNum = cellWidth/colWidth;
		
		this.setColumns(colNum);
		
		//int areaHeight = this.getHeight();
		
		int rowsNum = this.getLineCount();
		
		int cellHeight = rowsNum * this.getRowHeight();

		table.setRowHeight(row, cellHeight);
		*/
		
		//ustaw rozmiar pola tekstowego na szeroko�� kolumny i domy�ln� wysoko��
		//this.setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight());

		
		return this;
	}

}
