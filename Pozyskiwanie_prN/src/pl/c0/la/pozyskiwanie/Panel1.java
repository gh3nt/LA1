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
	public final MainFrame parent;

	/**
	 * Create the panel.
	 */
	public Panel1(MainFrame parent) {
		
		this.parent = parent;
				
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
		btnOtwrz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz.setBounds(263, 77, 89, 23);
		add(btnOtwrz);
		
		JButton btnNewButton = new JButton("Przywróæ domyœlny");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(361, 77, 140, 23);
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
		btnOtwrz_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOtwrz_1.setBounds(263, 160, 89, 23);
		add(btnOtwrz_1);
		
		JButton btnPrzywrDomylny = new JButton("Przywr\u00F3\u0107 domy\u015Blny");
		btnPrzywrDomylny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPrzywrDomylny.setBounds(361, 160, 140, 23);
		add(btnPrzywrDomylny);
		
		JButton btnRozpocznij = new JButton("Rozpocznij");
		btnRozpocznij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rozpocznij();
			}
		});
		btnRozpocznij.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRozpocznij.setForeground(Color.RED);
		btnRozpocznij.setBackground(UIManager.getColor("Button.background"));
		btnRozpocznij.setBounds(263, 231, 103, 35);
		add(btnRozpocznij);

	}
	protected void rozpocznij(){
		parent.pobierzNormyDoTabeli();
	}
}
