package pl.c0.la.pozyskiwanie;

import java.io.Serializable;

/**
 * 
 * Klasa, kt�rej obiekty przechowuj� informacje o projektach norm poddanych ankiecie powszechnej PKN
 * 
 * @author ght
 *
 */
public class ProjektNormy implements Serializable{
	
	//pe�ny numer projektu (typu prPN-prEN 21344-3:11/A1...)
	private String numer;
		
	//skr�cony numer projektu, tzn. tylko numer normy 21344-3
	private String numerKrotki;
	
	///pe�na nazwa projektu	po polsku
	private String nazwa;
	
	///pe�na nazwa projektu	po angielsku
	private String nazwaEN;
	
	//zakres projektu po polsku
	private String zakresPL;
	
	//zakres projektu po angielsku
	private String zakresEN;
	
	//data ko�ca ankiety - jako string
	private String koniecAnkiety;
	
	//numer Komitetu Technicznego, kt�ry pracuje nad projektem (domy�lnie zero, jezeli brak informacji o KT)
	private int nrKT = 0;
	
	//nazwa Komitetu Technicznego, kt�ry pracuje nad projektem 
	private String nazwaKT = "n/a";
	
	//czy norma jest zharmonizowana
	private boolean zharmonizowana;
	
	//czy norma jest w zakresie akredytacji ITB
	private boolean akredytacja;
	
	//czy jej zakres tematyczny jest zwi�zany z dzia�alno�ci� ITB
	private boolean zwiazany;
	
	/**
	 * Konstruktor z za�o�enia ma przyjmowa� dane dost�pne na stronie PKN z ogloszeniem o ankiecie powszechnej 
	 * @param numer
	 * @param nazwa
	 * @param nazwaEN
	 * @param zakres
	 * @param zakresEN
	 * @param koniecAnkiety
	 */
	public ProjektNormy(String numer, String nazwa, String nazwaEN, String zakres, String zakresEN, String koniecAnkiety){
		this.numer = numer;
		this.numerKrotki = skrocNumer(numer);
		this.nazwa = nazwa;
		this.nazwaEN = nazwaEN;
		this.zakresPL = zakresPL;
		this.zakresEN = zakresEN;
		this.koniecAnkiety = koniecAnkiety;
		
	}
	
	/**
	 * Konstruktor z za�o�enia ma przyjmowa� dane dost�pne na stronie PKN z ogloszeniem o ankiecie powszechnej (po zmianie strony w grudniu 2013)
	 * @param nrKT
	 * @param numer
	 * @param nazwa
	 * @param nazwaEN
	 * @param koniecAnkiety
	 */
	public ProjektNormy(int nrKT, String numer, String nazwa, String nazwaEN, String koniecAnkiety){
		this.nrKT = nrKT;
		this.numer = numer;
		this.numerKrotki = skrocNumer(numer);
		this.nazwa = nazwa;
		this.nazwaEN = nazwaEN;
		this.koniecAnkiety = koniecAnkiety;
		this.nazwaKT = SprawdzNazweKT(nrKT);
	}
	
	
	/**
	 * Sprawdza nazw� KT o podanym numerze
	 * @param Numer KT do sprawdzenia
	 * @return Nazwa KT
	 */
	private String SprawdzNazweKT(int nrKT) {
		//do zrobienia. ma sprawdza� nazw� KT o danym numerze, najlepiej na podstawie strony www, ew. na podstawie jakiej� listy
		nazwaKT = "n/a";
		return nazwaKT;
	}

	public String getNumer(){
		return numer;
	}
	
	public String getNumerKrotki(){
		return numerKrotki;
	}
	
	public String getNazwa(){
		return nazwa;
	}
	
	public String getNazwaEN(){
		return nazwaEN;
	}
	
	public String getZakres(){
		return zakresPL;
	}
	
	public String getZakresEN(){
		return zakresEN;
	}
	
	public String getKoniecAnkiety(){
		return koniecAnkiety;
	}
	
	public void setNrKt(int nrKT){
		this.nrKT = nrKT;
	}
	
	public int getNrKT(){
		return nrKT;
	}
	
	/**
	 * Czy projekt normy jest na li�cie norm zharmonizowanych
	 * @param b
	 */
	public void setZharmonizowana(boolean b){
		zharmonizowana = b;
	}
	
	/**
	 * Czy projekt normy jest na li�cie norm zharmonizowanych
	 * @return 
	 */
	public boolean getZharmonizowana(){
		return zharmonizowana;
	}
	
	/**
	 * Czy projekt normy jest w zakresie akredytacjiITB
	 * @param b
	 */
	public void setAkredytacja(boolean b){
		akredytacja = b;
	}
	
	/**
	 * Czy projekt normy jest w zakresie akredytacjiITB
	 * @return 
	 */
	public boolean getAkredytacja(){
		return akredytacja;
	}
	
	
	/**
	 * czy zakres tematyczny projektu normy jest zwi�zany z dzia�alno�ci� ITB
	 * @param b
	 */
	public void setZwiazany(boolean b){
		zwiazany = b;
	}
	
	/**
	 * czy zakres tematyczny projektu normy jest zwi�zany z dzia�alno�ci� ITB
	 * @return
	 */
	public boolean getZwiazany(){
		return zwiazany;
	}
	
	/**
	 * zwraca nazw� KT opracowuj�cego norm�
	 */
	public String getNazwaKT(){
		return nazwaKT;
	}
	
	/**
	 * Z pe�nego numeru projektu normy (typu prPN-prEN 12345-3:2011/prA1 wybiera tylko numer normy, bez daty czyli np. 12345-3
	 * @param numer
	 * @return
	 */
	public static String skrocNumer(String numer){
		
		String numerKrotki = "";
		char[] znaki = numer.toCharArray();
		
		//poczatek i koniec substringa
		int pocz = -1;
		int kon = -1;
		
		//zmienna pomocnicza do testowania znak�w w tablicy
		int i;
		
		//znajd� pocz�tek numeru (pierwsz� cyfr�)
		for(i = 0; i < znaki.length; i++){
			if (Character.isDigit(znaki[i])){
				pocz = i;
				break;
			}
		}
		
		//znajd� koniec numeru (znak po pierwszej cyfrze, kt�ry nie jest cyfr� ani "-"
		for(; i < znaki.length; i++){
			if (! ((Character.isDigit(znaki[i])) || (znaki[i] == '-') ) ){
				kon = i;
				break;
			}
		}
		
		//je�eli zosta�a znaleziona jaka� cyfra (pocz�tek numeru)
		if (pocz != -1 ){
			//je�eli zosta� znaleziony znak po numerze - pobierz numer od pocz�tku do ko�ca numeru
			if (kon != -1){
				numerKrotki = numer.substring(pocz, kon);
			} 
			//je�eli nie zosta� znaleziony znak po numerze - pobierz numer od pocz�tku do ko�ca ca�ego numeru
			else {
				numerKrotki = numer.substring(pocz);
			}
		}
		
		return numerKrotki;
	}
	
	public String toString(){
		return numer + " " + nazwa + " (KT " + nrKT + ") :" + koniecAnkiety;    
	}
	
	public void uzupelnijAkredytacjaZharmonizowane(String plikAkredytacja, String plikZharmonizowane){
		
	}
	
	public void uzupelnijAkredytacja(String plikAkredytacja){
		
	}
	
	public void uzupelnijZharmonizowane(String plikZharmonizowane){
		
	}
	
	

	
}
