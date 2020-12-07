package Vista;

import java.time.LocalDate;
import java.util.Vector;


public class VentaDebitoView extends VentaView {
	
	private String banco;
	private String codigoAutorizacion;
	
	public VentaDebitoView(LocalDate fecha, float total, int numero, boolean abierta,String codigoAutorizacion, String banco, Vector<Vector<String>> infoItems) {
		super(fecha, total, numero, abierta, infoItems);
		this.banco = banco;
		this.codigoAutorizacion = codigoAutorizacion;
	}
	
	@Override
	public Vector<Vector<String>> getInfoItemsVenta() {
		return infoItemsVentas;
	}

}
