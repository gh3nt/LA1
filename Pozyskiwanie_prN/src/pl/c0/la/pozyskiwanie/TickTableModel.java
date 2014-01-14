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
		return 8;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaPN.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object o = null;
		
		// pobierz odpowiedni¹ wartoœæ z odpowiedniego obiektu na liœcie
		switch (arg1){
			case 0: o = listaPN.get(arg0).getNumer(); 
					break;
			case 1: o = listaPN.get(arg0).getNazwa(); 
					break;
			case 2: o = listaPN.get(arg0).getNrKT(); 
					break;
			case 3: o = listaPN.get(arg0).getNazwaKT(); 
					break;
			case 4: o = listaPN.get(arg0).getKoniecAnkiety(); 
					break;
			case 5: o = listaPN.get(arg0).getAkredytacja(); 
					break;
			case 6: o = listaPN.get(arg0).getZharmonizowana(); 
					break;
			case 7: o = listaPN.get(arg0).getNazwaKT(); 
					break;
			default: o = "B³êdna wartoœæ";
					break;
		}
		return o;
	}

	
}
