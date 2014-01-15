package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

public class MyArrayList extends ArrayList<ProjektNormy> {
	
	/**
	 * usuwa z listy wszystkie te projekty norm, kt�re wystapi�y na li�cie "do usuni�cia". 
	 * Sprawdza po pe�nym numerze projektu (por�wnuje �a�cuchy znak�w)
	 * @param doUsuniecia
	 */
	public void usunProjekty(MyArrayList doUsuniecia){
		
		for (int i = 0; i < this.size(); i++){
			
			//pobierz numer projektu i
			String nr = this.get(i).getNumer();
			
			//je�eli projekt wyst�puje na li�cie do usuni�cia - usu� go
			for(int j = 0; j < doUsuniecia.size(); j++){
				if (doUsuniecia.get(j).getNumer().equals(this.get(i).getNumer())){
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
