package pl.c0.la.pozyskiwanie;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

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
		
		
		test14();
		

	}
	
	private static void test14(){
		String s = "17.11.2014";
		Scanner sc = new Scanner(s).useDelimiter("\\.");
		
		while (sc.hasNext()){
			System.out.println(sc.next());
		}
		
	}
	
	private static void test13(){
		String s1 = "eurokod 123123 tralalala";
		String s2 = "dsfsfsdfEurocode 2313231414";
		String s3 = "dsfsfsdfeurokod 2313231414";
		String s4 = "eurocode 123123 tralalala";
		String s5 = "sdsargrgraeijgfriogj;ejrigjio;ejoi;hgoitheo;i";
		
		String e1 = "eurokod";
		String e2 = "eurocode";
		
		System.out.println(s1.contains("Eurokod") || s1.contains("eurocode"));
		System.out.println(s2.contains("eurokod") || s2.contains("eurocode"));
		System.out.println(s3.contains("eurokod") || s3.contains("eurocode"));
		System.out.println(s4.contains("eurokod") || s4.contains("eurocode"));
		System.out.println(s5.contains("eurokod") || s5.contains("eurocode"));
		
		
	}
	
	private static void test12(){
		MainFrame mf = new MainFrame();
		
		
		MyArrayList_v2 lista = new MyArrayList_v2();
		
		ProjektNormy_v2 pn1 = new ProjektNormy_v2(111, "prPN-prEN 14904E", "nazwa1", "nazwaEN", "2014-05-05");
		ProjektNormy_v2 pn2 = new ProjektNormy_v2(222, "prPN-prEN ISO 844E", "nazwa2", "nazwaEN", "2014-05-05");
		ProjektNormy_v2 pn3 = new ProjektNormy_v2(333, "prPN-prEN ISO 14846E", "nazwa2", "nazwaEN", "2014-05-05");
		
		lista.add(pn1);
		lista.add(pn2);
		lista.add(pn3);
		
		for (ProjektNormy_v2 pn :lista){
			pn.uzupelnijAkredytacjaZharmonizowane(mf.getPlikAkredytacja(), mf.getPlikAkredytacjaZC(),  mf.getPlikZharmonizowane());
			System.out.println(pn.getNumer() + " | " + pn.getNumerKrotki() + " | " + pn.getZharmonizowana());
		}
	}
	
	
	private static void test11(){
		MainFrame mf = new MainFrame();
		
		
		MyArrayList_v2 lista = new MyArrayList_v2();
		
		ProjektNormy_v2 pn1 = new ProjektNormy_v2(111, "prPN-prEN 14904E", "nazwa1", "nazwaEN", "2014-05-05");
		ProjektNormy_v2 pn2 = new ProjektNormy_v2(222, "prPN-prEN ISO 844E", "nazwa2", "nazwaEN", "2014-05-05");
		ProjektNormy_v2 pn3 = new ProjektNormy_v2(333, "prPN-prEN ISO 140-5", "nazwa2", "nazwaEN", "2014-05-05");
		
		lista.add(pn1);
		lista.add(pn2);
		lista.add(pn3);
		
		lista.ustawAkredytacjaZharmonizowane(mf.getPlikAkredytacja(), mf.getPlikAkredytacjaZC(), mf.getPlikZharmonizowane());
		
		for (ProjektNormy_v2 pn :lista){
			System.out.println(pn.getNumer() + " | " + pn.getNumerKrotki() + " | " + pn.getAkredytacja() );
		}
		
		
	}
	
	private static void test10(){
		File f = null;
		try {
			f = new File(Test.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = "";
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		System.out.println(f.getAbsolutePath());
	}
	
	private static void test9(){
		String adres = "https://pzn.pkn.pl/kt/?pid=kt";
		WebManager wm = new WebManager();
		
		String s = wm.pobierzTekstZeStrony(adres);
		System.out.println(s);
	}
	
	private static void test8() {
		FileManager fm = new FileManager();
		String s = fm.pobierzDate();
		System.out.println(s);
		
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
		String s3 = fm.pobierzTekstZPliku("\\temp\\akredytacja.txt");
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
		MyArrayList_v2 lista1 = fm.pobierzProjekty(plik);
		
		MyArrayList_v2 lista2 = new MyArrayList_v2();
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
		MyArrayList_v2 list = new MyArrayList_v2();
		list.add(new ProjektNormy_v2(5, "a","b", "c", "12.12.12"));
		list.add(new ProjektNormy_v2(10, "f","gg", "fbg", "12.12.12"));		
		fm.serializeObject(list, plikPrzetwarzane);
		System.out.println("serializacja wykonana");
		*/
		
		//deserializacja
		MyArrayList_v2 drugaLista = (MyArrayList_v2) fm.deserializeObject(plikPrzetwarzane);
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
				ProjektNormy_v2 prN = new ProjektNormy_v2("prPN-prEN ISO 12345-6-7E:2011/prA","nazwa projektiu", "nazwa EN", "zakres", "zakresEN", "12.12.2013");
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
