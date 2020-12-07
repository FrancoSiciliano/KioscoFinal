package Vista;

import java.time.LocalDate;
import java.util.Vector;


public class VentaContadoView extends VentaView {

	public VentaContadoView(LocalDate fecha, float total, int numero, boolean abierta, Vector<Vector<String>> infoItems) {
		super(fecha, total, numero, abierta, infoItems);
	}

	@Override
	public Vector<Vector<String>> getInfoItemsVenta() {
		return infoItemsVentas;
	}

}
