package Modelo;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

import Vista.VentaDebitoView;

public class VentaTarjetaDebito extends Ventas {
	
	private String banco;
	private String codigoAutorizacion;

	public VentaTarjetaDebito(String codigoAutorizacion, String banco) {
		super();
		this.banco = banco;
		this.codigoAutorizacion = codigoAutorizacion;
	}
	
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public void setCodigoAutorizacion(String codigo) {
		codigoAutorizacion = codigo;
	}
	
	public VentaDebitoView getView() {
		return new VentaDebitoView(fecha,getTotal(),numero,abierta,codigoAutorizacion,banco, getDatosInfoItems());
	}

	@Override
	public Vector<String> getDatosVenta() {
		Vector<String> datos = new Vector<String>();
		datos.add(""+numero);
		datos.add(this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		datos.add(banco);
		datos.add(codigoAutorizacion);
		datos.add(total+"");
		return datos;
	}

}
