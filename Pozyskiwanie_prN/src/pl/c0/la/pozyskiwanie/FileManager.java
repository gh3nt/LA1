package pl.c0.la.pozyskiwanie;

import java.awt.Desktop;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
			if (sc!=null)sc.close();
		}
		return s;
	}
	
	/**
	 * Pobiera zawartoœæ pliku w formie listy stringów (1 pozycja - 1 linia)
	 * @param plik
	 * @return
	 */
	public List<String> pobierzListeLiniiZPliku(String plik){
		List<String> lines = new ArrayList<String>();
		Scanner sc = null;

		try{
			
			sc = new Scanner(new File(plik));
			while(sc.hasNextLine()){
				lines.add(sc.nextLine());
			}
		
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			if (sc!=null)sc.close();
		}
		return lines;
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
		
		wypiszDaneZListy(listaPN, sheet1, wb);
		
		wstawFormulySumowania(sheet1, wb);
		
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
	
	/**
	 * wypisuje dane z podanej listy w podanym arkuszu
	 * @param listaPN
	 * @param sheet1
	 */
	private void wypiszDaneZListy(MyArrayList listaPN, Sheet s, XSSFWorkbook wb) {
		//zdefiniuj styl komórek
		CellStyle style = getStylStandardowy(wb);
		CellStyle styleB = getStylWyrozniony(wb);
		
		
		// TODO Auto-generated method stub
		//dodaj pozycje z listy
		int rowNum = 1;
		for (int i = 0; i < listaPN.size(); i++){
			
			if(listaPN.get(i).getZwiazany()){
				Row r = s.createRow(rowNum);
				r.createCell(0).setCellValue(listaPN.get(i).getNrKT());
				r.createCell(1).setCellValue(listaPN.get(i).getNazwaKT());
				r.createCell(2).setCellValue(listaPN.get(i).getNumer());
				r.createCell(3).setCellValue(listaPN.get(i).getNazwa());
				r.createCell(4).setCellValue(listaPN.get(i).getKoniecAnkiety());
				if (listaPN.get(i).getAkredytacja()){
					r.createCell(5).setCellValue("TAK");
				} else{
					r.createCell(5).setCellValue("NIE");
				}
				if (listaPN.get(i).getZharmonizowana()){
					r.createCell(6).setCellValue("TAK");
				} else{
					r.createCell(6).setCellValue("NIE");
				}
				r.createCell(7).setCellValue("");
				
				//ustaw styl standardowydla ka¿dej z komórek
				for (int j = 0; j < 8; j++){
					r.getCell(j).setCellStyle(style);
				}
				//pogrubiona czcionka dla akredytacja lub harmonizacja TAK
				if (listaPN.get(i).getAkredytacja()){
					r.getCell(5).setCellStyle(styleB);
				}
				if (listaPN.get(i).getZharmonizowana()){
					r.getCell(6).setCellStyle(styleB);
				}
				
				rowNum++;
			}

		}
		
	}

	private CellStyle getStylWyrozniony(XSSFWorkbook wb) {
		XSSFFont fontB = wb.createFont();
		fontB.setFontHeightInPoints((short)8);
		fontB.setFontName("Arial");
		fontB.setBold(true);
		CellStyle styleB = wb.createCellStyle();
		styleB.setFont(fontB);
		styleB.setWrapText(true);
		styleB.setAlignment(CellStyle.ALIGN_LEFT);
		styleB.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		short border = CellStyle.BORDER_THIN;
		styleB.setBorderBottom(border);
		styleB.setBorderLeft(border);
		styleB.setBorderRight(border);
		styleB.setBorderTop(border);
		return styleB;
	}

	private CellStyle getStylStandardowy(XSSFWorkbook wb) {
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)8);
		font.setFontName("Arial");
		font.setBold(false);
		CellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		short border = CellStyle.BORDER_THIN;
		style.setBorderBottom(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderTop(border);
		return style;
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
		CellStyle style = getStylNaglowka(wb);
		
		//wypisz nag³ówki
		for(int i = 0; i < col.length; i++){
			Cell c = row.createCell(i);
			c.setCellStyle(style);
			c.setCellValue(col[i].nazwa);	
		}

		return s;
	}

	private void wstawFormulySumowania(Sheet s, XSSFWorkbook wb) {
		
		//numer kolumny z kodami zak³adów
		int kolKZ = 9;
		
		//numer kolumny sumami
		int kolS = kolKZ + 1;
		String[] zaklady = {"NA", "NB", "NF", "NG", "NK", "NM", "NP", "OSK", "OWN", "LN", "ZC"}; 
		CellStyle style = getStylStandardowy(wb);
		
		for(int row = 1; row <= zaklady.length; row++ ){
			
			//sprawdŸ czy istnieje wiersz, jak nie to utwórz
			Row r = s.getRow(row);
			if(r == null){
				r = s.createRow(row);
			}
			
			//sprawdz czy istnieje komórka na kod KT, jak nie to utwórz
			Cell c = r.getCell(kolKZ);
			if (c == null){
				c= r.createCell(kolKZ);
			}
			
			//wstaw kod zak³adu
			c.setCellStyle(style);
			c.setCellValue(zaklady[row-1]);
			
			//sprawdz czy istnieje komórka na formu³ê sumy, jak nie to utwórz
			c = r.getCell(kolS);
			if (c == null){
				c= r.createCell(kolS);
			}
			
			//wstaw formu³e
			c.setCellStyle(style);
			String formula = "COUNTIF(H2:H500,\"*" + zaklady[row-1] + "*\")"; //(H2:H500;"*NA*)
			c.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			c.setCellFormula(formula);
		}
		
		
		
	}

	private CellStyle getStylNaglowka(XSSFWorkbook wb) {
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
		return style;
	}
	

	/**
	 * zwraca aktualn¹ date w formacie dd_mm_yyyy_HHmmss
	 * @return
	 */
	public String pobierzDate() {
		DateFormat f = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
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

	/**
	 * Pobiera listê plików przetwarzanych, dodaje te, które by³y przetwarzane w bie¿¹cej sesji i zapisuje wszysktie razem na liœcie przetwarzanych
	 * @param listaPrzetwarzanych
	 * @param plikPrzetwarzane
	 */
	public void zapiszListePrzetwarzanych(MyArrayList listaPrzetwarzanych,
			String plikPrzetwarzane) {
		MyArrayList starePrzetwarzane = (MyArrayList)this.deserializeObject(plikPrzetwarzane);
		if (starePrzetwarzane!=null) listaPrzetwarzanych.addAll(starePrzetwarzane);
		this.serializeObject(listaPrzetwarzanych, plikPrzetwarzane);
		
	}
	
	public void usunPlik(String nazwa){
		File f = new File(nazwa);
		try{
			f.delete();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * tworzy pusty plik Excela o podanej nazwie
	 * @param plik
	 */
	public void utworzPlikExcela(String plik) {
		XSSFWorkbook wb = new XSSFWorkbook();
		wb.createSheet("wklej_projekty");		
		
		//zapisz plik
		String fName = plik;
		FileOutputStream fileOut = null;
		try{
			fileOut = new FileOutputStream(fName);
			wb.write(fileOut);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * tworzy plik telstowy z list¹ projektów norm wybranych do ankietyzacji wewnêtrznej w ITB
	 * @param listaPN
	 * @param katalog
	 */
	public void wypiszTekst(MyArrayList listaPN, String katalog) {
		//pobierz datê do nazwy pliku
		String data = pobierzDate();

		//nazwa pliku zawiera datê i czas utworzenia
		String fName = katalog +"ankietyzacja" + data + ".txt";
		
		//utwórz writera
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fName), "utf-8"));
			
			//dla ka¿dego projektu na liœcie
			for (ProjektNormy pn : listaPN){
				if (pn.getZwiazany()){
					bw.write(pn.getNumer());
					bw.newLine();
					bw.write("Zharmonizowany: " + (pn.getZharmonizowana() ? "TAK " : "NIE ") 
							+ " ||  W zakresie akredytacji ITB: " + (pn.getAkredytacja() ? "TAK" : "NIE"));
					bw.newLine();
					bw.write(pn.getNazwa());
					bw.newLine();
					bw.write("KT nr " + pn.getNrKT() + " " + pn.getNazwaKT());
					bw.newLine();
					bw.write("Koniec ankiety: " + pn.getKoniecAnkiety());
					bw.newLine();
					bw.newLine();
				}
			}
			
			
			bw.close();
			otworzPlik(fName);
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Tworzy plik tesktowy z list¹ projektów norm zawieraj¹cych w nazwie s³owo "Eurokod" lub "Eurocode"
	 */
	public void wypiszEurokody(MyArrayList listaPN, String katalog){
		//pobierz datê do nazwy pliku
		String data = pobierzDate();

		//nazwa pliku zawiera datê i czas utworzenia
		String fName = katalog +"ankietyzacja_eurokody" + data + ".txt";
		int counter = 0; //ile projektów jest Eurokodami
		
		//utwórz writera
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fName), "utf-8"));
			
			
			//dla ka¿dego projektu na liœcie
			for (ProjektNormy pn : listaPN){
				//zamieœæ projekt w pliku, je¿eli zosta³ wskazany jako zwi¹zany i je¿eli ma w nazwie  ze jest eurokodem
				if (pn.getZwiazany() && (pn.getNazwa().contains("Eurokod") || pn.getNazwa().contains("Eurocode"))){
					counter++;
					bw.write(pn.getNumer());
					bw.newLine();
					bw.write(pn.getNazwa());
					bw.newLine();
					bw.write("KT nr " + pn.getNrKT() + " " + pn.getNazwaKT());
					bw.newLine();
					bw.write("Koniec ankiety: " + pn.getKoniecAnkiety());
					bw.newLine();
					bw.newLine();
				}
			}
			
			//podaj liczbê projektów na koñcu pliku
			bw.write("Liczba projektów: " + counter);
			
			bw.close();
			otworzPlik(fName);
		} catch (Exception e){
			e.printStackTrace();
		}	
	}	
	
	
}
