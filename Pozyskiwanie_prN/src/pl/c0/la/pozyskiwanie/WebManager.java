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
	
	//adres strony z ankiet¹ powszechn¹ PKN
	private String urlAnkiety = "https://pzn.pkn.pl/pzn-share/page/pzn/polling";
	
	
	public void otworzStrone(){
		if (Desktop.isDesktopSupported()) {
            // Windows
            try{
            	Desktop.getDesktop().browse(new URI(urlAnkiety));
            } catch(Exception e){
            	JOptionPane.showMessageDialog(null, "Przy próbie otworzenia domyœlnej przegl¹darki WWW wyst¹pi³ b³¹d. /n Otwórz stronê z ankiet¹ powszechn¹ rêcznie.");
            }
        }
	}
	
}
