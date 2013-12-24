package pl.c0.la.pozyskiwanie;

import java.io.*;
import java.util.ArrayList;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * 
 * @author Luk
 *
 */
public class WebManager {
	
	//scie¿ka dostêpu do pliku z informacjami o projektach norm poddanych ankiecie powszechnej (*.xlsx) 
	private String filePath;
	
	public WebManager(String filePath){
		this.filePath = filePath;
	}
	
}
