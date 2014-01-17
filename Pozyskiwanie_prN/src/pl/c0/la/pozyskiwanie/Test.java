package pl.c0.la.pozyskiwanie;

import java.io.IOException;

import javax.swing.JTextArea;


//test jsoup
import org.jsoup.*;
import org.jsoup.Connection.*;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
//====

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		test7();
		

	}
	
	private static void test7(){
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setText("one two three four six seven eigh nine ten");
		System.out.println("000" + ta.getPreferredSize());
		System.out.println("000" + ta.getSize());
		ta.setSize(100, 1);
		System.out.println("100" + ta.getPreferredSize());
		ta.setSize(ta.getPreferredSize());
	}
	
	private static void test6(){
		String s1 = "dupa aaaaaaaa";
		String s2 = "du";
		
		boolean b = s1.contains(s2);
		System.out.println(b);
		
		FileManager fm = new FileManager();
		String s3 = fm.pobierzTekstZPliku("c:\\temp\\akredytacja.txt");
		System.out.println(s3);
	}
	
	//pobieranie tekstu z pliku
	private static void test5() {
		
		FileManager fm = new FileManager();
		String s = fm.pobierzTekstZPliku("c:\\temp\\dupa.txt");
		System.out.println(s);
		
	}
	
	//zapisz kilka projektow na liœcie przetwarzanych
	private static	void test4(){
		//zapisz trochê projektów na liscie przetwarzanych
		
		//sciezka dostêpu do pliku Excela, gdzie bêd¹ wklejane informacje
		String plik = "c:\\temp\\test.xlsx";
		FileManager fm = new FileManager();
		MyArrayList lista1 = fm.pobierzProjekty(plik);
		
		MyArrayList lista2 = new MyArrayList();
		lista2.add(lista1.get(0));
		lista2.add(lista1.get(1));
		lista2.add(lista1.get(2));
		
		String plikPrzetwarzane = "c:\\temp\\przetwarzane.list";
		fm.serializeObject(lista2, plikPrzetwarzane);
		
		
		
		
		
		
		
		
	}
	
	private static void test3(){
		
		String plikPrzetwarzane = "c:\\temp\\przetwarzane.list";
		
		FileManager fm = new FileManager();
		
		/*
		//serializacja
		MyArrayList list = new MyArrayList();
		list.add(new ProjektNormy(5, "a","b", "c", "12.12.12"));
		list.add(new ProjektNormy(10, "f","gg", "fbg", "12.12.12"));		
		fm.serializeObject(list, plikPrzetwarzane);
		System.out.println("serializacja wykonana");
		*/
		
		//deserializacja
		MyArrayList drugaLista = (MyArrayList) fm.deserializeObject(plikPrzetwarzane);
		System.out.println("deserializacja wykonana");
		System.out.println(drugaLista);
		
	}
	
	private static void test2(){
		String plik = "c:\\temp\\test.xlsx";
		FileManager fm = new FileManager();
		fm.pobierzProjekty(plik);
	}
	
	private static void test1(){
		//test funkcji wybieraj¹cej skrócony numer
				ProjektNormy prN = new ProjektNormy("prPN-prEN ISO 12345-6-7E:2011/prA","nazwa projektiu", "nazwa EN", "zakres", "zakresEN", "12.12.2013");
				System.out.println(prN.getNumer());
				System.out.println(prN.getNumerKrotki());
				
				//test pobierania informacji ze strony jsoup
				
				String adres = "https://pzn.pkn.pl/kt/?pid=ppnlp&id=237&back=kt";
				Document doc = null;
				
				/*
				try {
					//doc = Jsoup.connect("https://pzn.pkn.pl/pzn-share/page/pzn/polling").execute();
					doc = Jsoup.connect("https://pzn.pkn.pl/pzn-share/page/pzn/polling").execute().parse();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				*/

				Response resp = null;
				
				
				Connection con = Jsoup.connect(adres);
				con.followRedirects(true);
				
				try{
					resp = con.timeout(90000)
							.execute();
					//System.out.println(resp.statusMessage());
					//doc = resp.parse();
				} catch(Exception e){
					e.printStackTrace();
				}
				
				try {
					System.out.println("start sleep");
				    Thread.sleep(9000);
				    System.out.println("stop sleep");
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				
				//con.
				
				try{
					//resp = con.timeout(90000)
					//		.execute();
					
					System.out.println(resp.statusMessage());
					//doc = resp.parse();
					doc = Jsoup.connect(adres).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").timeout(90000)
							.get();
					//doc = con.get();
				} catch(Exception e){
					e.printStackTrace();
				}
				System.out.println(doc);
				
				
				//==========
	}


	
}
