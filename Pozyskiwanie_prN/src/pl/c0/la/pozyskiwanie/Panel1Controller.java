package pl.c0.la.pozyskiwanie;

public class Panel1Controller {
	
	//obiekt zarz¹dzaj¹cy operacjami na stronach internetowych
	WebManager wm;
	
	//obiekt zarz¹dzaj¹cy operacjami na plikach
	FileManager fm;
	
	//g³owny kontroler programu
	MainFrame parent;
	
	public Panel1Controller(MainFrame parent){
		this.parent = parent;
		wm = new WebManager();
		fm = new FileManager();
	}
	
	/**
	 * otwórz strone w domyœlnej przegl¹darce
	 * @param url
	 */
	public void otworzStrone(String url){
		wm.otworzStrone(url);
	}

	public void otworzPlikExcela(String plik) {
		fm.usunPlik(plik);
		fm.utworzPlikExcela(plik);	
		fm.otworzPlik(plik);
	}
	
	/**
	 * Rozpocznij pobieranie danych z pliku. Wstaw dane do tablicy obiektów ProjektNormy i zmien panel w oknie g³ównym
	 */
	public void rozpocznij(String plik){
		
		parent.krok2();
		
	}
	
	public void otworzPlik(String nazwaPliku){
		fm.otworzPlik(nazwaPliku);
	}
	
	public void usunPlik(String nazwa){
		fm.usunPlik(nazwa);
	}
	

}
