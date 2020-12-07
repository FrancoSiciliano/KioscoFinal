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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class finalizarVentaCredito extends JFrame {

	private JPanel contentPane;
	private JTextField codAut;
	private JTextField codSeg;

	public finalizarVentaCredito(int codVenta) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Finalizando Venta con Tarjeta de Cr\u00E9dito");
		setResizable(false);
		setBounds(100, 100, 438, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ca = new JLabel("C\u00F3digo de Autorizaci\u00F3n");
		ca.setBounds(36, 38, 133, 14);
		contentPane.add(ca);
		
		JLabel cs = new JLabel("C\u00F3digo de Seguridad");
		cs.setBounds(36, 63, 133, 14);
		contentPane.add(cs);
		
		codAut = new JTextField();
		codAut.setBounds(179, 35, 182, 20);
		contentPane.add(codAut);
		codAut.setColumns(10);
		
		codSeg = new JTextField();
		codSeg.setBounds(179, 66, 182, 20);
		contentPane.add(codSeg);
		codSeg.setColumns(10);
		
		finalizarVentaCredito vactual = this;
		
		JButton back = new JButton("< Atras");
		back.setBounds(318, 115, 89, 23);
		contentPane.add(back);
		
		JButton end = new JButton("Finalizar");
		end.addActionListener(new ActionListener() { //boton finalizar
			public void actionPerformed(ActionEvent e) {
				try {
					if (codSeg.getText().isBlank() || codAut.getText().isBlank()) {
						JOptionPane.showMessageDialog(null,  "No deje espacios en blanco","Error",JOptionPane.ERROR_MESSAGE);						
					} else {
						int codSeguridad = Integer.parseInt(codSeg.getText());
						AdministradorVentas.getAdministradorVentas().cerrarVentaCredito(codVenta, codAut.getText(), codSeguridad);
						VentVentas ventana = new VentVentas();
						vactual.setVisible(false);
						ventana.setVisible(true);
					}
					
					
				}catch (NumberFormatException ex) {
					try {
						float s = Float.parseFloat(codSeg.getText());
						JOptionPane.showMessageDialog(null,  "El c\u00f3digo de seguridad no puede ser un n\u00famero no entero","Error num\u00e9rico",JOptionPane.ERROR_MESSAGE);						
					
					} catch (NumberFormatException ex2) {
						JOptionPane.showMessageDialog(null,  "El c\u00f3digo de Seguridad no puede contener letras o espacios","Error",JOptionPane.ERROR_MESSAGE);						
					}
				}
			}
		});
		end.setBounds(219, 115, 89, 23);
		contentPane.add(end);
		
		
		back.addActionListener(new ActionListener() { // boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentanaIntermediaVentas vprevia = new VentanaIntermediaVentas(2,codVenta, true);
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
	}
}
