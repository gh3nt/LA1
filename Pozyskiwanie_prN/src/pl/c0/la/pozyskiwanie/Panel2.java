package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

public class Panel2 extends JPanel {
	
	//lista obiekt�w ProjektNormy
	ArrayList<ProjektNormy> listaPN;
	
	//tabela, kt�ra pokazuje projekty norm
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Panel2(ArrayList<ProjektNormy> listaPN) {
		
		this.listaPN = listaPN;
		table = new JTable();
		add(table);

	}

}
