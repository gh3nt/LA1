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
	
	//sciezka dostêpu do pliku Excela, gdzie bêd¹ wklejane informacje
	private String plik = "c:\\temp\\test.xlsx";
	
	//sciezka dostepu do pliku zserializowanej listy projektów norm wczeœniej przetwarzanych
	private String plikPrzetwarzane = "c:\\temp\\przetwarzane.list";
	
	//splitPane, na którym bêd¹ zmieniane panele podrzêdne
	private JSplitPane splitPane;
	
	//panel dolny wystêpuj¹cy jako drugi w kolejnoœci, z tabel¹, w której mo¿na wkazaæ
	//normy do ankietyzacji wewnêtrznej
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
	 * Przekazuje do panelu 2 listê norm z pliku excela do tabeli, zmienia panel dolny na ten z tabel¹
	 */
	public void krok2(MyArrayList listaPN){
		
		//usuñ z listy projektów norm te, ktore znajduj¹ siê na liœcie przetwarzanych
		FileManager fm = new FileManager();		
		MyArrayList przetwarzane = (MyArrayList)fm.deserializeObject(plikPrzetwarzane);
		listaPN.usunProjekty(przetwarzane);
		System.out.println("usunieto przetwarzane");
		System.out.println("size " + listaPN.size());
		
		//utwórz panel 2
		panel2 = new Panel2(listaPN, this);
		
		this.addComponentListener(new ComponentListener(){
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				System.out.println("resize w listenerze pocz¹tek");
				panel2.resizeScrollPane();
				System.out.println("resize w listenerze koniec");
				
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
		System.out.println("pokaz panel 2");
	}
	
	/**
	 * wyswietla nowy panel 2 w dolnej czêœci g³ównego panelu
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
	 * zwraca œcie¿kê dostêpu do pliku Excela, do którego maj¹ byæ wklejone dan odczytywane póŸniej przez sytem
	 */
	
	public String getPlik(){
		return plik;
	}
	
}
