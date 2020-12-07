package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;

public class reporteMensualVentasDiscriminado extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	public reporteMensualVentasDiscriminado(LocalDate fechaInicio, LocalDate fechaFin) {
		setResizable(false);
		
		setTitle("Reporte de las ventas desde " + fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " a " + fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 559, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 543, 150);
		contentPane.add(scrollPane);

		reporteMensualVentasDiscriminado vactual = this;
		
		JButton back = new JButton("< Atras");
		back.setBounds(412, 573, 121, 23);
		contentPane.add(back);
		
		back.addActionListener(new ActionListener() { // boton atras
			public void actionPerformed(ActionEvent evt) 
			{
				rangoFechas vprevia = new rangoFechas();
				vprevia.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		Vector<String> columNames = new Vector<String>();
		columNames.add("N\u00famero");
		columNames.add("Fecha");
		columNames.add("Total");
		
		table = new JTable(AdministradorVentas.getAdministradorVentas().generarListadoVentasMensualesContado(fechaInicio, fechaFin), columNames){// tabla ventas contado
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 214, 543, 150);
		contentPane.add(scrollPane_1);
		
		Vector<String> columNames1 = new Vector<String>();
		columNames1.add("N\u00famero");
		columNames1.add("Fecha");
		columNames1.add("Cod. Autorizaci\u00f3n");
		columNames1.add("Total");
		
		table_1 = new JTable(AdministradorVentas.getAdministradorVentas().generarListadoVentasMensualesCredito(fechaInicio, fechaFin), columNames1){ // tabla ventas credito
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table_1.setEnabled(false);		
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_1.setBounds(0, 395, 543, 150);
		contentPane.add(scrollPane_1_1);
		
		Vector<String> columNames2 = new Vector<String>();
		columNames2.add("N\u00famero");
		columNames2.add("Fecha");
		columNames2.add("Cod. Autorizaci\u00f3n");
		columNames2.add("Banco");
		columNames2.add("Total");
		
		table_2 = new JTable(AdministradorVentas.getAdministradorVentas().generarListadoVentasMensualesDebito(fechaInicio, fechaFin), columNames2){// tablas ventas debito
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table_2.setEnabled(false);
		scrollPane_1_1.setViewportView(table_2);
		
		JLabel lblNewLabel = new JLabel("Ventas Contado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 128, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVentasCreditoPai = new JLabel("Ventas Credito");
		lblVentasCreditoPai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVentasCreditoPai.setBounds(10, 195, 128, 14);
		contentPane.add(lblVentasCreditoPai);
		
		JLabel lblVentasDebitoPai = new JLabel("Ventas Debito");
		lblVentasDebitoPai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVentasDebitoPai.setBounds(10, 372, 164, 23);
		contentPane.add(lblVentasDebitoPai);
	}
}


