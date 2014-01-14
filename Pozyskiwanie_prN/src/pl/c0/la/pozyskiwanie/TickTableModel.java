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
		
		// pobierz odpowiedni� warto�� z odpowiedniego obiektu na li�cie
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
			default: o = "B��dna warto��";
					break;
		}
		return o;
	}

	
}
