package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

public class MyArrayList extends ArrayList<ProjektNormy> {
	
	/**
	 * usuwa z listy wszystkie te projekty norm, które wystapi³y na liœcie "do usuniêcia". 
	 * Sprawdza po pe³nym numerze projektu (porównuje ³añcuchy znaków)
	 * @param doUsuniecia
	 */
	public void usunProjekty(MyArrayList doUsuniecia){
		
		for (int i = 0; i < this.size(); i++){
			
			//pobierz numer projektu i
			String nr = this.get(i).getNumer();
			
			//je¿eli projekt wystêpuje na liœcie do usuniêcia - usuñ go
			for(int j = 0; j < doUsuniecia.size(); j++){
				if (doUsuniecia.get(j).getNumer().equals(this.get(i).getNumer())){
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
