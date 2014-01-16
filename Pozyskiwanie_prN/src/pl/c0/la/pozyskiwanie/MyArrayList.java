package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;
import java.io.Serializable;

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
				
				//je�eli flaga "usunac" jest "true" usun element z listy i przestaw wska�nik i
				if (usunac) {
					this.remove(i);
					i--;
				}
				
			}
		}
	}

	
	/**
	 * dodaje wszystkie  projekty norm znajduj�ce si� na li�cie "do dodania" do bie��cej listy
	 */
	public void dodajListe(MyArrayList doDodania){
		this.addAll(doDodania);
	}
	
}
