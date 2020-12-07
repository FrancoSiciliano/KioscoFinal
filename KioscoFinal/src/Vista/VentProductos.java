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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProducto;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class VentProductos extends JFrame {

	private JPanel contentPane;
	private static VentProductos instancia;

	/**
	 * Create the frame.
	 */
	private VentProductos() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setResizable(false);
		setTitle("Administrador de Productos");
		setBounds(100, 100, 458, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		VentProductos vactual = this; //ventana actual
		
		JLabel fecha = new JLabel("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		fecha.setBounds(10, 11, 175, 14);
		contentPane.add(fecha);
		
		JButton crearProd = new JButton("Crear Producto");
		crearProd.setBounds(109, 47, 202, 33);
		contentPane.add(crearProd);
		
		crearProd.addActionListener(new ActionListener() { // boton crear producto
			public void actionPerformed(ActionEvent evt) 
			{
				CrearProducto ventana2 = new CrearProducto();
				ventana2.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		JButton modProd = new JButton("Modificar Producto");
		modProd.setBounds(109, 91, 202, 33);
		contentPane.add(modProd);
		
		modProd.addActionListener(new ActionListener() { // boton modificar producto
			public void actionPerformed(ActionEvent evt) {
				modificarProducto ventana = new modificarProducto();
				ventana.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		
		JButton elimProd = new JButton("Eliminar Producto");
		elimProd.setBounds(109, 135, 202, 33);
		contentPane.add(elimProd);
		
		elimProd.addActionListener(new ActionListener() { // boton elminiar producto
			public void actionPerformed(ActionEvent e) {
				eliminarProducto ventana = new eliminarProducto();
				ventana.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		JButton listadoStock = new JButton("Listado Stock M\u00EDnimo");
		listadoStock.setBounds(109, 179, 202, 33);
		contentPane.add(listadoStock);
		
		listadoStock.addActionListener(new ActionListener() { //boton listado stock minimo
			public void actionPerformed(ActionEvent evt) { 
				
				Vector<Vector<String>> data = AdministradorProducto.getAdministradorProducto().getDatosListadoStockMinimo();
				
				if (!data.isEmpty()) {
					listadoStockMinimo ventana = new listadoStockMinimo(data);
					ventana.setVisible(true);
					vactual.setVisible(false);					
				}
				
				else {
					JOptionPane.showMessageDialog(null,  "En este momento no hay productos con stock minimo","Aviso",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	
		
		
		JButton back = new JButton("< Atras");
		back.setBounds(343, 267, 89, 23);
		contentPane.add(back);
		
		
		
		back.addActionListener(new ActionListener() { //boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				MenuPrincipal vprevia = new MenuPrincipal();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		JButton invent = new JButton("Inventario");
		invent.setBounds(109, 224, 202, 33);
		contentPane.add(invent);
		
		invent.addActionListener(new ActionListener() {// boton Inventario
			public void actionPerformed(ActionEvent e) {
				inventario ventana = new inventario(AdministradorProducto.getAdministradorProducto().datosInventario());
				ventana.setVisible(true);
				vactual.setVisible(false);
				
			}
		});
		
	}
	
	public static VentProductos getVentanaProductos() {
		if (instancia == null) {
			instancia = new VentProductos();
			return instancia;
		}
		else {
			return instancia;
		}
	}
}
