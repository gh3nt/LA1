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
			case 7: o = listaPN.get(arg0).getZwiazany(); 
					break;
			default: o = "B��dna warto��";
					break;
		}
		return o;
	}

	/**
	 * zwraca klas� Class<?> do kt�rej b�d� nale�ec warto�ci w danej kolumnie
	 */
	@Override
	public Class<?> getColumnClass(int arg1){
		Class c = null;
		
		// pobierz odpowiedni� warto�� z odpowiedniego obiektu na li�cie
		switch (arg1){
			case 0: c = String.class;
					break;
			case 1: c = String.class; 
					break;
			case 2: c = Integer.class; 
					break;
			case 3: c = String.class; 
					break;
			case 4: c = String.class; 
					break;
			case 5: c = Boolean.class; 
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
	 * zwraca nazw� kolumny o podanym numerze
	 */
	public String getColumnName(int arg1){
		String cn = "";
		
		switch (arg1){
		case 0: cn = "Numer";
				break;
		case 1: cn = "Tytu�"; 
				break;
		case 2: cn = "Nr KT"; 
				break;
		case 3: cn = "Nazwa KT"; 
				break;
		case 4: cn = "Koniec ankiety"; 
				break;
		case 5: cn = "W akredytacji"; 
				break;
		case 6: cn = "Zharmonizowana";
				break;
		case 7: cn = "Do ankiety wew.";
				break;
		default: cn = "n/a";
				break;
		}
		
		return cn;
		
	}
	
	/**
	 * okre�la, czy kom�rki sa edytowalne. Tak - tylko dla kolumny 7 ("Do ankiety wewn�trznej")
	 */
	public boolean isCellEditable(int rowIndex, int colIndex){
		if (colIndex == 7) return true;
		else return false;
	}
	
	/**
	 * zmienia warto�� kom�rki (tylko dla kolumny 7)
	 */
	public void setValueAt(Object val, int rowIndex, int colIndex){
		if ((colIndex == 7) && (val instanceof Boolean)){
			listaPN.get(rowIndex).setZwiazany((Boolean)val);
			
		}
	}
	
}
