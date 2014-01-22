package pl.c0.la.pozyskiwanie;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.Desktop;
import java.net.URI;
import javax.swing.*;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * 
 * @author Luk
 *
 */
public class WebManager {
	

	
	public WebManager(){

	}
	
	/**
	 * Otwiera strone podana w konstruktorze obiektu w domyœlnej przegl¹darce (dzia³a tylko dla windows)
	 */
	public void otworzStrone(String filePath){
		if(Desktop.isDesktopSupported()){
			try{
				Desktop.getDesktop().browse(new URI(filePath));
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "B³¹d otwierania domyœlnej przegl¹darki lub z³y format adresu (http://...)");
			}
		}
	}
	
	/**
	 * pobiera tekst ze strony o pdanym adresie
	 * @param adres
	 * @return
	 */
	public String pobierzTekstZeStrony(String adres){
		
		Document doc = null;
		String s = null;
		
		try{
			Connection con = Jsoup.connect(adres);
			con.timeout(3000);
			doc = Jsoup.connect(adres).get();
			s = doc.text();
		} catch(Exception e){
			e.printStackTrace();
			s = null;
		}
		
		return s;
	}
	
	
	/**
	 * Pobiera nazwe KT na podstawie jego numeru
	 * @param nrKT
	 * @return
	 */
	public String pobierzWykazKT(){
		
		String adres = "https://pzn.pkn.pl/kt/?pid=kt";
		String s = pobierzTekstZeStrony(adres);
		return s;
	}
		
	
	
}
