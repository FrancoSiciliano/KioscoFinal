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
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Controlador.AdministradorVentas;

public class reporteMensualVentasTotalizadas extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public reporteMensualVentasTotalizadas(LocalDate fechaInicio, LocalDate fechaFin) {
		setResizable(false);
		setTitle("Reporte de las ventas desde " + fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " a " + fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 563, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 543, 260);
		contentPane.add(scrollPane);

		reporteMensualVentasTotalizadas vactual = this;
		
		JButton back = new JButton("< Atras");
		back.setBounds(422, 263, 121, 23);
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
		columNames.add("Fecha");
		columNames.add("Cantidad de Ventas");
		columNames.add("Total");
		
		table = new JTable(AdministradorVentas.getAdministradorVentas().generarListadoVentasMensualesTotalizado(fechaInicio, fechaFin), columNames){
		      public boolean isCellEditable(int row, int column){
		          return false;
		        }
		      };
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
	}

}
