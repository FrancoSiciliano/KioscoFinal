package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorCaja;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class listadoVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton back;
	
	/**
	 * Create the frame.
	 */
	public listadoVentas(int tipo, Vector<Vector<String>> dataContado, Vector<Vector<String>> dataDebito, Vector<Vector<String>> dataCredito, cajaView caja) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //tipo 1 es ingresar por el boton info caja y 2 es por cerrar caja
		if (tipo == 1) {
			setTitle("Información de la caja del día " + caja.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - Saldo: $" + caja.getSaldo());			
		}else {
			setTitle("Resumen de la caja del día " + caja.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - Saldo: $" + caja.getSaldo());						
		}
		setBounds(100, 100, 559, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 36, 543, 142);
		contentPane.add(scrollPane);

		listadoVentas vactual = this;
		
		if (tipo == 1) {
			back = new JButton("< Atras");			
		} else {
			back = new JButton("Finalizar");
		}
		
		back.setBounds(405, 556, 128, 23);
		contentPane.add(back);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				VentCajas vprevia = new VentCajas();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		Vector<String> columNames = new Vector<String>();
		columNames.add("N\u00famero");
		columNames.add("Fecha");
		columNames.add("Total");
		
		table = new JTable(dataContado, columNames){ //tabla ventas contado
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 214, 543, 142);
		contentPane.add(scrollPane_1);
		
		Vector<String> columNames1 = new Vector<String>();
		columNames1.add("N\u00famero");
		columNames1.add("Fecha");
		columNames1.add("Cod. Autorizaci\u00f3n");
		columNames1.add("Total");
		
		table_1 = new JTable(dataCredito, columNames1){ //tabla ventas credito
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_1.setBounds(0, 401, 543, 142);
		contentPane.add(scrollPane_1_1);
		
		Vector<String> columNames2 = new Vector<String>();
		columNames2.add("N\u00famero");
		columNames2.add("Fecha");
		columNames2.add("Cod. Autorizaci\u00f3n");
		columNames2.add("Banco");
		columNames2.add("Total");
		table_2 = new JTable(dataDebito, columNames2){ //tabla ventas debito
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table_2.setEnabled(false);
		scrollPane_1_1.setViewportView(table_2);
		
		
		int cantVentascont = table.getRowCount();
		int cantVentasCred = table_1.getRowCount();
		int cantVentasDeb = table_2.getRowCount();
		
		JLabel lblNewLabel = new JLabel("Ventas Contado - " + ((cantVentascont == 0) ? cantVentascont:(cantVentascont-1)) + " Ventas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 313, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVentasCredito = new JLabel("Ventas Cr\u00E9dito - " + ((cantVentasCred == 0) ? cantVentasCred:(cantVentasCred-1)) + " Ventas");
		lblVentasCredito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVentasCredito.setBounds(10, 189, 313, 14);
		contentPane.add(lblVentasCredito);
		
		JLabel lblVentasDebito = new JLabel("Ventas D\u00E9bito - " + ((cantVentasDeb == 0) ? cantVentasDeb:(cantVentasDeb-1)) + " Ventas");
		lblVentasDebito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVentasDebito.setBounds(10, 367, 313, 23);
		contentPane.add(lblVentasDebito);
	}
}
