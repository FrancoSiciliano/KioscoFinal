package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProducto;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;

public class modificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField descripcion;
	private JTextField pre;
	private JTextField stk;
	private JTextField stkmin;


	public modificarProducto() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		setResizable(false);
		Vector<JTextField> data = new Vector<JTextField>();
		
		setTitle("Modificar Producto");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		descripcion = new JTextField();
		descripcion.setBounds(191, 62, 203, 20);
		descripcion.setEnabled(false);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		data.add(descripcion);
		
		pre = new JTextField();
		pre.setBounds(191, 93, 203, 20);
		pre.setEnabled(false);
		contentPane.add(pre);
		pre.setColumns(10);
		data.add(pre);
		
		stk = new JTextField();
		stk.setBounds(191, 124, 203, 20);
		stk.setEnabled(false);
		contentPane.add(stk);
		stk.setColumns(10);
		data.add(stk);
		
		stkmin = new JTextField();
		stkmin.setBounds(191, 155, 203, 20);
		stkmin.setEnabled(false);
		contentPane.add(stkmin);
		stkmin.setColumns(10);
		data.add(stk);
		
		JLabel cod = new JLabel("C\u00F3digo de Barra");
		cod.setBounds(55, 33, 110, 14);
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
		
		JButton finalizar = new JButton("Modificar Producto");
		finalizar.setBounds(132, 211, 149, 23);
		contentPane.add(finalizar);
		
		JButton back = new JButton("< Atras");
		back.setBounds(291, 211, 89, 23);
		contentPane.add(back);
		
		Vector<String> codigos = AdministradorProducto.getAdministradorProducto().getCodigos();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(codigos));
		comboBox.setBounds(191, 29, 203, 22);
		contentPane.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() { //dropdown
			public void actionPerformed(ActionEvent e) {
				
				try {
					int cd = comboBox.getSelectedIndex();
					int codigo = Integer.parseInt(codigos.elementAt(cd));
					
					descripcion.setEnabled(true);
					stk.setEnabled(true);
					stkmin.setEnabled(true);
					pre.setEnabled(true);
					
					descripcion.setText(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getDescripcion());
					stk.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStock()));
					stkmin.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStockMinimo()));
					pre.setText(Float.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getPrecio()));
					
				} catch (java.lang.ArrayIndexOutOfBoundsException ae) {
					JOptionPane.showMessageDialog(null,  "No hay productos que modificar","Producto no modificado",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		modificarProducto vactual = this; //ventana actual
		
		back.addActionListener(new ActionListener() { //boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentProductos vprevia = VentProductos.getVentanaProductos();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		finalizar.addActionListener(new ActionListener() { // boton finalizar
			public void actionPerformed(ActionEvent evt) {
				try {
					
					boolean completo = true;
					for (JTextField texto: data) {
						if(texto.getText().isBlank()) {
							completo = false;
						}
					}
					
					if (completo)
					{
						float p = Float.parseFloat(pre.getText());
						int cd = comboBox.getSelectedIndex();
						int codigo = Integer.parseInt(codigos.elementAt(cd));
						int s = Integer.parseInt(stk.getText());
						int sm = Integer.parseInt(stkmin.getText());
						
						boolean respuesta = AdministradorProducto.getAdministradorProducto().modificarProducto(codigo, descripcion.getText(), p, s, sm);
						
						if (respuesta) {
							JOptionPane.showMessageDialog(null,  "El producto se ha modificado exitosamente","Producto Modficado",JOptionPane.INFORMATION_MESSAGE);
							descripcion.setText("");
							pre.setText("");
							stk.setText("");
							stkmin.setText("");
							
							
						}
						else {
							JOptionPane.showMessageDialog(null,  "El producto no puede ser modificado en este momento","Producto No disponible",JOptionPane.ERROR_MESSAGE);
						}
							
					}
					else {
						JOptionPane.showMessageDialog(null,  "No puede dejar campos en blanco","Formulario incompleto",JOptionPane.ERROR_MESSAGE);
					}
					
				} catch(NumberFormatException ex) {
					
					try {
						float s = Float.parseFloat(stk.getText());
						float sm = Float.parseFloat(stkmin.getText());
						JOptionPane.showMessageDialog(null,  "No ingrese n\u00fameros no enteros en los campos: Stock ni Stock M\u00ednimo","Error num\u00e9rico",JOptionPane.ERROR_MESSAGE);						
					
					} catch (NumberFormatException ex2) {
						JOptionPane.showMessageDialog(null,  "No ingrese letras o espacios en los campos num\u00e9rico","Error num\u00e9rico",JOptionPane.ERROR_MESSAGE);
						
					}
				} catch(java.lang.ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null,  "No hay ning\u00fan producto seleccionado.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
			}
			}
		});
	}
}
