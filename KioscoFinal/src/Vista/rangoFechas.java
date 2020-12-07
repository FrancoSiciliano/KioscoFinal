package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class rangoFechas extends JFrame {

	private JPanel contentPane;
	private JTextField dd1;
	private JTextField mm1;
	private JTextField yy1;
	private JTextField dd2;
	private JTextField mm2;
	private JTextField yy2;
	
	public rangoFechas() {
		setResizable(false);
		setTitle("Elija el rango de fechas para el reporte");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 404, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dd1 = new JTextField(""+LocalDate.now().minusDays(1).getDayOfMonth());
		dd1.setBounds(128, 65, 33, 20);
		contentPane.add(dd1);
		dd1.setColumns(10);
		
		mm1 = new JTextField(""+LocalDate.now().minusDays(1).getMonthValue());
		mm1.setBounds(171, 65, 45, 20);
		contentPane.add(mm1);
		mm1.setColumns(10);
		
		yy1 = new JTextField(""+LocalDate.now().minusDays(1).getYear());
		yy1.setBounds(226, 65, 86, 20);
		contentPane.add(yy1);
		yy1.setColumns(10);
		
		dd2 = new JTextField(""+LocalDate.now().getDayOfMonth());
		dd2.setBounds(128, 96, 33, 20);
		contentPane.add(dd2);
		dd2.setColumns(10);
		
		mm2 = new JTextField(""+LocalDate.now().getMonthValue());
		mm2.setBounds(171, 96, 45, 20);
		contentPane.add(mm2);
		mm2.setColumns(10);
		
		yy2 = new JTextField(""+LocalDate.now().getYear());
		yy2.setBounds(226, 96, 86, 20);
		contentPane.add(yy2);
		yy2.setColumns(10);
		
		JLabel fI = new JLabel("Fecha Inicio:");
		fI.setBounds(53, 68, 108, 14);
		contentPane.add(fI);
		
		JLabel ff = new JLabel("Fecha Fin:");
		ff.setBounds(53, 99, 65, 14);
		contentPane.add(ff);
		
		JLabel d = new JLabel("D\u00EDa");
		d.setBounds(128, 44, 46, 14);
		contentPane.add(d);
		
		JLabel m = new JLabel("Mes");
		m.setBounds(171, 44, 46, 14);
		contentPane.add(m);
		
		rangoFechas vactual = this;
		
		JButton total = new JButton("Mostrar Totalizado");
		total.setBounds(10, 139, 176, 43);
		contentPane.add(total);
		
		total.addActionListener(new ActionListener() { // boton mostrar totalizado
			public void actionPerformed(ActionEvent e) {
				LocalDate fechaInicio, fechaFin;
				
				try {
					fechaInicio = LocalDate.of(Integer.parseInt(yy1.getText()), Integer.parseInt(mm1.getText()), Integer.parseInt(dd1.getText()));
					fechaFin = LocalDate.of(Integer.parseInt(yy2.getText()), Integer.parseInt(mm2.getText()), Integer.parseInt(dd2.getText()));
					
					if(fechaFin.isBefore(fechaInicio)) {
						JOptionPane.showMessageDialog(null, "La fecha de de fin no puede ser anterior a la de inicio", "No se puede seguir", JOptionPane.ERROR_MESSAGE);						
					} else {
						reporteMensualVentasTotalizadas ventana = new reporteMensualVentasTotalizadas(fechaInicio, fechaFin);
						vactual.setVisible(false);
						ventana.setVisible(true);						
					}
					
				} catch (java.time.DateTimeException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese fecha/s válidas", "No se puede seguir", JOptionPane.ERROR_MESSAGE);
					
				} catch (NumberFormatException ex) {					
					JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco, ni utilizar numeros no enteros", "No se puede seguir", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton discriminado = new JButton("Mostrar Discriminado");
		discriminado.setBounds(196, 139, 176, 43);
		contentPane.add(discriminado);
		discriminado.addActionListener(new ActionListener() { // boton mostrar discriminado
			public void actionPerformed(ActionEvent e) {
				LocalDate fechaInicio, fechaFin;
				
				try {
					fechaInicio = LocalDate.of(Integer.parseInt(yy1.getText()), Integer.parseInt(mm1.getText()), Integer.parseInt(dd1.getText()));
					fechaFin = LocalDate.of(Integer.parseInt(yy2.getText()), Integer.parseInt(mm2.getText()), Integer.parseInt(dd2.getText()));
					
					if(fechaFin.isBefore(fechaInicio)) {
						JOptionPane.showMessageDialog(null, "La fecha de de fin no puede ser anterior a la de inicio", "No se puede seguir", JOptionPane.ERROR_MESSAGE);						
					} else {
						reporteMensualVentasDiscriminado ventana = new reporteMensualVentasDiscriminado(fechaInicio,fechaFin);
						vactual.setVisible(false);
						ventana.setVisible(true);
					}
					
				} catch (java.time.DateTimeException ex) {
					JOptionPane.showMessageDialog(null,  "Ingrese fecha/s válidas","No se puede seguir",JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException ex) {					
					JOptionPane.showMessageDialog(null, "No puede dejar campos en blanco, ni utilizar numeros no enteros", "No se puede seguir", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JLabel y = new JLabel("A\u00F1o");
		y.setBounds(226, 44, 46, 14);
		contentPane.add(y);
		
		JButton back = new JButton("< Atr\u00E1s");
		back.setBounds(289, 11, 89, 23);
		contentPane.add(back);
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentVentas ventana = new VentVentas();
				ventana.setVisible(true);
				vactual.setVisible(false);
			}
		});
		
		
	}
	
}
