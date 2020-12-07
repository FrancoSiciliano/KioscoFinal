package Modelo;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

import Vista.VentaCreditoView;

public class VentaTarjetaCredito extends Ventas {
	
	private int codigoSeguridad;
	private String codigoAutorizacion;

	public VentaTarjetaCredito( String codigoAutorizacion, int codigoSeguridad) {
		super();
		this.codigoSeguridad = codigoSeguridad;
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public String getCodAut(){
		return this.codigoAutorizacion;
	}
	
	public VentaCreditoView getView() {
		return new VentaCreditoView(fecha,getTotal(),numero,abierta,codigoSeguridad,codigoAutorizacion, getDatosInfoItems());
		
	}
	
	public void setCodigoSeguridad(int codigo) {
		codigoSeguridad = codigo;
	}
	
	public void setCodigoAutorizacion(String codigo) {
		codigoAutorizacion = codigo;
	}

	@Override
	public Vector<String> getDatosVenta() {
		Vector<String> datos = new Vector<String>();
		datos.add(""+numero);
		datos.add(this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		datos.add(codigoAutorizacion);
		datos.add(total+"");
		return datos;
	}

}
