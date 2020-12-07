package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class confirmacionVenta extends JFrame {

	private JPanel contentPane;

	public confirmacionVenta(int tipo, int codigo) { //tipo 1 es contado, 2 credito, 3 debito
		setTitle("Infomarci\u00F3n venta N\u00B0" + codigo);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 318, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		if(tipo == 1) {
			VentaContadoView vc = AdministradorVentas.getAdministradorVentas().buscarVentaContadoView(codigo);			
			JLabel texto = new JLabel("El total de la venta N\u00B0" + codigo + " es: $" + vc.getTotal());
			texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			texto.setBounds(33, 11, 264, 56);
			contentPane.add(texto);
		} else if (tipo == 2) {
			VentaCreditoView vcred = AdministradorVentas.getAdministradorVentas().buscarVentaCreditoView(codigo);			
			JLabel texto = new JLabel("El total de la venta N\u00B0" + codigo + " es: $" + vcred.getTotal());
			texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			texto.setBounds(33, 11, 264, 56);
			contentPane.add(texto);
		} else {
			VentaDebitoView vdeb = AdministradorVentas.getAdministradorVentas().buscarVentaDebitoView(codigo);			
			JLabel texto = new JLabel("El total de la venta N\u00B0" + codigo + " es: $" + vdeb.getTotal());
			texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			texto.setBounds(33, 11, 264, 56);
			contentPane.add(texto);
		}
		
		confirmacionVenta vactual = this;
		
		JButton back = new JButton("< Atr\u00E1s");
		back.setBounds(165, 78, 89, 23);
		contentPane.add(back);
		back.addActionListener(new ActionListener() { //boton atras
			public void actionPerformed(ActionEvent e) {
				if(tipo == 1) {
					VentanaIntermediaVentas vprevia = new VentanaIntermediaVentas(1,codigo, true);
					vprevia.setVisible(true);
					vactual.setVisible(false);
				}else if(tipo == 2) {
					VentanaIntermediaVentas vprevia = new VentanaIntermediaVentas(2,codigo, true);
					vprevia.setVisible(true);
					vactual.setVisible(false);					
				} else {
					VentanaIntermediaVentas vprevia = new VentanaIntermediaVentas(3,codigo, true);
					vprevia.setVisible(true);
					vactual.setVisible(false);					
				}
			}
		});
		
		
		JButton confirm = new JButton("Confirmar");
		confirm.setBounds(43, 78, 112, 23);
		contentPane.add(confirm);
		
		
		confirm.addActionListener(new ActionListener() { //boton confirmar
			public void actionPerformed(ActionEvent e) {
				if(tipo == 1) {					
					AdministradorVentas.getAdministradorVentas().cerrarVentaContado(codigo);
					VentVentas ventana = new VentVentas();
					vactual.setVisible(false);
					ventana.setVisible(true);
					
				} else if (tipo == 2) {
					finalizarVentaCredito ventana = new finalizarVentaCredito(codigo);
					ventana.setVisible(true);
					vactual.setVisible(false);
					
				} else {
					finalizarVentaDebito ventana = new finalizarVentaDebito(codigo);
					ventana.setVisible(true);
					vactual.setVisible(false);
				}					
			}
		});
		
	}
}
