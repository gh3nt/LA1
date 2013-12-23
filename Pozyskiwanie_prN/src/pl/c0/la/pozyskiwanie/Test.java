package pl.c0.la.pozyskiwanie;

import java.io.IOException;


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
		
		//test funkcji wybieraj¹cej skrócony numer
		ProjektNormy prN = new ProjektNormy("prPN-prEN ISO 12345-6-7E:2011/prA","nazwa projektiu", "nazwa EN", "zakres", "zakresEN", "12.12.2013");
		System.out.println(prN.getNumer());
		System.out.println(prN.getNumerKrotki());
		
		//test pobierania informacji ze strony jsoup
		
		String adres = "https://pzn.pkn.pl/pzn-share/page/pzn/polling";
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
		    Thread.sleep(90000);
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
