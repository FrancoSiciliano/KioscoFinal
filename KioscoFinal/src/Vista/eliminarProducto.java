package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProducto;

public class eliminarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField descripcion;
	private JTextField pre;
	private JTextField stk;
	private JTextField stkmin;
	private Vector<String> codigos; //vector que contiene los codigos para el menu dropdown
	/**
	 * Create the frame.
	 */
	public eliminarProducto() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		setTitle("Eliminar Producto");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		descripcion = new JTextField();
		descripcion.setEditable(false);
		descripcion.setBounds(191, 62, 203, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		
		
		pre = new JTextField();
		pre.setEditable(false);
		pre.setBounds(191, 93, 203, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		
		stk = new JTextField();
		stk.setEditable(false);
		stk.setBounds(191, 124, 203, 20);
		contentPane.add(stk);
		stk.setColumns(10);
		
		
		stkmin = new JTextField();
		stkmin.setEditable(false);
		stkmin.setBounds(191, 155, 203, 20);
		contentPane.add(stkmin);
		stkmin.setColumns(10);
		
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
		
		JButton finalizar = new JButton("Eliminar Producto");
		finalizar.setBounds(132, 211, 149, 23);
		contentPane.add(finalizar);
		
		JButton back = new JButton("< Atras");
		back.setBounds(291, 211, 89, 23);
		contentPane.add(back);
		
		codigos = AdministradorProducto.getAdministradorProducto().getCodigos();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(codigos));					
		comboBox.setBounds(191, 29, 203, 22);
		contentPane.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() { //dropdown
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					int cd = comboBox.getSelectedIndex();
					int codigo = Integer.parseInt(codigos.elementAt(cd));
					
					descripcion.setText(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getDescripcion());
					stk.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStock()));
					stkmin.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStockMinimo()));
					pre.setText(Float.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getPrecio()));
					
				} catch (java.lang.NullPointerException np) {
					JOptionPane.showMessageDialog(null,  "El producto seleccionado ya no existe.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
				} catch (java.lang.ArrayIndexOutOfBoundsException ae) {
					JOptionPane.showMessageDialog(null,  "No hay ning\u00fan producto seleccionado.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		eliminarProducto vactual = this; // ventana actual
		
		back.addActionListener(new ActionListener() { // boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentProductos vprevia = VentProductos.getVentanaProductos();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		finalizar.addActionListener(new ActionListener() { //boton finalizar
			public void actionPerformed(ActionEvent e) {
				try {
					int cd = comboBox.getSelectedIndex();
					int codigo = Integer.parseInt(codigos.elementAt(cd));
					
					boolean respuesta = AdministradorProducto.getAdministradorProducto().eliminarProducto(codigo);
					descripcion.setText("");
					stk.setText("");
					stkmin.setText("");
					pre.setText("");
					
					if (respuesta) {
						JOptionPane.showMessageDialog(null,  "El producto se ha eliminado exitosamente","Producto Eliminado",JOptionPane.INFORMATION_MESSAGE);
						codigos = AdministradorProducto.getAdministradorProducto().getCodigos();
						comboBox.setModel(new DefaultComboBoxModel(codigos));
					}
					
					else {
						JOptionPane.showMessageDialog(null,  "El producto no se ha podido eliminar exitosamente","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
					}					
				} catch(java.lang.ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null,  "No hay ning\u00fan producto seleccionado.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
				}
			
			
			}
		});
		
	}

}
