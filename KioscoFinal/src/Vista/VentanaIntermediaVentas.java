package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIntermediaVentas extends JFrame {

	private JPanel contentPane;
	private int tipo;

	public VentanaIntermediaVentas(int tipo, int codigo, boolean hayProductos) { //tipo 1 es contado, tipo 2 es credito tipo 3 es debito
		
		this.tipo = tipo;
		
		if(tipo ==1) {
			
			setTitle("Administrando Venta N\u00B0" + codigo +" al contado");			
		} else if (tipo == 2) {
			setTitle("Administrando Venta N\u00B0"+ codigo +" con Tarjeta de Cr\u00e9dito");			
		} else {
			setTitle("Administrando Venta N\u00B0"+ codigo +" con Tarjeta de D\u00e9bito");						
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 647, 104);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		VentanaIntermediaVentas vactual = this;
		
		JButton agprod = new JButton("Agregar un Producto");
		agprod.addActionListener(new ActionListener() { //boton agregar un producto
			public void actionPerformed(ActionEvent e) {
				agregarProductos ventana = new agregarProductos(tipo, codigo);
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		agprod.setBounds(10, 11, 197, 43);
		contentPane.add(agprod);
		
		JButton cerrarVenta = new JButton("Cerrar la Venta");
		cerrarVenta.addActionListener(new ActionListener() { //boton cerrar la venta
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(tipo == 1) {
						if(!hayProductos) {
							throw new AssertionError();
						}
						confirmacionVenta ventana = new confirmacionVenta(1,codigo);
						vactual.setVisible(false);
						ventana.setVisible(true);
						
					} else if (tipo == 2) {
						if(!hayProductos) {
							throw new AssertionError();
						}
						confirmacionVenta ventana = new confirmacionVenta(2,codigo);
						ventana.setVisible(true);
						vactual.setVisible(false);
						
					} else {
						if(!hayProductos) {
							throw new AssertionError();
						}
						confirmacionVenta ventana = new confirmacionVenta(3,codigo);
						ventana.setVisible(true);
						vactual.setVisible(false);
					}										
				} catch(AssertionError ex ) {
					JOptionPane.showMessageDialog(null,  "No puede cerrar una venta sin productos","Venta no cerrada",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		cerrarVenta.setBounds(217, 11, 197, 43);
		contentPane.add(cerrarVenta);
		
		JButton cancel = new JButton("Cancelar la Venta");
		cancel.addActionListener(new ActionListener() { // boton cancelar venta
			public void actionPerformed(ActionEvent e) {
				
				if(tipo == 1) {
					AdministradorVentas.getAdministradorVentas().cancelarVentaContado();											
				} else if (tipo == 2) {
					AdministradorVentas.getAdministradorVentas().cancelarVentaCredito();											
				} else {
					AdministradorVentas.getAdministradorVentas().cancelarVentaDebito();																	
				}
				
				VentVentas ventana = new VentVentas();
				vactual.setVisible(false);
				ventana.setVisible(true);
			}
		});
		cancel.setBounds(424, 11, 197, 43);
		contentPane.add(cancel);
	}

}
