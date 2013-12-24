package pl.c0.la.pozyskiwanie;

import java.io.*;
import java.util.ArrayList;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xssf.usermodel.*;
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
	 * Otwiera strone podana w konstruktorze obiektu w domy�lnej przegl�darce (dzia�a tylko dla windows)
	 */
	public void otworzStrone(String filePath){
		if(Desktop.isDesktopSupported()){
			try{
				Desktop.getDesktop().browse(new URI(filePath));
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "B��d otwierania domy�lnej przegl�darki lub z�y format adresu (http://...)");
			}
		}
	}
	
}
