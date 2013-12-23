package pl.c0.la.pozyskiwanie;


import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;
/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * @author Luk
 *
 */
public class WebManager {
	
	//adres strony z ankiet� powszechn� PKN
	private String urlAnkiety = "https://pzn.pkn.pl/pzn-share/page/pzn/polling";
	
	
	public void otworzStrone(){
		if (Desktop.isDesktopSupported()) {
            // Windows
            try{
            	Desktop.getDesktop().browse(new URI(urlAnkiety));
            } catch(Exception e){
            	JOptionPane.showMessageDialog(null, "Przy pr�bie otworzenia domy�lnej przegl�darki WWW wyst�pi� b��d. /n Otw�rz stron� z ankiet� powszechn� r�cznie.");
            }
        }
	}
	
}
