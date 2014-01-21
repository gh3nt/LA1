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
	MyArrayList listaPN;

	
	public TickTableModel(MyArrayList listaPN){
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
			case 0: o = listaPN.get(arg0).getNrKT();			 
					break;
			case 1: o = listaPN.get(arg0).getNazwaKT(); 
					break;
			case 2: o = listaPN.get(arg0).getNumer();
					break;
			case 3: o = listaPN.get(arg0).getNazwa();
					break;
			case 4: o = listaPN.get(arg0).getZwiazany(); 
					break;
			case 5: o = listaPN.get(arg0).getKoniecAnkiety();
					break;
			case 6: o = listaPN.get(arg0).getAkredytacja();
					break;
			case 7: o = listaPN.get(arg0).getZharmonizowana();
					break;
			default:o = "B³êdna wartoœæ";
					break;
		}
		return o;
	}

	/**
	 * zwraca klasê Class<?> do której bêd¹ nale¿ec wartoœci w danej kolumnie
	 */
	@Override
	public Class<?> getColumnClass(int arg1){
		Class c = null;
		
		// pobierz odpowiedni¹ wartoœæ z odpowiedniego obiektu na liœcie
		switch (arg1){
			case 0: c = Integer.class;
					break;
			case 1: c = String.class; 
					break;
			case 2: c = String.class;
					break;
			case 3: c = String.class; 
					break;
			case 4: c = Boolean.class; 
					break;
			case 5: c = String.class; 
					break;
			case 6: c = Boolean.class;
					break;
			case 7: c = Boolean.class;
					break;
			default: c = Object.class;
					break;
		}
		return c;
	}
	
	/**
	 * zwraca nazwê kolumny o podanym numerze
	 */
	public String getColumnName(int arg1){
		String cn = "";
		
		switch (arg1){
		case 0: cn = "Nr KT";
				break;
		case 1: cn = "Nazwa KT"; 
				break;
		case 2: cn = "Numer"; 
				break;
		case 3: cn = "Tytu³"; 
				break;
		case 4: cn = "Do ankiety wew.";
				break;
		case 5: cn = "Koniec ankiety";
				break;
		case 6: cn = "W akredytacji"; 
				break;
		case 7: cn = "Zharmonizowana";
				break;
		default: cn = "n/a";
				break;
		}
		
		return cn;
		
	}
	
	/**
	 * okreœla, czy komórki sa edytowalne. Tak - tylko dla kolumny 7 ("Do ankiety wewnêtrznej")
	 */
	public boolean isCellEditable(int rowIndex, int colIndex){
		if (colIndex == 4) return true;
		else return false;
	}
	
	/**
	 * zmienia wartoœæ komórki (tylko dla kolumny 7)
	 */
	public void setValueAt(Object val, int rowIndex, int colIndex){
		if ((colIndex == 4) && (val instanceof Boolean)){
			listaPN.get(rowIndex).setZwiazany((Boolean)val);
			
		}
	}
	
}
