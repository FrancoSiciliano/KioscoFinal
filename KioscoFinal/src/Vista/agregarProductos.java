package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorProducto;
import Controlador.AdministradorVentas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;

public class agregarProductos extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField descripcion;
	private JTextField pre;
	private JTextField stk;
	private Vector<String> codigos;
	private JTable table;
	private int tipo;
	private int codigoVenta;
	private boolean hayProductos;

	public agregarProductos(int tipo, int codigoVenta) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setTitle("Agregando Productos");
		setResizable(false);
		setBounds(100, 100, 745, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		descripcion = new JTextField();
		descripcion.setEditable(false);
		descripcion.setBounds(104, 62, 203, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		
		
		pre = new JTextField();
		pre.setEditable(false);
		pre.setBounds(104, 93, 203, 20);
		contentPane.add(pre);
		pre.setColumns(10);
		
		
		stk = new JTextField();
		stk.setEditable(false);
		stk.setBounds(104, 124, 203, 20);
		contentPane.add(stk);
		stk.setColumns(10);
		
		JLabel cod = new JLabel("C\u00F3digo de Barra");
		cod.setBounds(10, 33, 110, 14);
		contentPane.add(cod);
		
		JLabel descrp = new JLabel("Descripci\u00F3n");
		descrp.setBounds(10, 65, 110, 14);
		contentPane.add(descrp);
		
		JLabel precio = new JLabel("Precio");
		precio.setBounds(10, 96, 110, 14);
		contentPane.add(precio);
		
		JLabel stock = new JLabel("Stock");
		stock.setBounds(10, 127, 110, 14);
		contentPane.add(stock);
		
		JButton back = new JButton("Finalizar");
		back.setBounds(607, 196, 110, 23);
		contentPane.add(back);
		
		codigos = AdministradorProducto.getAdministradorProducto().getCodigos();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(codigos));					
		comboBox.setBounds(104, 29, 203, 22);
		contentPane.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 29, 385, 144);
		contentPane.add(scrollPane);
		
		JLabel cantidad = new JLabel("Cantidad:");
		cantidad.setBounds(10, 159, 110, 14);
		contentPane.add(cantidad);
		
		JButton add = new JButton("Agregar");
		add.setBounds(218, 196, 89, 23);
		contentPane.add(add);
		
		comboBox.addActionListener(new ActionListener() { //dropdown
			public void actionPerformed(ActionEvent e) {
				
				try {
					int cd = comboBox.getSelectedIndex();
					int codigo = Integer.parseInt(codigos.elementAt(cd));
					
					descripcion.setText(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getDescripcion());
					stk.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStock()));
					pre.setText(Float.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getPrecio()));
					
				} catch (java.lang.NullPointerException np) {
					JOptionPane.showMessageDialog(null,  "El producto seleccionado ya no existe.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
				} catch (java.lang.ArrayIndexOutOfBoundsException ae) {
					JOptionPane.showMessageDialog(null,  "No hay ning\u00fan producto seleccionado.","Producto no eliminado",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Codigo");
		columnNames.add("Descripcion");
		columnNames.add("Precio");
		columnNames.add("Cantidad");
	
		Vector<Vector<String>> data;
		
		if (tipo == 1) {
			data = AdministradorVentas.getAdministradorVentas().buscarVentaContadoView(codigoVenta).getInfoItemsVenta();			
		} else if (tipo == 2) {
			data = AdministradorVentas.getAdministradorVentas().buscarVentaCreditoView(codigoVenta).getInfoItemsVenta();
		} else {
			data = AdministradorVentas.getAdministradorVentas().buscarVentaDebitoView(codigoVenta).getInfoItemsVenta();
		}
		
		
		table = new JTable(data, columnNames){
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		      
		table.setRowSelectionAllowed(true);
		table.setEnabled(true);
		scrollPane.setViewportView(table);
		
		JButton sacarProd = new JButton("Quitar Producto");
		sacarProd.setBounds(461, 196, 136, 23);
		sacarProd.setEnabled(false);
		contentPane.add(sacarProd);
		
		JSpinner cant = new JSpinner();
		cant.setBounds(245, 155, 62, 20);
		contentPane.add(cant);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sacarProd.setEnabled(true);
			}
		});
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sacarProd.setEnabled(false);
				table.clearSelection();
			}
		});
		
		agregarProductos vactual = this;
		sacarProd.addActionListener(new ActionListener() { //accion boton quitar producto
			public void actionPerformed(ActionEvent e) {
				
				AdministradorVentas.getAdministradorVentas().quitarProducto(codigoVenta, Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)), Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 3)));
				Vector<Vector<String>> data;
				
				if (tipo == 1) {
					data = AdministradorVentas.getAdministradorVentas().buscarVentaContadoView(codigoVenta).getInfoItemsVenta();			
				} else if (tipo == 2) {
					data = AdministradorVentas.getAdministradorVentas().buscarVentaCreditoView(codigoVenta).getInfoItemsVenta();
				} else {
					data = AdministradorVentas.getAdministradorVentas().buscarVentaDebitoView(codigoVenta).getInfoItemsVenta();
				}
				
				if(data.isEmpty()) {
					hayProductos = false;
					
				} else {
					hayProductos = true;
					
				}
				
				table = new JTable(data, columnNames){
				      public boolean isCellEditable(int row, int column){
				          return false;
				        }
				      };
				      
				table.setRowSelectionAllowed(true);
				table.setEnabled(true);
				scrollPane.setViewportView(table);
				
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						sacarProd.setEnabled(true);
					}
				});
				int cd = comboBox.getSelectedIndex();
				int codigo = Integer.parseInt(codigos.elementAt(cd));
				
				stk.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStock()));
				
			}
		});
		
		
		add.addActionListener(new ActionListener () { //accion boton agregarProducto
			
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					cant.commitEdit();
					int cantidadTotal = (Integer) cant.getValue();					
					int cd = comboBox.getSelectedIndex();
					int codigo = Integer.parseInt(codigos.elementAt(cd));
					
					if (cantidadTotal > 0) {
						if (AdministradorVentas.getAdministradorVentas().agregarProductoVenta(codigoVenta, codigo, cantidadTotal)) {
							Vector<Vector<String>> data;
							
							if (tipo == 1) {
								data = AdministradorVentas.getAdministradorVentas().buscarVentaContadoView(codigoVenta).getInfoItemsVenta();			
							} else if (tipo == 2) {
								data = AdministradorVentas.getAdministradorVentas().buscarVentaCreditoView(codigoVenta).getInfoItemsVenta();
							} else {
								data = AdministradorVentas.getAdministradorVentas().buscarVentaDebitoView(codigoVenta).getInfoItemsVenta();
							}
							
							hayProductos = true;
							
							table = new JTable(data, columnNames){
								public boolean isCellEditable(int row, int column){
									return false;
								}
							};
							
							table.setRowSelectionAllowed(true);
							table.setEnabled(true);
							scrollPane.setViewportView(table);
							
							table.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									sacarProd.setEnabled(true);
								}
							});
							
							stk.setText(Integer.toString(AdministradorProducto.getAdministradorProducto().buscarProductoView(codigo).getStock()));				
						
						} else {
							hayProductos = true;
						}
						
					} else {
						JOptionPane.showMessageDialog(null,  "No puede agregar cantidades no v\u00e1lidas","Producto no agregado",JOptionPane.ERROR_MESSAGE);
			
					}
					
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,  "No ingrese letras o espacios en el campo cantidad","Producto no agregado",JOptionPane.ERROR_MESSAGE);
				} catch ( java.text.ParseException exc ) {					
					JOptionPane.showMessageDialog(null,  "No ingrese caracteres o espacios en el campo de cantidad","Producto no agregado",JOptionPane.ERROR_MESSAGE);							
				} catch( java.lang.ArrayIndexOutOfBoundsException ab) {
					JOptionPane.showMessageDialog(null,  "No seleccion\u00f3 ning\u00fan producto","Producto no agregado",JOptionPane.ERROR_MESSAGE);												
				} catch( NullPointerException ex2) {					
					JOptionPane.showMessageDialog(null,  "No queda suficiente stock del producto elegido","Producto no agregado",JOptionPane.ERROR_MESSAGE);												
					
					Vector<Vector<String>> data;
					
					if (tipo == 1) {
						data = AdministradorVentas.getAdministradorVentas().buscarVentaContadoView(codigoVenta).getInfoItemsVenta();			
					} else if (tipo == 2) {
						data = AdministradorVentas.getAdministradorVentas().buscarVentaCreditoView(codigoVenta).getInfoItemsVenta();
					} else {
						data = AdministradorVentas.getAdministradorVentas().buscarVentaDebitoView(codigoVenta).getInfoItemsVenta();
					}
					
					if(data.isEmpty()) {
						hayProductos = false;
					} else {
						hayProductos = true;
					}
				}
				
			}
		});
		
		
		back.addActionListener(new ActionListener() { //boton finalizar
			public void actionPerformed(ActionEvent e) {
				VentanaIntermediaVentas ventana = new VentanaIntermediaVentas(tipo, codigoVenta, hayProductos);
				ventana.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		
	}
}
