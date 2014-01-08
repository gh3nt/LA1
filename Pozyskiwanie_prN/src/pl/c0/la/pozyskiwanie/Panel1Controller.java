package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

import javax.swing.*;


public class Panel1Controller {
	
	//obiekt zarz�dzaj�cy operacjami na stronach internetowych
	WebManager wm;
	
	//obiekt zarz�dzaj�cy operacjami na plikach
	FileManager fm;
	
	//g�owny kontroler programu
	MainFrame parent;
	
	public Panel1Controller(MainFrame parent){
		this.parent = parent;
		wm = new WebManager();
		fm = new FileManager();
	}
	
	public void otworzStrone(String url){
		wm.otworzStrone(url);
	}

	public void otworzPlikExcela(String plik) {
		fm.otworzPlik(plik);	
	}
	
	/**
	 * Rozpocznij pobieranie danych z pliku. Wstaw dane do tablicy obiekt�w ProjektNormy i zmien panel w oknie g��wnym
	 */
	public void rozpocznij(String plik){
		ArrayList<ProjektNormy> listaPN = fm.pobierzProjekty(plik);
		
		System.out.println("size: " + listaPN.size());
		
		parent.krok2(listaPN);
		
	}
	

}
