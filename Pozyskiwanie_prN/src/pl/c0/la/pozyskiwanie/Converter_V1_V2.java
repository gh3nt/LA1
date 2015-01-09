package pl.c0.la.pozyskiwanie;

/**
 * Zadaniem klasy jest deserializacja i konwersja obiektu MyArrayList,
 * zawieraj¹cego obiekty ProjektNormy (lista "przetwarzane"), na obiekt MyArrayList_v2 zawieraj¹cy obiekty ProjektNormy_v2
 * a nastêpnie jego serializacja
 * @author Luk
 *
 */
public class Converter_V1_V2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//mened¿er plikow wykorzystywany do serializacji
		FileManager fm = new FileManager();
		
		//œcie¿ki do plików
		String przetwarzane = "przetwarzane.list";
		String przetwarzane_v2 = "przetwarzane_v2.list";
		
		
		//deserializuj obiekt
		MyArrayList list1 = (MyArrayList)fm.deserializeObject(przetwarzane);
		
		//przerób obiekt
		MyArrayList_v2 list2 = new MyArrayList_v2();
		
		for(ProjektNormy pn:list1){
			list2.add(przeksztalcPN(pn));
		}
		
		//serializuj obiekt
		fm.serializeObject(list2, przetwarzane_v2);
		
		//weryfikacja
		System.out.println(list1);
		System.out.println(list2);
		
	}
	
	/**
	 * przekszta³ca obiekt typu ProjektNormy na ProjektNormy_v2
	 */
	static ProjektNormy_v2 przeksztalcPN(ProjektNormy pn){
		
		ProjektNormy_v2 pn2 = new ProjektNormy_v2(pn.getNrKT(), pn.getNumer(), pn.getNazwa(), pn.getNazwaEN(), pn.getKoniecAnkiety());
		return pn2;
		
	}

}
