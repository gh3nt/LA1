package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;
import java.io.Serializable;

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
				
				//je¿eli flaga "usunac" jest "true" usun element z listy i przestaw wskaŸnik i
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
	
}
