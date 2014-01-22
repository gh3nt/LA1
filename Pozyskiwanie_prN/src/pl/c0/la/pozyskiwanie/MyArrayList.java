package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

/**
 * W³asna klasa dziedzicz¹ca po ArrayList, stworzona na potrzeby przechowywania informacji o projektach norm
 * @author Luk
 *
 */
public class MyArrayList extends ArrayList<ProjektNormy> implements Serializable {
	
	/**
	 * usuwa z listy wszystkie te projekty norm, które wystapi³y na liœcie "do usuniêcia". 
	 * Sprawdza po pe³nym numerze projektu (porównuje ³añcuchy znaków)
	 * @param doUsuniecia
	 */
	public void usunProjekty(MyArrayList doUsuniecia){
		
		//je¿eli lista do usuniêcia istnieje (nie jest null)
		if(!(doUsuniecia == null)){
			for (int i = 0; i < this.size(); i++){
				
				//pobierz numer projektu i
				String nr = this.get(i).getNumer();
				
				//flaga - czy nale¿y usun¹æ projekt z listy
				boolean usunac = false;
				
				//je¿eli projekt wystêpuje na liœcie do usuniêcia - ustaw flage "usunac" na true"
				for(int j = 0; j < doUsuniecia.size(); j++){
					if (doUsuniecia.get(j).getNumer().equals(nr)){
						usunac = true;
					}
				}
				
				//je¿eli flaga "usunac" jest "true" usun element z listy i przestaw wskaŸnik "i"
				if (usunac) {
					this.remove(i);
					i--;
				}
				
			}
		}
	}

	
	/**
	 * dodaje wszystkie  projekty norm znajduj¹ce siê na liœcie "do dodania" do bie¿¹cej listy
	 */
	public void dodajListe(MyArrayList doDodania){
		this.addAll(doDodania);
	}
	
	/**
	 * ustaw odpowiednie flagi (czy projekt normy w zakresie akredytacji, czy projekt dotyczy normy zharmonizowanej)
	 * @param plikAkredytacja - œcie¿ka dostêpu do pliku tekstowego z zakresem akredytacji
	 * @param plikZharmonizowane - œciezka dostêpu do pliku tekstowego z wykazem norm zharmonizowanych
	 */
	public void ustawAkredytacjaZharmonizowane(String plikAkredytacja, String plikZharmonizowane){
		
		// pobierz do Stringów: zakres akredytacji ITB, wykaz norm zharmonizowanych
		FileManager fm = new FileManager();
		String tekstAkredytacja = fm.pobierzTekstZPliku(plikAkredytacja);
		String tekstZharmonizowane = fm.pobierzTekstZPliku(plikZharmonizowane);
		
		//ustaw odpowiednie flagi dla kazdego z projektów na liœcie (czy projekt normy w zakresie akredytacji, czy projekt dotyczy normy zharmonizowanej)
		for(int i = 0; i < this.size(); i++){
			this.get(i).uzupelnijAkredytacjaZharmonizowane(tekstAkredytacja, tekstZharmonizowane);
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
			
			//miejsce wyst¹pienia numeru KT
			int a = 0;
			
			//miejsca pocz¹tku wyszukiwania
			int b = 0;
			
			//czy wystêpuje przynajmniej 1
			boolean jest = false;
			
			//czy znaleziony
			boolean znal = false;
			
			
			if ((s != null) && (s.contains(sNr))){
				//znajdŸ nrKT w stringu
				do{
					znal = false;

					a =  s.indexOf(" " + nrKT + " ", b) + 1;
					
					//je¿eli po numerze i spacji wystêuje cyfra to szukaj dalej
					char c = s.charAt(a + rozmiar + 1);
					
					if (Character.isDigit(c)){
						//przesuñ pocz¹tek wyszukiwania
						b = a + 1;
						znal = false;
					} else{
						znal = true;
					}					
					
				}while (!znal);
				
				// znalezione jest a - pocz¹tek numeru KT, znajdŸ pocz¹tek nazwy
				int poczNaz = a + rozmiar + 1;
				
				//znajdŸ koniec nazwy = (index pierwszego miejsca, które jest cyfr¹) - 1
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
	 * sortuje projekty wg numerów KT, potem wg. nazw projektów
	 */
	public void sortuj(){
		Collections.sort(this, new Comparator<ProjektNormy>(){
			public int compare(ProjektNormy a, ProjektNormy b){
				int result = 0;
				//porównaj proijekty norm
				if (a.getNrKT() == b.getNrKT()){
					//jeœli numery KT s¹ równe, porównaj nazwy proejktów
					result = a.getNazwa().compareTo(b.getNazwa());
				}else{
					result = Integer.compare(a.getNrKT(), b.getNrKT());
				}
				
				return result;
			}
		});
	}
		
}
