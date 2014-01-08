package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * klasa zawiera model danych tabeli przechowuj¹cej informacje o projektach norm (obiektów typu ProjektNormy), 
 * pozwalajacej na zaznaczanie (tick) tych, których zakres tematyczny pokrywa siê z dzia³alnoœci¹ ITB
 * @author ght
 *
 */
public class TickTableModel extends AbstractTableModel {

	//lista projektów norm
	ArrayList<ProjektNormy> listaPN;
	
	//lista ticków 
	
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
