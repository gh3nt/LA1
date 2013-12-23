package pl.c0.la.pozyskiwanie;

import java.io.*;
import java.util.ArrayList;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * wersja pobieraj¹ca projekty z pliku EXCELA!!!!
 * @author Luk
 *
 */
public class WebManager {
	
	//scie¿ka dostêpu do pliku z informacjami o projektach norm poddanych ankiecie powszechnej (*.xlsx) 
	private String filePath;
	
	public WebManager(String filePath){
		this.filePath = filePath;
	}
	
	/**
	 * Pobiera informajce o projektach norm z pliku
	 * @return tablica projektów norm
	 */
	public ProjektNormy[] pobierzProjekty(){
		ArrayList<ProjektNormy> projekty = new ArrayList<ProjektNormy>() ;
		
		//otwieranie skoroszytu excel
		XSSFWorkbook workBook = null;
		try{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			workBook = new XSSFWorkbook(fis);
			//workBook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
		} catch(Exception e) {
			System.out.println("Przy otwieraniu pliku wyst¹pi³ b³¹d");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workBook.getSheetAt(0);
		XSSFRow row = sheet.getRow(7);
		XSSFCell cell = row.getCell(0);
		String value = cell.getStringCellValue();
		System.out.println(value);
		
		/* 
		try{
			inputFile.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		return projekty.toArray(new ProjektNormy[projekty.size()]);
	}
	
}
