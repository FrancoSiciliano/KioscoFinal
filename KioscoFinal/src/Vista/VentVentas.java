package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;
import Controlador.AdministradorVentas;

public class VentVentas extends JFrame {

	private JPanel contentPane;
	
	public VentVentas() {
		setResizable(false);
		setTitle("Administrar Ventas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 461, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fecha = new JLabel("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		fecha.setBounds(10, 11, 175, 14);
		contentPane.add(fecha);
		
		VentVentas vactual = this;
		
		JButton back = new JButton("< Atras");
		back.setBounds(345, 294, 89, 23);
		contentPane.add(back);
		
		
		JButton contado = new JButton("Iniciar Venta al Contado");
		contado.addActionListener(new ActionListener() { //boton iniciar venta contado
			public void actionPerformed(ActionEvent e) {
				int codigo = AdministradorVentas.getAdministradorVentas().iniciarVentaContado();
				VentanaIntermediaVentas ventana = new VentanaIntermediaVentas(1, codigo, false);
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		contado.setBounds(81, 42, 276, 52);
		contentPane.add(contado);
		
		JButton credito = new JButton("Iniciar Venta con Tarjeta de Cr\u00E9dito");
		credito.addActionListener(new ActionListener() { //boton iniciar venta credito
			public void actionPerformed(ActionEvent e) {
				int codigo = AdministradorVentas.getAdministradorVentas().iniciarVentaTarjetaCredito(0, "");
				VentanaIntermediaVentas ventana = new VentanaIntermediaVentas(2,codigo, false);
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		credito.setBounds(81, 105, 276, 52);
		contentPane.add(credito);
		
		JButton debito = new JButton("Iniciar Venta con Tarjeta de D\u00E9bito");
		debito.addActionListener(new ActionListener() {// boton iniciar venta debito
			public void actionPerformed(ActionEvent e) {
				int codigo = AdministradorVentas.getAdministradorVentas().iniciarVentaTarjetaDebito("", "");
				VentanaIntermediaVentas ventana = new VentanaIntermediaVentas(3,codigo, false);
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		debito.setBounds(81, 168, 276, 52);
		contentPane.add(debito);
		
		if (!AdministradorCaja.getAdministradorCaja().cajaAbierta()) {
			contado.setEnabled(false);
			debito.setEnabled(false);
			credito.setEnabled(false);
		}
		
		JButton reporteMes = new JButton("Mostrar Reporte Mensual de Ventas");
		reporteMes.setBounds(81, 231, 276, 52);
		contentPane.add(reporteMes);
		
		reporteMes.addActionListener(new ActionListener() { // boton reporte mensual de ventas
			public void actionPerformed(ActionEvent e) {
				rangoFechas ventana = new rangoFechas();
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		
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
