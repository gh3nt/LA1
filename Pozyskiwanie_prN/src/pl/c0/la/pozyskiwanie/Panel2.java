package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Panel2 extends JPanel {
	
	//tabela, kt�ra pokazuje projekty norm
	private JTable table;
	
	//panel nadrz�dny (wywo�uj�cy) typu MainFrame
	private MainFrame parent;
	
	//panel przewijany, w kt�rym umieszczona b�dzie tabela
	private JScrollPane sp;
	

	/**
	 * Create the panel.
	 */
	public Panel2(MyArrayList listaPN, MainFrame parent) {
		
		//usu� managera uk�adu
		setLayout(null);
		
		//umie�� we wska�niku this.parent odniesienie do nadrz�dnego panelu
		this.parent = parent;

		//utw�rz model danych tabeli oparty na przekazanej li�cie projekt�w norm
		TickTableModel ttm = new TickTableModel(listaPN);
		
		//utw�rz tabel�
		table = new JTable(ttm);
		
		//utw�rz panel przewijany
		sp = new JScrollPane(table);
		
		//sp.setSize(sp.getHeight(), parent.getWidth() - 10);
		resizeScrollPane();
		
		//dodaje tabel� do panelu
		add(sp);

	}
	
	public void resizeScrollPane(){
		System.out.println("resize w panelu, width: " + parent.getWidth() );
		sp.setBounds(5, 10, parent.getWidth() - 35, parent.getHeight() - 100);
		sp.repaint();
		table.updateUI();
		
	}

}
