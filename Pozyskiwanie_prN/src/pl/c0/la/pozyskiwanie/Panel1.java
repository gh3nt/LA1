package pl.c0.la.pozyskiwanie;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Panel1 extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	
	//nadrzêdna ramka (JFrame)
	public MainFrame parent;
	
	//kontroler panelu
	private Panel1Controller controller; 

	/**
	 * Create the panel.
	 */
	public Panel1(final MainFrame parent) {
		
		this.parent = parent;
		controller = new Panel1Controller(parent);
				
		setLayout(null);
		
		//kroki procedury
		
		//krok 1
		JLabel lblOtwrzStron = new JLabel("1. Otwórz stronê WWWW ankiety powszechnej PKN, zaznacz wszystko i skopiuj do schowka (ctrl + c)");
		lblOtwrzStron.setBounds(10, 11, 629, 35);
		add(lblOtwrzStron);
		
		JLabel lblAdresStronyAnkiety = new JLabel("Adres strony ankiety powszechnej:");
		lblAdresStronyAnkiety.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAdresStronyAnkiety.setBounds(23, 43, 182, 14);
		add(lblAdresStronyAnkiety);
		
		JButton btnOtwrz = new JButton("1. Otwórz stronê");
		btnOtwrz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.otworzStrone(textField.getText());
			}
		});
		//btnOtwrz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz.setBounds(263, 67, 140, 23);
		add(btnOtwrz);
		
		JButton btnNewButton = new JButton("Przywróæ domyœlny");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uzupelnijPole1();
			}
		});
		btnNewButton.setBounds(263, 90, 140, 23);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(23, 68, 230, 20);
		add(textField);
		textField.setColumns(10);
		
		//krok 2		
		JLabel lblOtworzTymczasowy = new JLabel("2. Otwórz tymczasowy plik xlsx i wklej informacje (ctrl+v) w polu A1");
		lblOtworzTymczasowy.setBounds(10, 123, 503, 14);
		add(lblOtworzTymczasowy);
		
		JLabel lblNazwaPlikuExcela = new JLabel("Nazwa pliku Excela z informacjami o projektach:");
		lblNazwaPlikuExcela.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNazwaPlikuExcela.setBounds(23, 148, 380, 14);
		add(lblNazwaPlikuExcela);
		
		JButton btnOtwrz_1 = new JButton("2. Otwórz plik XLSX");
		btnOtwrz_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.otworzPlikExcela(textField_1.getText());
			}
		});
		//btnOtwrz_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz_1.setBounds(263, 172, 140, 23);
		add(btnOtwrz_1);
		
		JButton btnPrzywrDomylny = new JButton("Przywr\u00F3\u0107 domy\u015Blny");
		btnPrzywrDomylny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uzupelnijPole2();
			}
		});
		btnPrzywrDomylny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPrzywrDomylny.setBounds(263, 195, 140, 23);
		add(btnPrzywrDomylny);
		
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setBounds(23, 173, 230, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		//krok 3		
		JLabel lblZapiszPlik = new JLabel("3. Zamknij plik XLSX zapisuj¹c wprowadzone zmiany (nie zmieniaj nazwy ani katalogu!)");
		lblZapiszPlik.setBounds(10, 234, 503, 14);
		add(lblZapiszPlik);
		
		//krok 4
		JButton btnRozpocznij = new JButton("4. Rozpocznij");
		btnRozpocznij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rozpocznij(textField_1.getText());
			}
		});
		//btnRozpocznij.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRozpocznij.setForeground(Color.RED);
		btnRozpocznij.setBackground(UIManager.getColor("Button.background"));
		btnRozpocznij.setBounds(10, 271, 120, 35);
		add(btnRozpocznij);
		
		//uzupe³nij pola tekstowe domyœlnymi danymi
		uzupelnijPole1();
		uzupelnijPole2();
		
		//dane systemowe
		JLabel lblWprowadzanieDanych = new JLabel("Dane systemowe");
		lblWprowadzanieDanych.setBounds(32, 341, 131, 14);
		lblWprowadzanieDanych.setFont(new Font("Tahoma", Font.PLAIN, 11));;
		add(lblWprowadzanieDanych);
		

		
		JButton btnNewButton_1 = new JButton("zakres akredytacji zc");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					controller.otworzPlik(parent.getPlikAkredytacjaZC());
			}
		});
		btnNewButton_1.setBounds(32, 366, 157, 23);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 338, 658, 2);
		add(separator);
		
		JButton otworzAkredytacja = new JButton("zakres akredytacji LN");
		otworzAkredytacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.otworzPlik(parent.getPlikAkredytacja());
			}
		});
		otworzAkredytacja.setBounds(199, 366, 157, 23);
		otworzAkredytacja.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(otworzAkredytacja);
		
		JButton normyZharmonizowane = new JButton("normy zharmonizowane");
		normyZharmonizowane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.otworzPlik(parent.getPlikZharmonizowane());
			}
		});
		normyZharmonizowane.setBounds(366, 366, 157, 23);
		normyZharmonizowane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(normyZharmonizowane);
		
		JButton wyczyscPrzetwarzane = new JButton("wyczy\u015B\u0107 przetwarzane");
		wyczyscPrzetwarzane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.usunPlik(parent.getPlikPrzetwarzane());
			}
		});
		wyczyscPrzetwarzane.setBounds(533, 366, 157, 23);
		wyczyscPrzetwarzane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(wyczyscPrzetwarzane);
		
	}
	
	//uzupelnij pole adresu www domyœlnym adresem
	private void uzupelnijPole1() {
		//adres www
		textField.setText(parent.getAdresAnkiety());
	}
	
	//uzupelnij pole sciezki dostêpu do pliku
		private void uzupelnijPole2() {
			//sciezka i nazwa pliku Excela
			textField_1.setText(parent.getPlik());
		}
}
