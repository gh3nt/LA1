package pl.c0.la.pozyskiwanie;

import java.awt.Desktop;
import java.awt.Font;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

/**
 * Klasa odpowiedzialna za pobieranie informacji z Internetu (ze stron PKN)
 * wersja pobieraj¹ca projekty z pliku EXCELA!!!!
 * @author Luk
 *
 */
public class FileManager {
	
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

	/**
	 * Pobiera zawartoœæ pliku w formie tekstu (nie zachowuje podzia³u na linie)
	 * @param plik
	 * @return
	 */
	public String pobierzTekstZPliku(String plik){
		String s = "";
		Scanner sc = null;


		try{
			StringBuilder sb = new StringBuilder();
			sc = new Scanner(new File(plik));
			while(sc.hasNextLine()){
				sb.append(sc.nextLine()+ " ");
			}
			
			s = sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			sc.close();
		}
		return s;
	}

	/**
	 * tworzy i otwiera plik Excela z list¹ projektów do ankietyzacji;
	 */
	public void eksportujDoExcela(MyArrayList listaPN, String katalog){
		sprawdzKatalog(katalog);
		
		//pobierz datê do nazwy pliku
		String data = pobierzDate();

		//utwórz nowy skoroszyt z arkuszem
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet1 = przygotujArkusz(wb, data);
		
		/*
		Row row = sheet1.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("test1");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("test2");
		*/
		
		
		//zapisz plik
		String fName = katalog +"ankietyzacja" + data + ".xlsx";
		FileOutputStream fileOut = null;
		try{
			fileOut = new FileOutputStream(fName);
			wb.write(fileOut);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//otwórz plik w excelu
		this.otworzPlik(fName);
		
	}
	
	private Sheet przygotujArkusz(XSSFWorkbook wb, String data){
		
		//informacje o kolumnach
		Kolumna[] col = {
				new Kolumna("nr KT",5),
				new Kolumna("nazwa KT", 22),
				new Kolumna("nr projektu", 20),
				new Kolumna("nazwa projektu", 45),
				new Kolumna("koniec ankiety", 12),
				new Kolumna("Akredytacja", 9),
				new Kolumna("Harmonizacja", 9),
				new Kolumna("Zak³ady ITB", 8) 
		};
		
		Sheet s = wb.createSheet(data);
		Row row = s.createRow(0);
		
		//ustaw szerkosci kolumn
		for(int i = 0; i < col.length; i++){
			//s.setColumnWidth(i, col[i].szerokosc);
			s.setColumnWidth(i, col[i].szerokosc * 256);
		}
		
		
		//utwórz styl dla nag³ówków
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)8);
		font.setFontName("Arial");
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		short border = CellStyle.BORDER_MEDIUM;
		style.setBorderBottom(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderTop(border);
		
		//wypisz nag³ówki
		for(int i = 0; i < col.length; i++){
			Cell c = row.createCell(i);
			c.setCellStyle(style);
			c.setCellValue(col[i].nazwa);	
		}
		
		return s;
	}

	
	/**
	 * zwraca aktualn¹ date w formacie dd_mm_yyyy_HHmmss
	 * @return
	 */
	public String pobierzDate() {
		DateFormat f = new SimpleDateFormat("dd_mm_yyyy_HHmmss");
		Calendar cal = Calendar.getInstance();
		return f.format(cal.getTime());
	}

	/**
	 * sprawdza czy katalo istnieje, je¿eli nie - tworzy go
	 * @param katalog
	 */
	private void sprawdzKatalog(String katalog) {
		File path = new File(katalog);
		if (! (path.exists() && path.isDirectory() )){
			path.mkdirs();
		}
		
	}
	
	class Kolumna{
		String nazwa;
		int szerokosc;
		
		public Kolumna(String nazwa, int szerokosc){
			this.nazwa = nazwa;
			this.szerokosc = szerokosc;
		}
	}
	
}
