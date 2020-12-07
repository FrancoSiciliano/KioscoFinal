package Vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class abrirCaja extends JFrame {

	private JPanel contentPane;
	private JTextField saldoIngresado;


	public abrirCaja() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setTitle("Abrir Caja");
		setResizable(false);
		setBounds(100, 100, 411, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton back = new JButton("< Atras");
		back.setBounds(292, 96, 89, 26);
		contentPane.add(back);
		
		saldoIngresado = new JTextField();
		saldoIngresado.setBounds(182, 42, 160, 30);
		contentPane.add(saldoIngresado);
		saldoIngresado.setColumns(10);
		
		JLabel saldo = new JLabel("Ingrese saldo:");
		saldo.setBounds(52, 47, 160, 21);
		contentPane.add(saldo);
		
		JLabel date = new JLabel("Fecha de creaci\u00F3n:  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		date.setBounds(52, 12, 351, 23);
		contentPane.add(date);
		
		JButton open = new JButton("Confirmar");
		
		open.setBounds(182, 96, 98, 26);
		contentPane.add(open);
		
		abrirCaja vactual = this;
		
		back.addActionListener(new ActionListener() { //boton Atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentCajas vprevia = new VentCajas();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		open.addActionListener(new ActionListener() { //boton para finalizar
			public void actionPerformed(ActionEvent evt) {
				
				try {
					float saldo = Float.parseFloat(saldoIngresado.getText());
					if (saldo >= 0) {
						boolean respuesta = AdministradorCaja.getAdministradorCaja().abrirCaja(saldo);
						if (respuesta) {
							JOptionPane.showMessageDialog(null,  "La caja fue creada con \u00e9xito","Operaci\u00f3n exitosa",JOptionPane.INFORMATION_MESSAGE);
							VentCajas vprevia = new VentCajas();
							vprevia.setVisible(true);
							vactual.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null,  "Ya hay una caja activa para esta fecha","No se pudo finalizar la operaci\u00f3n",JOptionPane.ERROR_MESSAGE);
							VentCajas vprevia = new VentCajas();
							vprevia.setVisible(true);
							vactual.setVisible(false);
						}						
					} else {
						JOptionPane.showMessageDialog(null,  "No puede ingresar saldo negativo","No se pudo finalizar la operacion",JOptionPane.ERROR_MESSAGE);						
					}
					
					
				} catch (NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null,  "No ingrese letras o espacios en el campo del saldo","No se pudo finalizar la operacion",JOptionPane.ERROR_MESSAGE);
					saldoIngresado.setText("");
				}
				
			}
		
		});
	}
}
