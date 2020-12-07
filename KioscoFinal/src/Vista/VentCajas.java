package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class VentCajas extends JFrame {

	private JPanel contentPane;

	public VentCajas() {
		setResizable(false);
		setTitle("Administrador de Caja");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fecha = new JLabel("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		fecha.setBounds(10, 11, 175, 14);
		contentPane.add(fecha);
		
		VentCajas vactual = this;
		
		JButton open = new JButton("Abrir Caja");
		open.setBounds(109, 65, 202, 39);
		contentPane.add(open);
		
		open.addActionListener(new ActionListener() { //boton abrir caja
			public void actionPerformed(ActionEvent evt) {
				abrirCaja ventana = new abrirCaja();
				ventana.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		JButton close = new JButton("Cerrar Caja");
		close.setBounds(109, 115, 202, 39);
		contentPane.add(close);
		
		if(!AdministradorCaja.getAdministradorCaja().cajaAbierta()) {
			close.setEnabled(false);
		}
		
		close.addActionListener(new ActionListener() { // boton cerrar caja
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<Vector<String>> vtasContado = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasContado(LocalDate.now());
					Vector<Vector<String>> vtasDebito = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasDebito(LocalDate.now());
					Vector<Vector<String>> vtasCredito = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasCredito(LocalDate.now());					
					cajaView caja = AdministradorCaja.getAdministradorCaja().buscarCajaView(LocalDate.now());
					
					AdministradorCaja.getAdministradorCaja().cerrarCaja();
					
					JOptionPane.showMessageDialog(null,  "Se ha cerrado la caja con éxito","Caja Cerrada",JOptionPane.INFORMATION_MESSAGE);
					
					listadoVentas ventana = new listadoVentas(2,vtasContado, vtasDebito, vtasCredito,caja);
					ventana.setVisible(true);
					vactual.setVisible(false);
					
					
				} catch (NullPointerException np) {
					JOptionPane.showMessageDialog(null,  "No hay cajas para cerrar","Caja no cerrada",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton listado = new JButton("Información de la caja");
		listado.setBounds(109, 165, 202, 39);
		contentPane.add(listado);
		
		listado.addActionListener(new ActionListener() { // boton informacion de la caja
			public void actionPerformed(ActionEvent e) {
			
				try {					
					Vector<Vector<String>> vtasContado = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasContado(LocalDate.now());
					Vector<Vector<String>> vtasDebito = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasDebito(LocalDate.now());
					Vector<Vector<String>> vtasCredito = AdministradorCaja.getAdministradorCaja().generarListadoVentasDiariasCredito(LocalDate.now());
					cajaView caja = AdministradorCaja.getAdministradorCaja().buscarCajaView(LocalDate.now());
					listadoVentas ventana = new listadoVentas(1,vtasContado, vtasDebito, vtasCredito, caja);
					ventana.setVisible(true);
					vactual.setVisible(false);				
					
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null,  "No hay ninguna caja abierta","No hay una caja abierta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton back = new JButton("< Atras");
		back.setBounds(335, 227, 89, 23);
		contentPane.add(back);
		
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				MenuPrincipal vprevia = new MenuPrincipal();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
	}
	
	
}
