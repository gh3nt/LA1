package pl.c0.la.pozyskiwanie;

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
		
		//utwórz panel przewijany
		sp = new JScrollPane(table);
		
		//sp.setSize(sp.getHeight(), parent.getWidth() - 10);
		resizeScrollPane();
		
		//dodaje tabelê do panelu
		add(sp);

	}
	
	public void resizeScrollPane(){
		System.out.println("resize w panelu, width: " + parent.getWidth() );
		sp.setBounds(5, 10, parent.getWidth() - 35, parent.getHeight() - 100);
		sp.repaint();
		table.updateUI();
		
	}

}
