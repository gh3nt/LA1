package pl.c0.la.pozyskiwanie;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	//nazwa okna
	String no = "Pozyskiwanie informacji o projektach norm v 0.6 ";
	
	//WSZYSTKIE ADRESY PLIK�W SA ZMIENIANE W Konstruktorze - dodawana jest �cie�ka bezwzgl�dna
	//adres strony ankiety powszechnej
	private String urlAnkiety = "https://pzn.pkn.pl/pzn-share/page/pzn/polling";
	
	//sciezka dost�pu do pliku Excela, gdzie b�d� wklejane informacje
	private String plik = "\\resources\\wklej_projekty.xlsx";
	
	//sciezka dostepu do pliku zserializowanej listy projekt�w norm wcze�niej przetwarzanych
	private String plikPrzetwarzane = "\\resources\\przetwarzane.list";
	
	//sciezka dost�pu do pliku txt, gdzie b�d� informacje o zakresie akredytacji LN ITB
	private String plikAkredytacja = "\\resources\\akredytacja.txt";
	
	//sciezka dost�pu do pliku txt, gdzie b�d� informacje o zakresie akredytacji ZC ITB 
	private String plikAkredytacjaZC = "\\resources\\akredytacjaZC.txt";
		
	//sciezka dost�pu do pliku txt, gdzie b�d� informacje o normach zharmonizowanych
	private String plikZharmonizowane = "\\resources\\zharmonizowane.txt";
	
	//sciezka dost�pu do katalogu na wyeksportowane arkusze excela do ankietyzacji 
	private String katalogEksport = "\\resources\\eksport\\";
	
	//splitPane, na kt�rym b�d� zmieniane panele podrz�dne
	private JSplitPane splitPane;
	
	//panel dolny wyst�puj�cy jako drugi w kolejno�ci, z tabel�, w kt�rej mo�na wkaza�
	//normy do ankietyzacji wewn�trznej
	private Panel2 panel2;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		//ustaw sciezke dost�pu do plik�w
		ustawPliki();
		
		//ustaw tytu� okna
		this.setTitle(no);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		this.setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//dodaj panel dziel�cy przestrze� na 2 cz�ci
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		//panel g�rny - informacja o programie (wersja, autor)
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblPozyskiwanieInformacjiO = new JLabel(no + "Ankieta powszechna PKN => Ankieta wewn�trzna ITB");
		lblPozyskiwanieInformacjiO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPozyskiwanieInformacjiO.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblPozyskiwanieInformacjiO);
		
		JLabel lblAutorukaszAdamus = new JLabel("Autor: �ukasz Adamus");
		lblAutorukaszAdamus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAutorukaszAdamus.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblAutorukaszAdamus);
		
		//Panel dolny - pierwssze okno
		splitPane.setRightComponent(new Panel1(this));
		
	}
	


	private void ustawPliki() {
		plik = getDecodedPath() + plik;// TODO Auto-generated method stub
		plikPrzetwarzane = getDecodedPath() + plikPrzetwarzane;
		plikAkredytacjaZC = getDecodedPath() + plikAkredytacjaZC;
		plikAkredytacja = getDecodedPath() + plikAkredytacja;
		plikZharmonizowane = getDecodedPath() + plikZharmonizowane;
		katalogEksport = getDecodedPath() + katalogEksport;
	}

	/**
	 * Przekazuje do panelu 2 list� norm z pliku excela do tabeli, zmienia panel dolny na ten z tabel�
	 */
	public void krok2(){
		
		//usu� z listy projekt�w norm te, ktore znajduj� si� na li�cie wcze�niej przetwarzanych
		FileManager fm = new FileManager();	
		MyArrayList_v2 listaPN_surowa = fm.pobierzProjekty(this.getPlik()); //lista, z kt�rej zostan� okre�lone przetwarzane projekty
		MyArrayList_v2 przetwarzane = (MyArrayList_v2)fm.deserializeObject(getPlikPrzetwarzane());
		MyArrayList_v2 listaPN = new MyArrayList_v2();
		listaPN.addAll(listaPN_surowa);
		listaPN.usunProjekty(przetwarzane);
		listaPN_surowa.usunProjekty(przetwarzane); //z listy projekt�w do zpaisania jako przetwarzane usuwam przetwarzane wcze�niej, �eby nie duplikowa� 
		
		//zaznacz normy w zakresie akredytacji i zharmonizowane
		listaPN.ustawAkredytacjaZharmonizowane(plikAkredytacja, plikAkredytacjaZC, plikZharmonizowane);
		
		//wype�nij informacje o nazwach KT
		listaPN.pobierzNazwyKT();
		
		//sortuj projekty
		listaPN.sortuj();
		
		
		//utw�rz panel 2
		panel2 = new Panel2(listaPN, this, listaPN_surowa);
		
		//dodaj listener, kt�ry b�dzie reagow�� na zmian� rozmiaru okna i odpowiednio dostosowywa� panel 2
		this.addComponentListener(new ComponentListener(){
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel2.resizeScrollPane();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		pokazPanel(panel2);
	}
	
	/**
	 * wyswietla nowy panel 2 w dolnej cz�ci g��wnego panelu
	 */
	private void pokazPanel(JPanel panel){
		splitPane.setRightComponent(panel);
	}
	
	/**
	 * zwraca adres strony internetowej ankiety powszechnej
	 * @return 
	 */
	public String getAdresAnkiety(){
		return urlAnkiety;
	}
	
	/**
	 * zwraca �cie�k� dost�pu do pliku Excela, do kt�rego maj� by� wklejone dan odczytywane p�niej przez sytem
	 */
	public String getPlik(){
		return this.plik;
	}
	
	public String getKatalogEksport(){
		return this.katalogEksport;
	}
	
	public String getPlikPrzetwarzane(){
		return this.plikPrzetwarzane;
	}
	
	public String getPlikAkredytacja(){
		return this.plikAkredytacja;
	}
	
	public String getPlikAkredytacjaZC(){
		return this.plikAkredytacjaZC;
	}
	
	public String getPlikZharmonizowane(){
		return this.plikZharmonizowane;
	}
	
	public String getDecodedPath(){
		
		
		File f = null;
		
		try {
			
			String path = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			f =  new File(path);
			//JOptionPane.showMessageDialog(this, f.getCanonicalPath());
			//JOptionPane.showMessageDialog(this, f.getPath());
			//JOptionPane.showMessageDialog(this, f.getAbsolutePath());
			//f = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getStackTrace());
			// TODO Auto-generated catch block
			e1.printStackTrace();
			f=new File("c:\\");
		}

		String s = "";
		try {
			s = f.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
		
		/*
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = "";
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, decodedPath);
		return decodedPath;
		*/
	}
	
}
