package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * klasa zawiera model danych tabeli przechowuj�cej informacje o projektach norm (obiekt�w typu ProjektNormy), 
 * pozwalajacej na zaznaczanie (tick) tych, kt�rych zakres tematyczny pokrywa si� z dzia�alno�ci� ITB
 * @author ght
 *
 */
public class TickTableModel extends AbstractTableModel {

	//lista projekt�w norm
	ArrayList<ProjektNormy> listaPN;
	
	//lista tick�w 
	
	public TickTableModel(ArrayList<ProjektNormy> listaPN){
		this.listaPN = listaPN;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
