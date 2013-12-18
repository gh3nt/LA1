package pl.c0.la.pozyskiwanie;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * wersja pobieraj�ca projekty z pliku EXCELA!!!!
 * @author Luk
 *
 */
public class WebManager {
	
	//scie�ka dost�pu do pliku z informacjami o projektach norm poddanych ankiecie powszechnej (*.xlsx) 
	private String filePath = "d:/temp/test.xlsx";
	
	/**
	 * Pobiera informajce o projektach norm z pliku
	 * @return tablica projekt�w norm
	 */
	public ProjektNormy[] pobierzProjekty(){
		ArrayList<ProjektNormy> projekty = new ArrayList<ProjektNormy>() ;
		
		
		return projekty.toArray(new ProjektNormy[projekty.size()]);
	}
	
}
