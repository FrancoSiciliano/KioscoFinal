package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class finalizarVentaDebito extends JFrame {

	private JPanel contentPane;
	private JTextField banco;
	private JTextField codAut;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public finalizarVentaDebito(int codVenta) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Finalizando Venta D\u00E9bito");
		setBounds(100, 100, 488, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		banco = new JTextField();
		banco.setBounds(167, 26, 196, 20);
		contentPane.add(banco);
		banco.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Banco");
		lblNewLabel.setBounds(25, 57, 40, 20);
		contentPane.add(lblNewLabel);
		
		codAut = new JTextField();
		codAut.setBounds(167, 57, 196, 20);
		contentPane.add(codAut);
		codAut.setColumns(10);
		
		lblNewLabel_1 = new JLabel("C\u00F3digo de autorizaci\u00F3n");
		lblNewLabel_1.setBounds(25, 29, 160, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton back = new JButton("< Atras");
		back.setBounds(358, 101, 89, 23);
		contentPane.add(back);
		
		finalizarVentaDebito vactual = this; //ventana actual
		
		JButton end = new JButton("Finalizar");
		end.addActionListener(new ActionListener() { // boton finalizar
			public void actionPerformed(ActionEvent e) {
				if (banco.getText().isBlank() || codAut.getText().isBlank()) {
					JOptionPane.showMessageDialog(null,  "No se puede dejar espacios en blanco","Llene los campos",JOptionPane.ERROR_MESSAGE);
				}
				else {
					AdministradorVentas.getAdministradorVentas().cerrarVentaDebito(codVenta, codAut.getText(), banco.getText());
					VentVentas ventana = new VentVentas();
					ventana.setVisible(true);
					vactual.setVisible(false);
				}					
			}
		});
		
		end.setBounds(235, 101, 113, 23);
		contentPane.add(end);
		
		back.addActionListener(new ActionListener() { //boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentanaIntermediaVentas vprevia = new VentanaIntermediaVentas(3,codVenta, true);
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
	}
}
