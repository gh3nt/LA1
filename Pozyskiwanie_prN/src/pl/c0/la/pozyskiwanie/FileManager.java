package pl.c0.la.pozyskiwanie;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * wersja pobieraj¹ca projekty z pliku EXCELA!!!!
 * @author Luk
 *
 */
public class FileManager {
	
	//scie¿ka dostêpu do pliku z informacjami o projektach norm poddanych ankiecie powszechnej (*.xlsx) 
	private String filePath = "";
	
	public FileManager(){
	}
	
	/**
	 * Pobiera informajce o projektach norm z pliku
	 * @return lista projektów norm
	 */
	public MyArrayList pobierzProjekty(String fileName){
		
		//wiersz, w którym zaczynaja siê informacje o projektach (1 w excelu = 0 w javie)
		int wierszPocz = 7;
		
		//wiersz pomocniczy
		int nrWiersza = wierszPocz;
		
		//numer arkusza (w skoroszycie) gdzie powinny byæ projekty
		int nrArkusza = 0;
		
		//Lista projektów norm 
		MyArrayList projekty = new MyArrayList() ;
		
		//otwieranie skoroszytu excel
		XSSFWorkbook workBook = null;
		try{
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			workBook = new XSSFWorkbook(fis);
		} catch(Exception e) {
			System.out.println("Przy otwieraniu pliku wyst¹pi³ b³¹d");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		//wybierz arkusz w skoroszycie
		XSSFSheet sheet = workBook.getSheetAt(nrArkusza);
		
		//wybierz wiersz pocz¹tkowy		
		XSSFRow row = sheet.getRow(nrWiersza);
		
		//dla kazdego wiersza, który nie jest null, pobierz obiekt ProjektNormy i wstaw do tabeli
		while(row != null){
			
			//sprawdŸ, czy komórki nie s¹ puste (zdarza siê tak jak projekt jest zapisany w kilku scalonych komórkach
			if(sheet.getRow(nrWiersza).getCell(0).getNumericCellValue() != 0){
				projekty.add(odczytajProjektNormy(row));
			}
		
			row = sheet.getRow(++nrWiersza);
		}

		return projekty;
	}
	
	/**
	 * otwiera plik o podanej nazwie w odpowiednim programie zgodnie z przypisaniami w windows (dzia³a tylko dla windows)
	 */
	public void otworzPlik(String fileName){
		if(Desktop.isDesktopSupported()){
			try{
				Desktop.getDesktop().open(new File(fileName));
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "B³¹d otwierania pliku " + fileName);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * z podanego wiersza Excela próbuje odczytaæ projekt normy
	 * @param wiersz
	 * @return
	 */
	private ProjektNormy odczytajProjektNormy(XSSFRow wiersz){
				
		//nrKt
		XSSFCell komorka1 = wiersz.getCell(0);
		int nrKT = (int)komorka1.getNumericCellValue();
		
		//numer Projektu
		XSSFCell komorka2 = wiersz.getCell(1);
		String nrProjektu = komorka2.getStringCellValue();
		
		// data zakoñczenia
		XSSFCell komorka3 = wiersz.getCell(2);
		Date d = komorka3.getDateCellValue();
		String dataKoncaAnkiety = pobierzDate(d);
		
		
		//tytu³ PL
		XSSFCell komorka4 = wiersz.getCell(3);
		String tytulPL = komorka4.getStringCellValue();
		
		//tytul EN
		XSSFCell komorka5 = wiersz.getCell(4);
		String tytulEN = komorka5.getStringCellValue();

		ProjektNormy pn =  new ProjektNormy(nrKT, nrProjektu, tytulPL, tytulEN, dataKoncaAnkiety );
		
		return pn;
		
	}
	
	/**
	 * przerabia datê w formacie Date na stringa xx.xx.xxxx (dzieñ, miesi¹c, rok)
	 */
	private String pobierzDate(Date d){
		GregorianCalendar cal=  new GregorianCalendar();
		cal.setTime(d);
		
		
		String data = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1)  + "." + cal.get(Calendar.YEAR);
		return data;
	}

	public void serializeObject(Object o, String file){
		if (o instanceof Serializable){
			try{
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(o);
				oos.close();
				fos.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public Object deserializeObject(String file){
		Object o = null;
		try{
			
			FileInputStream fos = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fos);
			o= oos.readObject();
			oos.close();
			fos.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return o;
	}

}