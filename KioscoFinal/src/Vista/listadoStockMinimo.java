package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class listadoStockMinimo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public listadoStockMinimo(Vector<Vector<String>> data) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setTitle("Listado Stock Minimo");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, -1, 434, 239);
		contentPane.add(scrollPane);

		listadoStockMinimo vactual = this;
		
		JButton back = new JButton("< Atras");
		back.setBounds(313, 238, 121, 23);
		contentPane.add(back);
		
		back.addActionListener(new ActionListener() { // boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				VentProductos vprevia = VentProductos.getVentanaProductos();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Codigo");
		columnNames.add("Descripcion");
		columnNames.add("Precio");
		columnNames.add("Stock");
		columnNames.add("Stock Minimo");
		
		
		table = new JTable(data, columnNames){
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
	
	}	
}

