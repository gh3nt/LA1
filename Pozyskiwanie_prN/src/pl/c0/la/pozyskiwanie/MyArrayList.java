package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * W�asna klasa dziedzicz�ca po ArrayList, stworzona na potrzeby przechowywania informacji o projektach norm
 * @author Luk
 *
 */
public class MyArrayList extends ArrayList<ProjektNormy> implements Serializable {
	
	/**
	 * usuwa z listy wszystkie te projekty norm, kt�re wystapi�y na li�cie "do usuni�cia". 
	 * Sprawdza po pe�nym numerze projektu (por�wnuje �a�cuchy znak�w)
	 * @param doUsuniecia
	 */
	public void usunProjekty(MyArrayList doUsuniecia){
		
		//je�eli lista do usuni�cia istnieje (nie jest null)
		if(!(doUsuniecia == null)){
			for (int i = 0; i < this.size(); i++){
				
				//pobierz numer projektu i
				String nr = this.get(i).getNumer();
				
				//flaga - czy nale�y usun�� projekt z listy
				boolean usunac = false;
				
				//je�eli projekt wyst�puje na li�cie do usuni�cia - ustaw flage "usunac" na true"
				for(int j = 0; j < doUsuniecia.size(); j++){
					if (doUsuniecia.get(j).getNumer().equals(nr)){
						usunac = true;
					}
				}
				
				//je�eli flaga "usunac" jest "true" usun element z listy i przestaw wska�nik "i"
				if (usunac) {
					this.remove(i);
					i--;
				}
				
			}
		}
	}
	
	/**
	 * usuwa projekty o dacie zako�czenia o ponad miesi�c wcze�niejszej ni� obecna
	 */
	public void usunBardzoStare(){
		
		Calendar teraz = Calendar.getInstance();
		String koniecAnkiety = "";
		int dzien = 0;
		int mies = 0;
		int rok = 0;
		
		//dla ka�dego projektu na li�cie
		for (int i = 0; i < this.size(); i++){
				
			
			//utw�rz dat� z informacji o prn
			Scanner scanner = new Scanner(this.get(i).getKoniecAnkiety()).useDelimiter("\\.");
			//System.out.println(scanner.nextInt());
			//System.out.println(scanner.next());
			dzien = scanner.nextInt();
			mies = scanner.nextInt();
			rok = scanner.nextInt();
			GregorianCalendar dataKA = new GregorianCalendar(rok, mies, dzien);
		
			
			
			//je�eli data zakonczenia ankiety jest wcze�niejsza od obecnej, usu� wpis			
			if (dataKA.before(teraz)) {
				this.remove(i);
				i--;
			}
			
		}
	}

	
	/**
	 * dodaje wszystkie  projekty norm znajduj�ce si� na li�cie "do dodania" do bie��cej listy
	 */
	public void dodajListe(MyArrayList doDodania){
		this.addAll(doDodania);
	}
	
	/**
	 * ustaw odpowiednie flagi (czy projekt normy w zakresie akredytacji, czy projekt dotyczy normy zharmonizowanej)
	 * @param plikAkredytacja - �cie�ka dost�pu do pliku tekstowego z zakresem akredytacji
	 * @param plikZharmonizowane - �ciezka dost�pu do pliku tekstowego z wykazem norm zharmonizowanych
	 */
	public void ustawAkredytacjaZharmonizowane(String plikAkredytacja, String plikZharmonizowane){
		
		
		//ustaw odpowiednie flagi dla kazdego z projekt�w na li�cie (czy projekt normy w zakresie akredytacji, czy projekt dotyczy normy zharmonizowanej)
		for(int i = 0; i < this.size(); i++){
			this.get(i).uzupelnijAkredytacjaZharmonizowane(plikAkredytacja, plikZharmonizowane);
		}		
		
	}
	
	public void pobierzNazwyKT(){
		
		//pobierz wykaz KT (string ze wszystkimi nazwami)
		WebManager wm = new WebManager();
		String s = wm.pobierzWykazKT();
						
		for(int i = 0; i < this.size(); i++){
			ProjektNormy pn = this.get(i);
			Integer nrKT = pn.getNrKT();
			String nazwaKT = "";
			
			//rozmiar stringa z numerem KT
			String sNr = nrKT.toString();
			int rozmiar = sNr.length();
			
			//miejsce wyst�pienia numeru KT
			int a = 0;
			
			//miejsca pocz�tku wyszukiwania
			int b = 0;
			
			//czy wyst�puje przynajmniej 1
			boolean jest = false;
			
			//czy znaleziony
			boolean znal = false;
			
			
			if ((s != null) && (s.contains(sNr))){
				//znajd� nrKT w stringu
				do{
					znal = false;

					a =  s.indexOf(" " + nrKT + " ", b) + 1;
					
					//je�eli po numerze i spacji wyst�uje cyfra to szukaj dalej
					char c = s.charAt(a + rozmiar + 1);
					
					if (Character.isDigit(c)){
						//przesu� pocz�tek wyszukiwania
						b = a + 1;
						znal = false;
					} else{
						znal = true;
					}					
					
				}while (!znal);
				
				// znalezione jest a - pocz�tek numeru KT, znajd� pocz�tek nazwy
				int poczNaz = a + rozmiar + 1;
				
				//znajd� koniec nazwy = (index pierwszego miejsca, kt�re jest cyfr�) - 1
				int konNaz = poczNaz;
				for (int j  = poczNaz; j < s.length(); j++){
					char c = s.charAt(j);
					if (Character.isDigit(c)){
						konNaz = j;
						break;
					}
				}
				
				nazwaKT = "ds. " + s.substring(poczNaz, konNaz);
				
			} else{
				nazwaKT = "n/a";
			}
			
			pn.setNazwaKT(nazwaKT);	
		}
		
	}
	
	/**
	 * sortuje projekty wg numer�w KT, potem wg. nazw projekt�w
	 */
	public void sortuj(){
		Collections.sort(this, new Comparator<ProjektNormy>(){
			public int compare(ProjektNormy a, ProjektNormy b){
				int result = 0;
				//por�wnaj proijekty norm
				if (a.getNrKT() == b.getNrKT()){
					//je�li numery KT s� r�wne, por�wnaj nazwy proejkt�w
					result = a.getNazwa().compareTo(b.getNazwa());
				}else{
					result = Integer.compare(a.getNrKT(), b.getNrKT());
				}
				
				return result;
			}
		});
	}
		
}
