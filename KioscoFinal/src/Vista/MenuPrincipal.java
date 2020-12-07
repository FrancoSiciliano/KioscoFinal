package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;
import Controlador.AdministradorProducto;

import java.time.format.*;

import javax.swing.JButton;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		
		setResizable(false);
		
		setTitle("Men\u00FA principal - Kiosco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MenuPrincipal vactual = this; //ventana actual
		
		JLabel fecha = new JLabel("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		fecha.setBounds(10, 11, 175, 14);
		contentPane.add(fecha);
	
		
		JButton botonCaja = new JButton("Caja");
		botonCaja.setBounds(94, 106, 228, 44);
		contentPane.add(botonCaja);
		
		botonCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				VentCajas ventana1 = new VentCajas();
				ventana1.setVisible(true);
				vactual.setVisible(false);
				
			}
		});
		
		JButton botonProductos = new JButton("Productos");
		botonProductos.setBounds(94, 51, 228, 44);
		contentPane.add(botonProductos);
		
		botonProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				VentProductos ventana2 = VentProductos.getVentanaProductos();
				ventana2.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		JButton botonVentas = new JButton("Ventas");
		botonVentas.setBounds(94, 161, 228, 44);
		contentPane.add(botonVentas);
		
		
		botonVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				VentVentas ventana2 = new VentVentas();
				ventana2.setVisible(true);
				vactual.setVisible(false);
				
			}
		});
		
		
	}
}
