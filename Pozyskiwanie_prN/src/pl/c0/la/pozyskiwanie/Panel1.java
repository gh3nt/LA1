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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(23, 78, 230, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAdresStronyAnkiety = new JLabel("Adres strony ankiety powszechnej:");
		lblAdresStronyAnkiety.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAdresStronyAnkiety.setBounds(23, 53, 182, 14);
		add(lblAdresStronyAnkiety);
		
		JButton btnOtwrz = new JButton("Otw\u00F3rz");
		btnOtwrz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.otworzStrone(textField.getText());
			}
		});
		btnOtwrz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz.setBounds(263, 77, 89, 23);
		add(btnOtwrz);
		
		JButton btnNewButton = new JButton("Przywróæ domyœlny");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uzupelnijPole1();
			}
		});
		btnNewButton.setBounds(263, 100, 140, 23);
		add(btnNewButton);
		
		JLabel lblNazwaPlikuExcela = new JLabel("Nazwa pliku Excela z informacjami o projektach:");
		lblNazwaPlikuExcela.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNazwaPlikuExcela.setBounds(23, 136, 230, 14);
		add(lblNazwaPlikuExcela);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setBounds(23, 161, 230, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnOtwrz_1 = new JButton("Otw\u00F3rz");
		btnOtwrz_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.otworzPlikExcela(textField_1.getText());
			}
		});
		btnOtwrz_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz_1.setBounds(263, 160, 89, 23);
		add(btnOtwrz_1);
		
		JButton btnPrzywrDomylny = new JButton("Przywr\u00F3\u0107 domy\u015Blny");
		btnPrzywrDomylny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uzupelnijPole2();
			}
		});
		btnPrzywrDomylny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPrzywrDomylny.setBounds(263, 183, 140, 23);
		add(btnPrzywrDomylny);
		
		JButton btnRozpocznij = new JButton("Rozpocznij");
		btnRozpocznij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rozpocznij(textField_1.getText());
			}
		});
		btnRozpocznij.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRozpocznij.setForeground(Color.RED);
		btnRozpocznij.setBackground(UIManager.getColor("Button.background"));
		btnRozpocznij.setBounds(23, 244, 103, 35);
		add(btnRozpocznij);
		
		JButton otworzAkredytacja = new JButton("zakres akredytacji");
		otworzAkredytacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.otworzPlik(parent.getPlikAkredytacja());
			}
		});
		otworzAkredytacja.setBounds(495, 11, 157, 23);
		otworzAkredytacja.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(otworzAkredytacja);
		
		JButton normyZharmonizowane = new JButton("normy zharmonizowane");
		normyZharmonizowane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.otworzPlik(parent.getPlikZharmonizowane());
			}
		});
		normyZharmonizowane.setBounds(495, 45, 157, 23);
		normyZharmonizowane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(normyZharmonizowane);
		
		JButton wyczyscPrzetwarzane = new JButton("wyczy\u015B\u0107 przetwarzane");
		wyczyscPrzetwarzane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.usunPlik(parent.getPlikPrzetwarzane());
			}
		});
		wyczyscPrzetwarzane.setBounds(495, 77, 157, 23);
		wyczyscPrzetwarzane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(wyczyscPrzetwarzane);
		
		uzupelnijPole1();
		uzupelnijPole2();

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
