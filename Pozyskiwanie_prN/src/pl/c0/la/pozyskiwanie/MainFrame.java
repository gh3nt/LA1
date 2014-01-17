package pl.c0.la.pozyskiwanie;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	//adres strony ankiety powszechnej
	private String urlAnkiety = "https://pzn.pkn.pl/pzn-share/page/pzn/polling";
	
	//sciezka dost�pu do pliku Excela, gdzie b�d� wklejane informacje
	private String plik = "c:\\temp\\test.xlsx";
	
	//sciezka dostepu do pliku zserializowanej listy projekt�w norm wcze�niej przetwarzanych
	private String plikPrzetwarzane = "c:\\temp\\przetwarzane.list";
	
	//sciezka dost�pu do pliku txt, gdzie b�d� informacje o zakresie akredytacji ITB
	private String plikAkredytacja = "c:\\temp\\akredytacja.txt";
		
	//sciezka dost�pu do pliku txt, gdzie b�d� informacje o zakresie akredytacji ITB
	private String plikZharmonizowane = "c:\\temp\\zharmonizowane.txt";
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 378);
		this.setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblPozyskiwanieInformacjiO = new JLabel("Pozyskiwanie informacji o projektach norm poddanych ankiecie powszechnej PKN");
		lblPozyskiwanieInformacjiO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPozyskiwanieInformacjiO.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblPozyskiwanieInformacjiO);
		
		JLabel lblAutorukaszAdamus = new JLabel("Autor: \u0141ukasz Adamus");
		lblAutorukaszAdamus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAutorukaszAdamus.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblAutorukaszAdamus);
		
		//JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(new Panel1(this));
	}
	
	/**
	 * Przekazuje do panelu 2 list� norm z pliku excela do tabeli, zmienia panel dolny na ten z tabel�
	 */
	public void krok2(){
		
		//usu� z listy projekt�w norm te, ktore znajduj� si� na li�cie wcze�niej przetwarzanych
		FileManager fm = new FileManager();	
		MyArrayList listaPN = fm.pobierzProjekty(plik);
		MyArrayList przetwarzane = (MyArrayList)fm.deserializeObject(plikPrzetwarzane);
		listaPN.usunProjekty(przetwarzane);
		
		//zaznacz normy w zakresie akredytacji i zharmonizowane
		listaPN.ustawAkredytacjaZharmonizowane(plikAkredytacja, plikZharmonizowane);
		
		
		//utw�rz panel 2
		panel2 = new Panel2(listaPN, this);
		
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
		return plik;
	}
	
}
