package Vista;

import java.time.LocalDate;
import java.util.Vector;

public class VentaCreditoView extends VentaView {
	
	private int codigoSeguridad;
	private String codigoAutorizacion;
	
	public VentaCreditoView(LocalDate fecha, float total, int numero, boolean abierta, int codigoSeguridad, String codigoAutorizacion, Vector<Vector<String>> infoItems) {
		super(fecha, total, numero, abierta, infoItems);
		this.codigoSeguridad = codigoSeguridad;
		this.codigoAutorizacion = codigoAutorizacion;
	}
	
	@Override
	public Vector<Vector<String>> getInfoItemsVenta() {
		return infoItemsVentas;
	}
}
