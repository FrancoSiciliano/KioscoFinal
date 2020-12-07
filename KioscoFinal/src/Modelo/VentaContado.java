package Modelo;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

import Vista.VentaContadoView;


public class VentaContado extends Ventas{

	public VentaContado() {
		super();
	}
	
	public VentaContadoView getView() {
		return new VentaContadoView(fecha,getTotal(),numero,abierta, getDatosInfoItems());
	}
	
	@Override
	public Vector<String> getDatosVenta() {
		Vector<String> datos = new Vector<String>();
		datos.add("" + this.numero);
		datos.add(this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		datos.add(this.total+"");
		return datos;
	}

	

}
