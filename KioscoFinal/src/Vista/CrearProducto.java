package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;
import Controlador.AdministradorProducto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CrearProducto extends JFrame {

	private JPanel contentPane;
	private JTextField codBarra;
	private JTextField descripcion;
	private JTextField pre;
	private JTextField stk;
	private JTextField stkmin;

	/**
	 * Create the frame.
	 */
	public CrearProducto() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		Vector<JTextField> data = new Vector<JTextField>();  //vector que contiene todos los campos de texto.
		
		setTitle("Crear Producto");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		codBarra = new JTextField();
		codBarra.setBounds(191, 35, 203, 20);
		contentPane.add(codBarra);
		codBarra.setColumns(10);
		data.add(codBarra);
		
		
		descripcion = new JTextField();
		descripcion.setBounds(191, 62, 203, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		data.add(descripcion);
		
		pre = new JTextField();
		pre.setBounds(191, 93, 203, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		data.add(pre);
		
		stk = new JTextField();
		stk.setBounds(191, 124, 203, 20);
		contentPane.add(stk);
		stk.setColumns(10);
		data.add(stk);
		
		stkmin = new JTextField();
		stkmin.setBounds(191, 155, 203, 20);
		contentPane.add(stkmin);
		stkmin.setColumns(10);
		data.add(stk);
		
		JLabel cod = new JLabel("C\u00F3digo de Barra");
		cod.setBounds(55, 38, 110, 14);
		contentPane.add(cod);
		
		JLabel descrp = new JLabel("Descripci\u00F3n");
		descrp.setBounds(55, 65, 110, 14);
		contentPane.add(descrp);
		
		JLabel precio = new JLabel("Precio");
		precio.setBounds(55, 96, 110, 14);
		contentPane.add(precio);
		
		JLabel stock = new JLabel("Stock");
		stock.setBounds(55, 127, 110, 14);
		contentPane.add(stock);
		
		JLabel stockmin = new JLabel("Stock M\u00EDnimo");
		stockmin.setBounds(55, 158, 110, 14);
		contentPane.add(stockmin);
		
		JButton finalizar = new JButton("Crear Producto");
		finalizar.setBounds(132, 211, 149, 23);
		contentPane.add(finalizar);
		
		JButton back = new JButton("< Atras");
		back.setBounds(291, 211, 89, 23);
		contentPane.add(back);
		
		CrearProducto vactual = this; //ventana actual
		
		back.addActionListener(new ActionListener() { //boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentProductos vprevia = VentProductos.getVentanaProductos();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		finalizar.addActionListener(new ActionListener() {// boton finalizar
			public void actionPerformed(ActionEvent e) {
				try
				{
					boolean completo = true;
					for (JTextField texto: data) {
						if(texto.getText().isBlank()) {
							completo = false;
						}
					}
					
					if (completo)
					{
						float p = Float.parseFloat(pre.getText());
						int cb = Integer.parseInt(codBarra.getText());
						int s = Integer.parseInt(stk.getText());
						int sm = Integer.parseInt(stkmin.getText());
						
						if(p > 0 && cb >= 0 && s > 0 && sm > 0) {
							boolean respuesta = AdministradorProducto.getAdministradorProducto().crearProducto(cb, descripcion.getText(), p, s, sm);
							
							if (respuesta)
							{
								JOptionPane.showMessageDialog(null,  "El producto se ha creado exitosamente","Producto Creado",JOptionPane.INFORMATION_MESSAGE);
								descripcion.setText("");
								codBarra.setText("");
								pre.setText("");
								stk.setText("");
								stkmin.setText("");
								
							}
							else
							{
								JOptionPane.showMessageDialog(null,  "El producto ya existe en el sistema","Producto Duplicado",JOptionPane.ERROR_MESSAGE);
							}							
						} else {
							JOptionPane.showMessageDialog(null,  "No ingrese valores no v\u00e1lidos","Formulario incompleto",JOptionPane.ERROR_MESSAGE);							
						}
						
							
					}
					else
					{
						JOptionPane.showMessageDialog(null,  "No puede dejar campos en blanco","Formulario incompleto",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(NumberFormatException ex)
				{	
					try {
						float cb = Float.parseFloat(codBarra.getText());
						float s = Float.parseFloat(stk.getText());
						float sm = Float.parseFloat(stkmin.getText());
						JOptionPane.showMessageDialog(null,  "No ingrese n\u00fameros no enteros en los campos: Codigo de Barra, Stock ni Stock M\u00ednimo","Error num\u00e9rico",JOptionPane.ERROR_MESSAGE);						
					} catch (NumberFormatException ex2) {
						JOptionPane.showMessageDialog(null,  "No ingrese letras o espacios en los campos num\u00e9rico","Error num\u00e9rico",JOptionPane.ERROR_MESSAGE);
						
					}
				} 
				
			}
		});
	}
}
