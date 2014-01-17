package pl.c0.la.pozyskiwanie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Panel2 extends JPanel {
	
	//tabela, która pokazuje projekty norm
	private JTable table;
	
	//panel nadrzêdny (wywo³uj¹cy) typu MainFrame
	private MainFrame parent;
	
	//panel przewijany, w którym umieszczona bêdzie tabela
	private JScrollPane sp;
	

	/**
	 * Create the panel.
	 */
	public Panel2(MyArrayList listaPN, MainFrame parent) {
		
		//usuñ managera uk³adu
		setLayout(null);
		
		//umieœæ we wska¿niku this.parent odniesienie do nadrzêdnego panelu
		this.parent = parent;


		//utwórz model danych tabeli oparty na przekazanej liœcie projektów norm
		TickTableModel ttm = new TickTableModel(listaPN);
		
		//utwórz tabelê
		table = new JTable(ttm);
		
		//ustawioanie szerokoœci kolumn
		
		
		//utwórz panel przewijany
		sp = new JScrollPane(table);
		
		//sp.setSize(sp.getHeight(), parent.getWidth() - 10);
		resizeScrollPane();
		
		//dodaje tabelê do panelu
		add(sp);

	}
	
	public void resizeScrollPane(){
		sp.setBounds(5, 10, parent.getWidth() - 35, parent.getHeight() - 100);
		sp.repaint();
		table.updateUI();
		ustawSzerokoscKolumn(table);
		
	}
	
	//ustawia szerokoœæ wszystkich kolumn w tabeli
	private void ustawSzerokoscKolumn(JTable table){
		Dimension tableSize = sp.getSize();
		
		/*
		ustawSzerokoscKolumny(table, 0, tableSize, 0.1f);
		ustawSzerokoscKolumny(table, 1, tableSize, 0.4f);
		ustawSzerokoscKolumny(table, 2, tableSize, 0.06f);
		ustawSzerokoscKolumny(table, 3, tableSize, 0.2f);
		ustawSzerokoscKolumny(table, 4, tableSize, 0.06f);
		ustawSzerokoscKolumny(table, 5, tableSize, 0.06f);
		ustawSzerokoscKolumny(table, 6, tableSize, 0.06f);
		ustawSzerokoscKolumny(table, 7, tableSize, 0.06f);
		*/
		
		table.getColumn(table.getColumnName(0)).setPreferredWidth(Math.round((tableSize.width) * 0.1f));
		table.getColumn(table.getColumnName(1)).setPreferredWidth(Math.round((tableSize.width) * 0.40f));
		table.getColumn(table.getColumnName(2)).setPreferredWidth(Math.round((tableSize.width) * 0.04f));
		table.getColumn(table.getColumnName(3)).setPreferredWidth(Math.round((tableSize.width) * 0.2f));
		table.getColumn(table.getColumnName(4)).setPreferredWidth(Math.round((tableSize.width) * 0.06f));
		table.getColumn(table.getColumnName(5)).setPreferredWidth(Math.round((tableSize.width) * 0.06f));
		table.getColumn(table.getColumnName(6)).setPreferredWidth(Math.round((tableSize.width) * 0.06f));
		table.getColumn(table.getColumnName(7)).setPreferredWidth(Math.round((tableSize.width) * 0.06f));
		
		
		// ?? roziw¹zanie tymczasowe
		table.setRowHeight(20);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setForeground(Color.black);
		
		
	}
	
	/*
	private void ustawSzerokoscKolumny(JTable table, int nrKol, Dimension tableSize, float percentage){
		table.getColumn(table.getColumnName(nrKol)).setPreferredWidth(Math.round((tableSize.width) * percentage));
		table.getColumn(table.getColumnName(nrKol)).setMinWidth(Math.round((tableSize.width) * percentage));
		table.getColumn(table.getColumnName(nrKol)).setMaxWidth(Math.round((tableSize.width) * percentage));
	}
	*/

}
