package pl.c0.la.pozyskiwanie;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test funkcji wybieraj¹cej skrócony numer
		ProjektNormy prN = new ProjektNormy("prPN-prEN ISO 12345-6-7E:2011/prA","nazwa projektiu", "nazwa EN", "zakres", "zakresEN", "12.12.2013");
		System.out.println(prN.getNumer());
		System.out.println(prN.getNumerKrotki());

	}

}
